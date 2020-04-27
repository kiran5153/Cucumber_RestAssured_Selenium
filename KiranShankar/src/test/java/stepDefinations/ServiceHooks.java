package stepDefinations;

import io.cucumber.java.*;
import utilities.TestBase;

public class ServiceHooks extends TestBase {

    @Before ("@front-end")
    public void setup() {
        openBrowser();
    }

    @After("@front-end")
    public void tearDown() {
        closeBrowser();
    }

}
