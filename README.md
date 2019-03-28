# World Population Rest API - Web Frontend

## Overview
This is a Java web application that uses Managed Beans and a front end created with JSF (JavaServer Faces) and PrimeFaces. It runs on an Application Server and Wildfly has been used during development. 

## How it works
The implementation makes calls to a public Rest API and presents the received data.

The used public Rest API is the *World Population API* that can be found here:

[http://api.population.io](http://api.population.io)

When the web application starts, it fetches a list of countries from the public Rest API. This list is then applied to the menu boxes for "Country". (The other lists in the menu boxes are hard coded depending on the limitations of the Rest API.)

When your selections have been done and "Submit" is clicked, a call is made to the public Rest API and the received result is presented in a pop-up window.

## Implementation details
- Used IDE: Eclipse
- Build tool: Maven
- Application server: Wildfly v15
- Version handling: Git
