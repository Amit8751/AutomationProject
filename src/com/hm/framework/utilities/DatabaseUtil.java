package com.hm.framework.utilities;


import com.hm.framework.config.Settings;

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

    private DatabaseUtil() throws SQLException {
        try {
            Class.forName(Settings.DB_Driver);
            this.connection = DriverManager.getConnection(Settings.AUT_DB_ConnectionString, Settings.AUT_DB_User, Settings.AUT_DB_Password);
        } catch (ClassNotFoundException ex) {
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


    public static List<Map<String,String>> getSqlResultInMap(String query, Connection connection)  {


        try {
            statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  testDataAllRows;
    }

}



