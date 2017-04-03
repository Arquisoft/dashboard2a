package uo.asw.dbmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(unique = true)
	private String login;

	@NotNull
	private String password;

	// Para las vistas
	@NotNull
	private boolean viewCategories;
	@NotNull
	private boolean viewComments;
	@NotNull
	private boolean viewSuggestions;

	User() {
	}

	public User(String login) {
		this.login = login;
	}

	public User(String login, String password, boolean viewCategories, boolean viewComments, boolean viewSuggestions) {
		this(login);
		this.password = password;
		this.viewCategories = viewCategories;
		this.viewComments = viewComments;
		this.viewSuggestions = viewSuggestions;
	}

	public Long getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isViewCategories() {
		return viewCategories;
	}

	public void setViewCategories(boolean viewCategories) {
		this.viewCategories = viewCategories;
	}

	public boolean isViewComments() {
		return viewComments;
	}

	public void setViewComments(boolean viewComments) {
		this.viewComments = viewComments;
	}

	public boolean isViewSuggestions() {
		return viewSuggestions;
	}

	public void setViewSuggestions(boolean viewSuggestions) {
		this.viewSuggestions = viewSuggestions;
	}

	public String getLogin() {
		return login;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
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
		User other = (User) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [login=" + login + ", password=" + password + ", viewCategories=" + viewCategories
				+ ", viewComments=" + viewComments + ", viewSuggestions=" + viewSuggestions + "]";
	}

}
