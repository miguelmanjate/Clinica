package mz.ciuem.uclinica.entity.parametro;

import mz.ciuem.uclinica.entity.consulta.Consulta;

public class FacturaForm {
	
	private Consulta consulta;
	
	private Factura factura;

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	
	

}
