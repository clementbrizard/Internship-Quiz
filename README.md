#S R03 Project Internship

## Requirements
* Maven
* A mysql database named "db" running at port 8889 with root:root as credentials (with {M/W/L}AMP for example)
* MailDev installed (with docker eventually : docker pull djfarrelly/maildev && docker run -p 1080:80 -p 1025:25 djfarrelly/maildev )

## How to run
Simply run the following command within the directory :
~~~
mvn jetty:run
~~~

## Other useful command
~~~
mvn clean package
~~~
~~~
java -jar target/ProjectInternship-0.0.1-SNAPSHOT.jar
~~~

## Phpmyadmin 

http://localhost:8888/phpMyAdmin

Then go to http://localhost:8080
