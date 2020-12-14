[![CircleCI](https://circleci.com/gh/spe-uob/Healthcare-Data-Simulators.svg?style=svg&circle-token=0faba2d088c5ee4a35b1893dc4a024da07143a2d)](https://app.circleci.com/pipelines/github/spe-uob/Healthcare-Data-Simulators)

# Healthcare-Data-Simulators
This project will create a set of configurable ‘healthcare data simulators’ that generate as-live data
to simulate the regional healthcare landscape.

## Motivation
Digital healthcare provided by the NHS in England typically operates in silos. GPs have electronic systems to manage patient care which are distinct from hospital systems which are distinct from the ambulance service, 111, mental health services etc. Each data owner has a wealth of data that, if combined, would generate a more valuable resource than it does in isolation. While there are solutions to integrate this data for direct care purposes, there is no centralised solution to use this data to inform future care or service provisioning.

## Technologies
[SyntheaTM Patient Generator](https://github.com/synthetichealth/synthea)   
[Lyniate Rhapsody](https://www.lyniate.com/rhapsody/)

## Installation
1. `git clone https://github.com/spe-uob/Healthcare-Data-Simulators.git`
2. Add [this jar file](https://github.com/synthetichealth/synthea/releases/download/master-branch-latest/synthea-with-dependencies.jar)  to `src/main/java/`
3. Enjoy!

## Contributors
Vlad Andrei Bucur  
George Edward Nechitoaia  
Victor Traistaru  
Ena Balatinac  
Victor Kingi

## License
This software is being developed under the MIT License.
