package com.titov.state_machine.controller;

import com.titov.state_machine.state_machine.DocStateMachineBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class ChangeStateWithBuilderController {
    private final StateMachine<String, String> stateMachine;

    public ChangeStateWithBuilderController(DocStateMachineBuilder docStateMachineBuilder) throws Exception {
        this.stateMachine = docStateMachineBuilder.initMachine();
        this.stateMachine.start();
    }

    @GetMapping("change3/{event}")
    public String changeStateWithBuilder(@PathVariable String event){
        log.info("----");
        log.info("event: " + event);
        log.info("current state: " + getState());
        stateMachine.sendEvent(event);
        log.info("new state: " + getState());

        log.info("----");
        return getState();
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
