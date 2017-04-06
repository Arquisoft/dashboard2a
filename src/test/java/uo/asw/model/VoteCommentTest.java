package uo.asw.model;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;



import uo.asw.dbmanagement.model.*;
import uo.asw.dbmanagement.model.types.VoteType;

public class VoteCommentTest {

	private Citizen c1, c2, c3;
	private Comment co1, co2, co3;
	private VoteComment vs1, vs2, vs3, vs4, vs5, vs6, vs7;
	
	@Before
	public void setUp() {
		c1 = new Citizen("1234", "user1", "12356789", "user1", "surname1", new Date(), "user1@gmail.com", "Oviedo", "Español");
		c2 = new Citizen("1234", "user2", "12356789", "user2", "surname2", new Date(), "user2@gmail.com", "Oviedo", "Español");
		c3 = new Citizen("1234", "user3", "12356789", "user3", "surname3", new Date(), "user3@gmail.com", "Oviedo", "Español");
		
		
		co1 = new Comment("1234", "Buena sugerencia");
		co2 = new Comment("567", "Mala sugerencia");
		co3 = new Comment("91011", "Mala sugerencia");
		
		vs2 = new VoteComment(c1, co2);
		vs2.setVote(VoteType.NEGATIVE);
		
		vs1 = new VoteComment(c2, co1);
		vs1.setVote(VoteType.POSITIVE);
		
		vs3 = new VoteComment(c3, co1);
		vs3.setVote(VoteType.NEGATIVE);
		
		vs4 = new VoteComment(c1, co2);
		vs4.setVote(VoteType.POSITIVE);
		
		
		vs5 = new VoteComment(c2, co3);
		vs5.setVote(VoteType.POSITIVE);
		
		vs6 = new VoteComment(c3, co3);
		vs6.setVote(VoteType.POSITIVE);
		
		vs7 = new VoteComment(c1, co1);
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
		
		Association.VoteToComment.unlink(vs1);
		Association.VoteToComment.unlink(vs7);
		
		assertEquals(0, co1.getPositiveVotes());
		
		Association.VoteToComment.unlink(vs3);
		assertEquals(0, co1.getNegativeVotes());
	}
	
	@Test
	public void testLink(){
		
		new VoteComment(c1, co1).setVote(VoteType.POSITIVE);
		new VoteComment(c2, co1).setVote(VoteType.POSITIVE);
		new VoteComment(c3, co1).setVote(VoteType.POSITIVE);
		new VoteComment(c1, co1).setVote(VoteType.NEGATIVE);
		new VoteComment(c2, co1).setVote(VoteType.NEGATIVE);
		new VoteComment(c3, co1).setVote(VoteType.NEGATIVE);
		
		assertEquals(4, co1.getNegativeVotes());
		assertEquals(5, co1.getPositiveVotes());
		
		
	}

}
