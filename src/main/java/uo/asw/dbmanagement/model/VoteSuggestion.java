package uo.asw.dbmanagement.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import uo.asw.dbmanagement.model.types.VoteSuggestionKey;
import uo.asw.dbmanagement.model.types.VoteType;

import java.util.Map;
import java.util.HashMap;

@Entity
@IdClass(VoteSuggestionKey.class)
public class VoteSuggestion {

	@Enumerated(EnumType.STRING)
	private VoteType vote;

	@Id
	@ManyToOne
	@JoinColumn(name = "citizen_id", referencedColumnName = "id")
	private Citizen citizen;

	@Id
	@ManyToOne
	@JoinColumn(name = "suggestion_id", referencedColumnName = "id")
	private Suggestion suggestion;

	VoteSuggestion() {
	}
	
	public VoteSuggestion(Citizen c, Suggestion s) {
		Association.VoteToSuggestion.link(c, this, s);
	}

	/* package */ void _setCitizen(Citizen c) {
		this.citizen = c;
	}

	/* package */ void _setSuggestion(Suggestion s) {
		this.suggestion = s;
	}

	public VoteType getVote() {
		return vote;
	}

	public void setVote(VoteType type){
		this.vote = type;
	}
	
	public Citizen getCitizen() {
		return citizen;
	}

	public Suggestion getSuggestion() {
		return suggestion;
	}

    public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", vote);
		map.put("citizen", citizen.toMap());
		map.put("suggestion", suggestion.toMap());
		return map;
    }
}
