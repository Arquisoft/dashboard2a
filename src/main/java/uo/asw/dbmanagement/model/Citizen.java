package uo.asw.dbmanagement.model;

import java.util.Date;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Citizen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String password;
	@NotNull
	@Column(unique = true)
	private String userName;

	@NotNull
	@Column(unique = true)
	private String dni;
	private String name;
	private String surname;
	@Temporal(TemporalType.DATE)
	private Date bornDate;
	private String email;
	private String postAddress;
	private String nationality;

	@OneToMany(mappedBy = "citizen")
	private Set<Comment> comments = new HashSet<>();

	@OneToMany(mappedBy = "citizen")
	private Set<Suggestion> suggestions = new HashSet<>();

	@OneToMany(mappedBy = "citizen")
	private Set<VoteSuggestion> voteSuggestions = new HashSet<>();

	@OneToMany(mappedBy = "citizen")
	private Set<VoteComment> voteComments = new HashSet<>();

	Citizen() {
	}

	public Citizen(String dni) {
		this.dni = dni;
	}

	public Citizen(String password, String userName, String dni, String name, String surname, Date bornDate,
			String email, String postAddress, String nationality) {
		this(dni);
		this.password = password;
		this.userName = userName;
		this.name = name;
		this.surname = surname;
		this.bornDate = bornDate;
		this.email = email;
		this.postAddress = postAddress;
		this.nationality = nationality;
	}

	public Set<Comment> getComments() {
		return new HashSet<>(comments);
	}

	public Set<Suggestion> getSuggestions() {
		return new HashSet<>(suggestions);
	}

	/* package */ Set<Suggestion> _getSuggestions() {
		return suggestions;
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

    public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", this.getId());
		map.put("dni", this.getDni());
		map.put("name", this.getName());
		map.put("surname", this.getSurname());
		map.put("email", this.getEmail());
		return map;
    }
}