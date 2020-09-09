package Models;

import Products.BaseProduct;

public class CompanyOrder {
    Company company;
    BaseProduct emailProduct;
    BaseProduct smsProduct;


    public CompanyOrder(Company company, BaseProduct emailProduct, BaseProduct smsProduct) {
        this.company = company;
        this.emailProduct = emailProduct;
        this.smsProduct = smsProduct;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public BaseProduct getEmailProduct() {
        return emailProduct;
    }

    public void setEmailProduct(BaseProduct emailProduct) {
        this.emailProduct = emailProduct;
    }

    public BaseProduct getSmsProduct() {
        return smsProduct;
    }

    public void setSmsProduct(BaseProduct smsProduct) {
        this.smsProduct = smsProduct;
    }

    public void decreaseQuotaOfSmsProduct() {
        BaseProduct p = (BaseProduct) smsProduct;
        p.deacreaseQuota();
    }

    public boolean canSendFromSmsProduct() {
        BaseProduct p = (BaseProduct) smsProduct;
        return p.getQuota() != 0;
    }

    public boolean canSendFromEmailProduct() {
        BaseProduct p = (BaseProduct) emailProduct;
        return p.getQuota() != 0;
    }

    public void decreaseQuotaOfEmailProduct() {
        BaseProduct p = (BaseProduct) emailProduct;
        p.deacreaseQuota();
    }


}
