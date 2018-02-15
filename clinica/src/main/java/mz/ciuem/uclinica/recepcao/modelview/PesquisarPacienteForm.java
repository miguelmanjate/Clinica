package mz.ciuem.uclinica.recepcao.modelview;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class PesquisarPacienteForm {
	
	@NotNull(message = "Mandatorio informar o nome")
	@NotBlank(message = "Mandatorio informar o nome")
	private String parametroDePesquisa;
	private ParametrosDePesquisaPaciente tipo;
	
	public String getParametroDePesquisa() {
		return parametroDePesquisa;
	}
	public void setParametroDePesquisa(String parametroDePesquisa) {
		this.parametroDePesquisa = parametroDePesquisa;
	}

	public ParametrosDePesquisaPaciente getTipo() {
		return tipo;
	}
	public void setTipo(ParametrosDePesquisaPaciente tipo) {
		this.tipo = tipo;
	}
		

}
