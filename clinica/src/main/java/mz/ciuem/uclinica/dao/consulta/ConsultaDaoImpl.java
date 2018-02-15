package mz.ciuem.uclinica.dao.consulta;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mz.ciuem.uclinica.dao.impl.GenericDaoImpl;
import mz.ciuem.uclinica.entity.consulta.Consulta;
import mz.ciuem.uclinica.entity.paciente.Paciente;

@Repository
public class ConsultaDaoImpl extends GenericDaoImpl<Consulta> implements ConsultaDao {

	@Override
	public List<Consulta> getConsultasDoPaciente(Paciente paciente) {
		
		Query query = getCurrentSession().createQuery("FROM Consulta consulta WHERE consulta.paciente = :paciente ORDER BY consulta.id DESC");
		query.setParameter("paciente", paciente);
		query.setMaxResults(20);
		return query.list();
	}

}
