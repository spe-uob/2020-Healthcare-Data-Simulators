# Installation and Configuration


## Linux Setup

1. Download installer.sh from this folder.
2. Run the following command: `sh installation.sh`
3. To start the server: `sudo service rabbitmq-server start`
4. You can enable the RabbitMQ web UI (*recommended*): `sudo rabbitmq-plugins enable rabbitmq_management`
5. You can access it at: `http://localhost:15672/`
6. You can check the server status using the following command: `sudo service rabbitmq-server status`
7. To close the server: `sudo rabbitmqctl shutdown`

The default login credentials are:
| Username | Password         
| :----: |:----:|
| guest | guest |


### Common issues
If you get any errors compiling Sender/Receiver class please check `dependencies.txt` and make sure you have those dependencies included in your pom.xml.
