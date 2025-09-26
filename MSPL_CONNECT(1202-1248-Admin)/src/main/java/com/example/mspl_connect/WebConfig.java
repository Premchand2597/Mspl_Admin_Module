package com.example.mspl_connect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {   
	
	    @Autowired 
	    private NoCacheInterceptor noCacheInterceptor;

	    @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(noCacheInterceptor);
	    }
	    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/insertResponse").setViewName("forward:/");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
    
    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/img/**")
                .addResourceLocations("file:/opt/myapp/images/")
                .setCachePeriod(0);  // Disable cache to always serve the latest version
    }*/
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	
        // Serve images from the specified directory    C:\Users\Ravi\Desktop\EmpDocs
    	/*
		 * registry.addResourceHandler("/Desktop/EmpDocs/**")
		 * .addResourceLocations("file:///C:/Users/COMP/Desktop/EmpDocs/");file:///C://Users//Ravi//Desktop//EmpDocs/
	    */
    	
    	registry.addResourceHandler("/Desktop/EmpDocs/**")
        .addResourceLocations("file:///D:/Desktop/EmpDocs/");
    	
    }
}

