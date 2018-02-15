package mz.ciuem.uclinica.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import mz.ciuem.uclinica.dao.GenericDao;
import mz.ciuem.uclinica.entity.GenericEntity;
@Transactional
public abstract class GenericDaoImpl<T extends GenericEntity> implements GenericDao<T>{
	
	@PersistenceContext
	protected EntityManager entityMananger;
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	protected Class<T> type;
	
	@SuppressWarnings("unchecked")
	public GenericDaoImpl(){
		
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class<T>) pt.getActualTypeArguments()[0];
	}
	
	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	
	public long count() {
		
		String entity = type.getSimpleName();
		final StringBuffer queryString = new StringBuffer(
				"select count(ent) from " + entity + " ent");
		final Query query = this.entityMananger.createQuery(queryString.toString());

		return (Long) query.getSingleResult();
	}

	public T create(final T t) {
		entityMananger.persist(t);
		return t;
	}

	public void delete(final T t) {
		getCurrentSession().delete(t);
	}

	public T find(Long id) {
		return entityMananger.find(type, id);
	}

	public T update(final T t) {
		return entityMananger.merge(t);
	}
	
	public void  saveOrUpdate(T t){
		 getCurrentSession().saveOrUpdate(t);
		
	}
	

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		Query query = entityMananger.createQuery("from " + type.getName());
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public T last() {
		Query query = entityMananger.createQuery("from " + type.getName()
				+ " order by created desc");
		return (T) query.getResultList().get(0);
	}

	@SuppressWarnings("unchecked")
	public T first() {
		Query query = entityMananger.createQuery("from " + type.getName()
				+ " order by created asc");
		return (T) query.getResultList().get(0);
	}
	
	

}
