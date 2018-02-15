package mz.ciuem.uclinica.conf;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import mz.ciuem.uclinica.auth.User;
import mz.ciuem.uclinica.entity.GenericEntity;

@SpringBootApplication(scanBasePackages = { "mz.ciuem.uclinica.*" })
@EntityScan(basePackageClasses = {GenericEntity.class,User.class})
public class SpringBootWebApplication extends SpringBootServletInitializer {
	
	

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		
		return application.sources(SpringBootWebApplication.class);
	}

	@Bean
	public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
		return hemf.getSessionFactory();
	}

}
