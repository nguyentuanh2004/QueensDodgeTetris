package data;

import java.sql.*;

public class MyJDBC {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/new_schema",
                    "root",
                    "anh223630670$"
            );
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}