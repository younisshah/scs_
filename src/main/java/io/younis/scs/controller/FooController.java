package io.younis.scs.controller;

import io.younis.scs.request.Foo;
import io.younis.scs.request.FooHeaders;
import io.younis.scs.service.FooService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
public class FooController {

    FooService fooService;

    public FooController(FooService fooService) {
        this.fooService = fooService;
    }

    @PostMapping(value = "/foo/publish")
    public ResponseEntity<String> publish(@RequestBody Foo foo) {

        log.info("[+] blocking publish");
        foo.setType("BLOCKING");
        fooService.publishBlocking(foo);
        log.info("[+] done");

        log.info("[+] async publish");
        foo.setType("ASYNC");
        fooService.publishAsync(foo);

        return ResponseEntity.ok("{\"message\": \"OK\"}");
    }

    @PostMapping(value = "/foo/publish/headers")
    public ResponseEntity<String> publishHeaders(@RequestBody Foo foo) {

        HashMap<String, String> headers = new HashMap<>();
        headers.put("k1", "value 1");
        headers.put("k2", "value 2");

        FooHeaders fooHeaders = FooHeaders.builder().headers(headers).build();
        fooService.publishWithHeaders(foo, fooHeaders);

        return ResponseEntity.ok("{\"message\": \"OK\"}");
    }

}
