package com.kodluyoruz.subscription.configs;

import com.kodluyoruz.subscription.contracts.response.SubscriptionResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MockDBConfig {

    ArrayList<SubscriptionResponse> db = new ArrayList<>();

    @Bean
    ArrayList<SubscriptionResponse> configMockDB(){
        return db;
    }
}
