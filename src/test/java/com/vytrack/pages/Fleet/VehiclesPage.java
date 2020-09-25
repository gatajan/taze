package com.vytrack.pages.Fleet;

import com.vytrack.pages.BasePage;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VehiclesPage extends BasePage {

    @FindBy(partialLinkText = "Create Car")
    private WebElement createCar;

    public void clickToCreateCar() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 40);
        wait.until(ExpectedConditions.elementToBeClickable(createCar)).click();
    }
}
