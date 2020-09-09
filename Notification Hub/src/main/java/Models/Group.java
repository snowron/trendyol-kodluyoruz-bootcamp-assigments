package Models;

import java.util.List;

public class Group {
    String companyName;
    List customers;

    public Group(String companyName, List customers) {
        this.companyName = companyName;
        this.customers = customers;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List getCustomers() {
        return customers;
    }

    public void setCustomers(List customers) {
        this.customers = customers;
    }
}
