package com.kodluyoruz.subscription.repositories;

import com.kodluyoruz.subscription.contracts.requests.SubscriptionRequest;
import com.kodluyoruz.subscription.contracts.requests.SubscriptionUpdateRequest;
import com.kodluyoruz.subscription.contracts.response.SubscriptionResponse;

import java.util.ArrayList;
import java.util.List;


public interface SubscriptionRepository {

    ArrayList<SubscriptionResponse> getSubscriptionsByUserId(String userId);

    ArrayList<SubscriptionResponse> getSubscriptionAll();

    String createSubscription(SubscriptionRequest request);

    void updateSubscription(SubscriptionUpdateRequest request);

    void deleteSubscription(String id);

    SubscriptionResponse getSubscription(String id);

}
