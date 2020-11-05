/**
 * 
 */

 var windowReporte = null; 
 var varTablRO = null; 
 var submitButtonId = null; 

$(document).ready(function() {
	submitButtonId = document.getElementById("submitButtonId");
	submitButtonId.style.visibility = "hidden"; 
	var resetButtonId = document.getElementById("resetButtonId");
	resetButtonId.style.visibility = "hidden"; 
	$("#repDeFallaLab").hide(); 
	$("#repDeFallaLabInput").hide(); 
	$("#GuardarBtn").hide(); 
	$("#CancelarBtn").hide(); 
	
	 varTablRO = $('#TablROServCorr').DataTable( {
		"order": [[ 0, "desc" ]],
    	"scrollY": 300,
        "scrollX": true,
        "language": { "url": "/BustrainSystemWeb/resources/json/Spanish.json"},
        "columns": [
    	    { "orderable": true },   /*(1)*/
    	    { "orderable": false },  /*(2)*/
    	    { "orderable": false },  /*(3)*/
    	    { "orderable": false },   /*(4)*/
    	    { "orderable": false },   /*(5)*/
    	    { "orderable": false },   /*(6)*/
    	    { "orderable": false }    /*(7)*/
    	    ]
    } );
	
	 $('#TablROServCorr tbody').on('click', 'tr', function () {
	       
		    if ( $(this).hasClass('danger') ) {
	            $(this).removeClass('danger');
	        }
	        else {
	        	varTablRO.$('tr.danger').removeClass('danger');
	            $(this).addClass('danger');
	        }
		  
	        var data = varTablRO.row( this ).data();
	        var varFolioRO = document.getElementById("folioRO"); 
	        var varEcoRO = document.getElementById("ecoRO"); 
	        var varReportaRO = document.getElementById("reportaRO"); 
	        var varDescripcionFalla = document.getElementById("descripcionFalla"); 
	        
	        var varReportaROLength = data[4].length;
	        varReportaROLength = varReportaROLength+10;
	        
	        varReportaRO.readOnly=true;
	        varReportaRO.size=varReportaROLength;
	        
	        varDescripcionFalla.readOnly=true;
	        
	        varFolioRO.value=data[0];
	        varEcoRO.value=data[1];
	        varReportaRO.value=data[4];
	        varDescripcionFalla.value=data[2];
	        
	    } );
	 
	 document.getElementById("folioRO").onkeypress = document.getElementById("folioRO").onkeypress =  document.getElementById("folioRO").onkeypress = handleEvent; 
	 document.getElementById("ecoRO").onkeypress = document.getElementById("ecoRO").onkeypress =  document.getElementById("ecoRO").onkeypress = handleEvent; 
	 document.getElementById("reportaRO").onkeypress = document.getElementById("reportaRO").onkeypress =  document.getElementById("reportaRO").onkeypress = handleEvent; 
	 document.getElementById("descripcionFalla").onkeypress = document.getElementById("descripcionFalla").onkeypress =  document.getElementById("descripcionFalla").onkeypress = handleEvent; 
	 
});


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
	}else if("Reporte"==pButton){
		
		if(varTablRO.$('tr.danger').length>0){
			var objNumero = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
			var objNumeroTrx = document.getElementById("numeroTrx"); 
			varAccionFormId.value ="Modificar";
			objNumeroTrx.value = objNumero; 
		}else{
			alert("Seleccionar algun registro");
			return;
		}
		
		$("#ReporteBtn").hide(); 
		$("#SalirBtn").hide(); 
		
		$("#GuardarBtn").show(); 
		$("#CancelarBtn").show(); 
		$("#repDeFallaLab").show(); 
		$("#repDeFallaLabInput").show(); 
		
		var objrepDeFallaLabInput = document.getElementById("repDeFallaLabInput"); 
		objrepDeFallaLabInput.focus();
		
		return; 
	}else if("Guardar"==pButton){
		varAccionFormId.value="Guardar";
		submitButtonId.click();
		return; 
	}else if("Cancelar"==pButton){
		
		$("#repDeFallaLabInput").val("");
		$("#GuardarBtn").hide(); 
		$("#CancelarBtn").hide(); 
		$("#repDeFallaLab").hide(); 
		$("#repDeFallaLabInput").hide();
		
		$("#ReporteBtn").show(); 
		$("#SalirBtn").show(); 
	}
	
}

function validaFormulario(pForma){
	pForma.repDeFallaLabInput.value = pForma.repDeFallaLabInput.value.toUpperCase();
	return true; 
}

function handleEvent(e){
	 
	 console.log("event");
	 console.log(e.type);
	 
	if(e.type=="keypress"){
		 console.log(e.code);
		  if(e.code=="Enter"||e.code=="Tab"){
	     	 e.preventDefault(); 
	     	 var objectID = e.target.id; 
	     	 if("folioRO"==objectID){
	     		 document.getElementById("ecoRO").focus(); 
	     	 }else if("ecoRO"==objectID){
	     		document.getElementById("reportaRO").focus(); 
	     	 }else if("reportaRO"==objectID){
	     		document.getElementById("descripcionFalla").focus(); 
	     	 }else if("descripcionFalla"==objectID){
	     		document.getElementById("ReporteBtn").focus(); 
	     	 }
	     	 
		  }
	 }
}
