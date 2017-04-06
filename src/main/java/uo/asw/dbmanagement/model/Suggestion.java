package uo.asw.dbmanagement.model;

import uo.asw.dbmanagement.model.types.VoteType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
public class Suggestion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(unique = true)
	private String code;

	@NotNull
	private String title;
	private String description;
	private int minVotes;

	@ManyToOne
	private Citizen citizen;

	@OneToMany(mappedBy = "suggestion", fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
	private Set<Comment> comments = new HashSet<>();

	@ManyToOne
	private Category category;

	@OneToMany(mappedBy = "suggestion", fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
	private Set<VoteSuggestion> voteSuggestions = new HashSet<>();

	Suggestion() {
	}

	public Suggestion(String code) {
		this.code = code;
	}

	public Suggestion(String code, String title, String description, int minVotes) {
		this(code);
		this.title = title;
		this.description = description;
		this.minVotes = minVotes;
	}
	
	public Suggestion(Citizen ci, Category ca, String code, String title, String description, int minVotes) {
		this(code, title, description, minVotes);
		Association.CreateSuggestion.link(ci, this);
		Association.CategorySuggestion.link(ca, this);
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


	public int getPositiveVotes(){
		int count = 0;
		for (VoteSuggestion vs :
				voteSuggestions) {
			if(vs.getVote().equals(VoteType.POSITIVE)){
				count++;
			}
		}
		return count;
	}

	public int getNegativeVotes(){
		int count = 0;
		for (VoteSuggestion vs :
				voteSuggestions) {
			if(vs.getVote().equals(VoteType.NEGATIVE)){
				count++;
			}
		}
		return count;
	}

	public Map<String, Object> toMap(){
		Map<String, Object> map = new HashMap<>();
		map.put("id", this.getId());
		map.put("title", this.getTitle());
		map.put("category_id", this.getCategory().getId());
		map.put("code", this.getCode());
		map.put("description", this.getDescription());
		map.put("citizen", this.getCitizen().toMap());
		map.put("minVotes", this.getMinVotes());
		map.put("votes_positive", getPositiveVotes());
		map.put("votes_negative", getNegativeVotes());
		return map;
	}
	
}
