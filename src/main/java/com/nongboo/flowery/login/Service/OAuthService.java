package com.nongboo.flowery.login.Service;

import com.nongboo.flowery.entity.User;
import com.nongboo.flowery.login.Constant;
import com.nongboo.flowery.login.SocialOAuthRes;
import com.nongboo.flowery.login.google.GoogleOAuthToken;
import com.nongboo.flowery.login.google.GoogleOauth;
import com.nongboo.flowery.login.google.GoogleUser;
import com.nongboo.flowery.repository.UserRepository;
import com.nongboo.flowery.util.auth.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class OAuthService { //로그인 방식에 따라 해당 클래스 호출

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    private final GoogleOauth googleOauth;
    private final HttpServletResponse response;

    public void request(Constant.SocialLoginType socialLoginType) throws IOException{
        String redirectURL;
        switch (socialLoginType){
            case GOOGLE:{
                redirectURL = googleOauth.getOauthRedirectURL();
            }break;
            default:{
                throw new IllegalArgumentException("알 수 없는 소셜 로그인 형식입니다.");
            }

        }
        response.sendRedirect(redirectURL);

    }

    public SocialOAuthRes oAuthLogin(Constant.SocialLoginType socialLoginType, String code) throws IOException {

        switch (socialLoginType) {
            case GOOGLE: {
                //구글로 일회성 코드를 보내 액세스 토큰이 담긴 응답객체를 받아옴
                ResponseEntity<String> accessTokenResponse = googleOauth.requestAccessToken(code);
                //응답 객체가 JSON형식으로 되어 있으므로, 이를 deserialization해서 자바 객체에 담을 것이다.
                GoogleOAuthToken oAuthToken = googleOauth.getAccessToken(accessTokenResponse);

                //액세스 토큰을 다시 구글로 보내 구글에 저장된 사용자 정보가 담긴 응답 객체를 받아온다.
                ResponseEntity<String> userInfoResponse = googleOauth.requestUserInfo(oAuthToken);
                //다시 JSON 형식의 응답 객체를 자바 객체로 역직렬화한다.
                GoogleUser googleUser = googleOauth.getUserInfo(userInfoResponse);

                String userEmail = googleUser.getEmail();
                //db와 대조하여 해당 user가 존재하는 지 확인한다.
                Long userId = userRepository.findUserIdByUserEmail(userEmail);
                System.out.println(userId);
                if (userId == null) {
                    //db에 user가 존재하지 않으면 DB에 user 정보 저장 및 앞으로 회원 인가 처리를 위한 jwtToken을 발급한다.
                    User user = userRepository.save(User.createUser("", userEmail));
                    System.out.println(user.getId().intValue());
                    String jwtToken = jwtService.createJWT(user.getId().intValue());
                    return new SocialOAuthRes(jwtToken, userEmail, oAuthToken.getAccess_token(), oAuthToken.getToken_type());
                }else{
                    String jwtToken = jwtService.createJWT(userId.intValue());
                    return new SocialOAuthRes(jwtToken, userEmail, oAuthToken.getAccess_token(), oAuthToken.getToken_type());
                }
            }
            default: {
                throw new IllegalArgumentException("알 수 없는 소셜 로그인 형식입니다.");
            }
        }
    }
}
