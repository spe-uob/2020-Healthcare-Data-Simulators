@echo off
rem This will initialize the web interface for rabbitmq
color b
@echo Enabling web-ui...
cls
c: && cd C:\Program Files\RabbitMQ Server\rabbitmq_server-3.8.11\sbin && rabbitmq-plugins enable rabbitmq_management || cmd /k
