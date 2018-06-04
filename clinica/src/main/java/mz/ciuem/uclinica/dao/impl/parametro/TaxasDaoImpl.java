package mz.ciuem.uclinica.dao.impl.parametro;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mz.ciuem.uclinica.dao.impl.GenericDaoImpl;
import mz.ciuem.uclinica.dao.parametro.TaxasDao;
import mz.ciuem.uclinica.entity.consulta.ItemConsultaServico;
import mz.ciuem.uclinica.entity.consulta.Taxa;
import mz.ciuem.uclinica.entity.parametro.Especialidade;
@Repository
public class TaxasDaoImpl extends GenericDaoImpl<Taxa> implements TaxasDao {

	@Override
	public List<Taxa> buscarTaxasPorIdServico(Long id) {
		Query query = getCurrentSession().createQuery("select taxa from Taxa taxa join taxa.servico servico where servico.id = :servico_id");
		query.setParameter("servico_id", id);
		return(List<Taxa>) query.list();
	}

	@Override
	public Taxa getTaxaPorTipoCliente(String tipoDeClinte) {
		
		Query query = getCurrentSession()
				.createQuery("from Taxa taxa where taxa.tipoCliente = :tipoCliente");
		query.setParameter("tipoCliente", tipoDeClinte);

		return (Taxa) query.uniqueResult();
	}

}
