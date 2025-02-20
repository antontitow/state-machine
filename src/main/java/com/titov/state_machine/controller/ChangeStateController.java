package com.titov.state_machine.controller;

import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.stream.Collectors;

import static com.titov.state_machine.config.SimpleStateMachineConfiguration.EVENTS;

@RestController
@Slf4j
public class ChangeStateController {
    private final StateMachine<String, String> stateMachine;

    public ChangeStateController(StateMachine<String, String> stateMachine) {
        this.stateMachine = stateMachine;
        this.stateMachine.start();
    }


    @GetMapping("change/{event}")
    public String changeState(@PathVariable @Nullable String event) {
        if (event.isEmpty()) {
            return "empty event";
        }

        if (!EVENTS.contains(event.toUpperCase())) {
            return "wrong state";
        }

        log.info(" ------ ");
        log.info("state: " + getState());
        stateMachine.sendEvent(event.toUpperCase());
        log.info("event: " + event.toUpperCase());
        String state = getState();

        log.info("state: " + state);
        log.info(" ------ ");

        return "state" + state;
    }

    private String getState() {
        return Optional.ofNullable(stateMachine
                        .getState())
                .map(t -> t.getStates()
                        .stream()
                        .collect(Collectors.toList()).toString())
                .orElse("not found");
    }
}
