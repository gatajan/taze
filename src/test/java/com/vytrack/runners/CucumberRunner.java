package com.vytrack.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", //path to feature files

        // We can give direct path to specific feature files as "src/test/resources/features/Activities/CalendarEvents.feature"
        // feature = {"src/test/resources/features/Activities"
        //            "src/test/resources/features/fleet"
        //            },

        glue = "com/vytrack/step_definitions", //path to step definition
        tags = "@smoke_test",//with tags we can run specific scenarios and ignore others
        //"~@Negative_Test" or "not @Negative_Test"- will run other tests other than this one
        //"@Negative_Test or @driver" - will run any test which contains these annotations
        //"@Negative_Test and @driver" - then only tests that have
        plugin = {"html:target/default-cucumber-reports",
                  "json:target/cucumber.json",
                   "rerun:target/rerun.txt"},
        dryRun = false
)
public class CucumberRunner {

}
