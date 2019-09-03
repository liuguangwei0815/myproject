package com.quicky.demo.kafka;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Configuration
@EnableKafka
public class KafkaConfig {
	
	
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;//kafka服务配置
	@Value("${kafka.topic}")
	private  String topic;
	@Value("${kafka.groupId}")
	private String groupId;
	

	/**
	 * -- 生产 生产消息的工厂
	 * 
	 * @return
	 */
	public ProducerFactory<Integer, String> producerFactory() {
		return new DefaultKafkaProducerFactory<Integer, String>(producerConfigs());
	}

	/**
	 * 生产kafka配置，用于生产信息。
	 * 
	 * @return
	 */
	public Map<String, Object> producerConfigs() {
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("bootstrap.servers", bootstrapServers);
		props.put("acks", "all");
		props.put("retries", 0);//重试机制
		props.put("batch.size", 16384);//producer批量发送的基本单位，默认是16384Bytes 即16kB
		props.put("linger.ms", 1);//；lingger.ms是sender线程在检查batch是否ready时候，判断有没有过期的参数，默认大小是0ms
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		return props;
	}

	/**
	 * 生产信息模板 将生产配置信息放入模板中
	 */
	@Bean
	public KafkaTemplate<Integer, String> kafkaTemplate() {
		return new KafkaTemplate<Integer, String>(producerFactory());
	}

	/**
	 * 配置监听，将消费工厂信息配置进去
	 * 
	 * @return
	 */
	@Bean
	public ConcurrentKafkaListenerContainerFactory<Integer, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<Integer, String>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

	/**
	 * -- 消费 消费工厂，用于people那边的人员权限管理
	 * 
	 * @return
	 */
	@Bean
	public ConsumerFactory<Integer, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<Integer, String>(consumerConfigs());
	}

	// 消费配置
	public Map<String, Object> consumerConfigs() {
		HashMap<String, Object> props = new HashMap<String, Object>();
		props.put("bootstrap.servers", bootstrapServers);
		props.put("group.id", "mygroupid");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		return props;
	}

//	// 向Kafka推送信息 示列
//	public static void main(String[] args) {
//		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(KafkaConfig.class);
//		KafkaTemplate<Integer, String> kafkaTemplate = (KafkaTemplate<Integer, String>) ctx.getBean("kafkaTemplate");
//		String data = "";
//		// ListenableFuture 产生回调
//		ListenableFuture<SendResult<Integer, String>> send = kafkaTemplate.send(topic, data);
//		send.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
//			public void onFailure(Throwable throwable) {
//				System.out.println("失败");
//			}
//
//			public void onSuccess(SendResult<Integer, String> integerStringSendResult) {
//				System.out.println("成功");
//			}
//		});
//	}
}