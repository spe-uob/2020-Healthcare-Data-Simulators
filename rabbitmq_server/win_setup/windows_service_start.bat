@echo off
cls
rem This will initialize the web interface for rabbitmq
color b
@echo Starting...
c: && cd C:\Program Files\RabbitMQ Server\rabbitmq_server-3.8.11\sbin && rabbitmq-service start || cmd/k