package io.younis.scs.gateway;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

import static io.younis.scs.constants.Constants.*;


public interface FooBarChannels {

    @Output(FOO_INPUT)
    MessageChannel fooInput();

    @Input(FOO_HEADER_INPUT)
    MessageChannel fooHeaderInput();
}

