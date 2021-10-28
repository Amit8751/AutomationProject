package test.com.hm.test.steps;

import com.hm.framework.config.ConfigReader;
import com.hm.framework.utilities.DatabaseUtility;
import com.hm.framework.utilities.ExcelUtil;
import com.hm.framework.utilities.JsonDBComparison;
import com.hm.framework.utilities.JsonFileUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


import java.io.IOException;

public class JsonFileVerificationSteps {


    private JsonFileUtil jsonFileUtil=new JsonFileUtil();
    private DatabaseUtility databaseUtility=new DatabaseUtility();

    @When("^I read JSON file$")
    public void i_read_json_file() throws Throwable {
        System.out.println(jsonFileUtil.getJsonDataInMap());
        System.out.println("...JSON file data read successfully...");
    }


    @And("^I read data from database$")
    public void i_read_data_from_database() throws Throwable {
        System.out.println(databaseUtility.getSqlResultInMap());
        System.out.println("...Database data read successfully...");
    }

    @And("^I compare JSON file data with database data$")
    public void i_compare_json_file_data_with_database_data() throws Throwable {
        System.out.println("...Verification is in progress...");
    }

    @Then("^JSON data and database data should be matched successfully$")
    public void json_data_and_database_data_should_be_matched_successfully() throws Throwable {
        JsonDBComparison.jsonDataToDBDataComparison(jsonFileUtil.getJsonDataInMap(),databaseUtility.getSqlResultInMap());
        System.out.println("... Both the data matched successfully");
        databaseUtility.closeConnection();
    }

    @And("^I insert JSON file data in database$")
    public void i_insert_json_file_data_in_database() throws Throwable {
        databaseUtility.insertDataInDB();

    }

    @And("I insert JSON file data in excel file")
    public void iInsertJSONFileDataInExcelFile() throws IOException {
         ExcelUtil excelUtil=new ExcelUtil(ConfigReader.getProperty("ExcelSheetPath"));
         excelUtil.setCellData(jsonFileUtil.getJsonDataInMap());
    }

    @And("I read data from excel")
    public void iReadDataFromExcel() throws IOException {
        ExcelUtil excelUtil=new ExcelUtil(ConfigReader.getProperty("ExcelSheetPath"));
        excelUtil.getTestDataInMap();
    }

    @And("I compare JSON file data with excel data")
    public void iCompareJSONFileDataWithExcelData() {
        System.out.println("...Verification is in progress...");
    }

    @Then("JSON data and excel data should be matched successfully")
    public void jsonDataAndExcelDataShouldBeMatchedSuccessfully() throws IOException {
        ExcelUtil excelUtil=new ExcelUtil(ConfigReader.getProperty("ExcelSheetPath"));
        Assert.assertEquals(jsonFileUtil.getJsonDataInMap(),excelUtil.getTestDataInMap());
    }
}
