#SR03 Project Internship

## Requirements
* Maven
* A mysql database named "db" running at port 8889 with root:root as credentials (with {M/W/L}AMP for example)


## How to run
Simply run the following command within the directory :
~~~
mvn spring-boot:run
~~~
or
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

http://localhost:8888/phpMyAdmin/tbl_structure.php?server=1&db=db

Then go to http://localhost:8080