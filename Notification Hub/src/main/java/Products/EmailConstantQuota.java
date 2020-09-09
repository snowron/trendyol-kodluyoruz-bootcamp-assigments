package Products;

public class EmailConstantQuota extends BaseProduct {
    public EmailConstantQuota() {
        super();
        productName = "EmailConstant";
        quota = 1000;
        price = 10;
        MAX_QUOTA=1000;
    }


}
