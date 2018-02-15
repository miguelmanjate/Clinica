package mz.ciuem.uclinica.user.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mz.ciuem.uclinica.auth.User;
import mz.ciuem.uclinica.auth.service.UserService;
import mz.ciuem.uclinica.test.GenericTest;

public class UserTest extends GenericTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void registar(){
		User user = new User();
		
		user.setEnabled(true);
		user.setUsername("miguel");
		user.setPassword("ambrosio2");
		
		userService.create(user);
		
	}

}
