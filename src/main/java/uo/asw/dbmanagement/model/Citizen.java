package uo.asw.dbmanagement.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Citizen {

	private Long id;
	private String password;
	private String userName;
	private String dni;
	private String name;
	private String surname;
	private Date bornDate;
	private String email;
	private String postAddress;
	private String nationality;

	private Set<Comment> comments = new HashSet<>();

	private Set<Suggestion> suggestions = new HashSet<>();

	private Set<VoteSuggestion> voteSuggestions = new HashSet<>();
	
	private Set<VoteComment> voteComments = new HashSet<>();

	Citizen() {
	}

	public Set<Comment> getComments() {
		return new HashSet<>(comments);
	}

	public Set<Suggestion> getSuggestions() {
		return new HashSet<>(suggestions);
	}
	
	/* package */ Set<Suggestion> _getSuggestions(){
		return suggestions;
	}

	public Citizen(String dni) {
		this.dni = dni;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBornDate() {
		return bornDate;
	}

	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPostAddress() {
		return postAddress;
	}

	public void setPostAddress(String postAddress) {
		this.postAddress = postAddress;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Long getId() {
		return id;
	}

	public String getDni() {
		return dni;
	}

	/* package */ Set<VoteSuggestion> _getVoteSuggestions() {
		return voteSuggestions;
	}

	public Set<VoteSuggestion> getVoteSuggestions() {
		return new HashSet<>(voteSuggestions);
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
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
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
		Citizen other = (Citizen) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Citizen [password=" + password + ", userName=" + userName + ", dni=" + dni + ", name=" + name
				+ ", surname=" + surname + ", bornDate=" + bornDate + ", email=" + email + ", postAddress="
				+ postAddress + ", nationality=" + nationality + "]";
	}

	/* package */ Set<Comment> _getComments() {
		return comments;
	}

}
