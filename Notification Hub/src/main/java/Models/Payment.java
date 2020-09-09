package Models;

import Products.BaseProduct;

import java.time.LocalDate;

public class Payment {
    double bill;
    LocalDate lastTimePayment;
    double additionalSmsPrice;
    double additionalEmailPrice;
    boolean inBlackList = false;

    public Payment() {
        lastTimePayment = LocalDate.now();
    }

    public boolean inBlackList() {
        return inBlackList;
    }

    public double calculateAllBill() {
        return bill;
    }

    public void takePayment(LocalDate ld) {
        bill = 0;
        lastTimePayment = ld;
        inBlackList = false;
    }

    public void addProductToBill(BaseProduct product) {
        bill += product.getPrice();
    }

    public void setLastTimePayment(LocalDate lastTimePayment) {
        this.lastTimePayment = lastTimePayment;
    }

    public LocalDate getLastTimePayment() {
        return lastTimePayment;
    }

    public void addAdditionalSms(double perPrice) {
        bill += perPrice;
        additionalSmsPrice += perPrice;
    }

    public void addAdditionalEmail(double perPrice) {
        bill += perPrice;
        additionalEmailPrice += perPrice;
    }

    public void markAsBlacklistCompany() {
        inBlackList = true;
    }
}
