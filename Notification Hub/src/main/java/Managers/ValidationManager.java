package Managers;

import Exceptions.BillDateMustBeBiggerThanOneMonth;
import Exceptions.BlacklistException;
import Exceptions.HasNoRequest;
import Exceptions.MessageCantBeEmpty;
import Models.Company;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ValidationManager {
    String country;

    public ValidationManager(String country) {
        this.country = country;
    }

    public boolean isBillDateBiggerThanOneMonth(LocalDate last, LocalDate next) {
        System.out.println(ChronoUnit.DAYS.between(last, next));
        if (ChronoUnit.DAYS.between(last, next) < 29) {
            if (isTurkeyCompany()) {

                throw new BillDateMustBeBiggerThanOneMonth("Bir aylık fatura döneminiz için beklemelisiniz");

            }
            throw new BillDateMustBeBiggerThanOneMonth("You must wait to complete a month");
        }
        return true;
    }

    public boolean isTurkeyCompany() {
        if (this.country == "Turkey") {
            return true;
        }
        return false;
    }

    public boolean smsCanSendValidation(Company company, String message) {
        isMessageEmpty(message);
        inBlacklistChecker(company);
        return true;
    }

    public boolean emailCanSendValidation(Company company, String message) {
        isMessageEmpty(message);
        inBlacklistChecker(company);
        return true;
    }

    public void isMessageEmpty(String message) {
        if (message == "") {
            if (this.isTurkeyCompany()) {
                throw new MessageCantBeEmpty("Mesaj boş geçilemez");
            }
            throw new MessageCantBeEmpty("The message cant be empty");
        }

    }

    public void inBlacklistChecker(Company company) {
        if (company.getPayment().inBlackList()) {
            if (this.isTurkeyCompany()) {
                throw new BlacklistException("Karalistede bulundu");
            }
            throw new BlacklistException("It is in blacklist");
        }
    }

    public void senderHasNoRequest(Boolean emailWillSend, Boolean smsWillSend) {

        if (emailWillSend || smsWillSend) {
        } else {
            if (this.isTurkeyCompany()) {
                throw new HasNoRequest("Lütfen bir bildirim tipi belirtin.");
            }
            throw new HasNoRequest("Please pick one or two notification type");
        }
    }

}
