package org.SETrain.CareerNavigator.Config;

import org.SETrain.CareerNavigator.Interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
     @Autowired
     private LoginInterceptor loginInterceptor;

     @Override
     public void addInterceptors(InterceptorRegistry registry) {
          registry.addInterceptor(loginInterceptor)
                    .excludePathPatterns(
                              "/account/login",
                              "/account/register",
                              "/swagger-ui/**",
                              "/v3/api-docs/**",
                              "/swagger-resources/**",
                              "/webjars/**",
                              "/*.ico",
                              "/*.html",
                              "/*.css",
                              "/*.js",
                              "/static/**");
     }
}
