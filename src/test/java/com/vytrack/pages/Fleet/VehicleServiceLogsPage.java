package com.vytrack.pages.Fleet;

import com.vytrack.pages.BasePage;
import com.vytrack.utilities.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VehicleServiceLogsPage extends BasePage {

    @FindBy (css = "[title='Create Vehicle Services Logs']")
    private WebElement createVehicleServiceLogs;

    public void clickToCreateVehicleServicesLog() {
        BrowserUtils.waitForVisibility(createVehicleServiceLogs, 20);
        BrowserUtils.waitForClickability(createVehicleServiceLogs, 20);
        createVehicleServiceLogs.click();


    }
}
