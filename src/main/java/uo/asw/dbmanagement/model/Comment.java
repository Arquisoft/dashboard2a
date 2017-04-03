package uo.asw.dbmanagement.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;



@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(unique = true)
	private String code;
	@NotNull
	private String description;

	@ManyToOne
	private Citizen citizen;

	@ManyToOne
	private Suggestion suggestion;

	@OneToMany(mappedBy = "comment" , fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<VoteComment> voteComments = new HashSet<>();

	Comment() {
	}

	public Comment(String code) {
		this.code = code;
	}

	public Comment(String code, String description) {
		this(code);
		this.description = description;
	}

	public Comment(String code, Citizen c, Suggestion s) {
		this(code);
		Association.CreateComment.link(c, this, s);
	}
	
	public Comment(String code, Citizen c, Suggestion s, String description){
		this(code, c, s);
		this.description = description;
	}

	public Citizen getCitizen() {
		return citizen;
	}

	public Suggestion getSuggestion() {
		return suggestion;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	/* package */ Set<VoteComment> _getVoteComments() {
		return voteComments;
	}

	public Set<VoteComment> getVoteComments() {
		return new HashSet<>(voteComments);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", code=" + code + ", description=" + description + "]";
	}

	/* package */ void _setCitizen(Citizen ci) {
		this.citizen = ci;
	}

	/* package */ void _setSuggestion(Suggestion s) {
		this.suggestion = s;
	}

}
