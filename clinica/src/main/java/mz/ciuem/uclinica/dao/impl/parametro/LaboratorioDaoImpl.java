package mz.ciuem.uclinica.dao.impl.parametro;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mz.ciuem.uclinica.dao.impl.GenericDaoImpl;
import mz.ciuem.uclinica.dao.parametro.LaboratorioDao;
import mz.ciuem.uclinica.entity.consulta.Medico;
import mz.ciuem.uclinica.entity.exame.Laboratorio;

@Repository
public class LaboratorioDaoImpl extends GenericDaoImpl<Laboratorio> implements LaboratorioDao{

	@Override
	public Laboratorio getLaboratorioPorDescr(String descricao) {
		Query query = getCurrentSession().createQuery("from Laboratorio laboratorio where Laboratorio.descricao = :descricao");
		query.setParameter("descricao", descricao);
		
		return (Laboratorio) query.uniqueResult();
	}

}
