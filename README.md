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

### Setup and run this Reactive Springboot application
The following steps to setup:

* Set the environment variable 
	- message.forbidden.words and provide some comma separated words as value to this which will be used as forbidden words to validate content of message
		- for example, message.forbidden.words=password,secret

### Limitations

* H2 DB has been used instead of Mongo due to system limitation to run all containers (reactive api, kafka, db, docker) and other development tools together
* 