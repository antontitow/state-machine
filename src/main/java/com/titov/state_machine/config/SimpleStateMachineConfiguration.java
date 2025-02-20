package com.titov.state_machine.config;

import com.titov.state_machine.listener.StateMachineListener;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
@EnableStateMachine
@RequiredArgsConstructor
public class SimpleStateMachineConfiguration
        extends StateMachineConfigurerAdapter<String, String> {
    private final ActionConfig actionConfig;
    private final GuardConfig guardConfig;
    private final StateMachineListener stateMachineListener;

    public static final HashSet STATES = new HashSet<>(Arrays.asList("S1", "S2", "S3"));
    public static final HashSet EVENTS = new HashSet<>(Arrays.asList("E1", "E2", "END"));

    @Override
    public void configure(StateMachineStateConfigurer<String, String> states)
            throws Exception {

        states
                .withStates()
                .initial("SI")
                .stateEntry("S2", actionConfig.entryAction())
                .stateExit("S2", actionConfig.exitAction())
                .end("SF")
                .states(STATES);

    }

    @Override
    public void configure(
            StateMachineTransitionConfigurer<String, String> transitions)
            throws Exception {

        transitions.withExternal()
                .source("SI").target("S1")
                    .event("E1").guard(guardConfig.simpleGuard())
                    .action(actionConfig.initAction(), actionConfig.errorAction()).action(actionConfig.executeAction())
                    .and().withExternal()
                .source("S2").target("S1")
                    .event("E1")
                    .action(actionConfig.executeAction())
                    .guard(guardConfig.simpleGuard())
                    .and().withExternal()
                .source("S1").target("S2")
                    .event("E2")
                    .action(actionConfig.executeAction())
                    .and().withExternal()
                .source("S2").target("SF")
                    .action(actionConfig.executeAction())
                    .event("END");
    }
}
