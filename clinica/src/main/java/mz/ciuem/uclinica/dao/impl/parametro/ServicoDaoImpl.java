package mz.ciuem.uclinica.dao.impl.parametro;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mz.ciuem.uclinica.dao.impl.GenericDaoImpl;
import mz.ciuem.uclinica.dao.parametro.ServicoDao;
import mz.ciuem.uclinica.entity.parametro.Servico;

@Repository
public class ServicoDaoImpl extends GenericDaoImpl<Servico> implements ServicoDao{

	@Override
	public List<Servico> getServicosComSuasTaxas() {
		
		Query query = getCurrentSession().createQuery("from Servico servico LEFT JOIN FETCH servico.taxas ");
		query.setMaxResults(100);
		return query.list();
	}

}
