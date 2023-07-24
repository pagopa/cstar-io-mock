package it.pagopa.cstar.iomock;

import it.pagopa.cstar.iomock.config.AppConfiguration;
import it.pagopa.cstar.iomock.config.ReflectionConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportRuntimeHints;


@ImportRuntimeHints(ReflectionConfig.class)
@SpringBootApplication
@Import(AppConfiguration.class)
public class IoMockApplication {

	public static void main(String[] args) {
		SpringApplication.run(IoMockApplication.class, args);
	}

}
