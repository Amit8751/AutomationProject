package utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class DatabaseUtil {

    private static DatabaseUtil instance;
    private static Connection connection;

    public static JsonFileUtil jsonFileUtil=new JsonFileUtil();
    // jsonFileUtil=new JsonFileUtil();
    private static List<Map<String,String>> testDataAllRows=null;
    private static TreeMap testData=null;

    public DatabaseUtil() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public static Connection getConnection() {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6446631","sql6446631","Uf9j6TDB5J");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void executeQuery() throws SQLException {

        DatabaseUtil databaseUtil=new DatabaseUtil();
        connection=databaseUtil.getConnection();
        Statement statement = null;
        String query="SELECT * FROM MyPlayers";
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
                System.out.println(resultSet.getInt(1) + "  " + resultSet.getString(2) + "  " + resultSet.getString(3));
            // con.close();
            System.out.println("Executed the Query !!");
        } catch (Exception e) {

        }


    }

    public static List<Map<String,String>> getSqlResultInMap() throws SQLException {

        DatabaseUtil databaseUtil=new DatabaseUtil();
        connection=databaseUtil.getConnection();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM MyPlayers");
        System.out.println("Executed the Query !!");
        ResultSetMetaData md=resultSet.getMetaData();

        List list=new ArrayList();

        for(int i=1;i<=md.getColumnCount();i++){
            list.add(md.getColumnName(i));

        }

        testDataAllRows=new ArrayList<Map<String, String>>();
        while(resultSet.next()){
            testData=new TreeMap<String, String>();
            for(int i=1;i<=md.getColumnCount();i++){
                testData.put(md.getColumnName(i), resultSet.getString(i));

            }
            testDataAllRows.add(testData);
        }
        return  testDataAllRows;
    }

    public static void insertDataInDB(String query) throws SQLException {

        DatabaseUtil databaseUtil=new DatabaseUtil();
        connection=databaseUtil.getConnection();
        PreparedStatement pstmt =null;

        try {
            pstmt = connection.prepareStatement("INSERT INTO MyPlayers values (?, ?, ?, ?, ?, ? )");

            JSONArray jsonArray = jsonFileUtil.getJsonDataArray();

            for (Object object : jsonArray) {
                JSONObject record = ( JSONObject ) object;
                int id = Integer.parseInt(( String ) record.get("ID"));
                String first_name = ( String ) record.get("First_Name");
                String last_name = ( String ) record.get("Last_Name");
                String date = ( String ) record.get("Date_Of_Birth");
                long date_of_birth = Date.valueOf(date).getTime();
                String place_of_birth = ( String ) record.get("Place_Of_Birth");
                String country = ( String ) record.get("Country");

                pstmt.setInt(1, id);
                pstmt.setString(2, first_name);
                pstmt.setString(3, last_name);
                pstmt.setDate(4, new Date(date_of_birth));
                pstmt.setString(5, place_of_birth);
                pstmt.setString(6, country);
                pstmt.executeUpdate();

            }
            System.out.println("Records inserted Successfully....");

        }catch(SQLException e){
            e.printStackTrace();
        }

    }


}
