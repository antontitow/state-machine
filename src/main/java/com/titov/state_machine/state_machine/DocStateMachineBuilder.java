package com.titov.state_machine.state_machine;

import com.titov.state_machine.config.BuilderConfig;
import com.titov.state_machine.model.Events;
import com.titov.state_machine.model.States;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.stereotype.Component;

import static com.titov.state_machine.model.Events.SIFORK;
import static com.titov.state_machine.model.States.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class DocStateMachineBuilder {
    private final BuilderConfig builderConfig;

    public StateMachine<States, Events> initMachine() throws Exception {
        StateMachineBuilder.Builder<States, Events> builder
                = StateMachineBuilder.builder();
        builder.configureStates()
                .withStates()
                .initial(SI)

                .and().withStates().fork(FORK)
                    .and().withStates().parent(FORK).initial(SUB1).state(SUB11).state(SUB12)
                    .and().withStates().parent(FORK).initial(SUB2).state(SUB21).state(SUB22)
                .and().withStates().join(JOIN)

        //FORK
//                .fork("SFork")
//                .and().withStates()
//                    .parent("SFork")
//                    .initial("Sub1")
//                    .state("SUB11")
//                    .state("SUB12")
//                    .end("Sub1-2")
//                .and().withStates()
//                    .parent("SFork")
//                    .initial("Sub2")
//                    .state("SUB21")
//                    .state("SUB32")
//                    .end("Sub2-2");

//Junction + choice
//        builder.configureStates().withStates()
//                .initial("SI")
//                .state("S1")
//                .end("SF")
//                .and()// StateMachineState [getIds()=[SI, SUB1]
//                .withStates()
//                .parent("SI")
//                .initial("SUB1")
//                .state("SUB2")
//                .end("SUBEND")
//                .and().withStates()// StateMachineState [getIds()=[SI, SUB1]
//                .parent("SUB2")
//                .initial("SUBSUB1")
//                .state("SUBSUB2")
//                .junction("JF")
//                .state("JF1")
//                .state("JF2")
//                .state("JF3")
//                .and().withStates()
//                .choice("CH")
//                .state("CH1")
//                .state("CH2")
//                .state("CH3")
//                .state("CH4")
//                .state("CH5")

        ;

        builder.configureTransitions()
                .withExternal().source(SI).target(FORK).event(SIFORK)
                .and().withFork().source(FORK).target(SUB1).target(SUB2)
                .and().withExternal().source(SUB1).target(SUB11).event(Events.SUB11)
                .and().withExternal().source(SUB2).target(SUB22).event(Events.SUB22)
                .and().withJoin().source(SUB11).source(SUB22).target(JOIN);
//                .withExternal()
//                .source("SI").target("SFork").event("FORK")
//                .and().withFork().source("SFork")
//                    .target("Sub1")
//                    .target("Sub2")
//                .and().withExternal().source("Sub2").target("SUB22").event("SUB22")
//                .and().withExternal().source("SUB22").target("SUB33").event("SUB33")
//        ;
//                .withExternal()
//                .source("SI").target("S1").event("E01")
//                .and().withExternal()
//                .source("SI").target("SUB2").event("E1")
//                .and().withExternal()
//                .source("S1").target("SF").event("E2")
//                .and().withExternal()
//                .source("SUBSUB1").target("SUBSUB2").event("E2")
//                .and().withExternal().source("S1").target("JF").event("S1JF")
//                .and().withExternal().source("S1").target("CH").event("S1CH")
//                .and().withJunction().source("JF")
//                .first("JF1", builderConfig.mediumGuard())
//                .then("JF2", builderConfig.highGuard())
//                .last("JF3")
//                .and().withChoice().source("CH")
//                    .first("CH1", builderConfig.isCh1())
//                    .then("CH2", builderConfig.isCh2())
//                    .then("CH3", builderConfig.isCh3())
//                    .then("CH4", builderConfig.isCh4())
//                    .last("CH5");

        return builder.build();
    }
}
