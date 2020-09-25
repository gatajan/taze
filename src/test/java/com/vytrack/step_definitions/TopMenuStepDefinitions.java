package com.vytrack.step_definitions;
import com.vytrack.pages.LoginPage;
import io.cucumber.java.en.When;

public class TopMenuStepDefinitions {

    @When("user navigates to {string} then to {string}")
    public void user_navigates_to_then_to(String string, String string2) {
        LoginPage loginPage = new LoginPage();
        loginPage.navigateTo(string, string2);
    }

}
