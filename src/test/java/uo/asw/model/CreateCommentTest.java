package uo.asw.model;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import uo.asw.dbmanagement.model.Association;
import uo.asw.dbmanagement.model.Citizen;
import uo.asw.dbmanagement.model.Comment;
import uo.asw.dbmanagement.model.Suggestion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CreateCommentTest {

	private Citizen c1, c2, c3;
	private Suggestion co1, co2, co3;
	private Comment cm1, cm2, cm3;

	@Before
	public void setUp() {

		c1 = new Citizen("1234", "user1", "12356789", "user1", "surname1", new Date(), "user1@gmail.com", "Oviedo",
				"Español");
		c2 = new Citizen("1234", "user2", "12356789", "user2", "surname2", new Date(), "user2@gmail.com", "Oviedo",
				"Español");
		c3 = new Citizen("1234", "user3", "12356789", "user3", "surname3", new Date(), "user3@gmail.com", "Oviedo",
				"Español");

		co1 = new Suggestion("1234", "Nueva sugerencia 1", "Esta es la nueva sugerencia 1", 1);
		co2 = new Suggestion("5678", "Nueva sugerencia 2", "Esta es la nueva sugerencia 2", 2);
		co3 = new Suggestion("91011", "Nueva sugerencia 3", "Esta es la nueva sugerencia 3", 3);

		cm1 = new Comment("1234", c1, co1);
		cm2 = new Comment("567", c2, co2);
		cm3 = new Comment("91011", c3, co3);
		
	}

	@Test
	public void testUnlik() {
		assertNotNull(cm1.getCitizen());
		assertEquals(1, c1.getComments().size());
		Association.CreateComment.unlink(cm1);
		assertNull(cm1.getCitizen());
		assertEquals(0, c1.getComments().size());

		assertNotNull(cm2.getCitizen());
		assertEquals(1, c2.getComments().size());
		Association.CreateComment.unlink(cm2);
		assertNull(cm2.getCitizen());
		assertEquals(0, c2.getComments().size());
		
		assertNotNull(cm3.getCitizen());
		assertEquals(1, c3.getComments().size());
		Association.CreateComment.unlink(cm3);
		assertNull(cm3.getCitizen());
		assertEquals(0, c3.getComments().size());
	}
	
	
	@Test
	public void testLink(){
		assertNotNull(cm1.getCitizen());
		assertEquals(1, c1.getComments().size());
		
		new Comment("abscf", c1, co1);
		new Comment("abscsasf", c1, co1);
		new Comment("ab5454scf", c1, co1);
		new Comment("abakl45scf", c1, co1);
		
		assertEquals(5, c1.getComments().size());
	}
}
