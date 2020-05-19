# Open Hospital Web

## Prerequisites
* Install JDK. Use `Windows x64 Installer` https://www.oracle.com/java/technologies/javase-jdk11-downloads.html
* Install MySQL Server v5.7 
* Make sure MySQL is up & running on `3306` port
* Make sure MySQL service id is `MySQL` in the Windows Services.
* Use `mysql -u root -p` in order to check whether you can log in

## OH Web Service Installation
* Unzip `web-win64.zip` file into `C:\Programs\OHWeb` folder
* Go to `C:\Programs\OHWeb` folder
* If MySQL windows service is not `MySQL`, edit `depend` property in `WinSW.NET4.xml` and set the MySQL service id  
* Install service by executing `WinSW.NET4.exe install`
* Start `OHWeb` Windows Service
* Open http://localhost:8080/status/health in the browser 