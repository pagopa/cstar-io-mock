package it.pagopa.cstar.iomock.config;

import com.azure.monitor.opentelemetry.exporter.AzureMonitorExporterBuilder;
import io.opentelemetry.exporter.logging.LoggingSpanExporter;
import io.opentelemetry.sdk.logs.export.LogRecordExporter;
import io.opentelemetry.sdk.metrics.export.MetricExporter;
import io.opentelemetry.sdk.trace.export.SpanExporter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppInsightConfig {

  private final AzureMonitorExporterBuilder azureMonitorExporterBuilder;


  public AppInsightConfig(@Value("${applicationinsights.connection.string}") String appInsightConnectionString) {
    this.azureMonitorExporterBuilder = new AzureMonitorExporterBuilder()
        .connectionString(appInsightConnectionString);
  }

  @Bean
  public SpanExporter loggingSpanProcessor() {
    return LoggingSpanExporter.create();
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

    /*@Bean
  public OpenTelemetrySdk getOpenTelemetry() {
    final Resource serviceNameResource =
        Resource.create(Attributes.of(ResourceAttributes.SERVICE_NAME, "mockio"));


    final var tracerProvider = SdkTracerProvider.builder()
        //.addSpanProcessor(SimpleSpanProcessor.create(azureMonitorExporterBuilderOpt.buildTraceExporter()))
        .addSpanProcessor(SimpleSpanProcessor.create(LoggingSpanExporter.create()))
        .setResource(Resource.getDefault().merge(serviceNameResource))
        .setSampler(Sampler.alwaysOn())
        .build();

    final var loggerProvider = SdkLoggerProvider.builder()
        //.addLogRecordProcessor(SimpleLogRecordProcessor.create(azureMonitorExporterBuilderOpt.buildLogRecordExporter()))
        .addLogRecordProcessor(SimpleLogRecordProcessor.create(SystemOutLogRecordExporter.create()))
        .setResource(Resource.getDefault().merge(serviceNameResource))
        .build();

    final var metricProvider = SdkMeterProvider.builder()
        //.registerMetricReader(PeriodicMetricReader.create(azureMonitorExporterBuilderOpt.buildMetricExporter()))
        //.registerMetricReader(PeriodicMetricReader.create(LoggingMetricExporter.create())) // export to stdout
        .setResource(Resource.getDefault().merge(serviceNameResource))
        .build();

    return OpenTelemetrySdk.builder()
        .setTracerProvider(tracerProvider)
        .setLoggerProvider(loggerProvider)
        .setMeterProvider(metricProvider)
        .setPropagators(ContextPropagators.create(W3CTraceContextPropagator.getInstance()))
        .buildAndRegisterGlobal();
  }*/
}
