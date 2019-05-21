package com.query.dsl.user.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class QueryDslUserSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueryDslUserSearchApplication.class, args);
	}

	@Bean
    public MessageSource messageSource() {
      ResourceBundleMessageSource source = new ResourceBundleMessageSource();
      source.setBasename("messages");
      return source;
    }
}
