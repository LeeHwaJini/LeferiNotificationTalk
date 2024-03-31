package com.leferi.notification.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.ContentVersionStrategy;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
    
    @Bean
	MappingJackson2JsonView jsonView() {
		return new MappingJackson2JsonView();
	}
    
    @Override 
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	  VersionResourceResolver versionResourceResolver = new VersionResourceResolver();
	  
	  versionResourceResolver.addVersionStrategy(new ContentVersionStrategy(),"/**"); 
	  
	  registry.addResourceHandler("/resources/**")
			  .addResourceLocations("classpath:/static/")
			  .setCachePeriod(3600)
			  .resourceChain(false)
			  .addResolver(versionResourceResolver); 
	 }
	  
	@Bean
	ResourceUrlEncodingFilter resourceUrlEncodingFilter(){ 
		return new ResourceUrlEncodingFilter(); 
	
	}
    
}
