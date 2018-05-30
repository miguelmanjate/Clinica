package mz.ciuem.uclinica.dao.impl.parametro;

import org.springframework.stereotype.Repository;
import org.hibernate.Query;

import mz.ciuem.uclinica.dao.impl.GenericDaoImpl;
import mz.ciuem.uclinica.dao.parametro.FaculdadeDao;
import mz.ciuem.uclinica.entity.estudante.Faculdade;
@Repository
public class FaculdadeDaoImpl extends GenericDaoImpl<Faculdade> implements FaculdadeDao{

	@Override
	public Faculdade getFaculdadePorDescricao(String descricao) {
		
		Query query = getCurrentSession()
				.createQuery("from Faculdade faculdade where faculdade.descricao = :descricao");
		query.setParameter("descricao", descricao);
		
		return (Faculdade) query.uniqueResult();
	}

}
