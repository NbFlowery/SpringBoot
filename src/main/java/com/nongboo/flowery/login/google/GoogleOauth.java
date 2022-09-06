package com.nongboo.flowery.login.google;

import com.nongboo.flowery.login.SocialOauth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GoogleOauth implements SocialOauth {
    @Override
    public String getOauthRedirectURL() {
        return "";
    }
}
