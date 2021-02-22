[![CircleCI](https://circleci.com/gh/spe-uob/Healthcare-Data-Simulators.svg?style=svg&circle-token=0faba2d088c5ee4a35b1893dc4a024da07143a2d)](https://app.circleci.com/pipelines/github/spe-uob/Healthcare-Data-Simulators)

![alt text](logo.png)
# Healthcare-Data-Simulators
This project will create a set of configurable ‘healthcare data simulators’ that generate as-live data
to simulate the regional healthcare landscape.

## Motivation
Digital healthcare provided by the NHS in England typically operates in silos. GPs have electronic systems to manage patient care which are distinct from hospital systems which are distinct from the ambulance service, 111, mental health services etc. Each data owner has a wealth of data that, if combined, would generate a more valuable resource than it does in isolation. While there are solutions to integrate this data for direct care purposes, there is no centralised solution to use this data to inform future care or service provisioning.

## Technologies
[SyntheaTM Patient Generator](https://github.com/synthetichealth/synthea)   

## Requirements 
1. Java 11
2. Python 3 
3. boto3 module for Python `pip install boto3`


## Installation
1. `git clone https://github.com/spe-uob/Healthcare-Data-Simulators.git`
2. Add [this jar file](https://uob-my.sharepoint.com/:u:/g/personal/ot19588_bristol_ac_uk/EUhcf-s5CxlImXKEL_qvIeMBdWifARyrv-qVU8s65zZ3iA?e=vobhgr) to the root file. It is synthea with UK population. 
3. Add [this jar file](https://drive.google.com/file/d/1hjNVsVvLq2367R2de8Y2Fw4iPEm4D1qs/view?usp=sharing) to the root file.
4. If you do not wish to compile the source code, find the windows .exe file in `build_files/` directory. Before running, add the 2 jar files from step 2 and 3 above to `build_files/` directory.

## Contributors
Vlad Andrei Bucur  
George Edward Nechitoaia  
Victor Traistaru  
Ena Balatinac  
Victor Kingi

## License
This software is being developed under the MIT License.
