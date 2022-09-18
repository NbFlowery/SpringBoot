package com.nongboo.flowery.controller;


import com.nongboo.flowery.login.Constant;
import com.nongboo.flowery.login.Service.OAuthService;
import com.nongboo.flowery.login.SocialOAuthRes;
import com.nongboo.flowery.util.Header;
import com.nongboo.flowery.util.auth.NoAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    public OAuthService oAuthService;


    @NoAuth
    @GetMapping("/auth/{socialLoginType}")
    public void socialLoginRedirect(@PathVariable(name = "socialLoginType") String socialLoginPath) throws IOException{
        Constant.SocialLoginType socialLoginType = Constant.SocialLoginType.valueOf(socialLoginPath.toUpperCase(Locale.ROOT));
        oAuthService.request(socialLoginType);
    }

    @NoAuth
    @ResponseBody
    @GetMapping("/auth/{socialLoginType}/callback")
    public Header<SocialOAuthRes> callBack(@PathVariable(name = "socialLoginType") String socialLoginPath,
                                           @RequestParam(name = "code") String code){
        try{
            System.out.println(">> 소셜 로그인 API 서버로부터 받은 code :"+ code);
            Constant.SocialLoginType socialLoginType= Constant.SocialLoginType.valueOf(socialLoginPath.toUpperCase());
            SocialOAuthRes socialOAuthRes=oAuthService.oAuthLogin(socialLoginType,code);
            return Header.SUCCESS(socialOAuthRes);
        }catch (Exception e){
            return Header.FAIL(e);
        }

    }

}
