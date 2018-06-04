package mz.ciuem.uclinica.user.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mz.ciuem.uclinica.entity.consulta.Taxa;
import mz.ciuem.uclinica.entity.paciente.TipoDePaciente;
import mz.ciuem.uclinica.service.parametro.ServicoService;
import mz.ciuem.uclinica.service.parametro.TaxasServie;
import mz.ciuem.uclinica.test.GenericTest;

public class UserTest extends GenericTest {
	
	@Autowired
	private TaxasServie taxasServie;
	
	@Autowired
	private ServicoService servicoService;
	
//	@Autowired
//	private UserService userService;
//	
//	@Test
//	public void registar(){
//		User user = new User();
//		
//		user.setEnabled(true);
//		user.setUsername("miguel");
//		user.setPassword("ambrosio2");
//		
//		userService.create(user);
//		
//	}

	
	@Test
	public void getTaxaPorTipoCliente(){
		
		List<Taxa> taxas = taxasServie.buscarTaxasPorIdServico(20l);
		Taxa taxa = null;
		double d = 0;
		for(Taxa t : taxas){
			System.err.println(t.getTipoCliente());	
				
			 if(t.getTipoCliente().toString().equals(TipoDePaciente.PACIENTE_ESTUDANTE.toString())){
				 taxa = t;
			 }
		}
		
		if( taxa != null){
			d = taxa.getTaxaPorTipoConsulta("NORMAL");
		}
		System.err.println(taxa);	
		System.err.println(d);	
	}
}
