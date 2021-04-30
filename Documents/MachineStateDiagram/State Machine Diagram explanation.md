```
The behavioral state machine is used to model the behavior of the menus, the system regarding the usage of the third party Synthea, of the OpenPseudomiser for masking data and the correlation of producing, masking and sending the data with the cron job.

The transitions represent how state changes. For our design the most important feature is related to the scheduler which rules how and when the Synthea should produce data and when the data is masked and sent to Rabbit queues.

When pressing &quot;Pause&quot;, the system moves into a halting state. To escape this state, the user ought to press &quot;Resume&quot; and the application will keep on generating, masking and sending data until some other command is selected. Pressing &quot;Stop&quot; will cause the system to move into a finish state.

The sequence diagrams that accompany the State Machine Diagram picture how the classes and methods responsible for masking and sending data communicate over time and with the user interface.
```