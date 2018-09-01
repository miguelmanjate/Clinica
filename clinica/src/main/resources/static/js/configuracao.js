$(function() {

	$("#data-consulta").datepicker({
		dateFormat : 'dd-mm-yy',
			minDate: 0
	}).datepicker("setDate","0");

	$(".timepicker").wickedpicker({
		twentyFour : true,
		title : 'Hora da Consulta'
	});

	$("#btn-agendar").click(function() {

		var data = $(".timepicker").val().trim();
		var date = new Date();
		date.setHours(data.substring(0, 2));
		date.setMinutes(data.substring(4, 7));
		date.setSeconds("0");

		$(".timepicker").val(date);

	});

	$(".data-table").DataTable();
	$("body").removeClass("skin-black").addClass("skin-blue");

	$(".multiple-select").chosen();

	dataTablePreviouAndNextSpace();

	$(".select").change(function() {
		sendAjaxRequest();

		// alert(url);
	});

	$(".select").chosen("destroy");

	$(".select")
			.append(
					'<option value="Selecione" selected="selected" disabled>Select an Option</option>');
	$(".select").chosen();

	validatePass();
});

function validatePass() {

	$('.save-btn').click(function(event) {

		$("#label-min-senha").css("display", "none");
		$("#label-min-senha-confirm").css("display", "none");

		data = $('#password').val();
		var len = data.length;

		if (len < 5) {
			$("#label-min-senha").css("display", "block");
			$("#password").val('');
			$("#confirmpassword").val('');
			event.preventDefault();

			return;
		}

		if ($('#password').val() != $('#confirmpassword').val()) {
			$("#label-min-senha-confirm").css("display", "block");
			$("#password").val('');
			$("#confirmpassword").val('');
			event.preventDefault();
			return;
		}

		$(".form").submit();

	});
}

function sendAjaxRequest() {

	var url = $(location).attr('href');

	var select = $(".select");
	
	var cursosSelect = $(".cursos");
	cursosSelect.chosen("destroy");

	var medicosSelect = $(".medicos");
	medicosSelect.chosen("destroy");

	var servicosSelect = $(".servicos");
	servicosSelect.chosen("destroy");
	
	var laboratorioSelect = $(".laboratorios");
	laboratorioSelect.chosen("destroy");

	var selected = select.find(":selected").text();

	$.get(url + "/cursos?faculdade=" + selected, function(data) {
		cursosSelect.empty();

		$.each(data, function(key, value) {
			cursosSelect.append('<option value=' + value.id + '>'
					+ value.descricao + '</option>');
		});

		cursosSelect.chosen();

	});

	$.get(url + "/medicos?especialidade=" + selected, function(data) {
		medicosSelect.empty();

		$.each(data, function(key, value) {
			medicosSelect.append('<option value=' + value.id + '>' + value.nome
					+ " " + value.apelido + '</option>');
		});

		medicosSelect.chosen();

	});

	$.get(url + "/servicos?especialidade=" + selected, function(data) {
		servicosSelect.empty();
 
		$.each(data, function(key, value) {
			$(".servicos").append(
					'<option value=' + value.id + '>' + value.descricao
							+ '</option>');
		});

		servicosSelect.chosen();

	});
	
	$.get(url + "/laboratorios?especialidade=" + selected, function(data) {
		laboratorioSelect.empty();

		$.each(data, function(key, value) {
			laboratorioSelect.append('<option value=' + value.id + '>' + value.descricao
					 + '</option>');
		});

		laboratorioSelect.chosen();

	});

}

function dataTablePreviouAndNextSpace() {

	$(".paginate_disabled_previous").css({
		"margin-right" : "40px",
		"margin-top" : "10px"
	});

	$(".paginate_disabled_next").css({
		"margin-right" : "40px",
		"margin-top" : "10px"
	});
	$(".dataTables_paginate").css({
		"margin-top" : "20px",
		"margin-bottom" : "20px",
		"font-weight" : "bold"
	});
}
