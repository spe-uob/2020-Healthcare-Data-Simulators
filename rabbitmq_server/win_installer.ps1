Clear-Host
$currentPrincipal = New-Object Security.Principal.WindowsPrincipal([Security.Principal.WindowsIdentity]::GetCurrent())
$status = $currentPrincipal.IsInRole([Security.Principal.WindowsBuiltInRole]::Administrator)
if ( $status )
{
    Write-Output "Installing Chocolatey..."
    Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; Invoke-Expression ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))
    Write-Output "Installing Rabbitmq..."
    choco feature enable -n allowGlobalConfirmation
    choco feature enable -name=exitOnRebootDetected
    choco install rabbitmq

} else {
    Write-Output "Please run this script with Admin privileges"
}
