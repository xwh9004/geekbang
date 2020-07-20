package com.example.dubbo.api;

import java.util.concurrent.CompletableFuture;

public interface DemoService {
    String sayHello(String name);

    CompletableFuture<String> sayHelloAsync(String name);
}
