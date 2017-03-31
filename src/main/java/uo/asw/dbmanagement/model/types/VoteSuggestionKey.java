package uo.asw.dbmanagement.model.types;

import java.io.Serializable;

public class VoteSuggestionKey implements Serializable {

	private static final long serialVersionUID = 1L;

	Long citizen;

	Long suggestion;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((citizen == null) ? 0 : citizen.hashCode());
		result = prime * result + ((suggestion == null) ? 0 : suggestion.hashCode());
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
		VoteSuggestionKey other = (VoteSuggestionKey) obj;
		if (citizen == null) {
			if (other.citizen != null)
				return false;
		} else if (!citizen.equals(other.citizen))
			return false;
		if (suggestion == null) {
			if (other.suggestion != null)
				return false;
		} else if (!suggestion.equals(other.suggestion))
			return false;
		return true;
	}

}
