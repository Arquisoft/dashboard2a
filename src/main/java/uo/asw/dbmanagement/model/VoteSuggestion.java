package uo.asw.dbmanagement.model;

import uo.asw.dbmanagement.model.types.VoteType;

public class VoteSuggestion {
	
	private VoteType vote;
	
	private Citizen citizen;
	
	private Suggestion suggestion;
	
	public VoteSuggestion(Citizen c, Suggestion s){
		Association.VoteToSuggestion.link(c, this, s);
	}
	
	/* package */ void _setCitizen(Citizen c){
		this.citizen = c;
	}
	
	/* package */ void _setSuggestion(Suggestion s){
		this.suggestion = s;
	}

	public VoteType getVote() {
		return vote;
	}

	public Citizen getCitizen() {
		return citizen;
	}

	public Suggestion getSuggestion() {
		return suggestion;
	}
	
	

}
