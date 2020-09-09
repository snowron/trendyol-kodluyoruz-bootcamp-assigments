package Notifications;

import Exceptions.BlacklistException;
import Exceptions.MessageCantBeEmpty;
import Managers.ValidationManager;
import Models.Company;
import Models.Customer;
import Models.Group;
import Notifications.Channels.Email;
import Notifications.Channels.Sms;

import java.util.ArrayList;
import java.util.List;

public class Sender {

    private boolean emailWillSend;
    private boolean smsWillSend;
    private List<Customer> customers = new ArrayList<Customer>();
    private Company company;


    public Sender(Company company, Boolean emailWillSend, Boolean smsWillSend, Customer customer) {
        new ValidationManager(company.getCountry()).senderHasNoRequest(emailWillSend, smsWillSend);

        this.company = company;
        this.emailWillSend = emailWillSend;
        this.smsWillSend = smsWillSend;
        this.customers.add(customer);
    }

    public Sender(Company company, Boolean emailWillSend, Boolean smsWillSend, Group group) {
        new ValidationManager(company.getCountry()).senderHasNoRequest(emailWillSend, smsWillSend);
        this.company = company;
        this.emailWillSend = emailWillSend;
        this.smsWillSend = smsWillSend;
        this.customers = group.getCustomers();
    }


    public void send(String message) {
        for (Customer c : customers) {
            ValidationManager vm = new ValidationManager(this.company.getCountry());

            if (this.smsWillSend) {
                vm.smsCanSendValidation(this.company, message);
                new Sms(this.company, c.getTelephone(), message).send(message);
            }
            if (this.emailWillSend) {
                vm.emailCanSendValidation(this.company, message);
                new Email(this.company, c.getEmail(), message).send(message);
            }
        }
    }
}
