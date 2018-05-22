package mz.ciuem.uclinica.auth.dao.impl;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.thymeleaf.exceptions.TemplateInputException;

import mz.ciuem.uclinica.dao.impl.GenericDaoImpl;
import mz.ciuem.uclinica.entity.paciente.Paciente;

@Repository
public class PacienteDaoImpl extends GenericDaoImpl<Paciente> implements PacienteDao {

	@PersistenceContext
	protected EntityManager entityMananger;

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
		
		long nid = 0;
		List<Paciente> pacientes = new ArrayList<>();

		try {
			nid = Long.parseLong(parametro);
			Query query = getCurrentSession().createQuery("from Paciente paciente where paciente.id=:nid");
		
			query.setParameter("nid", nid);
			Paciente paciente = (Paciente) query.uniqueResult();
			if(paciente.getNid() != 0){
				pacientes.add(paciente);
			}
			
		} catch (Throwable e) {
			System.err.println("dentro do catch");
		}

		return pacientes;

	}

}
