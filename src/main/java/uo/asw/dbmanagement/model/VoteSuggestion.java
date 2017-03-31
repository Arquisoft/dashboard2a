package uo.asw.dbmanagement.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import uo.asw.dbmanagement.model.types.VoteSuggestionKey;
import uo.asw.dbmanagement.model.types.VoteType;

@Entity
@IdClass(VoteSuggestionKey.class)
public class VoteSuggestion {

	@Enumerated(EnumType.STRING)
	private VoteType vote;

	@Id
	@ManyToOne
	private Citizen citizen;

	@Id
	@ManyToOne
	private Suggestion suggestion;

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

	public Citizen getCitizen() {
		return citizen;
	}

	public Suggestion getSuggestion() {
		return suggestion;
	}

}
