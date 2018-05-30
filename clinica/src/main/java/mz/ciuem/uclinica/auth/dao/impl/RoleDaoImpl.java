package mz.ciuem.uclinica.auth.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mz.ciuem.uclinica.auth.Role;
import mz.ciuem.uclinica.auth.dao.RoleDao;
import mz.ciuem.uclinica.dao.impl.GenericDaoImpl;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {

	@Override
	public Role findByname(String rolename) {
		Query query = getCurrentSession().createQuery("From Role role where role.rolename = :rolename");
		query.setParameter("rolename", rolename);
		return (Role) query.uniqueResult();
	}

}
