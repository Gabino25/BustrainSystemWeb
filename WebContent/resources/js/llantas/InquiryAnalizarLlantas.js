/**
 * 
 */

$(document).ready(function() {
	  $.ajaxSetup({async: false}); 
	  
     	$.datepicker.regional['es'] = {
			closeText: "Cerrar",
			prevText: "&#x3C;Ant",
			nextText: "Sig&#x3E;",
			currentText: "Hoy",
			monthNames: [ "Enero","Febrero","Marzo","Abril","Mayo","Junio",
			"Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre" ],
			monthNamesShort: [ "ene","feb","mar","abr","may","jun",
			"jul","ago","sep","oct","nov","dic" ],
			dayNames: [ "domingo","lunes","martes","miércoles","jueves","viernes","sábado" ],
			dayNamesShort: [ "dom","lun","mar","mié","jue","vie","sáb" ],
			dayNamesMin: [ "D","L","M","X","J","V","S" ],
			weekHeader: "Sm",
			dateFormat: "dd/mm/yy",
			firstDay: 1,
			isRTL: false,
			showMonthAfterYear: false,
		    yearSuffix: ""
		    };
	     $.datepicker.setDefaults($.datepicker.regional['es']);
		 $("#desdeDatePicker" ).datepicker();
		 $("#hastaDatePicker" ).datepicker();
		 
		 $('#TablRO').DataTable( {
			    "order": [[ 0, "desc" ]],
		    	"scrollY": 300,
		        "scrollX": true,
		        "language": { "url": "/BustrainSystemWeb/resources/json/Spanish.json"},
		        "columns": [
		    	    { "orderable": true },   /*(1)*/
		    	    { "orderable": false },  /*(2)*/
		    	    { "orderable": false },  /*(3)*/
		    	    { "orderable": false },  /*(4)*/
		    	    { "orderable": false },  /*(5)*/
		    	    { "orderable": false },  /*(6)*/
		    	    { "orderable": false },  /*(7)*/
		    	    { "orderable": false },  /*(8)*/
		    	    { "orderable": false },  /*(9)*/
		    	    { "orderable": false },  /*(10)*/
		    	    { "orderable": false },  /*(11)*/
		    	    { "orderable": false },  /*(12)*/
		    	    { "orderable": false },  /*(13)*/ 
		    	    { "orderable": false },  /*(14)*/
		    	    { "orderable": false },  /*(15)*/
		    	    { "orderable": false },  /*(16)*/
		    	    { "orderable": false }   /*(17)*/
		    	  ]
		    } );

}); /** END ready(function() { **/


function detectaCambio (){
	
	 var objFechaDesde = $("#desdeDatePicker").datepicker("getDate");
	 var objFechaHasta = $("#hastaDatePicker").datepicker("getDate");
	  
	 var jsFechaDesdeDay = objFechaDesde.getDate(); 
	 var jsFechaDesdeMonth = objFechaDesde.getMonth(); 
	 var jsFechaDesdeFullYear = objFechaDesde.getFullYear(); 
	  
	 var jsFechaHastaDay = objFechaHasta.getDate(); 
	 var jsFechaHastaMonth = objFechaHasta.getMonth(); 
	 var jsFechaHastaFullYear = objFechaHasta.getFullYear(); 
	 
	 var objUnidadesTodas = document.getElementById("unidadesTodas"); 
	 var objSelectUnidadId = document.getElementById("selectUnidadId"); 
	 var objUnidades = "";
	 
	 var objTipoMovimientoTodas = document.getElementById("tipoMovimientoTodas");
	 var objSelectTipoMovimiento = document.getElementById("selectTipoMovimiento"); 
     var objTipoMovimiento = "";		
	 
     var objLlantasTodas = document.getElementById("llantasTodas");
	 var objSelectLlantasId = document.getElementById("selectLlantasId"); 
	 var objLlantas = "";
		
	 if(objUnidadesTodas.checked){
		 objUnidades = objUnidadesTodas.value;
	 }else{
		 if(""==objSelectUnidadId.value){
			 objUnidades ="";
		 }else{
			 objUnidades = objSelectUnidadId.value;
		 } 
	 }
	
	 if(objTipoMovimientoTodas.checked){
		 objTipoMovimiento = objTipoMovimientoTodas.value;
	 }else{
		 if(""==objSelectTipoMovimiento.value){
			 objTipoMovimiento ="";
		 }else{
			 objTipoMovimiento = objSelectTipoMovimiento.value;
		 } 
	 }
	 
	 if(objLlantasTodas.checked){
		 objLlantas = objLlantasTodas.value;
	 }else{
		 if(""==objSelectLlantasId.value){
			 objLlantas ="";
		 }else{
			 objLlantas = objSelectLlantasId.value;
		 } 
	 }
	 
	 
	 
	 $.post('AnalizarLlantasCO', {
		    accionAnalizarLlantas : "analizarLlantas",
		    jsFechaDesdeDay: jsFechaDesdeDay,
		    jsFechaDesdeMonth:jsFechaDesdeMonth,
		    jsFechaDesdeFullYear:jsFechaDesdeFullYear,
		    jsFechaHastaDay:jsFechaHastaDay,
		    jsFechaHastaMonth:jsFechaHastaMonth,
		    jsFechaHastaFullYear:jsFechaHastaFullYear,
		    Unidades:objUnidades,
		    TipoMovimiento:objTipoMovimiento,
		    Llantas:objLlantas
		}, function(responseText) {
			$('#DivTablRO').html(responseText);
			$('#TablRO').DataTable( {
				"order": [[ 0, "desc" ]],
				 "scrollY": 350,
		         "scrollX": true,
		        "language": { "url": "/BustrainSystemWeb/resources/json/Spanish.json"},
		        "columns": [
		    	    { "orderable": true },   /*(1)*/
		    	    { "orderable": false },  /*(2)*/
		    	    { "orderable": false },  /*(3)*/
		    	    { "orderable": false },  /*(4)*/
		    	    { "orderable": false },  /*(5)*/
		    	    { "orderable": false },  /*(6)*/
		    	    { "orderable": false },  /*(7)*/
		    	    { "orderable": false },  /*(8)*/
		    	    { "orderable": false },  /*(9)*/
		    	    { "orderable": false },  /*(10)*/
		    	    { "orderable": false },  /*(11)*/
		    	    { "orderable": false },  /*(12)*/
		    	    { "orderable": false },  /*(13)*/ 
		    	    { "orderable": false },  /*(14)*/
		    	    { "orderable": false },  /*(15)*/
		    	    { "orderable": false },  /*(16)*/
		    	    { "orderable": false }   /*(17)*/
		    	  ]
		    } ); 
		});
	 
	
	/*alert("Sale Detecta Cambio");*/
}

function detectaRadios(pButton){
	if("Unidades"==pButton){
		var objUnidadesTodas = document.getElementById("unidadesTodas");
		var objSelectUnidadId = document.getElementById("selectUnidadId"); 
		if(unidadesTodas.checked){
			objSelectUnidadId.value="";
		}
	}
	
	if("TipoMovimiento"==pButton){
		var objTipoMovimientoTodas = document.getElementById("tipoMovimientoTodas");
		var objSelectTipoMovimiento = document.getElementById("selectTipoMovimiento"); 
		if(objTipoMovimientoTodas.checked){
			objSelectTipoMovimiento.value="";
		}
	}
	
	if("Llantas"==pButton){
		var objLlantasTodas = document.getElementById("llantasTodas");
		var objSelectLlantasId = document.getElementById("selectLlantasId"); 
		if(objLlantasTodas.checked){
			objSelectLlantasId.value="";
		}
	}
	
}

function detectaSelects(pButton){
	if("Unidades"==pButton){
		var objUnidadesTodas = document.getElementById("unidadesTodas");
		objUnidadesTodas.checked = false;
	}
	if("TipoMovimiento"==pButton){
		var objTipoMovimientoTodas = document.getElementById("tipoMovimientoTodas");
		objTipoMovimientoTodas.checked = false;
	}
	if("Llantas"==pButton){
		var objLlantasTodas = document.getElementById("llantasTodas");
		objLlantasTodas.checked=false;
	}
}
         
function accionAnalizarLlantas(pButton){
	if("Filtrar"==pButton){
		detectaCambio();
	}
	
	if("Salir"==pButton){
		var objAccion = document.getElementById("accion");
		var objFormAnalizarLlantas = document.getElementById("formAnalizarLlantas"); 
		objAccion.value="Salir";
		objFormAnalizarLlantas.submit();
	}
	
}
