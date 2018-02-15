package mz.ciuem.uclinica.auth.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mz.ciuem.uclinica.dao.impl.GenericDaoImpl;
import mz.ciuem.uclinica.entity.paciente.Paciente;

@Repository
public class PacienteDaoImpl extends GenericDaoImpl<Paciente> implements PacienteDao {

	@Override
	public List<Paciente> procurarPacientePorNome(String parametro) {

		Query query = getCurrentSession().createQuery("from Paciente paciente where paciente.nome like :nome");
		query.setParameter("nome", "%" + parametro + "%");
		query.setMaxResults(20);
		return query.list();
	}

	@Override
	public List<Paciente> procurarPacientePorCodigo(String parametro) {
		Query query = getCurrentSession().createQuery("from Paciente paciente where paciente.nome like :codigo");
		query.setParameter("codigo", parametro);
		query.setMaxResults(20);
		return query.list();
	}

	@Override
	public List<Paciente> procurarPacientePorNID(String parametro) {
		Query query = getCurrentSession().createQuery("from Paciente paciente where paciente.nome like :NID");
		query.setParameter("NID", parametro);
		query.setMaxResults(20);
		return query.list();
	}

}
