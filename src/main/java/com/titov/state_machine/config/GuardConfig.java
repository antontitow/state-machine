package com.titov.state_machine.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.guard.Guard;

@Configuration
@Slf4j
public class GuardConfig {

    //Проерка до перехода из одного стейта в другой. При отрицательном значении просто состояние остается как было
    @Bean
    public Guard<String, String> simpleGuard() {
        return ctx -> (int) ctx.getExtendedState()
                .getVariables()
                .getOrDefault("approvalCount", 0) < 3;
    }
}
