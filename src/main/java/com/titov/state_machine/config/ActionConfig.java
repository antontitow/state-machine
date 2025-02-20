package com.titov.state_machine.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;

@Configuration
@Slf4j
public class ActionConfig {

    @Bean
    public Action<String, String> initAction() {
        return ctx -> log.info("action: " + ctx.getTarget().getId());
    }

    @Bean
    public Action<String, String> errorAction() {
        return ctx -> log.info("error: " + ctx.getTarget().getId());
    }

    @Bean
    public Action<String, String> entryAction() {
        return ctx -> System.out.println(
                "Entry " + ctx.getTarget().getId());
    }

    @Bean
    public Action<String, String> exitAction() {
        return ctx -> System.out.println(
                "Exit " + ctx.getSource().getId() + " -> " + ctx.getTarget().getId());
    }

    @Bean
    public Action<String, String> executeAction() {
        return ctx -> {
            int approvals = (int) ctx.getExtendedState().getVariables()
                    .getOrDefault("approvalCount", 0);
            approvals++;
            ctx.getExtendedState().getVariables()
                    .put("approvalCount", approvals);
            log.info(String.valueOf(approvals));
        };
    }
}
