/**
 * 
 */


 var windowReporte = null; 
 
$(document).ready(function() {
     
	var submitButtonId = document.getElementById("submitButtonId");
	
	submitButtonId.style.visibility = "hidden"; 
 	
	$('#TablRO').DataTable( {
	    	"scrollY": 300,
	        "scrollX": true,
	        "language": { "url": "/BustrainSystemWeb/resources/json/Spanish.json"},
	        "columns": [
	    	    { "orderable": true },   /*(1)*/
	    	    { "orderable": false },  /*(2)*/
	    	    { "orderable": false },  /*(3)*/
	    	    { "orderable": false }  /*(4)*/
	    	    ]
	    } );
	 
	$('#TablROServCorr').DataTable( {
    	"scrollY": 300,
        "scrollX": true,
        "language": { "url": "/BustrainSystemWeb/resources/json/Spanish.json"}
    } );
	
	   var table = $('#TablROServCorr').DataTable();
     
	    $('#TablROServCorr tbody').on('click', 'tr', function () {
	       
	    	  
	        var data = table.row( this ).data();
	        var varFolioRO = document.getElementById("folioRO"); 
	        var varEcoRO = document.getElementById("ecoRO"); 
	        var varReportaRO = document.getElementById("reportaRO"); 
	        var varDescripcionFalla = document.getElementById("descripcionFalla"); 
	        
	        var varReportaROLength = data[4].length;
	        varReportaROLength = varReportaROLength+10;
	        
	        varReportaRO.readOnly=true;
	        varReportaRO.size=varReportaROLength;
	        
	        varDescripcionFalla.readOnly=true;
	        
	        varReportaRO.classList.add("inputReadOnly");
	        
	        varFolioRO.value=data[0];
	        varEcoRO.value=data[1];
	        varReportaRO.value=data[4];
	        varDescripcionFalla.value=data[2];
	        
	    } );
	    
}); /** END ready(function() { **/



function accionRepDefa(pButton){
	  
	var varSubmitButtonId = document.getElementById("submitButtonId"); 
	var varAccionFormId = document.getElementById("accionFormId"); 
	var varFormReporteDeFallas = document.getElementById("formReporteDeFallas"); 
	
	if("Guardar"==pButton){
		varAccionFormId.value="Guardar";
		varSubmitButtonId.click();
	}	
	if("Salir"==pButton){
		varAccionFormId.value="Salir";
		varFormReporteDeFallas.submit();
	}
}

function accionServPrev(pButton){
	
	var varSubmitButtonId = document.getElementById("submitButtonId"); 
	var varAccionFormId = document.getElementById("accionFormId"); 
	var varFormServPrev = document.getElementById("formServPrev"); 
	
	if("Guardar"==pButton){
		varAccionFormId.value="Guardar";
		varSubmitButtonId.click();
	}	
	if("Salir"==pButton){
		varAccionFormId.value="Salir";
		varFormServPrev.submit();
	}	
}

function accionServCorr(pButton){
	
	if(!(null==windowReporte)){
    	if(!windowReporte.closed){
	    	 windowReporte.focus(); 
	    	 return; 
	    	}
	 }
	
	var varSubmitButtonId = document.getElementById("submitButtonId"); 
	var varAccionFormId = document.getElementById("accionFormId"); 
	var varFormServCorr = document.getElementById("formServCorr");
	var varFolioRO = document.getElementById("folioRO"); 
		
	if("Salir"==pButton){
		varAccionFormId.value="Salir";
		varFormServCorr.submit();
		return; 
	}	
	
	if("Reporte"==pButton){
		if(""==varFolioRO.value){
			alert("Ingresar un valor en el campo folio.");
			return; 
		}
		varAccionFormId.value="Reporte";
		varFormServCorr.submit();
		return; 
	}
	
}




