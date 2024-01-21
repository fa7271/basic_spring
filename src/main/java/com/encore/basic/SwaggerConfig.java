package com.encore.basic;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
//    Docket : swagger구성의 핵심 기능 클래스
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("swagger test") // docker bwan 이름 여러개일 경우 충돌방지
//                어떤 컨트롤러 또는 , 어떤 api swagger문서에 포함시킬지 선택
                .select()
//                모든 RequestHandler 문서화 대상으로 선택한다.
                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any()) // 모든 path컨트롤러의 swagger적용
                .paths(PathSelectors.ant("/rest/**/")) // url패턴 정의 *이 1개면 직계, 2개면 자손단위
                .build()
                .useDefaultResponseMessages(false); // swagger 기본 응답 메세지 사용여부 x
    }
//    swagger의 authorize 자물쇠 버튼 활성화를 위해서는 jwt, session 등의 별도의 설정 필요
}
