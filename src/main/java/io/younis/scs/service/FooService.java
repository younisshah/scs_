package io.younis.scs.service;

import io.younis.scs.gateway.FooBarGateway;
import io.younis.scs.request.Foo;
import io.younis.scs.request.FooHeaders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
public class FooService {

    FooBarGateway fooBarGateway;

    public FooService(FooBarGateway fooBarGateway) {
        this.fooBarGateway = fooBarGateway;
    }

    public void publishBlocking(Foo foo) {
        log.info("[+] blocking publish foo");
        fooBarGateway.blockingPublishFoo(foo);
        log.info("[+] published");
    }

    public void publishAsync(Foo foo) {
        log.info("[+] async publishing foo");
        fooBarGateway.asyncPublishFoo(foo).addCallback(new ListenableFutureCallback<Foo>() {

            @Override
            public void onFailure(Throwable throwable) {
                log.error("[X] failed to publish with error: {}", throwable.getMessage());
            }

            @Override
            public void onSuccess(Foo foo) {
                log.info("[+] published foo: {}", foo);
            }
        });
        log.info("[+] published");
    }

    public void publishWithHeaders(Foo foo, FooHeaders headers) {
        log.info("[+] publishing with headers: {}", foo);
        fooBarGateway.publishFooWithHeader(foo, headers.getHeaders());
        log.info("[+] published with headers");
    }
}
