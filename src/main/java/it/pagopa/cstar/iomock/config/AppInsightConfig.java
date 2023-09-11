package it.pagopa.cstar.iomock.config;

import com.azure.monitor.opentelemetry.exporter.AzureMonitorExporterBuilder;
import io.opentelemetry.exporter.logging.LoggingMetricExporter;
import io.opentelemetry.exporter.logging.LoggingSpanExporter;
import io.opentelemetry.sdk.logs.export.LogRecordExporter;
import io.opentelemetry.sdk.metrics.export.MetricExporter;
import io.opentelemetry.sdk.trace.export.SpanExporter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppInsightConfig implements BeanPostProcessor {

  private final AzureMonitorExporterBuilder azureMonitorExporterBuilder;

  public AppInsightConfig(@Value("${applicationinsights.connectionstring}") String appInsightConnectionString) {
    this.azureMonitorExporterBuilder = new AzureMonitorExporterBuilder()
        .connectionString(appInsightConnectionString);
  }

//  @Bean
//  public SpanExporter loggingSpanProcessor() {
//    return LoggingSpanExporter.create();
//  }
//
//  @Bean
//  public MetricExporter loggingMetricExporter() {
//    return LoggingMetricExporter.create();
//  }

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
}
