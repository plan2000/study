package com.samsunglife.sample.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.samsunglife.sample.core.auth.interceptors.AuthInterceptor;

@Configuration
@MapperScan(value={"com.**.mapper"})
public class WebConfig extends WebMvcConfigurerAdapter{

    
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor((HandlerInterceptor)new AuthInterceptor());
	}
}