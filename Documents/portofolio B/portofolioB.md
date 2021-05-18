# Healthcare Data Simulators - Portofolio B

## Overview
#### Client Description

Our client is Dr. Philip Harfield which is a "Shared Data Planning Programme Manager" at "NHS Healthier Together", where he supports the development of infrastructure for the Shared Data and Planning Platform.  The client has relevant experience in software development and hardware integration. These skills brought many benefits to our collaboration with him, exposing us to relevant technologies from the medical healthcare field. 

Moreover, he works within the Informatics theme of Bristol's Biomedical Research Centre (BRC) at University of Bristol where he promotes and facilitates research based on routinely collected health and care big data. Taking this into consideration his work, he acknowledged the need for a centralised solution to use this data to inform future care or service for all the regional healthcare providers.

#### Application Domain & Project Problem

Digital healthcare provided by the NHS in England typically operates in silos. GPs have electronic systems to manage patient care which are distinct from hospital systems which are distinct from the ambulance service, 111, mental health services etc. Each data owner has a wealth of data that, if combined, would generate a more valuable resource than it does in isolation. While there are solutions to integrate this data for direct care purposes, there is no centralised solution to use this data to inform future care or service provisioning. 

Coming up with a solution for the above problem, combining healthcare data sources would inform clinical decision making by offering more sophisticated insights into the patient's longitudinal health on arrival and understanding the merits of previous clinical decisions taken. Working with live patient data is notoriously difficult given the legislation surrounding sensitive patient data. This limits the ability to develop prototype systems that meet the needs of academics and NHS commissioners.
Therefore, it is necessary to provide a solution that simulates and distributes test healthcare data.

Our project delivers a set of configurable ‘healthcare data simulators’ that generate as-live data to simulate a specific regional healthcare landscape.

#### Vision for product

Our aim for this project is to provide a desktop java application which offers a scheduled configurable healthcare data simulator, which forwards the produced data to a specified API. We will be making use of the open source SyntheaTM Patient Generator to generate synthetic data stored in .CSV format, then the data will be processed to hide sensitive patient identifiers. 
The desktop application will be linked to a Mirth Healthcare Integration Engine configured to our needs. Within the integration system, the .CSV files will be converted into FHIR, which is the standard format for exchanging healthcare information electronically.  After conversion, the FHIR messages would be ingested by the specified API.  This solution would represent an important step in building a healthcare data centralised solution , because our application is simulating a synthetic set of data useful for testing, getting rid of the legislation problems.

 ## Requirements

 ## Personal Data, Privacy, Security and Ethics Management

 ## Architecture

 ## Development Testing

 ## Release Testing

 ## OO Design & UML
 
 #### Static UML modelling aspect

##### Class UML diagram

![](https://github.com/spe-uob/Healthcare-Data-Simulators/raw/main/Documents/BashProcess/BashProcess%20diagram.png)
One of the most important aspects of the design of our project is related to the abstract class BashProcess. We had multiple classes that were using the command-line, under the hood, calling external open-source scripts / JARs used throughout our project. The commands included several parameters which are highly correlated with the user's input. <br>
E.g. 1. Compute class calls Synthea Patient Generator (custom for UK). We have a configurable UI via which the user can enter settings regarding characteristics of the patients the user wants to generate. Under the hood, a jar file is called with custom parameters mapping the data provided by the user (population size, region, age range of the patients, sex, or diseases). 
2. OAuth class calls a python script with credentials linked to the user's input and returns an authentication token used for accessing a given API. 

The classes included in the diagram, which extend BashProcess, there are overridden methods such as "informUser()" which is used to inform the user whether tasks have completed successfully depending on the given context, or to alert the user ("alertUser()") if conditions are not met. 

As a consequence, in order to avoid duplicates in our code, we created this abstract class and have OAuth, Compute, GenerateCSVAndSendDataJob and Convertor extend it, allowing for the implementation of custom command line calls.



#### Dynamic UML modelling aspect
##### State Machine Diagram

![](https://github.com/spe-uob/Healthcare-Data-Simulators/raw/main/Documents/MachineStateDiagram/State%20Machine%20Diagram.png)

The machine state machine is used to model the behaviour of the menus, the system regarding the usage of the third party Synthea, of the OpenPseudonymiser for masking data and the correlation of producing, masking and sending the data with the cron-job. The transitions represent how state changes. For our design the most important feature is related to the scheduler which rules how and when the Synthea should produce data and when the data is masked and sent to Rabbit queues. When pressing Pause, the system moves into a halting state. To escape this state, the user ought to press Resume and the application will keep on generating, masking and sending data until some other command is selected. Pressing Stop will cause the system to move into a finish state.

##### Sequential Diagram

![](https://github.com/spe-uob/Healthcare-Data-Simulators/raw/main/Documents/MachineStateDiagram/Send%20Button.png)

It illustrates how the medical data is getting processed to be sent within the MessageBrokerSender class to a suitable RabbitMQ queue depending on the resource generated and region selected. You can observe how the generated CSV files get parsed and then masked, ensuring patient privacy.

#### Overview flow of the application - Activity UML diagram:

![](https://github.com/spe-uob/Healthcare-Data-Simulators/raw/main/Documents/UML%20Diagrams/Activity%20diagram1.png)

The purpose of this diagram is to have an overview of the **control flow**, showing the various paths that exist while the program is being executed. It was created to help us have a better understanding of the **sequential execution**, to provide a suggestive way to present how our project works, and **aid communication between developers and clients**. It changed many times during the developing every time a feature was added or modified, providing a useful vehicle to visualize the system functionality without needing to read the code in detail.


 ## Acceptance Testing (Evaluation)

 ## Reflection
