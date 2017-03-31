package uo.asw.dbmanagement.model;

import java.util.HashSet;
import java.util.Set;

public class Category {

	private Long id;
	private String name; // Unico
	
	private Set<Suggestion> suggestions = new HashSet<>();

	Category() {
	}

	
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public Set<Suggestion> getSuggestions(){
		return new HashSet<>(suggestions);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Category other = (Category) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}



	/* package */ Set<Suggestion> _getSuggestions() {
		return suggestions;
	}

}
