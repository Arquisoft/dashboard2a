package uo.asw.dashboard;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

	@RequestMapping("/userEjemplo")
	public UserInfo user() {
		return new UserInfo("pepe", 0);
	}

}