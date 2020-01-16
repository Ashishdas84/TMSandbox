Technology Used
Programming Language: Java
Rest Automation: JerseyClient API (Alternatives: HTTPClient, RestAssured)
Testing Framework: TestNG
Built tool: Maven 
Remote Repository: Git

All above should be installed on the machine, Please follow the below steps to execute the test

Execution Process
1. Clone project to local
   Command: git clone https://github.com/Ashishdas84/TMSandbox.git
2. Go to project folder and execute the below maven command
   Command: mvn install -DtestSuite=suite


File Locations:
Suite file location: /TMSandbox/src/test/java/org/tmsandbox/suite/suite.xml
Testcase location: /TMSandbox/src/test/java/org/tmsandbox/test/TMSandboxTest.java
Constants file: /TMSandbox/src/test/java/org/tmsandbox/test/TMSandboxConstants.java
Common Functions file: /TMSandbox/src/main/java/org/tmsandbox/JerseyClientUtils.java

