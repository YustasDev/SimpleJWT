import org.jongo.marshall.jackson.oid.MongoObjectId;

import java.util.List;

public class Store {

    @MongoObjectId
    private String _id;
    private String storeName;
    private List<Product> listProduct;

    public Store() {
    }

    public Store(String _id, String storeName, List<Product> listProduct) {
        this._id = _id;
        this.storeName = storeName;
        this.listProduct = listProduct;
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

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

    @Override
    public String toString() {
        return "Store{" +
                "_id='" + _id + '\'' +
                ", storeName='" + storeName + '\'' +
                ", listProduct=" + listProduct +
                '}';
    }

}
