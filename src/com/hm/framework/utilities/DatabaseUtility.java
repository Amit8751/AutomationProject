package com.hm.framework.utilities;

import com.hm.framework.config.ConfigReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DatabaseUtility {

    private static Connection connection;
    private static List<Map<String,String>> testDataAllRows=null;
    private static TreeMap testData=null;
    public static JsonFileUtil jsonFileUtil=new JsonFileUtil();

    public DatabaseUtility(){

        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName(ConfigReader.getProperty("DB_Driver"));

        } catch (Exception ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public static Connection getConnection() {
        try {
            //connection = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6446631","sql6446631","Uf9j6TDB5J");
            connection=DriverManager.getConnection(ConfigReader.getProperty("DB_ConnectionString"), ConfigReader.getProperty("DB_User"), ConfigReader.getProperty("DB_Password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static List<Map<String,String>> getSqlResultInMap() throws SQLException {

        DatabaseUtility databaseUtil=new DatabaseUtility();
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

    public static void insertDataInDB() throws SQLException {

        DatabaseUtility databaseUtil=new DatabaseUtility();
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

    public static void closeConnection(){

        try{
            if (connection != null) {
                connection.close();
                System.out.println("DB Connections has been Closed!!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }     finally {
            // statement.close(); // this is my line: 56 (im getting null pointer here)
            // connection.close();
            System.out.println("DB Connections has been Closed!! Finally");
        }


    }
}
