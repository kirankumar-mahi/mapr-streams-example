package livestreaming.kafka;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import livestreaming.data.Data;

@EnableAutoConfiguration
@Component
@ComponentScan
public class ProducerMain {
	
	@Autowired private Environment env;
	
	public void testProducer(Data data){
	      Properties props = new Properties();
			props.put("bootstrap.servers", env.getProperty("kafka.bootstrap.servers"));
			props.put("retries", 0);
			props.put("batch.size", 16384);
			props.put("linger.ms", 1);
			props.put("buffer.memory", 33554432);
			props.put("enable.auto.commit", "true");
			props.put("auto.commit.interval.ms", "1000");
			props.put("session.timeout.ms", "30000");
			props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
			props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	      
			KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
			ObjectMapper mapper = new ObjectMapper();
			String dataStr = null;
			try {
				dataStr = mapper.writeValueAsString(data);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			producer.send(new ProducerRecord<String, String>(env.getProperty("topic.name"), data.getxValue(),dataStr));
			producer.close();

	   }
	
	public void init() {
		ArrayList<String> lines = null;
		Data data = null;

		try {
			lines = (ArrayList) Files.readAllLines(Paths.get(env.getProperty("producer.data.path")),StandardCharsets.UTF_8);
			ArrayList<Double> colAPointsList = new ArrayList<Double>();
			ArrayList<Double> colBPointsList = new ArrayList<Double>();
			String previousValue = null;
			for (String line : lines) {
				String[] xValuesArr = line.split(",");
				if(previousValue == null){
					previousValue = xValuesArr[2];
				}
				if(previousValue.equals(xValuesArr[2])){
					colAPointsList.add(Double.parseDouble(xValuesArr[0]));
					colBPointsList.add(Double.parseDouble(xValuesArr[1]));
				}else{
					data = new Data(xValuesArr[2], colAPointsList.toArray(new Double[colAPointsList.size()]), colBPointsList.toArray(new Double[colBPointsList.size()]));
					testProducer(data);
					try {
						Thread.sleep(3000l);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					colAPointsList.clear();
					colBPointsList.clear();
					colAPointsList.add(Double.parseDouble(xValuesArr[0]));
					colBPointsList.add(Double.parseDouble(xValuesArr[1]));
					previousValue = xValuesArr[2];
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}
	
//	public void init() {
//		ArrayList<String> lines = null;
//		Data data = null;
//
//		try {
//			lines = (ArrayList) Files.readAllLines(Paths.get(env.getProperty("producer.data.path")),StandardCharsets.UTF_8);
//			ArrayList<Double> colAPointsList = new ArrayList<Double>();
//			ArrayList<Double> colBPointsList = new ArrayList<Double>();
//			int pointsLength = 0;
//			for (String line : lines) {
//				String[] xValuesArr = line.split(",");
//				if(pointsLength <= 5){
//					colAPointsList.add(Double.parseDouble(xValuesArr[0]));
//					colBPointsList.add(Double.parseDouble(xValuesArr[1]));
//					++pointsLength;
//				}else{
//					data = new Data(xValuesArr[2], colAPointsList.toArray(new Double[colAPointsList.size()]), colBPointsList.toArray(new Double[colBPointsList.size()]));				testProducer(data);
//					testProducer(data);
//					pointsLength = 0;
//					try {
//						Thread.sleep(800l);
//						colAPointsList.clear();
//						colBPointsList.clear();
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} 
//	}
}
