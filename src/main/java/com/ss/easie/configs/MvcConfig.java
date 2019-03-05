package com.ss.easie.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	/*
	 * public void addViewControllers(ViewControllerRegistry registry) {
	 * 
	 * 
	 * registry.addViewController("/home").setViewName("home");
	 * registry.addViewController("/").setViewName("redirect:/login");
	 * registry.addViewController("/hello").setViewName("hello");
	 * registry.addViewController("/login").setViewName("login");
	 * 
	 * 
	 * }
	 */
	/*
	 * @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
	 * registry.addResourceHandler(
	 * 
	 * "/assets/*") .addResourceLocations(
	 * 
	 * "classpath:/static/assets/"); }
	 */
	
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
}
