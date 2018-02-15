$(function() {
	
	$("#data-consulta").datepicker({ dateFormat: 'dd-mm-yy' });

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
});
