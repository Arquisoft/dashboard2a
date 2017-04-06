package uo.asw.others;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import uo.asw.Application;
import uo.asw.dbmanagement.GetParticipant;
import uo.asw.dbmanagement.GetUser;
import uo.asw.dbmanagement.model.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class DBTest {

	@Autowired
    private GetParticipant participant;
	
	@Autowired
    private GetUser user;

	@Test
    public void getExistingCitizen() throws Exception {
    	Citizen c1 = participant.getParticipant("user1", "1234");
    	Citizen c2 = participant.getParticipant("user2", "1234");

		assertEquals("user1", c1.getUserName());
		assertEquals("1234", c1.getPassword());
		
		assertEquals("user2", c2.getUserName());
		assertEquals("1234", c2.getPassword());
		

    	User u1 = user.getUser("alcalde", "1234");
    	User u2 = user.getUser("concejal1", "1234");

		assertEquals("alcalde", u1.getLogin());
		assertEquals("1234", u1.getPassword());
		
		assertEquals("concejal1", u2.getLogin());
		assertEquals("1234", u2.getPassword());
    }
	
	@Test
    public void getNonExistingCitizen() throws Exception {
    	Citizen c1 = participant.getParticipant("nonExistingUser1", "password1");
    	Citizen c2 = participant.getParticipant("no2ExistingUser1", "password2");

		assertNull(c1);
		assertNull(c2);

		User u1 = user.getUser("nonExistingUser3", "password3");
    	User u2 = user.getUser("nonExistingUser4", "password4");

		assertNull(u1);
		assertNull(u2);
    }
	

}
