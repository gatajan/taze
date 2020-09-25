package com.vytrack.pages;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.NoSuchElementException;

public abstract class BasePage {
    @FindBy(xpath="/html/body/div[4]")
    public WebElement loaderMask;

    @FindBy(css = "h1[class='oro-subtitle']")
    public WebElement pageSubTitle;

    @FindBy(css = "#user-menu > a")
    public WebElement userName;

    @FindBy(linkText = "Logout")
    public WebElement logOutLink;

    @FindBy(linkText = "My User")
    public WebElement myUser;


   public BasePage() {
     PageFactory.initElements(Driver.getDriver(), this);
   }

    public void navigateTo(String moduleName, String subModuleName) {
        //"normalize-space()" is used to remove spaces otherwise we can use "text()" only.
        String moduleLocator = "//*[normalize-space()='" + moduleName + "' and @class=\"title title-level-1\"]";
        String subModuleLocator = "//*[normalize-space()='"+ subModuleName + "' and @class=\"title title-level-2\"]";

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 50);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(moduleLocator)));

        WebElement module = Driver.getDriver().findElement(By.xpath(moduleLocator));

        wait.until(ExpectedConditions.visibilityOf(module));
        Actions action = new Actions(Driver.getDriver());
        action.moveToElement(module).perform();

        WebElement subModule = Driver.getDriver().findElement(By.xpath(subModuleLocator));
        wait.until(ExpectedConditions.visibilityOf(subModule));
        //BrowserUtils.clickWithWait(subModule); If Click is not working well.
        subModule.click();
        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        //BrowserUtils.waitForPageLoad(15); It waits until page loads and ajax calls are done. Use in case Loader Mask does not work.
    }

    public boolean waitUntilLoaderMaskDisappear() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 50);

        try{
            wait.until(ExpectedConditions.invisibilityOf(loaderMask));
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("Loader mask not found");
            System.out.println(e.getMessage());
            return true;

        } catch (WebDriverException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public String getPageSubTitle() {
        waitUntilLoaderMaskDisappear();
        BrowserUtils.waitForStaleElement(pageSubTitle);
        return pageSubTitle.getText();
    }

    public String getUserName() {
        waitUntilLoaderMaskDisappear();
        BrowserUtils.waitForVisibility(userName, 5);
        return userName.getText();
    }

    public void logOut() {
        BrowserUtils.Wait(5);
        BrowserUtils.clickWithJS(userName);
        BrowserUtils.clickWithJS(logOutLink);
    }

    public void goToMyUser() {
        waitUntilLoaderMaskDisappear();
        BrowserUtils.waitForClickability(userName, 5).click();
        BrowserUtils.waitForClickability(myUser, 5).click();
    }

    public void waitForPageSubTitle(String pageSubTitleText) {
        new WebDriverWait(Driver.getDriver(), 40).until(ExpectedConditions.textToBe(By.cssSelector("h1[class='oro-subtitle']"), pageSubTitleText));
    }
}
