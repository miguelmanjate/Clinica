package mz.ciuem.uclinica.conf;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mz.ciuem.uclinica.auth.User;
import mz.ciuem.uclinica.auth.dao.UserDao;
import mz.ciuem.uclinica.dao.impl.GenericDaoImpl;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao{

	@Override
	public User getUserByName(String login) {
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT user FROM User user where user.username = :username");
		query.setParameter("username", login);
		return (User) query.uniqueResult();
	}
	
	
	
	
	
	
	

}
