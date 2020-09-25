package com.vytrack.pages.Activities;
import com.vytrack.pages.BasePage;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CalendarEventsPage extends BasePage {

    @FindBy(css="[title='Create Calendar event']")
    private WebElement createCalendarEvent;

    @FindBy(css = "[title='Grid Settings']")
    private WebElement gridSettingsButtonElement;

    @FindBy(css = "[title='Reset']")
    private WebElement resetButtonElement;

    @FindBy(css=".grid-header-cell__label")
    private List<WebElement> headers;

    @FindBy(xpath = "//*[@class='control-label']/..//button")
    private WebElement viewPerPageElement;

    @FindBy(xpath = "//*[@class=\"btn-group open\"]//li")
    private List<WebElement> viewPerPageOptionsElement;


    public void clickToCreateCalendarEvent() {
        BrowserUtils.waitForVisibility(createCalendarEvent, 5);
        BrowserUtils.waitForClickability(createCalendarEvent, 5);
        createCalendarEvent.click();
    }

    public void selectGridSetting(String name, boolean yesOrNo) {
        BrowserUtils.clickWithWait(gridSettingsButtonElement);

        String checkbox = "//label[.='" + name + "']/../following-sibling::td/input";
        WebElement gridOption = Driver.getDriver().findElement(By.xpath(checkbox));

        if((yesOrNo && !gridOption.isSelected()) || (!yesOrNo && gridOption.isSelected())) {
            gridOption.click();
        }
    }

    public boolean verifyHeaderExists(String headerNameOrColumnName) {
        for(WebElement tableHeader: headers) {
            if(tableHeader.getText().equals(headerNameOrColumnName)) {
                return true;
            }
        }
        return false;
    }

    public List<String> getColumnNames() {
        return BrowserUtils.getListOfString(headers);
    }

    public List<String> getViewPerPageOptions() {
        BrowserUtils.waitForVisibility(viewPerPageElement, 40);
        BrowserUtils.clickWithWait(viewPerPageElement);
        return BrowserUtils.getListOfString(viewPerPageOptionsElement);
    }


}
