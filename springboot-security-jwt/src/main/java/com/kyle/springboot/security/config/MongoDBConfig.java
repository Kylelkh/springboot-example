package com.kyle.springboot.security.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoDBConfig {

    @Bean
    public MongoClient mongoClient() {
        return new MongoClient("localhost:27018");
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "security");
    }
}
