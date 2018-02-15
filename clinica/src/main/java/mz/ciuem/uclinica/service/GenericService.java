package mz.ciuem.uclinica.service;

import java.util.List;

public interface GenericService <T>{
	
	public long count();

    public T create(T t);

    public void delete(T id);

    public T find(Long id);
    
    public List<T> getAll();

    public T update(T t); 
    
    public void saveOrUpdate(T t); 

    public T first();
    
    public T last();

}
