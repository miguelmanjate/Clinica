package mz.ciuem.uclinica.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.uclinica.dao.PessoaDao;
import mz.ciuem.uclinica.entity.Pessoa;
import mz.ciuem.uclinica.service.PessoaService;
@Service("pessoaService")
public class PessoaServiceImpl extends GenericServiceImpl<Pessoa> implements PessoaService{
	
	@Autowired
	private PessoaDao pessoaDao;
	
	

}
