package com.kodluyoruz.subscription.contracts.enums;

import lombok.Getter;

@Getter
public enum PlanType {
    FreePlan(0),PremiumPlan(1),FamilyPlan(2);
    private int value;

    PlanType(int value) {
        this.value = value;
    }
}
