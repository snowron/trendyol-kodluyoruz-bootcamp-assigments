package com.kodluyoruz.subscription.repositories;

import com.kodluyoruz.subscription.contracts.enums.PlanType;
import com.kodluyoruz.subscription.contracts.requests.SubscriptionRequest;
import com.kodluyoruz.subscription.contracts.requests.SubscriptionUpdateRequest;
import com.kodluyoruz.subscription.contracts.response.SubscriptionResponse;
import com.kodluyoruz.subscription.exceptions.InternalServerErrorException;
import com.kodluyoruz.subscription.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.ArrayList;

@Repository
public class InMemoryMockRepository implements SubscriptionRepository {

    ArrayList<SubscriptionResponse> db;

    InMemoryMockRepository(@Qualifier("configMockDB") ArrayList<SubscriptionResponse> db) {
        this.db = db;
    }

    @Override
    public ArrayList<SubscriptionResponse> getSubscriptionsByUserId(String userId) {
        ArrayList<SubscriptionResponse> responses = new ArrayList<>();

        for (SubscriptionResponse response : this.db) {
            if (response.getUserId().equals(userId)) {
                responses.add(response);
            }
        }
        if (responses.size() > 0) {
            return responses;
        }
        throw new ResourceNotFoundException("Resource not found");
    }

    @Override
    public ArrayList<SubscriptionResponse> getSubscriptionAll() {
        return this.db;
    }

    @Override
    public String createSubscription(SubscriptionRequest request) {
        isPlanIdValid(request.getPlanId());
        try {
            SubscriptionResponse response = SubscriptionResponse.builder().id(String.valueOf(db.size() + 1))
                    .endDate(LocalDate.now()).startDate(LocalDate.now()).isPaid(false).planId(request.getPlanId())
                    .userId(request.getUserId()).price(10).build();

            this.db.add(response);
            return response.getId();
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error!");
        }
    }

    private void isPlanIdValid(int planId) {
        boolean isPlanExist = false;

        for (PlanType planType : PlanType.values()) {
            if (planId == planType.getValue()) {
                isPlanExist = true;
                break;
            }

        }

        if (!isPlanExist)
            throw new InvalidParameterException("Invalid Plan Id");
    }

    @Override
    public void updateSubscription(SubscriptionUpdateRequest request) {

        isPlanIdValid(request.getNewPlanId());

        SubscriptionResponse response = getSubscription(request.getId());

        response.setPlanId(request.getNewPlanId());
    }

    @Override
    public void deleteSubscription(String id) {
        for (SubscriptionResponse response : this.db) {
            if (response.getId().equals(id)) {
                this.db.remove(response);
                return;
            }
        }
        throw new ResourceNotFoundException("Resource not found");
    }

    @Override
    public SubscriptionResponse getSubscription(String id) {
        for (SubscriptionResponse response : this.db) {
            if (response.getId().equals(id)) {
                return response;
            }
        }
        throw new ResourceNotFoundException("Resource not found");
    }
}
