![alt text](https://github.com/spe-uob/Healthcare-Data-Simulators/blob/main/Documents/logo.png)
# Healthcare-Data-Simulators
This project will create a set of configurable ‘healthcare data simulators’ that generate as-live data
to simulate the regional healthcare landscape.

## Motivation
Digital healthcare provided by the NHS in England typically operates in silos. GPs have electronic systems to manage patient care which are distinct from hospital systems which are distinct from the ambulance service, 111, mental health services etc. Each data owner has a wealth of data that, if combined, would generate a more valuable resource than it does in isolation. While there are solutions to integrate this data for direct care purposes, there is no centralised solution to use this data to inform future care or service provisioning.

## Technologies
[SyntheaTM Patient Generator](https://github.com/synthetichealth/synthea)   
[RabbitMQ](https://www.rabbitmq.com)  
[Mirth Connect Integration Engine](https://www.nextgen.com/products-and-services/integration-engine)
[OpenPseudomiser](https://www.openpseudonymiser.org/Default.aspx)

## Requirements 
1. Java 11
2. Python 3 
3. boto3 module for Python `pip install boto3`


## Installation

### Setup for the external servers
1. [Here](https://github.com/spe-uob/Healthcare-Data-Simulators/tree/main/Mirth%20NEXTGEN) is a guide provided by us to install and setup Mirth Healthcare Integration Engine.
2. [Here](https://github.com/spe-uob/Healthcare-Data-Simulators/tree/main/rabbitmq_server) is a guide provided by us to install and setup the RabbitMQ server.

The `checksum.txt` file contains the correct sha512 hex values for each file. It will be used to check for corrupt data during extraction but optional.

### Getting started
1. `git clone https://github.com/spe-uob/Healthcare-Data-Simulators.git`
2. Add [this jar file](https://uob-my.sharepoint.com/:u:/g/personal/ot19588_bristol_ac_uk/EUhcf-s5CxlImXKEL_qvIeMBdWifARyrv-qVU8s65zZ3iA?e=vobhgr) to `src/main/resources/lib` folder. It is synthea with UK population. 
3. Add [this jar file](https://drive.google.com/file/d/1Y9OZQ4a_qfeo_JcPhpMSdHuNEZydhAWq/view?usp=sharing) to `src/main/resources/lib` folder. It is the OpenPseudomiser for masking data.
4. If you do not wish to compile the source code, find the jar file
   in releases.
Run `java -jar healthcare-data-simulators-x.x-SNAPSHOT.jar` from releases.

###### NB:- The jar files in step 2, 3 & 4 will be extracted from `main/resources/lib` folder to a `lib` folder for execution.



## Contributors
Vlad Andrei Bucur  
George Edward Nechitoaia  
Victor Traistaru  
Ena Balatinac  
Victor Kingi

## License
This software is being developed under the MIT License.
