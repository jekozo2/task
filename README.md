# Create GitHub Repository Automation Task

REST API TAF is a Java based framework for completing the Nuvei Task for Create GitHub Repository Automation Tests

## Setup
The project is build on Java 19.\
Apache [Maven] (https://maven.apache.org) should be installed.\
The most important libraries being used are:\
 - [TestNG] (https://testng.org/doc/)
 - [Cucumber] (https://cucumber.io/docs/cucumber)and 
 - [Rest Assured] (https://rest-assured.io)

## Running Tests

In order to change the parallel execution to sequential change @DataProvider(parallel=true) to false in TestRunner class \

```bash
mvn clean test
```

## Reporting

The TAF is using Extent Report that generates html and pdf reports in `target/reports` folder
