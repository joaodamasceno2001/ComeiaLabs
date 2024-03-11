package com.comeia.comeialabs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "ComeiaLabs OpenAPI", version = "1", description = "API Developed for technical testing"))
public class ComeialabsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComeialabsApplication.class, args);
	}

}
