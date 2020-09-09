package Products;

public class SmsConstantQuota extends BaseProduct {

    public SmsConstantQuota() {
        super();
        productName = "SmsConstant";
        quota = 1000;
        price = 20;
        MAX_QUOTA=1000;
    }


}
