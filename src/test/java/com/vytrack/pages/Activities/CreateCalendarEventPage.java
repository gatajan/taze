package com.vytrack.pages.Activities;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class CreateCalendarEventPage extends CalendarEventsPage {

    @FindBy(css = "[class='select2-chosen']")
    public WebElement owner;

    @FindBy(css="[id^=\"date_selector_oro_calendar_event_form_start\"]")
    private WebElement startDateElement;

    @FindBy(css="[id^=\"date_selector_oro_calendar_event_form_end\"]")
    private WebElement endDateElement;

    @FindBy(css = "id^='time_selector_oro_calendar_event_form_start'")
    private WebElement startTimeElement;

    @FindBy(css = "id^='time_selector_oro_calendar_event_form_end'")
    private WebElement endTimeElement;

    @FindBy(css ="[class='ui-timepicker-wrapper']")
    private WebElement timeDropdownElement;

    @FindBy(css = "[class='ui-datepicker-month']")
    private WebElement monthDropdownElement;

    @FindBy(css = "[class='ui-datepicker-year']")
    private WebElement yearDropdownElement;

    @FindBy(id = "[id^='recurrence-repeat-view']")
    private WebElement repeatCheckboxElement;

    @FindBy(css = "[id^='oro_calendar_event_form_title']")
    private WebElement titleElement;

    @FindBy(css = "body[id='tinymce'] > p")
    private WebElement descriptionElement;

    /**
     * Simple method than can select start and end date on create calendar event page
     *
     * @param date        format MM/dd/yyyy
     * @param startOrEnd  which date to click on start or end
     * */
    public void selectStartOrEndDate(String date, String startOrEnd) {
        waitUntilLoaderMaskDisappear();
        LocalDate ld = LocalDate.of(Integer.parseInt(date.substring(date.lastIndexOf("/") + 1)),
                Integer.parseInt(date.substring(0, date.indexOf("/"))),
                Integer.parseInt(date.substring(date.indexOf("/")+1, date.lastIndexOf("/"))));

        String month = DateTimeFormatter.ofPattern("MMM").format(ld);
        int year = ld.getYear();
        int day = ld.getDayOfMonth();

        //locator for day
        String dayLocator = "//a[@class='ui-state-default' and text()='" + day + "']";

        //click on start or end date
        if(startOrEnd.equalsIgnoreCase("start")) {
            BrowserUtils.waitForVisibility(startDateElement, 10);
            startDateElement.click();
        } else {
            BrowserUtils.waitForVisibility(endDateElement, 10);
            endDateElement.click();
        }

        //select month
        new Select(monthDropdownElement).selectByVisibleText(month);

        //select year
        new Select(yearDropdownElement).selectByVisibleText(year + "");

        //select day
        Driver.getDriver().findElement(By.xpath(dayLocator)).click();
    }
/*    public void selectTomorrowDay() {
        int day = LocalDate.now().plusDays(1).getDayOfMonth();
        int month = LocalDate.now().plusDays(1).getMonth().getValue();
        try {
            waitUntilLoaderMaskDisappear();
            startDateElement.click();
        } catch (WebDriverException e) {
            System.out.println(e.getMessage());
            BrowserUtils.clickWithWait(startDateElement);
        }
        Select monthSelect = new Select(monthDropdownElement);
        monthSelect.selectByIndex(month-1);
        String dayLocator = "//table[@class='ui-state-calendar']//a[text()='" + day + "']";
        Driver.getDriver().findElement(By.xpath(dayLocator)).click();

    }*/

    public void selectStartTime(int plusHours) {
        String time = LocalDateTime.now().plusHours(plusHours).format(DateTimeFormatter.ofPattern("h:00 a"));
        waitUntilLoaderMaskDisappear();
        String startTimeToSelect = "(//li[text()='" + time + "'])[1]";
        startTimeElement.click();
        new WebDriverWait(Driver.getDriver(), 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(startTimeToSelect)));
        Driver.getDriver().findElement(By.xpath(startTimeToSelect)).click();
    }

    public void selectStartTime(String time) {
        waitUntilLoaderMaskDisappear();
        String startTimeToSelect = "(//li[text()='" + time + "'])[1]";
        try{
            startTimeElement.click();
        } catch (WebDriverException e) {
            System.out.println(e.getMessage());
            BrowserUtils.clickWithWait(startDateElement);
        }
    }

    public void selectEndTime(String time) {
        waitUntilLoaderMaskDisappear();
        String endTimeToSelect = "(//li[text()='" + time + "'])[2]";
        endDateElement.click();
        Driver.getDriver().findElement(By.xpath(endTimeToSelect)).click();
    }

    public long differenceBetweenStartTimeAndEndTime() {
        LocalTime actualStartTime = LocalTime.parse(startTimeElement.getAttribute("value"), DateTimeFormatter.ofPattern("h:mm a"));

        try {
            new WebDriverWait(Driver.getDriver(), 10).until(ExpectedConditions.invisibilityOf(startTimeElement));
        } catch (Exception e) {
            System.out.println(e);
        }
        LocalTime actualEndTime = LocalTime.parse(endTimeElement.getAttribute("value"), DateTimeFormatter.ofPattern("h:mm a"));

        return ChronoUnit.HOURS.between(actualStartTime, actualEndTime);
    }

    public void selectTodaysDate() {
        int day = LocalDate.now().getDayOfMonth();
        startDateElement.click();
        String dayLocator = "//a[@class='ui-state-default' and text()='" + day + "']";
        try {
            Driver.getDriver().findElement(By.xpath(dayLocator)).click();
        } catch (Exception e) {
            BrowserUtils.clickWithWait(Driver.getDriver().findElement(By.xpath(dayLocator)));
        }
    }

    public String getStartDate() {
        return startDateElement.getAttribute("value");
    }
    public String getEndDate() {
        return endDateElement.getAttribute("value");
    }
    public String getStartTime() {
        return startTimeElement.getAttribute("value");
    }
    public String getEndTime() {
        return endTimeElement.getAttribute("value");
    }
}


