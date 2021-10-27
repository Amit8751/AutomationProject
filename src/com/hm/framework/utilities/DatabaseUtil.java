package com.hm.framework.utilities;


import com.hm.framework.config.Settings;

import java.sql.*;


public class DatabaseUtil {

    private static DatabaseUtil instance;
    private static Connection connection;

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


        Statement statement = null;

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

}



