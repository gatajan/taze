package com.vytrack.pages;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    //Constructor is necessary to use "@FindBy annotation.
    public LoginPage() {
        //"this." - means current class. You can put other classes as like Driver.class etc.
        PageFactory.initElements(Driver.getDriver(), this);//
    }

    @FindBy(id = "prependedInput") //This will initialize web element.
    private WebElement usernameInput;

    @FindBy(id="prependedInput2")//Without @FindBy. Web Element will be Null.
    private WebElement passwordInput;

    @FindBy(css="[class='alert alert-error']")
    private WebElement warningMessages;


    public void login(String username, String password) {
        BrowserUtils.waitForVisibility(usernameInput, 30);
        usernameInput.sendKeys(username);
        BrowserUtils.waitForVisibility(passwordInput, 30);
        passwordInput.sendKeys(password, Keys.ENTER);
    }

    public void login() {
        BrowserUtils.waitForVisibility(usernameInput, 30);
        usernameInput.sendKeys(ConfigurationReader.getProperty("user_name"));
        BrowserUtils.waitForVisibility(passwordInput, 30);
        passwordInput.sendKeys(ConfigurationReader.getProperty("password"), Keys.ENTER);
    }

    public void login(String role) {
        BrowserUtils.waitForVisibility(usernameInput, 40);
        if(role.equals("driver")) {
            usernameInput.sendKeys(ConfigurationReader.getProperty("driver.username"));
        }
        if(role.equals("store manager")) {
            usernameInput.sendKeys(ConfigurationReader.getProperty("store.manager.username"));
        }
        if(role.equals("sales manager")) {
            usernameInput.sendKeys(ConfigurationReader.getProperty("sales.manager.username"));
        }
        BrowserUtils.waitForVisibility(passwordInput, 30);
        passwordInput.sendKeys(ConfigurationReader.getProperty("password"), Keys.ENTER);
    }
}
