package com.example;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com.example")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.example")
@ExcludeTags("ignored")
public class RunCucumberTest {
}