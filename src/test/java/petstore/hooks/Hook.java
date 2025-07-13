package petstore.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import petstore.base_url.BaseUrl;

public class Hook {

    @Before
    public void setUp() throws Exception {
        // Hooks classtaki @Before methodu herbir scenariodan önce çalışır
    }

    @Before
    public void setupApiSpec() {
        BaseUrl.settingup();
    }

    @Before("@UITest") // Only for scenarios tagged with @UITest
    public void setUp2() throws Exception {
        // Sadece UITest tagına sahip olan scenariolardan ÖNCE çalışır
    }

    @After("@UITest") // Only for scenarios tagged with @UITest
    public void tearDown2() throws Exception {
        // Sadece UITest tagına sahip olan scenariolardan SONRA çalışır
    }

    @After
    public void tearDown(Scenario scenario) throws Exception {
        // For API tests, we don't need driver cleanup
        if (scenario.isFailed()) {
            System.out.println("Scenario failed: " + scenario.getName());
            System.out.println("Status: " + scenario.getStatus());
        }
    }
}