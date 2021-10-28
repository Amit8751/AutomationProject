package test.com.hm.test.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//import org.junit.Test;


//@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/com/hm/test/features/"},glue = {"com.hm.test.steps"},  plugin = {"pretty",
        "json:target/MyReports/report.json",
          "junit:target/MyReports/report.xml",
            "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        dryRun = false,
        monochrome = false)
public class TestRunner extends AbstractTestNGCucumberTests {


//    private TestNGCucumberRunner testNGCucumberRunner;
//
//    @BeforeClass(alwaysRun = true)
//    public void setUpCucumber() throws Exception {
//        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
//    }
//
//    @Test(groups = "cucumber",description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
//            public void runScenario( ) throws Throwable{
//        testNGCucumberRunner.runScenario(abc.);
//    }
//
//    @DataProvider(name = "dataProvider")
//    public Object[][] scenarios() {
//        return testNGCucumberRunner.provideScenarios();
//    }
//
//    @AfterClass(alwaysRun = true)
//    public void tearDownClass() throws Exception {
//        testNGCucumberRunner.finish();
//    }


//    @Test
//    public void runTests()
//    {
//        new TestNGCucumberRunner(getClass());
//    }



}
