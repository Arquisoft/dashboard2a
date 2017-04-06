package uo.asw.kafkastream.listeners;

import javax.annotation.ManagedBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.kafka.annotation.KafkaListener;

import uo.asw.dbmanagement.model.VoteSuggestion;
import uo.asw.dbmanagement.repository.VoteSuggestionRepository;
import uo.asw.kafkastream.Topics;

import java.io.IOException;
import java.util.Map;

@ManagedBean(value = "negativeSuggestion")
public class NegativeVoteSuggestionListener implements ApplicationEventPublisherAware {

	private static final Logger logger = Logger.getLogger(NegativeVoteSuggestionListener.class);

	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private VoteSuggestionRepository voteSuggestionRepository;


	@SuppressWarnings("unchecked")
	@KafkaListener(topics = Topics.NEGATIVE_VOTE_SUGGESTION)
	public void listen(String data) {
		logger.info("New message received in NegativeSuggestion: \"" + data + "\"");
		VoteSuggestion vs = null;
		Map<String, Object> map = null;
		try{
			vs = mapper.readValue(data, VoteSuggestion.class);
			map = mapper.readValue(data, Map.class);
		}catch (IOException e){
			e.printStackTrace();
		}
		if(vs!=null){
			Long citizenId =Long.parseLong(map.get("citizen_id").toString());
			Long suggestionId = Long.parseLong(map.get("suggestion_id").toString());
			vs = voteSuggestionRepository.findByCitizenIdAndSuggestionId(citizenId, suggestionId);
			if(vs != null)
				publisher.publishEvent(vs);
		}
	}

	private ApplicationEventPublisher publisher;
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
	}
}
