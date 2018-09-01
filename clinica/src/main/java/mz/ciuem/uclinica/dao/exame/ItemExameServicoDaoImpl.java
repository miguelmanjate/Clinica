package mz.ciuem.uclinica.dao.exame;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mz.ciuem.uclinica.dao.impl.GenericDaoImpl;
import mz.ciuem.uclinica.entity.exame.ItemExameServico;

@Repository
public class ItemExameServicoDaoImpl extends GenericDaoImpl<ItemExameServico> implements ItemExameServicoDao {

	@Override
	public List<ItemExameServico> buscarItensPorIdDaFactura(Long id) {
		
		Query query = getCurrentSession().createQuery("select item from ItemExameServico item join item.exame exame where exame.id = :exame_id");
		query.setParameter("exame_id", id);
		return(List<ItemExameServico>) query.list();
	
		
	}

}
