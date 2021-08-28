import java.util.ArrayList;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import java.util.List;

public class Store {

  @MongoObjectId
  private String _id;
  private String storeName;
  private List<Product> listProducts = new ArrayList<>();

  public Store() {
  }

  public Store(Store storeWithNewProduct) {
  }

  public Store(String _id, String storeName, List<Product> listProducts) {
    this._id = _id;
    this.storeName = storeName;
    this.listProducts = listProducts;
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

  public List<Product> getListProducts() {
    return listProducts;
  }

  public void setListProducts(List<Product> listProducts) {
    this.listProducts = listProducts;
  }
}
