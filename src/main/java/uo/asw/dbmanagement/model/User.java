package uo.asw.dbmanagement.model;

public class User {
	
	private String login;
	private String password;
	
	//Para las vistas
	private boolean viewCategories;
	private boolean viewComments;
	private boolean viewSuggestions;
	
	User() {}
	
	public User(String login){
		this.login = login;
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
