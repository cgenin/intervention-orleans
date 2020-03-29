package fr.mdpa.springboot.demo;

import com.samskivert.mustache.Mustache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mustache.MustacheEnvironmentCollector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@SpringBootApplication
@Configuration
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Bean
	public Mustache.Compiler mustacheCompiler(
			Mustache.TemplateLoader templateLoader,
			Environment environment) {

		MustacheEnvironmentCollector collector
				= new MustacheEnvironmentCollector();
		collector.setEnvironment(environment);

		return Mustache.compiler()
				.nullValue("")
				.withLoader(templateLoader)
				.withCollector(collector);
	}

}
