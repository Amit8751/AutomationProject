package com.hm.framework.config;

import com.hm.framework.base.BrowserType;

import java.sql.Connection;

public class Settings {

    //For Application Backend
    public static Connection AUTConnection;
    //For EARS Reporting
    public static Connection ReportingConnection;
    //For Application backend connection string
    public static String AUT_DB_ConnectionString;
    public static String ReportingConnectionString;
    //Log Path for framework
    public static String LogPath;
    //Driver Type for SQL Server connectivity
    public static String DB_Driver;
    public static String ExcelSheetPath;
    public static String AUT;
    public static BrowserType BrowserType;
    public static String AUT_DB_User;
    public static String AUT_DB_Password;



}
