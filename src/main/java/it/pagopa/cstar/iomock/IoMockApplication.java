package it.pagopa.cstar.iomock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;


@ImportRuntimeHints(ReflectionConfig.class)
@SpringBootApplication
public class IoMockApplication {

	public static void main(String[] args) {
		SpringApplication.run(IoMockApplication.class, args);
	}

}
