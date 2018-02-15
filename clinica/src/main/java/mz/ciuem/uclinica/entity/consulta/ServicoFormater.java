package mz.ciuem.uclinica.entity.consulta;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import mz.ciuem.uclinica.entity.parametro.Servico;

public class ServicoFormater implements Formatter<Servico>{

	
	@Override
		public String print(Servico servico, Locale locale) {
			return servico.getId().toString();
		}
	
		@Override
		public Servico parse(String id, Locale locale) throws ParseException {
			Servico servico = new Servico();
		servico.setId(Long.valueOf(id));
			return servico;
		}
}
