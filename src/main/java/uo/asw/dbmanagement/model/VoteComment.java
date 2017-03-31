package uo.asw.dbmanagement.model;

import uo.asw.dbmanagement.model.types.VoteType;

public class VoteComment {
	
	private VoteType vote;
	
	private Citizen citizen;
	private Comment comment;
	
	public VoteComment(Citizen citizen, Comment comment){
		Association.VoteToComment.link(citizen, this, comment);
	}
	
	
	/* pacakage */ void _setCitizen(Citizen c){
		this.citizen = c;
	}
	
	/* package */ void _setComment(Comment c){
		this.comment = c;
	}


	public VoteType getVote() {
		return vote;
	}


	public Citizen getCitizen() {
		return citizen;
	}


	public Comment getComment() {
		return comment;
	}

}
