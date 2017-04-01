package uo.asw.dbmanagement.model;

public class Association {

	public static class VoteToSuggestion {
		public static void link(Citizen c, VoteSuggestion v, Suggestion s) {
			v._setCitizen(c);
			v._setSuggestion(s);

			c._getVoteSuggestions().add(v);
			s._getVoteSuggestions().add(v);
		}

		public static void unlink(VoteSuggestion v) {
			v.getCitizen()._getVoteSuggestions().remove(v);
			v.getSuggestion()._getVoteSuggestions().remove(v);

			v._setCitizen(null);
			v._setSuggestion(null);
		}
	}

	public static class CreateSuggestion {
		public static void link(Citizen c, Suggestion s) {
			s._setCitizen(c);
			c._getSuggestions().add(s);
		}

		public static void unlink(Citizen c, Suggestion s) {
			c._getSuggestions().remove(s);
			s._setCitizen(null);
		}
	}

	public static class CreateComment {
		public static void link(Citizen ci, Comment comment, Suggestion s) {
			comment._setCitizen(ci);
			comment._setSuggestion(s);

			ci._getComments().add(comment);
			s._getComments().add(comment);
		}

		public static void unlink(Comment comment) {
			comment.getSuggestion()._getComments().remove(comment);
			comment.getCitizen()._getComments().remove(comment);

			comment._setSuggestion(null);
			comment._setCitizen(null);
		}
	}

	public static class CategorySuggestion {
		public static void link(Category c, Suggestion s) {
			s._setCategory(c);
			c._getSuggestions().add(s);
		}

		public static void unlink(Category c, Suggestion s) {
			c._getSuggestions().remove(s);
			s._setCategory(null);
		}
	}

	public static class VoteToComment {
		public static void link(Citizen ci, VoteComment vco, Comment com) {
			vco._setCitizen(ci);
			vco._setComment(com);

			ci._getVoteComments().add(vco);
			com._getVoteComments().add(vco);
		}

		public static void unlink(VoteComment v) {
			v.getCitizen()._getVoteComments().remove(v);
			v.getComment()._getVoteComments().remove(v);

			v._setCitizen(null);
			v._setComment(null);
		}
	}

}
