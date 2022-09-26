package kr.megaptera.makaobank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.configuration.*;

@SpringBootApplication
public class MakaobankApplication {

	public static void main(String[] args) {
		SpringApplication.run(MakaobankApplication.class, args);
	}

	@Bean
	public WebSecurityCustomizer ignoringCustomizer() {
		return (web) -> web.ignoring().antMatchers("/**");
	}
}
