package jdbsPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {

  public static void main(String[] args) {
    String url = "jdbc:mysql://localhost:3306/skillbox1?useSSL=false&serverTimezone=Europe/Moscow&useUnicode=true&characterEncoding=utf8";
    String user = "root";
    String password = "testtest";

    try {
      Connection connection = DriverManager.getConnection(url, user, password);

      Statement statement = connection.createStatement();

      ResultSet resultSet = statement.executeQuery(
          "SELECT course_name, cnt/sales_period as AVGMONTHSALES FROM (SELECT course_name, YEAR(subscription_date),"
              + " (MONTH(max(subscription_date)) - month(min(subscription_date))+1) as sales_period, COUNT(*) AS cnt "
              + "FROM purchaselist GROUP BY 1) foo GROUP BY 1;");

      System.out.println("Среднее количество покупок в месяц для каждого курса за 2018 год" + "\n");

      while (resultSet.next()) {

        String courseName = resultSet.getString("course_name");
        String avg_month_sales = resultSet.getString("AVGMONTHSALES");
        System.out.println(courseName + "    " + avg_month_sales);
      }
      resultSet.close();
      statement.close();
      connection.close();

    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }
}
