# QA Challenge Manual Test and Automated Tests

## Background
All test cases can be found in QAChallengeTestCases.pdf
Some test cases have been automated and are ran with the following instructions

## Pre-recs: 
- You have the JDK (Java Development Kit) - Available here: https://www.oracle.com/java/technologies/javase-downloads.html
- You have the Selenium Client & Java WebDriver Language Bindings - Available here https://www.selenium.dev/downloads/
- You have a Java IDE like Eclipse installed - Available here: https://www.eclipse.org/downloads/

## Running the project
1. In your commandline, in a local directory of your choosing, run the command ```git init```
2. Run the command ``` git pull https://github.com/wintonmj/StagingAppTesting.git``` in the same directory
3. Open the SeleniumTests project in Eclipse or IntelliJ. 
4. Right click any of the Test.java files
5. Click on the option "Run as" then "JUnit" to run the tests for the applicable page you would like tested

### Possible errors
If you are getting import errors:
open the 'selenium-java-3.141.59.zip' you would've downloaded from here https://www.selenium.dev/downloads/
and add the following jars to your class path: 
  - client-combined-3.141.59.jar
  - guava-25.0-jre.jar
  - okhttp-3.11.0.jar
  - ok.io-1.14.0.jar
  - commons-exec-1.3.jar
  - testng-7.1.0.jar
  
To add .jars to your class path, right click the JRE System Libraries tab, under your project files, in your IDE. 
Then click on 'Build Path', then 'Configure Build Path'.
Next, click the 'Add JARs' button and in the file explorer, find the jars mentioned above. 
  
You may also need to add JUnit4 to your class path (not modules) as well. Your IDE should be able to help you with that. 

To add JUnit4 to your class path, right click the JRE System Libraries tab, under your project files, in your IDE. 
Then click on 'Build Path', then 'Configure Build Path'.
Next, click the 'Add Libraries' button and find Junit4 in the options.  


