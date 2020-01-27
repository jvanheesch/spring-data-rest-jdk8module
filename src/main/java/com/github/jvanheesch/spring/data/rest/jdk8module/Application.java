package com.github.jvanheesch.spring.data.rest.jdk8module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// quickly enable auto-configuration, component-scan etc
@SpringBootApplication
public class Application {
    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }
}
