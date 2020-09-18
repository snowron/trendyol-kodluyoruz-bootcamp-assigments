package com.kodluyoruz.subscription;

import com.kodluyoruz.subscription.contracts.requests.SubscriptionRequest;
import com.kodluyoruz.subscription.contracts.requests.SubscriptionUpdateRequest;
import com.kodluyoruz.subscription.controllers.SubscriptionController;
import com.kodluyoruz.subscription.exceptions.ResourceNotFoundException;
import com.kodluyoruz.subscription.repositories.InMemoryMockRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.InvalidParameterException;
import java.util.ArrayList;

@SpringBootTest
class SubscriptionApplicationTests {

    @Autowired
    InMemoryMockRepository inMemoryMockRepository;

    @Test
    void it_should_create_subscription() {
        SubscriptionRequest subscriptionRequest = SubscriptionRequest.builder().planId(1).userId("0").build();
        assertThat(inMemoryMockRepository.createSubscription(subscriptionRequest).equals("1"));
    }

    @Test
    void it_should_return_subscription_by_user_id() {
        SubscriptionRequest subscriptionRequest = SubscriptionRequest.builder().planId(1).userId("0").build();
        inMemoryMockRepository.createSubscription(subscriptionRequest);
        ArrayList list = inMemoryMockRepository.getSubscriptionAll();
        assertThat(list.size() == 1);
    }

    @Test
    void it_should_return_subscriptions_all() {
        SubscriptionRequest subscriptionRequest = SubscriptionRequest.builder().planId(1).userId("0").build();
        inMemoryMockRepository.createSubscription(subscriptionRequest);
        ArrayList list = inMemoryMockRepository.getSubscriptionsByUserId("0");
        assertThat(list.size() == 1);
    }

    @Test
    void it_should_delete_subscriptions_with_id() {
        SubscriptionRequest subscriptionRequest = SubscriptionRequest.builder().planId(1).userId("0").build();
        inMemoryMockRepository.createSubscription(subscriptionRequest);
        inMemoryMockRepository.deleteSubscription("1");
        assertThat(inMemoryMockRepository.getSubscriptionAll().size() == 0);
    }

    @Test
    void it_should_update_the_user_subscription_plan() {
        SubscriptionRequest subscriptionRequest = SubscriptionRequest.builder().planId(1).userId("0").build();
        inMemoryMockRepository.createSubscription(subscriptionRequest);
        inMemoryMockRepository.updateSubscription(SubscriptionUpdateRequest.builder().id("1").newPlanId(2).build());
        assertThat(inMemoryMockRepository.getSubscription("1").getPlanId()==2);
    }
    @Test
    void it_should_throw_invalid_plan_id_when_update() {
        SubscriptionRequest subscriptionRequest = SubscriptionRequest.builder().planId(1).userId("0").build();
        inMemoryMockRepository.createSubscription(subscriptionRequest);

        // When
        Throwable throwable = catchThrowable(() -> inMemoryMockRepository.updateSubscription(SubscriptionUpdateRequest.builder().id("1").newPlanId(3).build()));

        // Then
        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(InvalidParameterException.class);
        assertThat(throwable).hasMessageContaining("Invalid Plan Id");
    }
    @Test
    void it_should_throw_exception_get_subscription_by_user_id() {
        // When
        Throwable throwable = catchThrowable(() -> inMemoryMockRepository.getSubscriptionsByUserId("1"));

        // Then
        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(ResourceNotFoundException.class);
        assertThat(throwable).hasMessageContaining("Resource not found");
    }

    @Test
    void it_should_throw_exception_get_subscription() {
        // When
        Throwable throwable = catchThrowable(() -> inMemoryMockRepository.getSubscription("123"));

        // Then
        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(ResourceNotFoundException.class);
        assertThat(throwable).hasMessageContaining("Resource not found");
    }

}
