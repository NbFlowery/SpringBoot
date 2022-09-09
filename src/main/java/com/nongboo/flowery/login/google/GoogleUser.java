package com.nongboo.flowery.login.google;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GoogleUser { // 구글로 액세스 토큰을 보내 받아올 구글에 등록된 사용자 정보
    public String id;
    public String email;
    public Boolean verifiedEmail;
    public String name;
    public String givenName;
    public String familyName;
    public String picture;
    public String locale;
}
