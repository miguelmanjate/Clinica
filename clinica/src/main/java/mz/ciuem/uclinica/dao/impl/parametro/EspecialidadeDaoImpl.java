package mz.ciuem.uclinica.dao.impl.parametro;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mz.ciuem.uclinica.dao.impl.GenericDaoImpl;
import mz.ciuem.uclinica.dao.parametro.EspecialidadeDao;
import mz.ciuem.uclinica.entity.parametro.Especialidade;
@Repository
public class EspecialidadeDaoImpl extends GenericDaoImpl<Especialidade> implements EspecialidadeDao{

	@Override
	public Especialidade getEspecialidadePorDesignacao(String descricao) {
		
			Query query = getCurrentSession()
					.createQuery("from Especialidade especialidade where especialidade.descricao = :descricao");
			query.setParameter("descricao", descricao);

			return (Especialidade) query.uniqueResult();
	
	}

}
