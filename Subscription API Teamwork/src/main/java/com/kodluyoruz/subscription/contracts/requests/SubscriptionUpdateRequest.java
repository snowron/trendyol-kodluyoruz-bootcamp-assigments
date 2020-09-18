package com.kodluyoruz.subscription.contracts.requests;


import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SubscriptionUpdateRequest {
    @NonNull
    private String id;
    @NonNull
    private int newPlanId;
}
