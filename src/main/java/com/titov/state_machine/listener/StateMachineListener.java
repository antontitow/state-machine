package com.titov.state_machine.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StateMachineListener extends StateMachineListenerAdapter {

    @Override
    public void stateChanged(State from, State to) {
        log.info("transitioned: ");
    }
}