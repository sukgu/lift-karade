package com.liftkarade.app.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class WebApplicationContext {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		 ctx.register(SpringServletConfig.class);
		 ctx.refresh();
	}

	
}
