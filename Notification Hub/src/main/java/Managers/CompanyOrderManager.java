package Managers;

import Models.Company;
import Models.CompanyOrder;
import Products.*;

public class CompanyOrderManager {
    Company company;

    public CompanyOrderManager(Company company) {
        this.company = company;
    }

    public void addOrderToCompany(BaseProduct email, BaseProduct sms) {
        CompanyOrder co = new CompanyOrder(this.company, email, sms);
        this.company.getPayment().addProductToBill(email);
        this.company.getPayment().addProductToBill(sms);
        this.company.setOrders(co);
    }

    public void refreshTheEmailOrder() {
        if (this.company.getOrders().getEmailProduct().getProductName() == "EmailConstant") {
            this.company.getOrders().getEmailProduct().refreshTheProduct();
            this.company.getPayment().addProductToBill(this.company.getOrders().getEmailProduct());

        } else {
            double perPrice = this.company.getOrders().getEmailProduct().getPerPrice();
            this.company.getPayment().addAdditionalEmail(perPrice);
        }
    }

    public void refreshTheSmsOrder() {
        if (this.company.getOrders().getSmsProduct().getProductName() == "SmsConstant") {
            this.company.getOrders().getSmsProduct().refreshTheProduct();
            this.company.getPayment().addProductToBill(this.company.getOrders().getSmsProduct());

        } else {
            double perPrice = this.company.getOrders().getSmsProduct().getPerPrice();
            this.company.getPayment().addAdditionalSms(perPrice);
        }
    }

    public void refreshTheOrdersAfterPayment() {
        this.company.getOrders().getEmailProduct().refreshTheProduct();
        this.company.getOrders().getSmsProduct().refreshTheProduct();
    }
}
