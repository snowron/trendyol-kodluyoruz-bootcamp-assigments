package Products;

public abstract class BaseProduct {
    String productName;
    int quota;
    double price;
    int MAX_QUOTA;
    double perPrice;

    public void deacreaseQuota() {
        quota--;
    }

    public void refreshTheProduct() {
        quota = MAX_QUOTA;
    }

    public int getQuota() {
        return quota;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getMAX_QUOTA() {
        return MAX_QUOTA;
    }

    public void setMAX_QUOTA(int MAX_QUOTA) {
        this.MAX_QUOTA = MAX_QUOTA;
    }

    public double getPerPrice() {
        return perPrice;
    }

    public void setPerPrice(double perPrice) {
        this.perPrice = perPrice;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public BaseProduct() {

    }
}
