# Open Hospital Web

## Prerequisites
* Install JDK. Use `Windows x64 Installer` https://www.oracle.com/java/technologies/javase-jdk11-downloads.html. Make sure the `java` is available in the system `PATH`. Start command terminal and type `java -version`
* Install MySQL Server v5.7. Please set MySQL service name to `MySQL` during installation. 
* Make sure MySQL is up & running on `3306` port. Use `mysql -u root -p` in order to check whether you can login

## OH Web Service Installation
* Unzip `web-win64.zip` file into `C:\Programs\OHWeb` folder
* Go to `C:\Programs\OHWeb` folder
* If MySQL windows service is not `MySQL`, edit `depend` property in `WinSW.NET4.xml` and set the MySQL service id.  
* Install service by executing `WinSW.NET4.exe install`
* Start `OHWeb` Windows Service using windows tools 
* Open http://localhost:8080/status/health in the browser. If the service is working fine, you should be able to see the following in the browser:
```
{
  "status": "UP"
}
```

## OH Web API usage


