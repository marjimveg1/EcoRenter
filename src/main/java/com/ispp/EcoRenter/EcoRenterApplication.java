package com.ispp.EcoRenter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.ispp.EcoRenter.helper.FactoryHelper;
import com.ispp.EcoRenter.model.Customisation;
import com.ispp.EcoRenter.repositories.CustomisationRepository;

@SpringBootApplication
public class EcoRenterApplication {

	private static final Log log = LogFactory.getLog(EcoRenterApplication.class);
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context;
		
		context = SpringApplication.run(EcoRenterApplication.class, args);
	
		FactoryHelper.initialise(context);
	}
	
	@Bean
	public CommandLineRunner demo(CustomisationRepository repository) {
		return (args) -> {
			repository.deleteAll();
			log.info("Borramos todo -------------------");
			
			repository.save(new Customisation("ecorenter@gmail.com", "hola", 3, 12));
			log.info("Stored one elements");
			
			log.info("Exito --------------------------");
		};
		
	}

}
