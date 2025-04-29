package com.springboot.mzuplusspringjpa.common.swaggger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("휴대폰 판매 관리 API")
                        .description("휴대폰 판매를 관리서버 API입니다")
                        .version("v.0.0.1"));
    }
}
