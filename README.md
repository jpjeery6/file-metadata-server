# File Metadata Storage solution - stores and computes client's file system directory statistics.
This is a client server application where there is a file statistics server which is running. Clients can POST their filesystem metadata information and the server will compute various statistics and persist it.

Each Client will have need a have a unique client_id which is identified by the server. By default a random fixed id is generated. New Clients need to use a unique id (can be provided at runtime).

## To run the server: 
java -jar FileStatisticsServer-0.0.1-SNAPSHOT.jar --server.port=<port_num>

eg: java -jar FileStatisticsServer-0.0.1-SNAPSHOT.jar --server.port=8080

## REST endpoints:
POST: <server_url>/uploadfilestats 

GET:  <server_url>/getfilestats

GET:  <server_url>/getservercomputedstats


## To run the client:
java -jar file-stats-client-1.0-SNAPSHOT.jar --path=<input_filesystem_path> --serverUrl=<valid_url> --clientId=<unique_integer_id>

eg: java -jar file-stats-client-1.0-SNAPSHOT.jar --path=/home/user/test_dir --serverUrl=http://localhost:8080


clientId is optional. If not provided default value=10011, So all updates will happen at 10011 only.

Client is available here: https://github.com/jpjeery6/file-metadata-client


Client runs multi-threading for making rest calls. Default MAX_NUM_OF_THREADS=3




## Features and Tech Stack Used:

Client side: Java maven project - jackson library for Object mapping, java's native Executor service for multi threading, java's native HTTP classes for REST call

Server side: Java Spring boot application with embedded Tomcat server - spring web, spring data jpa, h2 database.
