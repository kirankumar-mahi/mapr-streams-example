package livestreaming.kafka;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import livestreaming.data.Data;

@Component
public class TestConsumer {
	
	@Autowired private SimpMessagingTemplate simpMessagingTemplate;
	@Autowired private Environment env;
	@Value("${topic.name}") private String topicName;
	
	@KafkaListener(topics = "${topic.name}")
	public void myKafkaConsumer(String record){

		try {
			Data data =new ObjectMapper().readValue(record, Data.class);
			simpMessagingTemplate.convertAndSend("/topic/data", data);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
