package com.vytrack.pages.Fleet;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import com.vytrack.utilities.ExcelUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;

public class CreateCarPage extends VehiclesPage {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
        BrowserUtils.Wait(3);
        driver.findElement(By.xpath("//*[@id=\"at-cv-lightbox-button-holder\"]/a[2]")).click();
    }

    @FindBy(css = "[id^='custom_entity_type_LicensePlate']")
    public WebElement licensePlateElement;

    @FindBy(css = "[id^='custom_entity_type_Driver']")
    public WebElement driverElement;

    @FindBy(css = "[id^='custom_entity_type_Location']")
    public WebElement locationElement;

    @FindBy(css = "[id^='custom_entity_type_ModelYear']")
    public WebElement modelYearElement;

    @FindBy(css = "[id^='custom_entity_type_Color']")
    public WebElement colorElement;

    @FindBy(xpath = "//div/ul/li[1]/button")
    public WebElement saveAndCloseButtonElement;

    @FindBy(xpath ="//li[2]//button[@class='main-group action-button dropdown-item']")
    public WebElement saveAndNew;



/*    ExcelUtil excelUtil = new ExcelUtil("cars.xlsx", "cars");
    //Read data from excel spreadsheet as list of map
    List<Map<String, String>> testData = excelUtil.getDataList();

    public void enterLicensePlate() {
        licensePlateElement.sendKeys(testData.get(0).get("License Plate"));
    }

    public void enterDriver() {
        driverElement.sendKeys(testData.get(0).get("Driver"));
    }

    public void enterModelYear() {
        modelYearElement.sendKeys(testData.get(0).get("Model Year"));
    }

    public void enterColor() {
        colorElement.sendKeys(testData.get(0).get("Color"));
    }*/

    public void saveAndClose() {
        Driver.getDriver().findElement(By.cssSelector("a[class=\"btn-success btn dropdown-toggle\"]")).click();
        BrowserUtils.Wait(2);
        saveAndCloseButtonElement.click();
    }
    public void saveAndNew() {
        Driver.getDriver().findElement(By.cssSelector("a[class=\"btn-success btn dropdown-toggle\"]")).click();
        BrowserUtils.Wait(2);
        saveAndNew.click();
    }




}
