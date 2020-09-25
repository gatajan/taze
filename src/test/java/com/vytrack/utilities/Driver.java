package com.vytrack.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.net.URL;

public class Driver {
    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    //1-Create private constructor
    private Driver() {
    }

    public static WebDriver getDriver() {
        //if this thread does not have a web driver yet - create it and add to pool
        if (driverPool.get() == null) {
            System.out.println("TRYING TO CREATE DRIVER");
            //This line will tell which browser should open based on the value from properties file
            String browserParamFromEnv = System.getProperty("browser");
            String browser = browserParamFromEnv == null ? ConfigurationReader.getProperty("browser") : browserParamFromEnv;
            switch (browser) {
                case "chrome":
                case "CHROME":
                case "Chrome":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver());
                    break;
                case "chrome_headless":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver(new ChromeOptions().setHeadless(true)));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;
                case "firefox_headless":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver(new FirefoxOptions().setHeadless(true)));
                    break;
                case "ie":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
                        throw new WebDriverException("Your OS does not support Internet Explorer");
                    }
                    WebDriverManager.iedriver().setup();
                    driverPool.set(new InternetExplorerDriver());
                    break;
                case "edge":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
                        throw new WebDriverException("Your OS does not support Edge");
                    }
                    WebDriverManager.edgedriver().setup();
                    driverPool.set(new EdgeDriver());
                    break;
                case "safari":
                    if (!System.getProperty("os.name").toLowerCase().contains("mac")) {
                        throw new WebDriverException("Your OS does not support Safari");
                    }
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driverPool.set(new SafariDriver());
                    break;
                case "remote_chrome":
                    try {
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.setCapability("platform", Platform.ANY);
                        URL url = new URL("http://34.239.44.209:4444/wd/hub"); //or http://localhost:4444/wd/hub
                        driverPool.set(new RemoteWebDriver(url, chromeOptions));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                case "remote_firefox":
                    try {
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        firefoxOptions.setCapability("platform", Platform.ANY);
                        URL url = new URL("http://34.239.44.209:4444/wd/hub"); //or http://localhost:4444/wd/hub
                        driverPool.set(new RemoteWebDriver(url, firefoxOptions));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                default:
                    throw new RuntimeException("Wrong browser type!");
            }
        }
        //return corresponded to thread ID webdriver object
        return driverPool.get();
    }

    public static void close() {
        driverPool.get().quit();
        driverPool.remove();
    }
}
