package mz.ciuem.uclinica.conf;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import mz.ciuem.uclinica.auth.User;
import mz.ciuem.uclinica.entity.GenericEntity;
import mz.ciuem.uclinica.entity.consulta.ServicoFormater;


@SpringBootApplication(scanBasePackages = { "mz.ciuem.uclinica.*" })
@EntityScan(basePackageClasses = {GenericEntity.class,User.class})

public class Configuracao {


	public static void main(String[] args) {
		SpringApplication.run(Configuracao.class, args);
	}

	@Bean
	public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
		return hemf.getSessionFactory();
	}

	
	 @Configuration
	    static class MyConfig extends WebMvcConfigurerAdapter {
	        @Override
	        public void addFormatters(FormatterRegistry registry) {
	            registry.addFormatter(new ServicoFormater());
	        }
	    }
	

}
