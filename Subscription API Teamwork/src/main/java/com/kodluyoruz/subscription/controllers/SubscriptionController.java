package com.kodluyoruz.subscription.controllers;

import com.kodluyoruz.subscription.contracts.requests.SubscriptionRequest;
import com.kodluyoruz.subscription.contracts.requests.SubscriptionUpdateRequest;
import com.kodluyoruz.subscription.contracts.response.SubscriptionResponse;
import com.kodluyoruz.subscription.services.SubscriptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    SubscriptionsService subscriptionsService;

    @GetMapping
    public ResponseEntity getSubscriptions(@RequestParam(required = false) String userId) {
        if (Objects.nonNull(userId)) {
            return ResponseEntity.ok(subscriptionsService.getSubscriptionByUserId(userId));
        } else {
            return ResponseEntity.ok(subscriptionsService.getSubscriptionAll());
        }
    }

    @PostMapping
    public ResponseEntity createSubscription(@RequestBody SubscriptionRequest subscription) {
        String id = subscriptionsService.createSubscription(subscription);
        URI location = URI.create(String.format("/subscriptions/%s/", id));
        return ResponseEntity.created(location).build();
    }

    @PatchMapping
    public ResponseEntity updateSubscription(@RequestBody SubscriptionUpdateRequest subscriptionUpdateRequest) {
        subscriptionsService.updateSubscription(subscriptionUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionResponse> getSubscription(@PathVariable String id) {
        return ResponseEntity.ok(subscriptionsService.getSubscription(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSubscription(@PathVariable String id) {
        subscriptionsService.deleteSubscription(id);
        return ResponseEntity.noContent().build();
    }

}
