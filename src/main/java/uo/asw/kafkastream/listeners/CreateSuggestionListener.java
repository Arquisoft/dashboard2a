package uo.asw.kafkastream.listeners;

import javax.annotation.ManagedBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.kafka.annotation.KafkaListener;

import uo.asw.dbmanagement.model.Suggestion;

import uo.asw.dbmanagement.repository.SuggestionRepository;
import uo.asw.kafkastream.Topics;

import java.io.IOException;

@ManagedBean(value = "createSuggestion")
public class CreateSuggestionListener  implements ApplicationEventPublisherAware {

	private static final Logger logger = Logger.getLogger(CreateSuggestionListener.class);

	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private SuggestionRepository suggestionRepository;

	@KafkaListener(topics = Topics.CREATE_SUGGESTION)
	public void listen(String data) {
		logger.info("New message received in CreateSuggestion: \"" + data + "\"");
		Suggestion s = null;
		try {
			s = mapper.readValue(data, Suggestion.class);
		}catch (IOException e){
			e.printStackTrace();
		}
		if(s!=null){
			s = suggestionRepository.findOne(s.getId());
			if(s != null)
				publisher.publishEvent(s);
		}
	}

	private ApplicationEventPublisher publisher;
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
	}
}
