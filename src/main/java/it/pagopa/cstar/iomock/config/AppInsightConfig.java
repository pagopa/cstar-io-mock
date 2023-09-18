package it.pagopa.cstar.iomock.config;

import com.azure.monitor.opentelemetry.exporter.AzureMonitorExporterBuilder;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.instrumentation.spring.webflux.v5_3.SpringWebfluxTelemetry;
import io.opentelemetry.sdk.logs.export.LogRecordExporter;
import io.opentelemetry.sdk.metrics.export.MetricExporter;
import io.opentelemetry.sdk.trace.export.SpanExporter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.WebFilter;

@ConditionalOnProperty(value = "applicationinsights.connectionstring")
@Configuration
public class AppInsightConfig {

  private final AzureMonitorExporterBuilder azureMonitorExporterBuilder;

  public AppInsightConfig(@Value("${applicationinsights.connectionstring}") String appInsightConnectionString) {
    this.azureMonitorExporterBuilder = new AzureMonitorExporterBuilder()
        .connectionString(appInsightConnectionString);
  }

  @Bean
  public SpanExporter azureSpanProcessor() {
    return azureMonitorExporterBuilder.buildTraceExporter();
  }

  @Bean
  public MetricExporter azureMetricExporter() {
    return azureMonitorExporterBuilder.buildMetricExporter();
  }

  @Bean
  public LogRecordExporter azureLogRecordExporter() {
    return azureMonitorExporterBuilder.buildLogRecordExporter();
  }

  @Bean
  public SpringWebfluxTelemetry springWebfluxTelemetry(OpenTelemetry openTelemetry) {
    return SpringWebfluxTelemetry.builder(openTelemetry).build();
  }

  @Bean
  public WebFilter webFilter(SpringWebfluxTelemetry webfluxTelemetry) {
    return webfluxTelemetry.createWebFilterAndRegisterReactorHook();
  }
}
