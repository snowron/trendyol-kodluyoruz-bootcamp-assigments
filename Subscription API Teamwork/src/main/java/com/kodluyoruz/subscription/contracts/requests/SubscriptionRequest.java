package com.kodluyoruz.subscription.contracts.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SubscriptionRequest {
    @NonNull
    private String userId;
    @NonNull
    private int planId;
}
