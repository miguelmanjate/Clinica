package mz.ciuem.uclinica.service.impl.parametro;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mz.ciuem.uclinica.dao.impl.GenericDaoImpl;
import mz.ciuem.uclinica.entity.consulta.Medico;
@Repository
public class MedicoDaoImpl extends GenericDaoImpl<Medico> implements MedicoDao{

	@Override
	public Medico getMedicoPorNome(String nome) {
		
		Query query = getCurrentSession().createQuery("from Medico medico where medico.nome = :nome");
		query.setParameter("nome", nome);
		
		return (Medico) query.uniqueResult();
		
	}
	


}
