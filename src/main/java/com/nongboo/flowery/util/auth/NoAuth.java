package com.nongboo.flowery.util.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //어노테이션 레벨 결정
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface NoAuth {
}
