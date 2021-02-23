package kafka.consumer;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;



public class Consumer {
	public static void main(String[] args) {
		Properties configs = new Properties();
		configs.put("bootstrap.servers","localholst:8080");
		configs.put("group.id", "click_log_group");
		configs.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		configs.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		
		KafkaConsumer<String,String> consumer = new KafkaConsumer<String,String>(configs);
		
		consumer.subscribe(Arrays.asList("click_log"));
		
		//폴링 루프 구문  poll() 메소드가 포함된 무한루프  핵심 로직. 데이터를 가져옴.
		//poll 시간동안 데이터를 기다림.
		while(true) {
			@SuppressWarnings("deprecation")
			ConsumerRecords<String,String> records = consumer.poll(50000);
			for(ConsumerRecord<String,String> record :records) {
				System.out.println("Consumer : " + record.value() + "" + record.key());
			}
		}
		
//		 특정 파티션의 데이터만 가져오고 싶다면
//		TopicPartition partition0 = new TopicPartition(topicName,0);
//		TopicPartition partition1 = new TopicPartition(topicName,1);
//		consumer.assign(Arrays.asList(partition0,partition1));
		
		
	}
}
