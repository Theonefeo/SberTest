package demo.SberTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;


@SpringBootApplication
public class DemoApplication {
	
	 private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
	            "classpath:/META-INF/resources/", "classpath:/resources/",
	            "classpath:/static/", "classpath:/public/"
	    };
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	    
	 @Bean
		public WebMvcConfigurer staticPageConfigurer() {
			return new WebMvcConfigurer() {
				@Override
				public void addResourceHandlers(ResourceHandlerRegistry registry) {
			        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
			    }				
			};
		}
	 
	 @Configuration
	 public class SwaggerConfiguration {

	     @Bean
	     public OpenAPI openAPI() {
	         return new OpenAPI()
	                 .info(new Info().title("Logs system API").version("1.0").description("My logs RESTful system")
	                     .contact(new Contact().name("Feodor Karpov").url("https://career.habr.com/feodorkarpov12").email("fedorkarpov1312@gmail.com")));
	     }

	 }
}
