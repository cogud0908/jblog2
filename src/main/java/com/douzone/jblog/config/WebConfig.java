package com.douzone.jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.douzone.config.web.FileUploadConfig;
import com.douzone.config.web.MVCConfig;
import com.douzone.config.web.MessageConfig;
import com.douzone.config.web.SecurityConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.douzone.jblog.controller", "com.douzone.jblog.exception", "com.douzone.jblog.aspect"})
@Import(value= {MVCConfig.class, SecurityConfig.class, MessageConfig.class, FileUploadConfig.class})
public class WebConfig {
	
}
