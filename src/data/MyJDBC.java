package data;

import java.sql.*;

public class MyJDBC {
    private Connection connection;
    public MyJDBC() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/new_schema",
                    "root",
                    "anh223630670$"
            );
            /*
            Statement statement = connection.createStatement();
            String query  = "INSERT INTO `new_schema`.`users` (`playername`, `score`) VALUES (?, ?);";
            PreparedStatement insert = connection.prepareStatement(query);
            insert.setString(1, "tuanh");
            insert.setString(2, "20");
            insert.executeUpdate();
            //int rowsInserted = statement.executeUpdate(query);
            //System.out.println(rowsInserted + " row(s) inserted.");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("playername"));
                System.out.println(resultSet.getInt("score"));
            }

             */
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void insertToDatabase(String playerName, int score) {
        try {

            String query = "INSERT INTO `new_schema`.`users` (`playername`, `score`) VALUES (?, ?);";
            PreparedStatement insert = connection.prepareStatement(query);
            insert.setString(1, playerName);
            insert.setString(2, String.valueOf(score));
            insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void printResult() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("playername"));
                System.out.println(resultSet.getInt("score"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        MyJDBC myJDBC = new MyJDBC();
        myJDBC.insertToDatabase("Phuong", 7);
        myJDBC.printResult();
    }
}
