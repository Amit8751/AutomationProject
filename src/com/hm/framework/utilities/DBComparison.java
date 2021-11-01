package com.hm.framework.utilities;

import net.bytebuddy.agent.builder.AgentBuilder;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class DBComparison {

    public static void jsonDataToDBDataComparison(List<Map<String,String>> json, List<Map<String,String>> database){
        if(json.size()!=database.size()){
            if(json.size()>database.size()){
                System.out.println("...Size not matched...");
                json.removeAll(database);
                System.out.println("---Getting extra data from json file "+ json);
                Assert.fail("...Oops... Both Json file Data and Database data does not matched!");
            }
            else{
                System.out.println("...Size not matched...");
                database.removeAll(json);
                System.out.println("Getting extra data from database file"+database);
                Assert.fail("...Oops... Both Json file Data and Database data does not matched!");
            }
        }
        else if(json.size()==database.size()){
            Assert.assertEquals(json,database);
            System.out.println("... Json file data and database data matched successfully");
        }

    }

    public static void excelDataToDBDataComparison(List<Map<String,String>> excel, List<Map<String,String>> database){
        if(excel.size()!=database.size()){
            if(excel.size()>database.size()){
                System.out.println("...Size not matched...");
                excel.removeAll(database);
                System.out.println("---Getting extra data from excel file "+ excel);
                Assert.fail("...Oops... Both Excel file Data and Database data does not matched!");
            }
            else{
                System.out.println("...Size not matched...");
                database.removeAll(excel);
                System.out.println("Getting extra data from database file"+database);
                Assert.fail("...Oops... Both Excel file Data and Database data does not matched!");
            }
        }
        else if(excel.size()==database.size()){
            Assert.assertEquals(excel,database);
            System.out.println("... Excel file data and database data matched successfully");
        }

    }

}

