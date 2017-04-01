package uo.asw.kafkastream.listeners;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;

import javax.annotation.ManagedBean;

/**
 * Created by herminio on 28/12/16.
 */
@ManagedBean
public class MessageListener {

	private static final Logger logger = Logger.getLogger(MessageListener.class);
	public static List<String> con = new ArrayList<String>();

	@KafkaListener(topics = "exampleTopic")
	public void listen(String data) {
		con.add(data);
		logger.info("New message received: \"" + data + "\"");
	}

}
