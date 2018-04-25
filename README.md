# SWEN301 Architecture Project - Chromolite
Riley Blair (300371586)

## 1. Project Outline

The Chromolite project and repository consists of four major components. These are an Android application, desktop application, Arduino application and a web application. The architectural changes I implemented to the Chromolite project was the addition of a web-interface, web-server, as well as the development of a REST API and REST API server used to communicate with the connected RGB LED IoT devices.

Due to the project not being very well documented prior to my work on it,

## 2. Running The Project

Because this project requires hardware elements to be connected, all tests and aspects of the project can be executed at once. I am assuming here that the markers of this assignment will likely not have the required hardware for the project to be executed end-to-end, and will likely not have everything required to run the desktop server or Android application in their current state. The architecture change I have implemented however, including Angular web server and Spring REST API server can be run independently of the Arduino and connected IoT devices.

Use `./run` to execute a bash script that will compile and run the web server and REST API server simultaneously on the local machine. Root permissions are required in order to run this file, so the use of `chmod +x run`, `chmod +x initialise_web_server`, and `chmod +x initialise_api_server` may be required in order to have these permissions.

The web server requires npm dependencies which should automatically be installed upon using the `./run` command. This process may take a few minutes in order to install all required JavaScript libraries.

The REST API server requires a number of Spring framework dependencies to be installed in order to run. These should automatically be installed by using `./run`, but make take a couple of minutes to be successfully installed.

Once both sets of dependencies are installed, the web server should be operational at http://localhost:4200 and the REST API server should be operational at http://localhost:8080. Upon hitting an API endpoint, the result will be output to the command line. With the response output to the browser's JavaScript console. Navigating to http://localhost:4200 should display the web interface for Chromolite. Navigating to http://localhost:8080 should present an error. This is the intended functionality as this is not a valid endpoint and no valid JSON settings payload would have been provided.

*Note: Cross-Origin Restrictions MUST BE DISABLED in the browser prior to testing the project.* This is due to the web server and REST API server running on a local machine. On an actual deployed environment, cross-origin restrictions will be handled automatically by the hosted server.

Both servers will run simultaneously and should be terminated correctly when needed. If not terminated correctly, both servers will continue to run in the background until the computer is switched off.

## 3. Testing The Project

Due to the nature of the project requiring hardware components to be set up and connected together correctly, the project cannot be tested with end-to-end tests. The architectural changes made concern a web interface and REST API server. The web interface can be tested by visually inspecting the output and interacting with the visual elements on the screen. The outputs from these are reported in the browser's JavaScript console.

Because of the way RESTful APIs are designed, with a high level of abstraction, the API server is set up to communicate whether or not a valid API request was received and send a response based upon this. It does not output the results of whether or not the connected LEDs turned on or off as this is not the purpose of the API or API server. Further to this, it is not possible to get an output back from the Arduino regarding whether an output LED actually turned on without the use of light sensors. As these were not a part of the original hardware architecture, and the SWEN301 assignment requires a architectural change to the project's software, it is not possible to complete full end-to-end testing.

The initial project did not contain any test cases, and therefore the project requirement of ensuring all tests that ran previously still run is not possible. Instead, I ran the project initially, observed the output on a set of RGB LEDs connected to my Arduino micro-controller, and compared these observations with the observations I made after implementing the architectural changes to the project.

## 4. Notes

* I discussed the applicability of implementing a web interface and REST API in regards to the SWEN301 project 2 requirements with James Noble on 22/04/2018. Excerpts from our conversation regarding whether or not the architecture changes I planned to implement would be suitable are outlined below:

> Riley: I was just wondering whether or not it would be suitable to write a RESTful API and web interface for the architectural change component of the current SWEN301 assignment?

> James: Short answer is yes - that kind of thing is a good approach to this project, as long as it's manageable for you.

* As this project requires a considerable amount of specialist hardware in order to run fully, I am more than happy to provide further evidence of the project working in a full end-to-end capacity if required.

* The original project is based within GitHub (https://github.com/leytonblackler/Chromolite) with my fork of the repository based on my own GitHub account (https://github.com/Riley-Blair/Chromolite). My fork of the repository was then mirrored to my ECS GitLab account in order to have a version of the project accessible within the GitLab environment as required by the project guidelines.
