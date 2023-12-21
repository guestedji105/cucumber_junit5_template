package com.example;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com.example")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.example")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "me.jvt.cucumber.report.PrettyReports:target/cucumber")
@ExcludeTags("ignored")
public class RunCucumberTest {
}