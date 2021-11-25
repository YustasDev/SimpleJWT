
import java.sql.*;

public class DBConnection {

  private static Connection connection;

  private static String url = "jdbc:mysql://localhost:3306/learn?useSSL=false&serverTimezone=Europe/Moscow&useUnicode=true&characterEncoding=utf8";
  private static String user = "root";
  private static String password = "testtest";
  private static StringBuilder insertQuerry = new StringBuilder();

  public static Connection getConnection() {
    if (connection == null) {
      try {
        connection = DriverManager.getConnection(url, user, password);
        connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
        connection.createStatement().execute("CREATE TABLE voter_count(" +
            "id INT NOT NULL AUTO_INCREMENT, " +
            "name TINYTEXT NOT NULL, " +
            "birthDate DATE NOT NULL, " +
            "`count` INT NOT NULL, " +
            "PRIMARY KEY(id))");
      //      "UNIQUE KEY name_date(name(50), birthDate))");
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return connection;
  }

  public static void executeMultyInsert() throws SQLException {
    String sql = "INSERT INTO voter_count(name, birthDate, count)" +
        "VALUE" + XMLHandler.getInsertQuerry().toString();

     //   "ON DUPLICATE KEY UPDATE count = count + 1";
    DBConnection.getConnection().createStatement().execute(sql);

  }

  public static void printVoterCounts() throws SQLException {
    String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
    ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
    while (rs.next()) {
      System.out.println("\t" + rs.getString("name") + " (" +
          rs.getString("birthDate") + ") - " + rs.getInt("count"));
    }
    rs.close();
  }

  public static void createDuplicatesVotersTable() throws SQLException{
    String createSql = "CREATE table dupVoters AS SELECT * FROM voter_count WHERE name IN (SELECT name FROM voter_count GROUP BY name HAVING COUNT(*) > 1)"
        + " and birthDate IN (SELECT birthDate FROM voter_count GROUP BY birthDate HAVING COUNT(*) > 1) ORDER BY name;";
    ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(createSql);
    rs.close();
  }
}