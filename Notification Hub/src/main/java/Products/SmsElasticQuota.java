package Products;

public class SmsElasticQuota extends BaseProduct {
    public SmsElasticQuota() {
        super();
        productName = "SmsElastic";
        quota = 2000;
        price = 20;
        MAX_QUOTA=2000;
        perPrice=0.10;
    }
}
