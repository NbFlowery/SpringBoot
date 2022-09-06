package com.nongboo.flowery.login;

import com.nongboo.flowery.login.google.GoogleOauth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class OAuthService { //로그인 방식에 따라 해당 클래스 호출

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

    }

}
