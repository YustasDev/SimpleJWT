package jdbsPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {

    public static void main(String[] args) {

      String url = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=Europe/Moscow&useUnicode=true&amp;characterEncoding=utf8";
      String user = "root";
      String password = "testtest";

      try {
        Connection connection = DriverManager.getConnection(url, user, password);

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM Courses");
        while (resultSet.next()) {

          String courseName = resultSet.getString("name");
          System.out.println(courseName);
        }
        resultSet.close();
        statement.close();
        connection.close();

      } catch (SQLException ex) {
        ex.printStackTrace();
      }


    }
  }
