import java.io.IOException;
import java.util.List;

public class Main {

    private static final String RESOURCES = "src/main/resources/";
    static String fileName = "besimple.jpg";
    static String filePath = RESOURCES + fileName;

    static String fileName1 = "besimple1.jpg";
    static String filePath1 = RESOURCES + fileName1;


    public static void main(String[] args) {

        DocumentManagementSystem loadApp = new DocumentManagementSystem();
        try {
            loadApp.importFile(filePath);
            loadApp.importFile(filePath1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadApp.search("file:besimple").stream().forEach(System.out::println);

        }

    }

