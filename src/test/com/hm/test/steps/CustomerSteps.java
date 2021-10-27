package com.hm.test.steps;

import com.hm.framework.base.Base;
import com.hm.framework.base.DriverContext;
import com.hm.framework.utilities.CucumberUtil;
import com.hm.framework.utilities.Log;
import com.hm.test.pages.HomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.util.List;

public class CustomerSteps extends Base {

    private static Logger logger = LogManager.getLogger(Log.class.getName());

    @And("I Ensure application is opened successfully")
    public void i_ensure_application_is_opened_successfully() throws Throwable {

        CurrentPage = GetInstance(HomePage.class);
        Assert.assertTrue("The HomePage is not Loaded", CurrentPage.As(HomePage.class).IsHomeButtonPresent());
        logger.info("The Test Application Opened Successfully.");

    }

    @When("I click on Bank Manager Login Button")
    public void i_click_on_bank_manager_login_button() throws Throwable {


        CurrentPage.As(HomePage.class).clickBankManagerLoginButton();
        Thread.sleep(2000);
        logger.info("The Bank Manager Login Button is clicked Successfully");
    }


    @Then("I click on Add Customer Button")
    public void i_click_on_add_customer_button() throws Throwable  {
        CurrentPage.As(HomePage.class).clickAddCustomerButton();
        logger.info("The Add Customer Button has been Clicked Successfully");
        Thread.sleep(2000);
    }


    @When("I enter First_Name Last_Name And Post Code")
    public void i_enter_first_name_last_name_and_post_code(DataTable table) throws Throwable {

        CucumberUtil.createDataTableToDict(table);
        CurrentPage.As(HomePage.class).enterFistNameAndLastName(CucumberUtil.getCellValue("First_Name"),CucumberUtil.getCellValue("Last_Name"),CucumberUtil.getCellValue("Post_Code"));
        logger.info("The first name and last name has been enter successfully");
        Thread.sleep(2000);
    }

    @And("I click on Add Customer Button Below")
    public void i_click_on_add_customer_button_below() throws Throwable {
        CurrentPage.As(HomePage.class).clickAddCustButtonBelow();
        Thread.sleep(2000);
        logger.info("The Add Customer button has been clicked Successfully.");

    }


    @Then("I Should see the customer should be added Successfully")
    public void i_should_see_the_customer_should_be_added_successfully() throws Throwable{

        String [] Alrt = DriverContext.Browser.getAlertText().split(":");
        logger.info("The Alert Message is : " +Alrt[0]);
        DriverContext.Browser.acceptAlert();
        Thread.sleep(2000);
        logger.info("The Customer has been Added Successfully.");
        Assert.assertEquals("Customer added successfully with customer id",Alrt[0].trim());

    }

    @Given("The Bank Manager is Logged Successfully")
    public void the_bank_manager_is_logged_successfully() throws Exception {

        CurrentPage.As(HomePage.class).clickBankManagerLoginButton();
        Thread.sleep(2000);
        logger.info("The Bank Manager Login Button is clicked Successfully");

    }

    @When("I click on Customers Button")
    public void i_click_on_customers_button() throws Exception {

         boolean flag =  CurrentPage.As(HomePage.class).IsCustomersButton();
        CurrentPage.As(HomePage.class).clickCustomersButton();
        Thread.sleep(2000);
         Assert.assertTrue("The Customers button clicked Successfully" ,flag );
        if (flag) {
            logger.info("The Customers Button is clicked Successfully");
        } else {
            logger.info("The Customers Button is not Present or not clickable");
        }


    }

    @Then("I should see the search customer text box")
    public void i_should_see_the_search_customer_text_box() throws Exception {
        boolean flag =  CurrentPage.As(HomePage.class).IsSearchCustomer();
        Thread.sleep(2000);
        Assert.assertTrue("The Customer Search Box present" ,flag );

        if (flag) {
            logger.info("The Search Customer Text Box is Present");
        } else {
            logger.info("The Search Customer Text Boc is not Present or not Enabled");
        }

    }

    @When("I enter first_name in search field as {string}")
    public void i_enter_first_name_in_search_field_as(String string) throws Exception {

        CurrentPage.As(HomePage.class).searchCustomer(string);
        Thread.sleep(2000);
        logger.info("The Customers First Name Entered Successfully");
    }

    @Then("Customer details should be visible Successfully")
    public void customer_details_should_be_visible_successfully() throws Exception {

        boolean flag =  CurrentPage.As(HomePage.class).IsSearchCustomer();
        Thread.sleep(2000);
        Assert.assertTrue("The Customer Search Box present" ,flag );

    }


}
