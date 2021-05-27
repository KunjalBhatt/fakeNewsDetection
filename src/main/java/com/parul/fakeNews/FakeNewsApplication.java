package com.parul.fakeNews;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@SpringBootApplication
public class FakeNewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FakeNewsApplication.class, args);
	}

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("fakenewsdetector036@gmail.com");
		mailSender.setPassword("Fakenews123");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}

	@Bean
	public ClassLoaderTemplateResolver yourTemplateResolver() {
		ClassLoaderTemplateResolver configurer = new ClassLoaderTemplateResolver();
		configurer.setPrefix("/");
		configurer.setSuffix(".html");
		configurer.setTemplateMode(TemplateMode.HTML);
		configurer.setCharacterEncoding("UTF-8");
		// configurer.setOrder(0); // this is important. This way spring //boot will
		// listen to both places 0 and 1
		configurer.setCheckExistence(true);
		return configurer;
	}
}
