package Notifications.Channels;

import Models.Company;
import Managers.CompanyOrderManager;
import İnterfaces.Channel;

public class Email implements Channel {
    Company company;
    String receiver;
    String message;

    @Override
    public void send(String message) {
        if (this.company.getOrders().canSendFromEmailProduct()) {
            this.company.getOrders().decreaseQuotaOfEmailProduct();
            //  System.out.println("Successfully sended from : " + this.company.getCompanyName() + " and the message is : " +
            //             message + " to : " + this.receiver);
        } else {
            // System.out.println("Successfully sended from : " + this.company.getCompanyName() + " and the message is : " +
            //                    message + " to : " + this.receiver);
            CompanyOrderManager cop = new CompanyOrderManager(this.company);
            cop.refreshTheEmailOrder();
        }
    }

    public Email(Company company, String receiver, String message) {
        this.company = company;
        this.receiver = receiver;
        this.message = message;
    }

}
