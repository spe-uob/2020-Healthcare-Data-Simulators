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

 ## Acceptance Testing (Evaluation)

 ## Reflection
