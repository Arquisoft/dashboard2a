package uo.asw.model;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import uo.asw.dbmanagement.model.Association;
import uo.asw.dbmanagement.model.Category;
import uo.asw.dbmanagement.model.Citizen;
import uo.asw.dbmanagement.model.Suggestion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CreateSuggestionTest {

	private Category c1, c2, c3;
	private Citizen ci1, ci2, ci3;
	private Suggestion s1, s2, s3;
	
	@Before
	public void setUp(){
		c1 = new Category("Categoria 1");
		c2 = new Category("Categoria 2");
		c3 = new Category("Categoria 3");
		
	
		ci1 = new Citizen("1234", "user1", "sadf6789as", "user1", "surname1", new Date(), "user1@gmail.com", "Oviedo", "Español");
		ci2 = new Citizen("1234", "user2", "12adsf6789gd", "user2", "surname2", new Date(), "user2@gmail.com", "Oviedo", "Español");
		ci3 = new Citizen("1234", "user3", "8255asdf89lg", "user3", "surname3", new Date(), "user3@gmail.com", "Oviedo", "Español");
		
		s1 = new Suggestion(ci1, c1, "asfjsklf", "Sugestion1", "Description1", 1);
		s2 = new Suggestion(ci2, c2, "12345", "Sugestion2", "Description1", 1);
		s3 = new Suggestion(ci3, c3, "1asfc54sda", "Sugestion3", "Description1", 1);
		
	}
	
	@Test
	public void testUnlink(){
	
		assertEquals(1, ci1.getSuggestions().size());
		assertNotNull(s1.getCitizen());
		
		Association.CreateSuggestion.unlink(ci1, s1);
		
		assertEquals(0, ci1.getSuggestions().size());
		assertNull(s1.getCitizen());
		
		assertEquals(1, ci2.getSuggestions().size());
		assertNotNull(s2.getCitizen());
		
		Association.CreateSuggestion.unlink(ci2, s2);
		
		assertEquals(0, ci2.getSuggestions().size());
		assertNull(s2.getCitizen());
		
		assertEquals(1, ci3.getSuggestions().size());
		assertNotNull(s3.getCitizen());
		
		Association.CreateSuggestion.unlink(ci3, s3);
		
		assertEquals(0, ci3.getSuggestions().size());
		assertNull(s3.getCitizen());
		
	}
	
	
	
	@Test
	public void testLink(){
		
		assertEquals(1, ci1.getSuggestions().size());
		assertNotNull(s1.getCitizen());
		
		new Suggestion(ci1, c1, "asfjskl778f", "Sugestion1", "Description1", 1);
		new Suggestion(ci1, c2, "asfjskajksflf", "Sugestion1", "Description1", 1);
		new Suggestion(ci1, c3, "asfj787654sklf", "Sugestion1", "Description1", 1);
		new Suggestion(ci1, c1, "asfjs5as45klf", "Sugestion1", "Description1", 1);
		
		assertEquals(5, ci1.getSuggestions().size());
		
	}
}
