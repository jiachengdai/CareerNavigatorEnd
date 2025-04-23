package org.SETrain.CareerNavigator.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
        @Bean
        public OpenAPI openAPI() {
                return new OpenAPI()
                                .info(new Info()
                                                .title("职业导航系统API文档")
                                                .description("包含用户管理、账户管理、用户画像等功能")
                                                .version("3.0.0")
                                                .contact(new Contact()
                                                                .name("CareerNavigator Team")
                                                                .email("support@careernavigator.com"))
                                                .license(new License()
                                                                .name("Apache 2.0")
                                                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
        }
}