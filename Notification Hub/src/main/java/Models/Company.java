package Models;

import java.time.LocalDate;
import java.util.List;

public class Company {
    //Unique ID : companyName
    String companyName;
    String telephone;
    String country;
    String email;
    CompanyOrder orders;
    Payment payment;
    LocalDate created;


    public Company(String companyName, String telephone, String country, String email,  Payment payment, LocalDate created) {
        this.companyName = companyName;
        this.telephone = telephone;
        this.country = country;
        this.email = email;
        this.payment = payment;
        this.created = created;
    }
    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CompanyOrder getOrders() {
        return orders;
    }

    public void setOrders(CompanyOrder orders) {
        this.orders = orders;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }
}
