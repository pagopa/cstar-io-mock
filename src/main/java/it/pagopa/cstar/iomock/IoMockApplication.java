package it.pagopa.cstar.iomock;

import com.microsoft.applicationinsights.attach.ApplicationInsights;
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
		ApplicationInsights.attach();

		CoreRuntimeAttach runtimeAttach = new CoreRuntimeAttach(appInsightResourceName);

		SpringApplication.run(IoMockApplication.class, args);
	}

}
