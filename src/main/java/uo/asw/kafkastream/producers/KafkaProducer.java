package uo.asw.kafkastream.producers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import uo.asw.dbmanagement.model.Citizen;
import uo.asw.dbmanagement.model.Comment;
import uo.asw.dbmanagement.model.Suggestion;
import uo.asw.dbmanagement.model.VoteComment;
import uo.asw.dbmanagement.model.VoteSuggestion;
import uo.asw.dbmanagement.repository.CitizenRepository;
import uo.asw.dbmanagement.repository.CommentRepository;
import uo.asw.dbmanagement.repository.SuggestionRepository;
import uo.asw.dbmanagement.repository.VoteCommentRepository;
import uo.asw.dbmanagement.repository.VoteSuggestionRepository;
import uo.asw.kafkastream.Topics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.annotation.ManagedBean;

@ManagedBean
public class KafkaProducer {

	private static final Logger logger = Logger.getLogger(KafkaProducer.class);

	@Autowired
	private CitizenRepository repCitizen;

	@Autowired
	private CommentRepository repComment;

	@Autowired
	private SuggestionRepository repSuggestion;

	@Autowired
	private VoteCommentRepository repVoteComment;

	@Autowired
	private VoteSuggestionRepository repVoteSuggestion;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Scheduled(cron = "*/5 * * * * *")
	public void sendNewComment() {
		send(Topics.CREATE_COMMENT, commentToJson(createComment()));
	}

	

	public void send(String topic, String data) {
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, data);
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onSuccess(SendResult<String, String> result) {
				logger.info("Success on sending message \"" + data + "\" to topic " + topic);
			}

			@Override
			public void onFailure(Throwable ex) {
				logger.error("Error on sending message \"" + data + "\", stacktrace " + ex.getMessage());
			}
		});
	}

	// Auxiliares

	public Comment createComment() {
		Comment c = new Comment(generateRandomCode(), getRamdomCitizen(), getRandomSuggestion(), "Comentario");
		repComment.save(c);
		return c;
	}

	public VoteComment createVoteComment() {
		VoteComment vc = new VoteComment(getRamdomCitizen(), getRandomComment());
		repVoteComment.save(vc);
		return vc;
	}

	public Suggestion createSuggestion() {
		Suggestion s = new Suggestion(generateRandomCode(), "SUGGESTION", "Esto es una propuesta", 2);
		repSuggestion.save(s);
		return s;
	}

	public VoteSuggestion createVoteSuggestion() {
		VoteSuggestion vs = new VoteSuggestion(getRamdomCitizen(), getRandomSuggestion());
		repVoteSuggestion.save(vs);
		return vs;
	}

	private String generateRandomCode() {
		return UUID.randomUUID().toString();
	}

	private Suggestion getRandomSuggestion() {
		List<Suggestion> s = repSuggestion.findAll();
		return s.get(random(0, s.size() - 1));
	}

	private Citizen getRamdomCitizen() {
		List<Citizen> c = repCitizen.findAll();
		return c.get(random(0, c.size() - 1));
	}

	private Comment getRandomComment() {
		List<Comment> c = repComment.findAll();
		return c.get(random(0, c.size() - 1));
	}

	private int random(int menor, int mayor) {
		return new Random().nextInt(mayor - menor + 1) + menor;
	}

	
	
	/**
	 * Convierte un comentario en JSON
	 * @param c comentario
	 * @return comentario en formato JSON
	 */
	private String commentToJson(Comment c){
		Map<String, Object> map = new HashMap<>();
		map.put("id", c.getId());
		map.put("citizen_id", c.getCitizen().getId());
		map.put("suggestion_id", c.getSuggestion().getId());
		map.put("description", c.getDescription());
		map.put("code", c.getCode());
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "";
		}
	}
}
