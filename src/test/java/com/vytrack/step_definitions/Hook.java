package com.vytrack.step_definitions;
import com.vytrack.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class Hook {
    @Before
    public void setup() {
        System.out.println("Test setup");
        Driver.getDriver().manage().window().maximize();
    }
    @After
    public void tearDown(Scenario scenario) {
        if(scenario.isFailed()) {
            System.out.println("Test Failed!");
            //We will take a screenshot and attach it to the report later
            byte[] screenshot = ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image.png", scenario.getName());
        } else {
            System.out.println("Cleanup!");
            System.out.println("Test completed");
        }
        System.out.println("=============================");
        Driver.close();
    }
}
