package it.pagopa.cstar.iomock.config;

import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.boot.logging.logback.RootLogLevelConfigurator;

public class ReflectionConfig implements RuntimeHintsRegistrar {
    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        hints.reflection().registerType(RootLogLevelConfigurator.class, MemberCategory.values());
    }
} 
