package mz.ciuem.uclinica.dao.consulta;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mz.ciuem.uclinica.dao.impl.GenericDaoImpl;
import mz.ciuem.uclinica.entity.consulta.ItemConsultaServico;

@Repository
public class ItemConsultaServicoDaoImpl extends GenericDaoImpl<ItemConsultaServico> implements ItemConsultaServicoDao{

	@Override
	public List<ItemConsultaServico> buscarItensPorIdDaFactura(Long id) {
		
		Query query = getCurrentSession().createQuery("select item from ItemConsultaServico item join item.consulta consulta where consulta.id = :consulta_id");
		query.setParameter("consulta_id", id);
		return(List<ItemConsultaServico>) query.list();
	}



}
