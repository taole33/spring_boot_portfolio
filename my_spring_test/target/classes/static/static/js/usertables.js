$(function(){
	$("#user-table").dataTable({
		"language":{
			"url":"/webjars/datatables-plugins/i18n/Japanese.json"
		},
		
		dom:'Bfrtip',
		buttons:[
			'excelHtml5',
			'csvHtml5',
			'print'
		]	
	})
	
});


/**
 * 
 */