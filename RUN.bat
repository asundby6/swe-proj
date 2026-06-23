@echo off
REM Simple batch file to run the JavaFX application
REM This sets up Maven and runs the project

setlocal enabledelayedexpansion

REM Get the directory where this script is located
set "PROJECT_DIR=%~dp0"
set "MAVEN_BIN=%PROJECT_DIR%tools\apache-maven-3.9.4\bin"

REM Check if Maven exists in tools folder
if not exist "%MAVEN_BIN%\mvn.cmd" (
    echo Maven not found. Installing...
    powershell -NoProfile -ExecutionPolicy Bypass -Command "& {$v='3.9.4';$u='https://archive.apache.org/dist/maven/maven-3/'+$v+'/binaries/apache-maven-'+$v+'-bin.zip';$d='%PROJECT_DIR%tools\apache-maven-'+$v+'-bin.zip';New-Item -ItemType Directory -Path '%PROJECT_DIR%tools' -Force | Out-Null;Invoke-WebRequest -Uri $u -OutFile $d -UseBasicParsing;Expand-Archive -LiteralPath $d -DestinationPath '%PROJECT_DIR%tools' -Force;Remove-Item $d -Force;Write-Output 'Maven installed'}"
)

REM Set PATH to include Maven
set "PATH=%MAVEN_BIN%;%PATH%"

REM Run the application
cd /d "%PROJECT_DIR%"
call mvn clean javafx:run

pause

