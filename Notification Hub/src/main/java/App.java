import Managers.AccountManager;
import Managers.CompanyOrderManager;
import Models.*;
import Notifications.Sender;
import Products.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class App {
    public static ArrayList<Customer> copyCustomerToHundred() {
        ArrayList list = new ArrayList();
        for (int i = 0; i < 101; i++) {
            list.add(new Customer("Murat" + i, "Turan" + i, i + "murat@turan.com", "05391233311"));
        }

        System.out.println(list);
        return list;
    }

    public static void main(String[] args) {

        Company trendyol = new Company("Trendyol", "90123125929", "Turkey", "trendyol@trendyol.com",
                new Payment(), LocalDate.parse("2020-09-03"));
        Company kodluyoruz = new Company("Kodluyoruz", "90123125929", "Turkey", "kodluyoruz@kodluyoruz.com",
                new Payment(), LocalDate.parse("2020-08-03"));

        CompanyOrderManager com = new CompanyOrderManager(trendyol);
        CompanyOrderManager com2 = new CompanyOrderManager(kodluyoruz);

        com.addOrderToCompany(new EmailElasticQuota(), new SmsConstantQuota());
        com2.addOrderToCompany(new EmailConstantQuota(), new SmsConstantQuota());


        Group trendyolEliteCustomers = new Group("Trendyol", copyCustomerToHundred());
        Group kodluyoruzEliteCustomers = new Group("Kodluyoruz", copyCustomerToHundred());

        Customer customerMurat = new Customer("Turan", "Turan", "turan@turan", "05322229944");

        Sender sender = new Sender(trendyol, false, false, trendyolEliteCustomers);
        Sender sender2 = new Sender(kodluyoruz, false, true, kodluyoruzEliteCustomers);
        Sender sender3 = new Sender(trendyol, false, true, customerMurat);

        sender.send("Bu bir test gönderisidir.");
        sender2.send("Bu bir test gönderisidir.");
        sender3.send("Bu bir test gönderisidir.");

        AccountManager am = new AccountManager(trendyol);
        am.calculateTheBill();
        am.acceptPayment(LocalDate.parse("2020-08-03"));
    }


}
