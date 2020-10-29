# Assist a Purchase API


The purchase system is used to select Philips monitoring products for patients.
It serves both the customer as well as Philips personnel.


## About the project configuration
We have used Java version 11.
The project makes use of Gradle as the build tool and hence, Gradle must also be in the system path.
We have used in memory Json file for Database, hence no database is required to be installed in the system.
There is products.json file in resource folder which acts as database.

## Building and Executing jar file :
### Steps to run project from command prompt
1. start command promt and navigate to the root folder of project
2. write the following command on command prompt
```
gradlew build
```
3. Now the jar file is built and ready in build/libs
4. Execute the jar file using the following command
```
java -jar build\libs\Monitor-0.0.1-SNAPSHOT.jar
```
5. Now the project is executing and you can consume the rest apis using postman or browser.

## Execution
When the main spring application (com.example.monitor) has been run, the Tomcat Apache server will be set up. Then we can consume the rest apis using postman or browser.

## APIs Created
Http Method----------------|-------API------------|-----------Function of the API---------------------------------------|

GET -----------------------|---"/products/all"----|---------Displays all the products present in the repository---------|

GET -----------------------|---"/products/{pid}"---|-------Displays the details of the product whose product id(pid) is mentioned as pathvariable---|

GET -----------------------|----"/products?touchscreen={touchscreen}&size={size}&category={category}&transportMonitor={transportMonitor}"---|----This API returns the products according to user specifications---|

POST-----------------------|------"/products/add"------|-----This API adds a product into the repository according to Philips personnel use----|

PUT------------------------|-----"/products/update/{pid}-----|-----This API is used to update any details of a particular product in the repository----|

DELETE----------------------|------"/products/delete/{pid}-----|-------This API is used to delete the product whose pid is mentioned in the API from the repository---|

## Build and Run Client [Angular] Project

Pre-requisites : Download node.js from https://nodejs.org/en/
To check if node, npm are installed: Go to command prompt and type commands: node -v, npm -v
To install latest angular package: In command prompt -> Execute command -> npm install -g @angular/cli
Then check if angular cli is installed -> ng --version
Download and install VSCode

To Build and run the project:

- Navigate to "assist-purchase-client" folder in command prompt
- type "code ." to open project in VSCode.
- In VSCode, go to "Terminal" menu and select "New Terminal"
- Once terminal opens, go to drop-down located at top-right of terminal, click on "select default" and choose command prompt.
- Now execute command -> npm install --save to download and install all angular packages/libraries required for this project to run.
- Once installed. Execute command -> ng serve to start angular project.
- Go to chrome or any browser and enter "localhost:4200" as url.[Make sure to keep spring-boot project in running]
