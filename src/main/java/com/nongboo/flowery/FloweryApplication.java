package com.nongboo.flowery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // CreatedDate, LastModifiedDate 사용하기 위한 어노테이션
@SpringBootApplication
public class FloweryApplication {

    public static void main(String[] args) {
        SpringApplication.run(FloweryApplication.class, args);
    }

}
