package petstore.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import petstore.utilities.Driver;

public class Hook {@Before
public void setUp() throws Exception {
    //Hooks classtaki @Before methodu herbir scenariodan önce çalışır
}

    @Before("x")
    public void setUp2() throws Exception {
        //Sadece x tagına sahip olan scenariolardan ÖNCE çalışır
    }

    @After("y")
    public void tearDown2() throws Exception {
        //Sadece y tagına sahip olan scenariolardan SONRA çalışır

    }

    @After
    public void tearDown(Scenario scenario) throws Exception {
        if (scenario.isFailed()) {//eğer scenario fail olursa çalışır.
            TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
            scenario.attach(ts.getScreenshotAs(OutputType.BYTES), "image/png", "scenario_" + scenario.getName());
            Driver.closeDriver();
        }
    }


}





