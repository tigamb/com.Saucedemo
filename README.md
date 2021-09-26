#Automation Project<br>
https://www.saucedemo.com/ - eCommerce example website

Getting Started<br>
Prerequisites
  *Java 1.8 or above
  *Selenium 3.14 or above 


Copy the repo into your machine

Run test locally<br>
Right click the class file and select "Run As"

Run tests through the commandline<br>
As this project uses Maven, we can invoke the tests using Maven goals.
To run the test point Maven to the project path and use the goals:<br>
mvn clean test or maven clean test -DsuiteXmlFile='testng.xml'

Reporting
The default reporting provided by the framework is Allure.
By default, screenshots are taken after each step and after a failure (if any), which will display on each step of the report.
To turn this behaviour off and disable screenshots, pass the following property:<br>
mvn allure:serve


This project was built with:
<br>Eclipse IDE for Java
<br>Selenium - Browser automation framework
<br>TestNG - Testing framework
<br>Maven - Dependency management
<br>Allure - Reporting framework

<h3 align="left">Languages and Tools:</h3>
<p align="left">
<a href="https://maven.apache.org/" target="_blank"> 
<img src="https://maven.apache.org/images/maven-logo-black-on-white.png" alt="css3" width="100" height="40"/></a> 
<a href="https://www.java.com" target="_blank"> 
<img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="60" height="40"/></a> 
<a href="https://www.jenkins.io" target="_blank"> 
<img src="https://www.vectorlogo.zone/logos/jenkins/jenkins-icon.svg" alt="jenkins" width="60" height="40"/> </a>
<a href="https://www.selenium.dev" target="_blank"> 
<img src="https://raw.githubusercontent.com/detain/svg-logos/780f25886640cef088af994181646db2f6b1a3f8/svg/selenium-logo.svg" alt="selenium" width="60" height="40"/></a>
<a href="https://docs.qameta.io/allure/" target="_blank"> 
<img src="https://avatars.githubusercontent.com/u/5879127?s=200&v=4" alt="css3" width="60" height="40"/></a>
<a href="https://testng.org/doc/" target="_blank"> 
<img src="https://i0.wp.com/testingfreak.com/wp-content/uploads/2015/01/testng1.png?resize=568%2C201" alt="css3" width="100" height="40"/></a>
</p>

