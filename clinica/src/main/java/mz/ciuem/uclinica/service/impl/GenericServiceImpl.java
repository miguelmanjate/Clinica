package mz.ciuem.uclinica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import mz.ciuem.uclinica.dao.GenericDao;
import mz.ciuem.uclinica.entity.GenericEntity;
import mz.ciuem.uclinica.service.GenericService;
@Transactional
public abstract class GenericServiceImpl<T extends GenericEntity> implements GenericService<T> {

	@Autowired
	private GenericDao<T> specificDao;

	public long count() {
		return specificDao.count();
	}

	public T create(T t) {
		return specificDao.create(t);
	}

	public void delete(T id) {
		specificDao.delete(id);
	}

	public T find(Long id) {
		return specificDao.find(id);
	}

	public List<T> getAll() {
		return specificDao.getAll();
	}

	public T update(T t) {
		return specificDao.update(t);
	}

	public void saveOrUpdate(T t) {
		specificDao.saveOrUpdate(t);
	}

	public T first() {
		return specificDao.first();
	}

	public T last() {
		return specificDao.last();
	}

}
