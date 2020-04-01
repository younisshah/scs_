package io.younis.scs.gateway;

import io.younis.scs.request.Foo;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Map;

import static io.younis.scs.constants.Constants.FOO_HEADER_INPUT;
import static io.younis.scs.constants.Constants.FOO_INPUT;

@Component
@MessagingGateway
public interface FooBarGateway {

    @Gateway(requestChannel = FOO_INPUT)
    ListenableFuture<Foo> asyncPublishFoo(Foo foo);

    @Gateway(requestChannel = FOO_INPUT)
    void blockingPublishFoo(Foo foo);

    @Gateway(requestChannel = FOO_HEADER_INPUT)
    void publishFooWithHeader(Foo foo, @Headers Map<String, String> headers);
}
