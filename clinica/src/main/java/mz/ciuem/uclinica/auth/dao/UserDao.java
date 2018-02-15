package mz.ciuem.uclinica.auth.dao;

import mz.ciuem.uclinica.auth.User;

public interface UserDao {

	public User getUserByName(String login);

}
