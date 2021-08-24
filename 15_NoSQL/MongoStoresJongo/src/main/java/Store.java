import org.jongo.marshall.jackson.oid.MongoObjectId;

public class Store {

    @MongoObjectId
    private String _id;
    private String storeName;
    private Product product;


    public Store() {
    }

    public Store(String _id, String storeName, Product product) {
        this._id = _id;
        this.storeName = storeName;
        this.product = product;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
