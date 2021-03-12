@echo off
cls
rem This will initialize the web interface for rabbitmq
color b
@echo Stopping...
c: && cd C:\Program Files\RabbitMQ Server\rabbitmq_server-3.8.11\sbin && rabbitmq-service stop || cmd/k