import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import org.bson.BSON;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;
import org.jongo.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.mongodb.client.model.Aggregates.group;


public class Main<statistics> {

    private static Scanner scanner;
    private final static String COMMAND_SET = "(set)(\\D+)";
    private final static String COMMAND_ADD = "(add)(\\D+)(\\d+)";
    private final static String COMMAND_PLACE = "(place)\\s+(\\D+)\\s+(\\D+)";
    private final static String COMMAND_STATISTICS = "statistics";
    private final static String COMMAND_END = "END";
    private static volatile boolean statistics = false;

    public static void main(String[] args) {

//        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
//        MongoDatabase database = mongoClient.getDatabase("mongoStores");
//        MongoCollection<Document> listStores = database.getCollection("stores");
//        MongoCollection<Document> ListProducts = database.getCollection("products");
//        listStores.drop();
//        ListProducts.drop();

        /*
        working with the Jongo library replaces the top (commented out) lines
         */
        DB db = new MongoClient().getDB("mongoStores");
        Jongo jongo = new Jongo(db);
        MongoCollection stores = jongo.getCollection("stores");
        MongoCollection products = jongo.getCollection("products");
        /*
         commented out for development, so as not to create collections every time you run
        */
        //stores.drop();
        //products.drop();

        printСondition();

        for (; ; ) {
            scanner = new Scanner(System.in);
            String input = inputCommand(
                    "Введите команду:  ");

            Map<String, Document> mapDocument = commandRecognition(input);
            Document storeDocument = mapDocument.get("store");
            Document productDocument = mapDocument.get("product");
            Document placeProductInStore = mapDocument.get("placeProductInStore");

            if (storeDocument != null) {
                stores.save(storeDocument);
                System.out.println("Магазин: " + printDoc(storeDocument) + " внесен в базу данных. \n");
            }
            if (productDocument != null) {
                products.save(productDocument);
                System.out.println("Продукт: " + printDoc(productDocument) + " внесен в базу данных. \n");
            }
            if (placeProductInStore!= null) {
                String store = String.valueOf(placeProductInStore.get("placeStoreName"));
                String product = String.valueOf(placeProductInStore.get("placeProductName"));

                //  I have not found any other way how to check if records exist in the database
                MongoCursor<Product> cursorProduct = products.find("{productName:#}", product).as(Product.class);
                MongoCursor<Store> cursorStore = stores.find("{storeName:#}", store).as(Store.class);

                int productQuantity = cursorProduct.count();
                int storeQuantity = cursorStore.count();
                if (productQuantity > 0 && storeQuantity > 0) {
                FindOne productForSale = products.findOne("{productName:#}", product);
                Product forSaleProduct = productForSale.as(Product.class);

                FindOne storeForProduct = stores.findOne("{storeName:#}", store);
                Store storeWithNewProduct = storeForProduct.as(Store.class);

                List<Product> availableProductsInStore = storeWithNewProduct.getListProducts();
                availableProductsInStore.add(forSaleProduct);
                storeWithNewProduct.setListProducts(availableProductsInStore);
                stores.save(storeWithNewProduct);
                System.out.println("Продукт: " + "'" + product + "' выставлен на продажу в магазине '" + store + "'");
                }
                else {
                System.out.println("Информация о введенном магазине и/или продукте отсутствует в БД");
                }
            }
            if (statistics != false) {
                MongoCursor<Store> allStores = stores
                        .find()
                        .sort("{storeName: 1}").as(Store.class);

                for (Store store : allStores) {
                    int numberProductsNames = store.getListProducts().size();
                    System.out.println("В магазине: " + "'" + store.getStoreName()
                                    + "' общее количество наименований товаров, составляет: "
                                    + numberProductsNames);
                }

//                Bson unwind = Aggregates.unwind(Constants.$PRODUCTS);
//                Bson lookup = Aggregates.lookup(Constants.PRODUCTS, Constants.PRODUCTS, Constants.NAME,
//                        Constants.PRODUCTS_LIST);
//                Bson minGroup = Aggregates.group(Constants.$NAME,
//                        Accumulators.min(Constants.MIN_PRICE, Constants.$PRODUCTS_LIST_PRICE));
//                Bson maxGroup = Aggregates.group(Constants.$NAME,
//                        Accumulators.max(Constants.MAX_PRICE, Constants.$PRODUCTS_LIST_PRICE));
//                Bson match = Aggregates.match(Constants.LISTPRODUCTS_PRODUCTSPRICE, )


                Bson unwindListProducts = Aggregates.unwind(Constants.$LISTPRODUCTS);

                Bson match = Aggregates.match(new Document (Constants.LISTPRODUCTS_PRODUCTSPRICE, "{ $ne : 0 }"));

             //   Bson maxGroup = Aggregates.group(Constants.$STORENAME, Accumulators.max(Constants.MAX_PRICE, Constants.$LISTPRODUCTS_PRODUCTPRICE));

                Bson group = Aggregates.group(Constants.$STORENAME, Accumulators.max(Constants.MAX_PRICE, Constants.$LISTPRODUCTS_PRODUCTPRICE));

                Bson maxGroup1 = group(Constants.$STORENAME, Accumulators.max("_max", Constants.$LISTPRODUCTS_PRODUCTPRICE));

                //stores.aggregate(Arrays.asList(unwindListProducts, match, group))


                MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
                MongoDatabase database = mongoClient.getDatabase("driverMongoStores");
                com.mongodb.client.MongoCollection<Document> listStores = database.getCollection("driverStores");
                com.mongodb.client.MongoCollection<Document> ListProducts = database.getCollection("driverProducts");

                List<Document> inDriverProducts = new ArrayList<>();
                DBCursor cursorP = db.getCollection("products").find();
                for (DBObject dbo : cursorP){
                    Document doc = getDocument(dbo);
                    inDriverProducts.add(doc);
                }
                listStores.insertMany(inDriverProducts);


                List<Document> inDriverStores = new ArrayList<>();
                DBCursor cursorS = db.getCollection("stores").find();
                for (DBObject dbo : cursorS){
                    Document doc = getDocument(dbo);
                    inDriverStores.add(doc);
                }
                listStores.insertMany(inDriverStores);

                long count = listStores.countDocuments();
                System.out.println(count);

           //     Aggregate.ResultsIterator<Store> max = stores.aggregate
                       // ("{$unwind:{listProducts}}")
                   //     .and("{$match:{$listProducts.productPrice: {$ne : 0}}}")
                    //    .and("{$group:{$storeName}}")
                      //  .and("{$max:{$listProducts.productPrice}}")
      //                  .as(Store.class);

        //        for (Store store: max) {
     //               System.out.println(store);
     //           }




//                int max = stores.aggregate(Arrays.asList(unwindListProducts,
//                        group(null, Accumulators.max("max", "$listProducts.productPrice"))))
//                        .first().getInteger("max");
//                System.out.println(max);




//                System.out.println(Constants.MINIMUM_PRICE);
//                stores.aggregate(Arrays.asList(unwindListProducts,         unwind, lookup, unwindProducts, minGroup));
                // .forEach((Consumer<Document>) System.out::println);

//        db.stores.aggregate([
//... {
//...         $unwind: "$listProducts"
//...     },
//...     {
//...         $match : {
//...             "listProducts.productPrice" : { $ne : 0 }
//...         }
//...     },
//...     {
//...         $group : {
//...             _id : {
//...                 storeName : "$storeName",
//...             },
//...             MAX_Products : {
//...                 $max : "$listProducts.productPrice"
//...             }
//...         }
//...      }
//...  ])

                     statistics = false;
            }
        }
    }

    public static Document getDocument(DBObject doc)
    {
        if(doc == null) return null;
        return new Document(doc.toMap());
    }


    private static String inputCommand(String message) {
        for (; ; ) {
            System.out.println(message);
            String line = scanner.nextLine().trim();
            if (line != null) {
                return line;
            }
        }
    }

    private static String printDoc(Document document) {
        var settings = JsonWriterSettings.builder()
                .indent(true)
                .outputMode(JsonMode.SHELL)
                .build();
        return document.toJson(settings);
    }

    private static void printСondition() {
        System.out.println("Вы можете воспользоваться следующими функциями: \n"
                + "1. Добавить в базу данных магазин. Для этого ввести команду: \n"
                + "set + название магазина. В одно слово, без пробела. Пример: setПятерочка\n"
                + "\n"
                + "2. Добавить в базу данных товар. Для этого ввести команду:   \n"
                + "add + название товара, в одно слово, без пробела. Затем укажите целое число — цену товара в рублях.\n"
                + "Пример: addвафли54\n"
                + "\n"
                + "3. Разместить определенный товар на продажу в магазине. Для этого ввести команду: \n"
                + "place + название товара и магазина через пробелы.\n"
                + "Пример: place вафли Пятерочка\n"
                + "\n"
                + "4. Команда statistics выведет статистику товаров по каждому магазину \n"
                + "5. Если Вы хотите прекратить выполнение программы, наберите команду: END ");

        LocalDateTime ldt = LocalDateTime.now ();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timeNow = "Сегодня: " + ldt.format(formatter);
        System.out.println(timeNow);

    }

    private static Map<String, Document> commandRecognition (String command){
        Map<String, Document> dataMap = new HashMap<>();

        if (command.matches(COMMAND_SET)) {
            Pattern pattern = Pattern.compile(COMMAND_SET);
            Matcher matcher = pattern.matcher(command);
            if (matcher.find()) {
                String storeName = matcher.group(2);
                Document store = new Document()
                        .append("storeName", storeName);
                dataMap.put("store", store);
            }
        }
        else if (command.matches(COMMAND_ADD)) {
            Pattern pattern = Pattern.compile(COMMAND_ADD);
            Matcher matcher = pattern.matcher(command);
            if (matcher.find()) {
                String productName = matcher.group(2);
                String productPrice = matcher.group(3);
                Document product = new Document()
                        .append("productName", productName)
                        .append("productPrice", productPrice);
                dataMap.put("product", product);
            }
        }
        else if (command.matches(COMMAND_PLACE)) {
            String[] delimiter = command.split(" ");
            String placeProductName = delimiter[1];
            String placeStoreName = delimiter[2];
            Document placeProductInStore = new Document()
                    .append("placeProductName", placeProductName)
                    .append("placeStoreName", placeStoreName);
            dataMap.put("placeProductInStore", placeProductInStore);
        }
        else if (command.matches(COMMAND_STATISTICS)) {
            statistics = true;
        }
        else if (command.matches(COMMAND_END)) {
            System.exit(0);
        }
        else {
            System.out.println("Команда не распознана. Введите еще раз.");
        }
        return dataMap;
    }
}
