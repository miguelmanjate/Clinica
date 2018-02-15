package mz.ciuem.uclinica.test.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mz.ciuem.uclinica.auth.dao.PermissionDao;
import mz.ciuem.uclinica.entity.Pessoa;
import mz.ciuem.uclinica.service.PessoaService;
import mz.ciuem.uclinica.test.GenericTest;

public class PessoaServiceTest extends GenericTest{
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PermissionDao permissioDao;
	
	@Test
	public void create (){
		Pessoa pessoa  = new Pessoa();
		pessoaService.create(pessoa);
		Assert.assertNotNull(pessoa);
		
	}

}
