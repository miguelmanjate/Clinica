package mz.ciuem.uclinica.dao.exame;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mz.ciuem.uclinica.dao.impl.GenericDaoImpl;
import mz.ciuem.uclinica.entity.exame.Exame;
import mz.ciuem.uclinica.entity.paciente.Paciente;

@Repository
public class ExameDaoImpl extends GenericDaoImpl<Exame> implements ExameDao{

	@Override
	public List<Exame> getExamesDoPaciente(Paciente paciente) {
		
		Query query = getCurrentSession().createQuery("FROM Exame exame WHERE exame.paciente = :paciente ORDER BY exame.id DESC");
		query.setParameter("paciente", paciente);
		query.setMaxResults(20);
		return query.list();
	
	}

}
