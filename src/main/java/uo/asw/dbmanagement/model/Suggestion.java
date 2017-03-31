package uo.asw.dbmanagement.model;

import java.util.HashSet;
import java.util.Set;

public class Suggestion {

	private Long id;
	private String code;
	private String title;
	private String description;
	private int minVotes;

	private Citizen citizen;

	private Set<Comment> comments = new HashSet<>();

	private Category category;
	
	private Set<VoteSuggestion> voteSuggestions = new HashSet<>();

	Suggestion() {
	}

	public Suggestion(String code) {
		this.code = code;
	}

	public Citizen getCitizen() {
		return citizen;
	}

	

	public Category getCategory() {
		return category;
	}

	

	public Set<Comment> getComments() {
		return comments;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMinVotes() {
		return minVotes;
	}

	public void setMinVotes(int minVotes) {
		this.minVotes = minVotes;
	}

	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}
	
	/* package */ Set<VoteSuggestion> _getVoteSuggestions() {
		return voteSuggestions;
	}

	public Set<VoteSuggestion> getVoteSuggestions() {
		return new HashSet<>(voteSuggestions);
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
		Suggestion other = (Suggestion) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Suggestion [id=" + id + ", code=" + code + ", title=" + title + ", description=" + description
				+ ", minVotes=" + minVotes + "]";
	}

	/* package */ void _setCitizen(Citizen c) {
		this.citizen = c;		
	}

	/* package */ Set<Comment> _getComments() {
		return comments;
	}

	/* package */ void _setCategory(Category c) {
		this.category = c;		
	}

}
