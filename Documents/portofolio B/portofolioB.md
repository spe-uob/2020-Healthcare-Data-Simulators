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

 ## Development Testing

 ## Release Testing

 ## Architecture
 #### High level architecture diagram 

![](https://user-images.githubusercontent.com/58257818/118840042-b509ee00-b8cf-11eb-9734-2f27779e01f8.png)


### **INTRODUCTION ARCHITECTURE**
We propose the design of a client application which will:
1. Generate Data in csv standard
2. Anonymise data 
3. Use AMQP server to filter the flux of data 
4. Integrate and Centralise Data
5. Create a safe, fast, and efficient connection with web services

### Generate Data
#### SyntheaTM
We use open source SyntheaTM Patient Generator to generate as-live data to simulate the regional healthcare landscape. Specifically, the simulators will generate data from a specific region:
- Simulate more than one healthcare provider category (e.g. Acute hospital, primary care, 111)
- Patients

Synthetic patients can be simulated with models of disease progression and corresponding standards of care to produce risk-free realistic synthetic health care records at scale.

The framework for the synthetic data generation process utilized by Synthea is based on the use of PARSER, the Publicly Available Data Approach to the Realistic Synthetic EHR. The PADARSER framework, unlike EMERGE and medGAN, assumes that access to the real EHR is impossible or undesirable, relying instead on publicly available datasets to populate the synthetic EHR. Figure 1 presents the PADARSER framework.

**HL7 FHIR** Fast Healthcare Interoperability Resources (FHIR, pronounced &quot;fire&quot;) is a standard describing data formats and elements (known as &quot;resources&quot;) and an application programming interface(API) for exchanging electronic health records(EHR). The standard was created by the Health Level Seven International (HL7) health-care standards organization.

#### In our software:
Originally, Synthea generates US medical data (e.g. names, postcodes, cities, regions etc.). So, in order to generate data based on UK medical data distributions, we worked on the open source project and redeployed the new UK built Synthea generator.

### **DATA INTEGRATION AND CENTRALISATION**

We propose to use MIRTH NextGen Connect Data Centralisation and Integration Engine.

**MIRTH NextGen Connect** is a cross-platform interface engine used in the healthcare industry that enables the management of information using bi-directional sending of many types of messages. The primary use of this interface engine is in healthcare.

Benefits of using Mirth are:

• It is built for Healthcare.
• It has purpose-built solution for csv and FHIR (data translators).
• It supports Data Acquisition (large amounts of data from multiple sources) - AMQP server.

**Mirth** is a desktop java based application which have an intuitive UI (User Interface). It offers the possibility to work with multiple translators (from csv to FHIR), each one representing a specific channel.

#### In our software:
In our implementation, every Mirth channel:
- Connects to a AMQP server queue using a javascript script to take the incoming csv based data.
- Splits the csv file in multiple records
- Translates each record in FHIR resource 
- Using the previous obtained token builds the HTTP POST request to API
- Send the request

 ### **Data Transfer Protocols**

**Message broker technology** (AMQP) is an intermediary computer program module that translates a message from the formal messaging protocol of the sender to the formal messaging protocol of the receiver. Message brokers are elements in telecommunication or computer networks where software applications communicate by exchanging formally defined messages.

**HTTPS** is used for secure communication over a computer network, and is widely used on the Internet. In HTTPS, the communication protocol is encrypted using Transport Layer Security (TLS) or, formerly, Secure Sockets Layer (SSL).

 ### **Data Ingestion**

The system will authenticate and create a RESTful endpoint for HL7 FHIR messages.

**HL7 FHIR endpoint** describes the technical details of a location that can be connected to for the delivery/retrieval of information. Sufficient information is required to ensure that a connection can be made securely, and appropriate data transmitted as defined by the endpoint owner.

**RESTful API** is an architectural style for an application program interface (API) that uses HTTP requests to access and use data. That data can be used by using the CRUD approach: create, read, update, and delete.

**Amazon API Gateway** is a fully managed service that makes it easy for developers to create, publish, maintain, monitor, and secure APIs at any scale. Using API Gateway, RESTful APIs enables real-time two-way communication applications. API Gateway supports containerized and serverless workloads, as well as web applications.

**RabbitMQ** is a messaging system that uses AMQP 0.9.1 as the basis for a set of standards controlling the entire message passing process.

**Benefits of RabbitMQ:**

1. Delivery and order guarantee: The messages have been sent to a consumer in the same order in which they were created.
2. Redundancy: The queues persist the messages until they are processed completely.
3. Decoupling: Any third party system can consume the messages and interact with them, so you want the messages to be processed by someone who is not the actor who created the message, without any problems. This generates us a benefit, which is that it can be reused for many applications.
4. Scalability: we can have an application server dedicated to the processes and the other servers for browsing the web.

#### In our software:
In the "Architecture Diagram", the multiple arrows that go into **RabbitMQ** designates the different queues built on top of AMQP protocol:
- Resource queues. Used to filter the csv data.
                    E.g. "somerset_patient": A queue which servers as the way of transferring and storing the generated "patients.csv" file. This file contains data based on the Somerset region.
- Token queue. Used for transferring the generated API Cognito token to the integration engine (i.e. Mirth NextGen)


Classically, **RabbitMQ** server is working in background, with no need of user interaction. The deployment of the server is based on CLI (Command Line Interface) interaction, but there are alternatives. One of these is using a specific Plug-in which offers the opportunity to work with a UI (User Interface), which appears as a web application. We used this UI in order to analyse the movement of messages and to test its capabilities as well as new features (e.g. application scheduler).

### **Authentication**
**OAuth 2.0** is the industry-standard protocol for authorisation. OAuth 2.0 focuses on client developer simplicity while providing specific authorisation flows for web applications, desktop applications, mobile phones, and living room devices. 

#### In our software:
**Authentication** will be secured by using **Amazon Cognito**. The system will use a secure Token to access the API Gateway to create a safe and recognized connection with the HealthCare Lake/database infrastructure. The API Gateway will run a RESTful API and a HL7 FHIR message for ingestion into data lake. Amazon Cognito will verify the token and continue with the data transfer.


 ## (OO) Design & UML
 
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
