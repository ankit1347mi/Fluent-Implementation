package com.tyss.fluentdimplementation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class FluentdImplementationApplication {

    public static void main(String[] args) {
        SpringApplication.run(FluentdImplementationApplication.class, args);
    }

}
