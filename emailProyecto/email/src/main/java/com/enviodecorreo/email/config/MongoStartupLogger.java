package com.enviodecorreo.email.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MongoStartupLogger implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(MongoStartupLogger.class);
    private final Environment env;

    public MongoStartupLogger(Environment env) {
        this.env = env;
    }

    @Override
    public void run(ApplicationArguments args) {
        String uri = env.getProperty("spring.data.mongodb.uri", "(defecto)");
        String masked = uri.replaceAll("//([^:@/]+):([^@/]+)@", "//$1:***@");
        log.info("[MONGO] URI efectiva: {}", masked);
    }
}
