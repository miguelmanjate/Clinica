package mz.ciuem.uclinica.auth.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mz.ciuem.uclinica.auth.Authorization;
import mz.ciuem.uclinica.auth.Permission;
import mz.ciuem.uclinica.auth.dao.PermissionDao;
import mz.ciuem.uclinica.dao.impl.GenericDaoImpl;
@Repository
public class PermissionDaoImpl extends GenericDaoImpl<Permission> implements PermissionDao {

	@Override
	public List<Permission> buscarPelaAutorizacao(Authorization roleAdmin) {
		
		Query query = getCurrentSession().createQuery("FROM Permission permission where permission.authorization = :auth");
		query.setParameter("auth", roleAdmin);
		return query.list() ;
	}

	@Override
	public Permission buscarPermissaoPorDesignacao(Authorization auth) {
		Query query = getCurrentSession().createQuery("FROM Permission permission where permission.authorization = :auth");
		query.setParameter("auth", auth);		
		return (Permission) query.uniqueResult();
	}



}
