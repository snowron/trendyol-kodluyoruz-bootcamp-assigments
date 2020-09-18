package com.kodluyoruz.subscription.contracts.requests;

import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class SubscriptionPaymentRequest {

    @NonNull
    private String cardOwnerName;
    @NonNull
    private String cardNumber;
    @NonNull
    private String validThruMonth;
    @NonNull
    private String validThruYear;
    @NonNull
    private String cvc;
}
