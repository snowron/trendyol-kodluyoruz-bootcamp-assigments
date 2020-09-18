package com.kodluyoruz.subscription.controllers;

import com.kodluyoruz.subscription.contracts.requests.SubscriptionPaymentRequest;
import com.kodluyoruz.subscription.exceptions.InternalServerErrorException;
import com.kodluyoruz.subscription.services.SubscriptionPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscriptions/{id}/pay")
public class SubscriptionPaymentController {

    @Autowired
    SubscriptionPaymentService subscriptionPaymentService ;

    @PostMapping
    public ResponseEntity paySubscription(@RequestBody SubscriptionPaymentRequest subscriptionPaymentRequest, @PathVariable String id) {

        subscriptionPaymentService.paySubscription(subscriptionPaymentRequest,id);

        return ResponseEntity.noContent().build();

    }
}
