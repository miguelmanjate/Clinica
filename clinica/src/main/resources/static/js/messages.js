
$(function(){
	
	messagens();
});


function messagens() {

	if ($('.mgs').is(":visible")) {

		swal("Registo de Especialidade!",
				"Especialidade Registada com Sucesso...", "success");

	}

	if ($('.mgs1-tipopaciente').is(":visible")) {

		swal("Registo de Tipo de Paciente!",
				"Novo tipo de Paciente foi Registada com Sucesso...", "success");

	}

	if ($('.mgs1-consulta').is(":visible")) {

		swal("Agendamento de consultas!",
				"Nova consulta foi registada com sucesso...", "success");

	}

	if ($('.msg1-paciente').is(":visible")) {

		swal("Registo de Pacientes!",
				"Novo Paciente Registado com com sucesso...", "success");

	}

	if ($('.msg1-medico').is(":visible")) {

		swal("Registo de Médico!", "Novo Médico Registado com com sucesso...",
				"success");

	}

	if ($('.messageDuplicacaoConsulta').is(":visible")) {

		swal(
				"Erro – Duplicação de Consultas",
				"Não deve-se agendar mais do que uma consulta para um Paciente na mesma data...",
				"error");

	}

	if ($('.msg1-medico-update').is(":visible")) {

		swal("Actualizacão de Médico!",
				"Dados do Médico actualizados com sucesso...", "success");

	}

	if ($('.msgSemTaxas').is(":visible")) {

		swal(
				"Taxas do Serviço não configuradas !",
				"Não foi possível agendar a consulta pois para o serviço seleccionado não foram definidas as taxas.",
				"error");

	}
	
	if ($('.msgSemTaxas').is(":visible")) {

		swal(
				"Taxas do Serviço não configuradas !",
				"Não foi possível agendar a consulta pois para o serviço seleccionado não foram definidas as taxas.",
				"error");

	}
	
	
		if ($('.message-save-user').is(":visible")) {

			swal(
					"Registo de Utilizador !",
					"Novo Utilizador foi registado com sucesso!",
					"success");

		}
		
		if ($('.message-update-user').is(":visible")) {

			swal(
					"Actualização  de Utilizador !",
					"Os dados do utilizador foram actualizado com sucesso!",
					"success");

		}

}