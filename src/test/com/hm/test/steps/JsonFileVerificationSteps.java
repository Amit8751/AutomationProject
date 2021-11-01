package com.hm.test.steps;

import com.hm.framework.config.ConfigReader;
import com.hm.framework.utilities.DatabaseUtil;
import com.hm.framework.utilities.ExcelUtil;
import com.hm.framework.utilities.DBComparison;
import com.hm.framework.utilities.JsonFileUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.sql.SQLException;

public class JsonFileVerificationSteps {


    private JsonFileUtil jsonFileUtil=new JsonFileUtil();
    private DatabaseUtil databaseUtility=new DatabaseUtil();

    @When("^I read JSON file$")
    public void i_read_json_file() throws Throwable {
        System.out.println(jsonFileUtil.getJsonDataInMap());
        System.out.println("...JSON file data read successfully...");
    }


    @And("^I read data from database$")
    public void i_read_data_from_database() throws SQLException {
        System.out.println(databaseUtility.getSqlResultInMap());
        System.out.println("...Database data read successfully...");
    }

    @And("^I compare JSON file data with database data$")
    public void i_compare_json_file_data_with_database_data() throws Throwable {
        System.out.println("...Comparison of data is in progress...");
    }

    @Then("^JSON data and database data should be matched successfully$")
    public void json_data_and_database_data_should_be_matched_successfully() throws SQLException {
        DBComparison.jsonDataToDBDataComparison(jsonFileUtil.getJsonDataInMap(),databaseUtility.getSqlResultInMap());
        databaseUtility.closeConnection();

    }

    @And("^I insert JSON file data in database$")
    public void i_insert_json_file_data_in_database() throws Throwable {
        //databaseUtility.insertDataInDB();

    }

    @When("I read data from excel")
    public void iReadDataFromExcel() throws IOException {
        ExcelUtil excelUtil=new ExcelUtil(ConfigReader.getProperty("ExcelSheetPath"));
        System.out.println(excelUtil.getTestDataInMap());
        System.out.println("...Excel file data read successfully...");
    }

    @And("I compare Excel file data with database data")
    public void iCompareExcelFileDataWithDatabaseData() {
        System.out.println("...Comparison of data is in progress...");
    }

    @Then("Excel file data and database data should be matched successfully")
    public void excelFileDataAndDatabaseDataShouldBeMatchedSuccessfully() throws SQLException, IOException {
        ExcelUtil excelUtil = new ExcelUtil(ConfigReader.getProperty("ExcelSheetPath"));
        DBComparison.excelDataToDBDataComparison(excelUtil.getTestDataInMap(), databaseUtility.getSqlResultInMap());
        databaseUtility.closeConnection();
    }
}
