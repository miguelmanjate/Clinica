package mz.ciuem.uclinica.auth.service;

import mz.ciuem.uclinica.auth.User;
import mz.ciuem.uclinica.service.GenericService;

public interface UserService extends GenericService<User>{
	
	public User getUserByName(String login);


}