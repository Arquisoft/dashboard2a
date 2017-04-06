package uo.asw.model;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import uo.asw.dbmanagement.model.Association;
import uo.asw.dbmanagement.model.Citizen;
import uo.asw.dbmanagement.model.Comment;
import uo.asw.dbmanagement.model.Suggestion;
import uo.asw.dbmanagement.model.VoteComment;
import uo.asw.dbmanagement.model.VoteSuggestion;
import uo.asw.dbmanagement.model.types.VoteType;
import static org.junit.Assert.assertEquals;

public class VoteSuggestionTest {

		private Citizen c1, c2, c3;
		private Suggestion co1, co2, co3;
		private VoteSuggestion vs1, vs2, vs3, vs4, vs5, vs6, vs7;
		
		@Before
		public void setUp() {
			c1 = new Citizen("1234", "user1", "12356789", "user1", "surname1", new Date(), "user1@gmail.com", "Oviedo", "Español");
			c2 = new Citizen("1234", "user2", "12356789", "user2", "surname2", new Date(), "user2@gmail.com", "Oviedo", "Español");
			c3 = new Citizen("1234", "user3", "12356789", "user3", "surname3", new Date(), "user3@gmail.com", "Oviedo", "Español");
			
			
			co1 = new Suggestion("1234", "Nueva sugerencia 1", "Esta es la nueva sugerencia 1", 1);
			co2 = new Suggestion("5678", "Nueva sugerencia 2", "Esta es la nueva sugerencia 2", 2);
			co3 = new Suggestion("91011", "Nueva sugerencia 3", "Esta es la nueva sugerencia 3", 3);
		
			
			vs2 = new VoteSuggestion(c1, co2);
			vs2.setVote(VoteType.NEGATIVE);
			
			vs1 = new VoteSuggestion(c2, co1);
			vs1.setVote(VoteType.POSITIVE);
			
			vs3 = new VoteSuggestion(c3, co1);
			vs3.setVote(VoteType.NEGATIVE);
			
			vs4 = new VoteSuggestion(c1, co2);
			vs4.setVote(VoteType.POSITIVE);
			
			
			vs5 = new VoteSuggestion(c2, co3);
			vs5.setVote(VoteType.POSITIVE);
			
			vs6 = new VoteSuggestion(c3, co3);
			vs6.setVote(VoteType.POSITIVE);
			
			vs7 = new VoteSuggestion(c1, co1);
			vs7.setVote(VoteType.POSITIVE);
			
		}

		@Test
		public void testInit() {
			assertEquals(1, co1.getNegativeVotes());
			assertEquals(2, co1.getPositiveVotes());
			
			assertEquals(1, co2.getNegativeVotes());
			assertEquals(1, co2.getPositiveVotes());
			
			assertEquals(2, co3.getPositiveVotes());
			assertEquals(0, co3.getNegativeVotes());
		}

		@Test
		public void testUnlink(){
			assertEquals(1, co1.getNegativeVotes());
			assertEquals(2, co1.getPositiveVotes());
			
			Association.VoteToSuggestion.unlink(vs1);
			Association.VoteToSuggestion.unlink(vs7);
			
			assertEquals(0, co1.getPositiveVotes());
			
			Association.VoteToSuggestion.unlink(vs3);
			assertEquals(0, co1.getNegativeVotes());
		}
		
		@Test
		public void testLink(){
			
			new VoteSuggestion(c1, co1).setVote(VoteType.POSITIVE);
			new VoteSuggestion(c1, co1).setVote(VoteType.POSITIVE);
			new VoteSuggestion(c3, co1).setVote(VoteType.POSITIVE);
			new VoteSuggestion(c1, co1).setVote(VoteType.NEGATIVE);
			new VoteSuggestion(c2, co1).setVote(VoteType.NEGATIVE);
			new VoteSuggestion(c3, co1).setVote(VoteType.NEGATIVE);
			
			assertEquals(4, co1.getNegativeVotes());
			assertEquals(5, co1.getPositiveVotes());
			
			
		}

	}


