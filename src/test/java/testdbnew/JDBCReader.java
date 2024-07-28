package testdbnew;

import models.Teachers;
import org.testng.annotations.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
public class JDBCReader {
    private final static String URL = "jdbc:postgresql://localhost:4567/rd";
    private final static String USER = "rd";
    private final static String PASSWORD = "rd";
    private final static String QUERY = "select * from teachers where id =?";
    private final static String QUERY_INSERT = "insert into teachers values(?,?,?)";
    private final static String QUERY_UPDATE = "update teachers set firstName=? where id=?";
    private final static String QUERY_DELETE = "delete from teachers where id=?";

    public static List<Teachers> getTeachersFromDB() {
        List<Teachers> teachers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setInt(1, 3);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Teachers teacher = new Teachers(resultSet.getInt("id"), resultSet.getString("firstName"),
                        resultSet.getInt("age"));
                teachers.add(teacher);
            }

        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please check url, name, password"));
        }
        return teachers;
    }



    public static void insertTeacherName(int id, String name, int age){
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);
            preparedStatement.setInt(1, 8);
            preparedStatement.setString(2,"Jason");
            preparedStatement.setInt(3, 84);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("New user has been added");
        }
    }


    public static void updateTeacherName(String name, int id){
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE);
            preparedStatement.setString(1,"Nick");
            preparedStatement.setInt(2, 2);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Name is updated");
        }
    }

    public static void deleteTeacherId(int id){
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
            preparedStatement.setInt(1, 8);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("newly added id is deleted");
        }
    }



    public static void main(String[] args) {
        List<Teachers> result = getTeachersFromDB();
        result.forEach(System.out::println);
        insertTeacherName(8, "Jason", 84);
        updateTeacherName("Nick", 2);
        deleteTeacherId(8);
    }

}



