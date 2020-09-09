package Managers;

import Exceptions.BillDateMustBeBiggerThanOneMonth;
import Models.Company;

import java.time.LocalDate;

public class AccountManager {
    Company company;

    public AccountManager(Company company) {
        this.company = company;
    }

    public double calculateTheBill() {
        double bill = this.company.getPayment().calculateAllBill();
        return bill;
    }

    public void acceptPayment(LocalDate ld) {
        new ValidationManager(this.company.getCountry())
                .isBillDateBiggerThanOneMonth(this.company.getPayment().getLastTimePayment(), ld);
        this.company.getPayment().takePayment(ld);
        CompanyOrderManager com = new CompanyOrderManager(this.company);
        com.refreshTheOrdersAfterPayment();
    }

}
