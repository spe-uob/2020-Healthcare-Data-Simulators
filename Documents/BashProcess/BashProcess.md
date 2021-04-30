```
One of the most important aspects of the design of our project is related to the abstract class &quot;BashProcess&quot;. We had several main classes in our project that were supposed to perform similar tasks such as executing a command depending on an attribute related to the region the patients are generated from, informing the user wheather the task has completed succesfully, proccessing parameters related to executing different jobs or alerting the user if conditions are not met.

As a consequence, in order to avoid duplicates in our code, we created this abstract class and have &quot;Oauth&quot;, &quot;Compute&quot;, &quot;GenerateCSVAndSendDataJob&quot; and &quot;Convertor&quot; extend it, allowing for the implementation of custom functionalities.
```