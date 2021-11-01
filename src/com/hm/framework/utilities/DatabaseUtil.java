package com.hm.framework.utilities;


import com.hm.framework.config.ConfigReader;
import com.hm.framework.config.Settings;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class DatabaseUtil {

    private static List<Map<String,String>> testDataAllRows=null;
    private static TreeMap testData=null;
   // public static JsonFileUtil jsonFileUtil=new JsonFileUtil();
    private static DatabaseUtil instance;
    private static Connection connection;
    private  static Statement statement = null;
    public static JsonFileUtil jsonFileUtil=new JsonFileUtil();
    public DatabaseUtil()  {
        try {
            Class.forName(ConfigReader.getProperty("DB_Driver"));
            //this.connection = DriverManager.getConnection(Settings.AUT_DB_ConnectionString, Settings.AUT_DB_User, Settings.AUT_DB_Password);
            connection=DriverManager.getConnection(ConfigReader.getProperty("DB_ConnectionString"), ConfigReader.getProperty("DB_User"), ConfigReader.getProperty("DB_Password"));
        } catch (Exception ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {

        return connection;
    }

    public static DatabaseUtil getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseUtil();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseUtil();
        }

        return instance;
    }


    public static void executeQueryWithOutParam(String query, Connection connection) throws SQLException {


       // Statement statement = null;

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

    public static void main(String []args) throws SQLException {

        DatabaseUtil databaseUtil=new DatabaseUtil();
        connection=databaseUtil.getConnection();
        PreparedStatement pstmt =null;

        try {
            pstmt = connection.prepareStatement("INSERT INTO MyPlayers values (?, ?, ?, ?, ?, ? )");

            JSONArray jsonArray = jsonFileUtil.getJsonDataArray();

            for (Object object : jsonArray) {
                JSONObject record = ( JSONObject ) object;
                int id = Integer.parseInt((String) record.get("ID"));;
                String first_name = ( String ) record.get("First_Name");
                String last_name = ( String ) record.get("Last_Name");
                String date = (String) record.get("Date_Of_Birth");
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



