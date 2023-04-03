package com.shopme.admin;


import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//public class MvcConfig{
	public class MvcConfig implements WebMvcConfigurer{

	
	//Exposes absolute path to photos so they are avaliable
	//for the webclient browser to access
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		
	//User photos
	exposeDirectory("user-photos", registry);
	
	//Item images
	exposeDirectory("../category-images", registry);
	
	//Brand logos
	exposeDirectory("../brand-logos", registry);

	}
	
	private void exposeDirectory(String pathPattern, ResourceHandlerRegistry registry) {
		Path path = Paths.get(pathPattern);
		String absolutePath = path.toFile().getAbsolutePath();
		
		String logicalPath = pathPattern.replace("..","") + "/**";
		
		registry.addResourceHandler(logicalPath)
		.addResourceLocations("file:/" + absolutePath + "/");
	}
	//DEPRECATED

//@Configuration
//public class MvcConfig extends WebMvcConfigurer {
	
	//Exposes absolute path to user photos so they are visible in browser
	//@Override
	//public void addResourceHandlers(ResourceHandlerRegistry registry) {
	//	String dirName = "user-photos";
	//	Path userPhotosDir = Paths.get(dirName);
		
	//	String userPhotosPath = userPhotosDir.toFile().getAbsolutePath();
		
	//	registry.addResourceHandler("/" + dirName + "/**")
	//	.addResourceLocations("file:/" + userPhotosPath + "/");
//	}
	
	
	

}
