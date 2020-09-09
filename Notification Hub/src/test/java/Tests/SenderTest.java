package Tests;


import Exceptions.BillDateMustBeBiggerThanOneMonth;
import Exceptions.BlacklistException;
import Exceptions.HasNoRequest;
import Exceptions.MessageCantBeEmpty;
import Managers.AccountManager;
import Managers.CompanyOrderManager;
import Models.Company;
import Models.Customer;
import Models.Group;
import Models.Payment;
import Notifications.Sender;
import Products.EmailElasticQuota;
import Products.SmsConstantQuota;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

class SenderTest {
    public static ArrayList<Customer> copyCustomerToHundred() {
        ArrayList<Customer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new Customer("Murat" + i, "Turan" + i, i + "murat@turan.com", "05391233311"));
        }
        return list;
    }

    public static ArrayList<Customer> copyCustomerToTwoThousandTen() {
        ArrayList<Customer> list = new ArrayList<>();
        for (int i = 0; i < 2010; i++) {
            list.add(new Customer("Murat" + i, "Turan" + i, i + "murat@turan.com", "05391233311"));
        }
        return list;
    }

    //ERROR HANDLING TESTS
    @Test
    public void it_should_raise_exception_blacklist() {
        //Given
        Company trendyol = new Company("Trendyol", "90123125929", "Turkey", "trendyol@trendyol.com",
                new Payment(), LocalDate.parse("2020-09-03"));
        CompanyOrderManager com = new CompanyOrderManager(trendyol);
        com.addOrderToCompany(new EmailElasticQuota(), new SmsConstantQuota());
        trendyol.getPayment().setLastTimePayment(LocalDate.parse("2020-07-07"));
        Group trendyolEliteCustomers = new Group("Trendyol", copyCustomerToHundred());
        Sender sender = new Sender(trendyol, true, true, trendyolEliteCustomers);

        //When
        Throwable throwable = catchThrowable(() -> sender.send("Bu bir test gönderisi."));
        //Then
        assertThat(throwable).isNotNull();
        assertThat(throwable).hasMessageContaining("Karalistede bulundu");
        assertThat(throwable).isInstanceOf(BlacklistException.class);

    }

    @Test
    public void it_should_raise_exception_has_no_request() {
        //Given
        Company trendyol = new Company("Trendyol", "90123125929", "Turkey", "trendyol@trendyol.com",
                new Payment(), LocalDate.parse("2020-09-03"));
        CompanyOrderManager com = new CompanyOrderManager(trendyol);
        com.addOrderToCompany(new EmailElasticQuota(), new SmsConstantQuota());

        Customer customerMurat = new Customer("Turan", "Turan", "turan@turan", "05322229944");

        //When
        Throwable throwable = catchThrowable(() ->
                new Sender(trendyol, false, false, customerMurat));
        //Then
        assertThat(throwable).isNotNull();
        assertThat(throwable).hasMessageContaining("Lütfen bir bildirim tipi belirtin.");
        assertThat(throwable).isInstanceOf(HasNoRequest.class);

    }

    @Test
    public void it_should_raise_exception_message_cant_be_empty() {
        //Given
        Company trendyol = new Company("Trendyol", "90123125929", "Turkey", "trendyol@trendyol.com",
                new Payment(), LocalDate.parse("2020-09-03"));
        CompanyOrderManager com = new CompanyOrderManager(trendyol);
        com.addOrderToCompany(new EmailElasticQuota(), new SmsConstantQuota());

        Customer customerMurat = new Customer("Turan", "Turan", "turan@turan", "05322229944");

        Sender sender = new Sender(trendyol, true, true, customerMurat);

        //When
        Throwable throwable = catchThrowable(() -> sender.send(""));

        //Then
        assertThat(throwable).isNotNull();
        assertThat(throwable).hasMessageContaining("Mesaj boş geçilemez");
        assertThat(throwable).isInstanceOf(MessageCantBeEmpty.class);
    }

    @Test
    public void it_should_raise_exception_bill_date_must_be_bigger_than_one_month() {
        //Given
        Company trendyol = new Company("Trendyol", "90123125929", "Turkey", "trendyol@trendyol.com",
                new Payment(), LocalDate.parse("2020-09-03"));
        CompanyOrderManager com = new CompanyOrderManager(trendyol);
        com.addOrderToCompany(new EmailElasticQuota(), new SmsConstantQuota());
        trendyol.getPayment().setLastTimePayment(LocalDate.parse("2020-09-03"));

        //When
        AccountManager am = new AccountManager(trendyol);
        Throwable throwable = catchThrowable(() -> am.acceptPayment(LocalDate.parse("2020-09-04")));
        //Then
        assertThat(throwable).isNotNull();
        assertThat(throwable).hasMessageContaining("Bir aylık fatura döneminiz için beklemelisiniz");
        assertThat(throwable).isInstanceOf(BillDateMustBeBiggerThanOneMonth.class);
    }

    @Test
    public void it_should_raise_exception_blacklist_other_language() {
        //Given
        Company trendyol = new Company("Trendyol", "90123125929", "Italy", "trendyol@trendyol.com",
                new Payment(), LocalDate.parse("2020-09-03"));
        CompanyOrderManager com = new CompanyOrderManager(trendyol);
        com.addOrderToCompany(new EmailElasticQuota(), new SmsConstantQuota());
        trendyol.getPayment().setLastTimePayment(LocalDate.parse("2020-07-07"));
        Group trendyolEliteCustomers = new Group("Trendyol", copyCustomerToHundred());
        Sender sender = new Sender(trendyol, true, true, trendyolEliteCustomers);

        //When
        Throwable throwable = catchThrowable(() -> sender.send("Bu bir test gönderisi."));
        //Then
        assertThat(throwable).isNotNull();
        assertThat(throwable).hasMessageContaining("It is in blacklist");
        assertThat(throwable).isInstanceOf(BlacklistException.class);

    }

    @Test
    public void it_should_raise_exception_has_no_request_other_language() {
        //Given
        Company trendyol = new Company("Trendyol", "90123125929", "Italy", "trendyol@trendyol.com",
                new Payment(), LocalDate.parse("2020-09-03"));
        CompanyOrderManager com = new CompanyOrderManager(trendyol);
        com.addOrderToCompany(new EmailElasticQuota(), new SmsConstantQuota());

        Customer customerMurat = new Customer("Turan", "Turan", "turan@turan", "05322229944");

        //When
        Throwable throwable = catchThrowable(() ->
                new Sender(trendyol, false, false, customerMurat));
        //Then
        assertThat(throwable).isNotNull();
        assertThat(throwable).hasMessageContaining("Please pick one or two notification type");
        assertThat(throwable).isInstanceOf(HasNoRequest.class);

    }

    @Test
    public void it_should_raise_exception_message_cant_be_empty_other_language() {
        //Given
        Company trendyol = new Company("Trendyol", "90123125929", "Italy", "trendyol@trendyol.com",
                new Payment(), LocalDate.parse("2020-09-03"));
        CompanyOrderManager com = new CompanyOrderManager(trendyol);
        com.addOrderToCompany(new EmailElasticQuota(), new SmsConstantQuota());

        Customer customerMurat = new Customer("Turan", "Turan", "turan@turan", "05322229944");

        Sender sender = new Sender(trendyol, true, true, customerMurat);

        //When
        Throwable throwable = catchThrowable(() -> sender.send(""));

        //Then
        assertThat(throwable).isNotNull();
        assertThat(throwable).hasMessageContaining("The message cant be empty");
        assertThat(throwable).isInstanceOf(MessageCantBeEmpty.class);
    }

    @Test
    public void it_should_raise_exception_bill_date_must_be_bigger_than_one_month_other_lang() {
        //Given
        Company trendyol = new Company("Trendyol", "90123125929", "Italy", "trendyol@trendyol.com",
                new Payment(), LocalDate.parse("2020-09-03"));
        CompanyOrderManager com = new CompanyOrderManager(trendyol);
        com.addOrderToCompany(new EmailElasticQuota(), new SmsConstantQuota());
        trendyol.getPayment().setLastTimePayment(LocalDate.parse("2020-09-03"));
        //When
        AccountManager am = new AccountManager(trendyol);
        Throwable throwable = catchThrowable(() -> am.acceptPayment(LocalDate.parse("2020-09-02")));
        //Then
        assertThat(throwable).isNotNull();
        assertThat(throwable).hasMessageContaining("You must wait to complete a month");
        assertThat(throwable).isInstanceOf(BillDateMustBeBiggerThanOneMonth.class);
    }

    //RETURN TESTS
    @Test
    public void it_should_return_succesfully_send_message_to_group() {
        //Given
        Company trendyol = new Company("Trendyol", "90123125929", "Turkey", "trendyol@trendyol.com",
                new Payment(), LocalDate.parse("2020-09-03"));
        CompanyOrderManager com = new CompanyOrderManager(trendyol);
        com.addOrderToCompany(new EmailElasticQuota(), new SmsConstantQuota());
        Group trendyolEliteCustomers = new Group("Trendyol", copyCustomerToHundred());
        Sender sender = new Sender(trendyol, true, true, trendyolEliteCustomers);

        //When
        sender.send("Bu bir test gönderisi.");
        //Then
        assertThat(trendyol.getOrders().getEmailProduct().getQuota() < new EmailElasticQuota().getQuota());

    }

    @Test
    public void it_should_return_succesfully_send_message_to_one_customer() {
        //Given
        Company trendyol = new Company("Trendyol", "90123125929", "Turkey", "trendyol@trendyol.com",
                new Payment(), LocalDate.parse("2020-09-03"));
        CompanyOrderManager com = new CompanyOrderManager(trendyol);
        com.addOrderToCompany(new EmailElasticQuota(), new SmsConstantQuota());

        Customer customerMurat = new Customer("Turan", "Turan", "turan@turan", "05322229944");

        Sender sender = new Sender(trendyol, true, true, customerMurat);

        //When
        sender.send("Bu bir test gönderisi.");
        //Then
        assertThat(trendyol.getOrders().getEmailProduct().getQuota() < new EmailElasticQuota().getQuota());

    }

    @Test
    public void it_should_add_new_packet_to_company() {
        //Given
        Company trendyol = new Company("Trendyol", "90123125929", "Turkey", "trendyol@trendyol.com",
                new Payment(), LocalDate.parse("2020-09-03"));
        CompanyOrderManager com = new CompanyOrderManager(trendyol);
        com.addOrderToCompany(new EmailElasticQuota(), new SmsConstantQuota());
        Group trendyolEliteCustomers = new Group("Trendyol", copyCustomerToTwoThousandTen());
        Sender sender = new Sender(trendyol, true, true, trendyolEliteCustomers);

        //When
        sender.send("Bu bir test gönderisi.");
        //Then
        AccountManager am = new AccountManager(trendyol);
        assertThat(am.calculateTheBill() == 70.49999999999997);
    }

}