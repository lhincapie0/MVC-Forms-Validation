package co.edu.icesi.ci.thymeval;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.ci.thymeval.model.UserApp;
import co.edu.icesi.ci.thymeval.model.UserGender;
import co.edu.icesi.ci.thymeval.model.UserType;
import co.edu.icesi.ci.thymeval.service.UserService;

@SpringBootApplication
public class ThymeleafValidationApplication {

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}
	
	public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}

	public static void main(String[] args) {
		
		ConfigurableApplicationContext c = SpringApplication.run(ThymeleafValidationApplication.class, args);
		UserService u = c.getBean(UserService.class);
		UserApp admin = new UserApp();
		Date b = new Date(2323223232L);
		admin.setName("admin");
		admin.setEmail("admin@gmail.com");
		admin.setGender(UserGender.masculine);
		admin.setType(UserType.ADMIN);
		admin.setBirthDate(convertToLocalDateViaInstant(b));
		admin.setPassword("{noop}admin");
		admin.setUsername("admin");
		u.save(admin);
		UserApp user1 = new UserApp();
		b = new Date(2323223232L);
		user1.setName("Juan");
		user1.setEmail("jc@gmail.com");
		user1.setGender(UserGender.femenine);
		user1.setType(UserType.doctor);
		user1.setBirthDate(convertToLocalDateViaInstant(b));
		user1.setPassword("{noop}1234");
		user1.setUsername("user");
		u.save(user1);
		UserApp user2 = new UserApp();
		Date a = new Date(2323223232L);
		user2.setGender(UserGender.femenine);
		user2.setName("Ana");
		user2.setEmail("ana@gmail.com");
		user2.setType(UserType.patient);
		user2.setBirthDate(convertToLocalDateViaInstant(a));
		user2.setUsername("ana");
		user2.setPassword("{noop}1234");
		u.save(user2);
	}

	
	
}
