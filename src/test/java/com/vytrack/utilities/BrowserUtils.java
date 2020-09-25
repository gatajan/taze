package com.vytrack.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class BrowserUtils {

    public static void Wait(double seconds) {
        try {
            Thread.sleep((long)(seconds*1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitForStaleElement(WebElement element) {
        int y = 0;
        while(y <= 15) {
            try{
                element.isDisplayed();
                break;
            } catch (StaleElementReferenceException st) {
                y++;
                try{
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            break;
        }
    }

    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWaitInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("argument[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("argument[0].click;", element);
    }

    public static WebElement waitForClickability(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForPageTitle(String pageTitle) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 40);
        wait.until(ExpectedConditions.titleIs(pageTitle));
    }

    public static void clickWithWait(WebElement webElement) {
        Wait wait = new FluentWait<>(Driver.getDriver())
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotVisibleException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(WebDriverException.class);

        WebElement element = (WebElement) wait.until((Function<WebDriver, WebElement>) driver->webElement);

        try{
            element.click();
        } catch (WebDriverException e) {
            System.out.println(e.getMessage());
        }try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        element.click();
    }

    public static void waitForPageLoad(long timeOutSeconds) {
        ExpectedCondition<Boolean> expectation = driver->((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        ExpectedCondition<Boolean> expectation2 = driver-> ((JavascriptExecutor)driver).executeScript("return jQuery.active == 0").equals(true);

        try{
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeOutSeconds);
            wait.until(expectation);
            wait.until(expectation2);
        } catch(Throwable error) {
            error.printStackTrace();
        }
    }

    public static String getScreenshot(String name) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMMM-yy_HHmm");
        String date = dtf.format(LocalDateTime.now());

        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        File source = ts.getScreenshotAs((OutputType.FILE));

        String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name +"_" + date + ".png";

        File finalDestination =  new File(target);

        try{
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return target;
    }

    /**
     * This method will convert list of WEBELEMENTS INTO LIST OF STRING
     *
     * @param listOfWebElements
     * @return list of strings
     */

    public static List<String> getListOfString (List<WebElement> listOfWebElements) {
        List<String> listOfString = new ArrayList<>();
        for(WebElement element: listOfWebElements) {
            String value = element.getText().trim();
            if(value.length() > 0) {
                listOfString.add(element.getText().trim());
            }
        }
        return listOfString;
    }


}
