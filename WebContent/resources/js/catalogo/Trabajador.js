/**
 * 
 */

function accionTrabajador(pButton){
	var objAccionId = document.getElementById("accionId"); 
	var objTrabajadorFormId = document.getElementById("trabajadorFormId"); 
	if("Regresar"==pButton){
		accionId.value = "Regresar"; 
		objTrabajadorFormId.submit();
		return; 
	}
}