package uo.asw.dbmanagement.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import uo.asw.dbmanagement.model.types.VoteCommentKey;
import uo.asw.dbmanagement.model.types.VoteType;

@Entity
@IdClass(VoteCommentKey.class)
public class VoteComment {

	@Enumerated(EnumType.STRING)
	private VoteType vote;

	@Id
	@ManyToOne
	@JoinColumn(name = "citizen_id", referencedColumnName = "id")
	private Citizen citizen;

	@Id
	@ManyToOne
	@JoinColumn(name = "comment_id", referencedColumnName = "id")
	private Comment comment;

	VoteComment() {

	}

	public VoteComment(Citizen citizen, Comment comment) {
		Association.VoteToComment.link(citizen, this, comment);
	}

	/* pacakage */ void _setCitizen(Citizen c) {
		this.citizen = c;
	}

	/* package */ void _setComment(Comment c) {
		this.comment = c;
	}

	public VoteType getVote() {
		return vote;
	}

	public void setVote(VoteType type) {
		this.vote = type;
	}

	public Citizen getCitizen() {
		return citizen;
	}

	public Comment getComment() {
		return comment;
	}

}
