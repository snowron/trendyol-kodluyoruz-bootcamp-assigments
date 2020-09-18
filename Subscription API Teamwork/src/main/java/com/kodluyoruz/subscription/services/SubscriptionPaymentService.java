package com.kodluyoruz.subscription.services;

import com.kodluyoruz.subscription.contracts.requests.SubscriptionPaymentRequest;
import com.kodluyoruz.subscription.contracts.response.SubscriptionResponse;
import com.kodluyoruz.subscription.repositories.InMemoryMockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SubscriptionPaymentService {

    @Autowired
    InMemoryMockRepository db;

    @Autowired
    SubscriptionsService subscriptionsService;

    public void paySubscription(SubscriptionPaymentRequest subscriptionPaymentRequest, String id){

        SubscriptionResponse subscriptionResponse = subscriptionsService.getSubscription(id);
        subscriptionResponse.setPaid(true);
    }

}
