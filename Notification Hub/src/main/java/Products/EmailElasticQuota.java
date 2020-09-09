package Products;

public class EmailElasticQuota extends BaseProduct {

    public EmailElasticQuota() {
        super();
        productName = "EmailElastic";
        quota = 2000;
        price = 7.5;
        MAX_QUOTA=2000;
        perPrice=0.3;
    }

}
