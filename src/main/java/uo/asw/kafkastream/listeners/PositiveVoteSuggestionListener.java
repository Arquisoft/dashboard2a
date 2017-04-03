package uo.asw.kafkastream.listeners;

import javax.annotation.ManagedBean;

import org.apache.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;

import uo.asw.kafkastream.Topics;

@ManagedBean(value = "positiveSuggestion")
public class PositiveVoteSuggestionListener {

	private static final Logger logger = Logger.getLogger(PositiveVoteSuggestionListener.class);

	@KafkaListener(topics = Topics.CREATE_COMMENT)
	public void listen(String data) {
		logger.info("New message received in PositiveVoteSuggestion: \"" + data + "\"");
	}
}