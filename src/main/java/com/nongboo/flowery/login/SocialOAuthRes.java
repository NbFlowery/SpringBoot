package com.nongboo.flowery.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SocialOAuthRes { // 클라이언트로 보넬 jwtToken, accessToken 등이 담긴 객체
    private String jwtToken;
    private String user_id;
    private String accessToken;
    private String tokenType;
}
