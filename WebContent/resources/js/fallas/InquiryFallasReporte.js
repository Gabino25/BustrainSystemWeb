/**
 * 
 */

$(document).ready(function() {
	var submitButtonId = document.getElementById("submitButtonId");
	submitButtonId.style.visibility = "hidden"; 
}); /** END ready(function() { **/	
	
function accionServicioCorrectivoReporte(pButton){
	
	var varSubmitButtonId = document.getElementById("submitButtonId"); 
	var varAccionFormId = document.getElementById("accionFormId"); 
	var varFormServCorrReporte = document.getElementById("formServCorrReporte");
	
	if("Guardar"==pButton){
		varAccionFormId.value = "Guardar";
		varFormServCorrReporte.submit();
	}
	
	if("Regresar"==pButton){
		varAccionFormId.value = "Regresar"; 
		varFormServCorrReporte.submit();
	}
	
}