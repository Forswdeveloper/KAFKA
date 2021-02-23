package kafka.producer;

import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;



public class Producer {
	public static void main(String[] args) throws IOException{
		Properties configs = new Properties();
		configs.put("bootstrap.servers", "localhost:8080");
		configs.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		configs.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer"); 
		// key,value를 직렬화
		// Byte array,String,Integer 시리얼라이즈를 사용할 수 있다.
		// 키는 토픽의 파티션을 지정할 때쓰임.
		
		KafkaProducer<String,String> producer = new KafkaProducer<String,String>(configs);
		
		ProducerRecord record = new ProducerRecord<String,String>("click_log","Login"); // key없이 전송.
		// key  포함 전송 -> ("click_log","1","login");
		System.err.println(record.key() + " Producer Send " + record.value());
		producer.send(record);
		producer.close();
	}
}
