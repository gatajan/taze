package com.vytrack.step_definitions.Fleet;

import com.vytrack.pages.Fleet.CreateCarPage;
import com.vytrack.pages.Fleet.VehiclesPage;
import com.vytrack.pages.LoginPage;
import com.vytrack.utilities.BrowserUtils;
import io.cucumber.java.en.Then;

import java.util.List;
import java.util.Map;

public class VehiclesStepDefinition {

    VehiclesPage vehiclesPage = new VehiclesPage();
    CreateCarPage createCarPage = new CreateCarPage();

    @Then("user click on {string} button")
    public void user_click_on_button(String string) {
        vehiclesPage.waitUntilLoaderMaskDisappear();
        vehiclesPage.clickToCreateCar();
    }

    @Then("user adds new car information:")
    public void user_adds_new_car_information(List<Map<String, String>> dataTable) {
       for(int i = 0; i < dataTable.size(); i++) {
           createCarPage.waitUntilLoaderMaskDisappear();
           createCarPage.licensePlateElement.sendKeys(dataTable.get(i).get("License Plate"));
           createCarPage.driverElement.sendKeys(dataTable.get(i).get("Driver"));
           createCarPage.modelYearElement.sendKeys(dataTable.get(i).get("Model Year"));
           createCarPage.locationElement.sendKeys(dataTable.get(i).get("Location"));
           createCarPage.colorElement.sendKeys(dataTable.get(i).get("Color"));
           BrowserUtils.Wait(5);
           if(i==dataTable.size()-1) {
               createCarPage.saveAndClose();
               break;
           }
           createCarPage.saveAndNew();


       }

    }

}
