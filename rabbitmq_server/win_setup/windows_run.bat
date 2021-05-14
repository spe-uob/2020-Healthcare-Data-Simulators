@echo off
color b
@echo Initializing...
powershell -noexit -Command "; & Set-ExecutionPolicy RemoteSigned; & D:\git\Healthcare-Data-Simulators\rabbitmq_server\win_setup\win_installer.ps1"

