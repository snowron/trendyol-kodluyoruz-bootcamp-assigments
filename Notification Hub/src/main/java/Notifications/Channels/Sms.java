package Notifications.Channels;

import Models.Company;
import Managers.CompanyOrderManager;
import İnterfaces.Channel;

public class Sms implements Channel {
    Company company;
    String telephone;
    String message;

    public Sms(Company company, String telephone, String message) {
        this.company = company;
        this.telephone = telephone;
        this.message = message;
    }

    @Override
    public void send(String message) {
        if (this.company.getOrders().canSendFromSmsProduct()) {
            this.company.getOrders().decreaseQuotaOfSmsProduct();
//            System.out.println("Successfully sended from : " + this.company.getCompanyName() + " and the message is : " +
//                    message + " to : " + this.telephone);
        } else {
//            System.out.println("Successfully sended from : " + this.company.getCompanyName() + " and the message is : " +
//                    message + " to : " + this.telephone);
            CompanyOrderManager cop = new CompanyOrderManager(this.company);
            cop.refreshTheSmsOrder();
        }
    }
}
