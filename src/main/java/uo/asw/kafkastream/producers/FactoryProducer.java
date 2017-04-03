package uo.asw.kafkastream.producers;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import uo.asw.dbmanagement.impl.CitizenRepository;
import uo.asw.dbmanagement.impl.CommentRepository;
import uo.asw.dbmanagement.impl.SuggestionRepository;
import uo.asw.dbmanagement.impl.VoteCommetRepository;
import uo.asw.dbmanagement.impl.VoteSuggestionRepository;
import uo.asw.dbmanagement.model.Citizen;
import uo.asw.dbmanagement.model.Comment;
import uo.asw.dbmanagement.model.Suggestion;
import uo.asw.dbmanagement.model.VoteComment;
import uo.asw.dbmanagement.model.VoteSuggestion;

public class FactoryProducer {

	@Autowired
	private CitizenRepository repCitizen;
	
	@Autowired
	private CommentRepository repComment;
	
	@Autowired 
	private SuggestionRepository repSuggestion;
	
	@Autowired
	private VoteCommetRepository repVoteComment;
	
	@Autowired
	private VoteSuggestionRepository repVoteSuggestion;
	
	private static FactoryProducer instance = new FactoryProducer();

	FactoryProducer() {
	}

	public static FactoryProducer getInstance() {
		return instance;
	}

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
	
	private Suggestion getRandomSuggestion(){
		List<Suggestion> s = repSuggestion.findAllSuggestion();
		return s.get(random(0, s.size()-1));
	}
	private Citizen getRamdomCitizen(){
		List<Citizen> c = repCitizen.findAllCitizen();
		return c.get(random(0, c.size()-1));
	}
	
	private Comment getRandomComment(){
		List<Comment> c = repComment.findAllComents();
		return c.get(random(0, c.size()-1));
	}
	
	private int random(int menor, int mayor) {
		return new Random().nextInt(mayor - menor + 1) + menor;
	}
}
