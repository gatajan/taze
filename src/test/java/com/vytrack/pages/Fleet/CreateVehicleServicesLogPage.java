package com.vytrack.pages.Fleet;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateVehicleServicesLogPage extends VehicleServiceLogsPage {

    @FindBy (xpath = "//span[.='Choose a value...']")
    private WebElement serviceTypeElement;

    @FindBy(css = "[id^='custom_entity_type_TotalPrice']")
    private WebElement totalPriceElement;

    @FindBy(name = "custom_entity_type[OdometerDetails]")
    private WebElement odometerValueElement;

    @FindBy(css = "[id^=\"date_selector_custom_entity_type_Date\"]")
    private WebElement chooseDateElement;

    @FindBy(css = "[id^='custom_entity_type_Purchaser']")
    private WebElement purchaserElement;

    @FindBy(name = "custom_entity_type[Vendor]")
    private WebElement vendorElement;

    @FindBy(name = "custom_entity_type[InvoiceReference]")
    private WebElement invoiceReferenceElement;

    @FindBy(name = "custom_entity_type[Notes]")
    private WebElement notesElement;

    @FindBy(xpath = "//button[@type=\"submit\" and @class=\"btn btn-success action-button\"]")
    private WebElement saveAndCloseElement;

    @FindBy(css = "[id^=\"custom_entity_type_Test\"]>div>div button")
    private WebElement addVehicleModelElement;

    @FindBy(css = "[id^=\"custom_entity_type_License_Plate\"]>div>div button")
    private WebElement addLicensePlateElement;







    public void selectServiceType(String serviceType) {
        BrowserUtils.waitForClickability(serviceTypeElement, 15);
        serviceTypeElement.click();

        //Select select = new Select(serviceTypeElement);

        String locator = "//*[@class='select2 select2-offscreen']/option[.='" + serviceType+ "']";
        WebElement dropDown = Driver.getDriver().findElement(By.xpath(locator));
        //select.selectByVisibleText(serviceTypeName);
        BrowserUtils.waitForClickability(dropDown, 15);
        dropDown.click();
    }

    public void totalPriceInput(String price) {
        BrowserUtils.waitForVisibility(totalPriceElement, 15);
        totalPriceElement.sendKeys(price);
    }

    public void odometerValueInput(String odometerReading) {
        BrowserUtils.waitForVisibility(odometerValueElement, 15);
        odometerValueElement.sendKeys(odometerReading);
    }

    public void dateInput() {
        BrowserUtils.waitForVisibility(chooseDateElement, 15);
        BrowserUtils.waitForClickability(chooseDateElement, 15);
        chooseDateElement.click();
        WebElement todayButton = Driver.getDriver().findElement(By.xpath("//button[.='Today']"));
        BrowserUtils.waitForClickability(todayButton, 15);
        todayButton.click();

        //WebElement selectMonth = Driver.getDriver().findElement(By.xpath("//select[@class=\"ui-datepicker-month\"]"));
        //WebElement selectYear = Driver.getDriver().findElement(By.xpath("//select[@class=\"ui-datepicker-year\"]"));

        //Select select = new Select(selectMonth);
        //select.selectByVisibleText(month);

        //Select selectY = new Select(selectYear);
        //selectY.selectByVisibleText(year);

        //JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        //js.executeScript("arguments[0].scrollIntoView(true)", selectY.selectByVisibleText(year))

    }


    public void purchaserInput(String purchaserName) {
        BrowserUtils.waitForVisibility(purchaserElement, 15);
        purchaserElement.sendKeys(purchaserName);
    }

    public void vendorInput(String vendorName) {
        BrowserUtils.waitForVisibility(vendorElement, 15);
        vendorElement.sendKeys(vendorName);
    }

    public void invoiceReferenceInput(String invoiceReference) {
        BrowserUtils.waitForVisibility(invoiceReferenceElement, 15);
        invoiceReferenceElement.sendKeys(invoiceReference);
    }

    public void notesInput(String notes) {
        BrowserUtils.waitForVisibility(notesElement, 15);
        notesElement.sendKeys(notes);
    }

    public void clickToAddVehicleModel() {
        BrowserUtils.waitForVisibility(addVehicleModelElement, 15);
        BrowserUtils.waitForClickability(addVehicleModelElement, 15);
        addVehicleModelElement.click();
    }

    public void clickToAddLicensePlate() {
        BrowserUtils.waitForVisibility(addLicensePlateElement, 15);
        BrowserUtils.waitForClickability(addLicensePlateElement, 15);
        addLicensePlateElement.click();
    }

    public void clickSaveAndClose() {
        BrowserUtils.waitForVisibility(saveAndCloseElement, 15);
        BrowserUtils.waitForClickability(saveAndCloseElement, 15);
        saveAndCloseElement.click();
    }

}
