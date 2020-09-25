@create_A_car
Feature: Create a new car

  Background:
    Given user is on the login page
    And user logs in as store manager

  Scenario: Verify column names
    Then user navigates to "Fleet" then to "Vehicles"
    And user click on "Create Car" button
    Then user adds new car information:
      | License Plate | Driver    | Location | Model Year | Color     |
      | BG8567AH      | Chyrachy  | Ashgabat | 2006       | Gara      |
      | LB0125AG      | Mashennik | Lebap    | 2007       | Mokry     |
      | AK1091LB      | Chopan    | Balkan   | 2021       | Kumushsow |