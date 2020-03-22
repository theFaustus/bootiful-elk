package com.evil.learning.bootifulelk.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.HealthComponent;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
public class ActuatorMetricsService {

    private final MetricsEndpoint metricsEndpoint;
    private final HealthEndpoint healthEndpoint;
    private final InfoEndpoint infoEndpoint;

    @Autowired
    public ActuatorMetricsService(MetricsEndpoint metricsEndpoint, HealthEndpoint healthEndpoint, InfoEndpoint infoEndpoint) {
        this.metricsEndpoint = metricsEndpoint;
        this.healthEndpoint = healthEndpoint;
        this.infoEndpoint = infoEndpoint;
    }

    @Scheduled(initialDelay = 6000, fixedDelay = 60000)
    public void fetchMetrics() {
        metricsEndpoint.listNames().getNames().forEach(n -> {
            log.info(n + " = " + metricsEndpoint.metric(n, Collections.emptyList()).getMeasurements());
        });
    }

    @Scheduled(initialDelay = 5000, fixedDelay = 30000)
    public void fetchHealth() {
        HealthComponent health = healthEndpoint.health();
        log.info("health = {}" , health.getStatus());
    }

    @Scheduled(initialDelay = 6000, fixedDelay = 60000)
    public void fetchInfo() {
        infoEndpoint.info().forEach((k, v) -> log.info(k + " = " + v));
    }

}
