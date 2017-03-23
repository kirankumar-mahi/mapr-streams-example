# mapr-streams-example

live-mapr-streaming-producer is live stream producer and live-mapr-streaming-websocket project is Streams consumer. 

These projects are written using spring boot started and spring boot websocket. 

The producer reads the test.csv , consolidates the data and publishes to the topic

The consumer listens to the topic and rendors to the websocket which is registered.


To run the consumer

java -jar live-mapr-streaming-websocket-0.1.jar --spring.config.location=/home/mapr/live-mapr-streaming-websocket/application.properties 

To run the producer

java -jar live-mapr-streaming-producer-0.1.jar --spring.config.location=/home/mapr/live-mapr-streaming-producer/application.properties

check by opening the browser http://localhost:8099 
