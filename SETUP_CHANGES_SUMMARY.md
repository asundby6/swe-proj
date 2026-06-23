# Setup and Execution Summary

## What Changed
- Modified `pom.xml`: Added `<javafx.platform>win</javafx.platform>` property and platform classifiers to JavaFX dependencies
- Modified `pom.xml`: Added `<targetPath>Project</targetPath>` to resources so FXML files are packaged in the correct location
- Created `.idea/runConfigurations/Main.xml` to configure IntelliJ Run Configuration with JavaFX module path
- Created `RUN.bat` script for easy execution (double-click to run)

## How to Execute

### Option 1: Double-click RUN.bat (Easiest)
Simply double-click the `RUN.bat` file in the project root folder. The window will stay open after execution.

### Option 2: Use IntelliJ Run Configuration
1. In IntelliJ, go to **Run → Edit Configurations**
2. Select "Main" from the list
3. Click the green Play button to run

### Option 3: Terminal Command
```powershell
cd "C:\Users\fishe\Desktop\swe_project\swe_project"
$env:PATH = "C:\Users\fishe\Desktop\swe_project\swe_project\tools\apache-maven-3.9.4\bin;" + $env:PATH
mvn javafx:run
```

## What Was Fixed
- **Missing JavaFX natives**: Added platform classifier `win` to ensure Windows native libraries are downloaded
- **Missing FXML resources**: Resources now correctly placed in `target/classes/Project/` so `getClass().getResource("login.fxml")` finds them
- **Missing VM options**: IntelliJ Run Configuration now includes required JavaFX module path

## Project Requirements
- Java 17+ (currently using Java 25)
- Maven 3.9.4 (automatically downloaded to `tools/` folder if missing)
- Windows 10/11 64-bit

All dependencies will be downloaded automatically on first run.

