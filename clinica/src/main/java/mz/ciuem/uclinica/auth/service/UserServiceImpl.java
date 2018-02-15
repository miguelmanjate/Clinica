package mz.ciuem.uclinica.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mz.ciuem.uclinica.auth.User;
import mz.ciuem.uclinica.auth.dao.UserDao;
import mz.ciuem.uclinica.service.impl.GenericServiceImpl;

@Service("userService")
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService,UserDetailsService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public User getUserByName(String login) {
		return userDao.getUserByName(login);
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		return getUserByName(login);
	}
	



}
