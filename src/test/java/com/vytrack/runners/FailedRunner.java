package com.vytrack.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "@target/rerun.txt",
        glue = "com/vytrack/step_definitions",
        plugin = {"html:target/rerun_default-cucumber-reports",
                "json:target/rerun_cucumber.json",
                "rerun:target/rerun.txt"}

)

public class FailedRunner {
}
