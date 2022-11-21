# Getting Started

### Overview
This application has following REST endpoints:

* [POST] /api/v1/message 
	- to publish message to kafka topic and consumer will save it to db after validation
	
* [GET] /api/v1/message/{id} 
	- to get a single message by id
	
* [GET] /api/v1/messages/{pageNo}/{pageSize}?userId=dverma&topic=Telecom 
	- to get paginated list of messages, filters (userId & topic) are optional
	
* [DELETE] /api/v1/message/{id}
	- to delete message by id only if logged in user created that message

Following is the data.sql file which will load the H2 DB with some records to test our endpoints:
* src/main/resources/data.sql

Request body of message
	- 
	{
	    "topic": "Cooking",
	    "content": "So many youtube vloggers are posting video on cooking these days"
	}

### Setup and run this Reactive Springboot application with Docker
Following are the steps to setup and run the complete application if you have docker installed:

* Clone repository to local machine
	
* Set the below environment variable to provide forbidden words of your choice (you can also skip this step as there are few default forbidden words available in api)
	- message.forbidden.words and provide some comma separated words as value to this which will be used as forbidden words to validate content of message
		- for example, message.forbidden.words=password,secret

* Open command prompt in root directory (reactive-rest-message-service) of application and run below maven command
	- mvn clean install

* Run following command in same command prompt window to create docker image:
	- docker build --tag=reactive-rest-message-service:latest .

* Run following command to start zookeeper, kafka and springboot rest api via docker-compose.yml	
	- docker-compose up -d
	
* Import postman collection to Postman and hit endpoints

### Setup and run this Reactive Springboot application without Docker

* Download and install Apache Kafka to your local machine, run zookeeper and kafka server

* Clone repository to local machine

* Import the code to any IDE 

* Update kafka server config in application.properties
	- spring.kafka.bootstrap-servers=localhost:9092

* Import postman collection to Postman and hit endpoints

### Authentication via Spring security

* Following users are configured to access this api endpoints 
	- dverma, rsharma, mali, usaxena, mparupalli

* Password is same for all the users
	- password
	
* Use above credentials in Postman, Authorization tab -> Basic Auth -> Username & Password, to post messages from different userIds		

### Limitations

* H2 DB has been used instead of Mongo due to system limitation to run all containers (reactive api, kafka, db, docker) and other development tools together
* 