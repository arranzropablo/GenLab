package com.genlab.serverapplication.config;

import com.genlab.serverapplication.interceptor.CurrentSectionInterceptor;
import com.genlab.serverapplication.interceptor.MenuInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class GenLabConfig extends WebMvcConfigurerAdapter{
	
	@Bean
	public MenuInterceptor menuInterceptor() {
		return new MenuInterceptor();
	}
	
	@Bean
	public CurrentSectionInterceptor currentSectionInterceptor() {
		return new CurrentSectionInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(menuInterceptor());
		registry.addInterceptor(currentSectionInterceptor());
	}
	
}
