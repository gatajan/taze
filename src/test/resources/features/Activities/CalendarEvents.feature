@calendar_events
Feature:

  Background:
    Given user is on the login page
    And user logs in as store manager

  Scenario: Verify column names

    Then user navigates to "Activities" then to "Calendar Events"
    And user verifies that column names are displayed
      | TITLE             |
      | CALENDAR          |
      | START             |
      | END               |
      | RECURRENT         |
      | RECURRENCE        |
      | INVITATION STATUS |