package mz.ciuem.uclinica.dao.impl.parametro;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mz.ciuem.uclinica.dao.impl.GenericDaoImpl;
import mz.ciuem.uclinica.dao.parametro.CursoDao;
import mz.ciuem.uclinica.entity.estudante.Curso;

@Repository
public class CursoDaoImpl extends GenericDaoImpl<Curso> implements CursoDao {

	@Override
	public Curso getCursoPorDescricao(String descricao) {
		
		Query query = getCurrentSession().createQuery("from Curso curso where curso.descricao = :descricao");
		query.setParameter("descricao", descricao);
		
		return (Curso) query.uniqueResult();
	}

}
