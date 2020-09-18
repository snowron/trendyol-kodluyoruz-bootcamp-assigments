package com.kodluyoruz.subscription.services;

import com.kodluyoruz.subscription.contracts.requests.SubscriptionRequest;
import com.kodluyoruz.subscription.contracts.requests.SubscriptionUpdateRequest;
import com.kodluyoruz.subscription.contracts.response.SubscriptionResponse;
import com.kodluyoruz.subscription.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionsService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    public SubscriptionResponse getSubscription(String id) {
        return subscriptionRepository.getSubscription(id);
    }

    public ArrayList<SubscriptionResponse> getSubscriptionByUserId(String id) {
        return subscriptionRepository.getSubscriptionsByUserId(id);
    }

    public List<SubscriptionResponse> getSubscriptionAll() {
        return subscriptionRepository.getSubscriptionAll();
    }

    public String createSubscription(SubscriptionRequest response) {
        return subscriptionRepository.createSubscription(response);
    }

    public void updateSubscription(SubscriptionUpdateRequest request) {
        subscriptionRepository.updateSubscription(request);
    }

    public void deleteSubscription(String id) {
        subscriptionRepository.deleteSubscription(id);
    }
}
