import org.jongo.marshall.jackson.oid.MongoObjectId;

public class Product {

    @MongoObjectId
    private String _id;
    private String productName;
    private Integer productPrice;

    public Product() {
    }

    public Product(String _id, String productName, Integer productPrice) {
        this._id = _id;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public Product(Product forSaleProduct) {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "_id='" + _id + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}
