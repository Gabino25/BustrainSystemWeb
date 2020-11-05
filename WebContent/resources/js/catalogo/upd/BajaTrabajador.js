/**
 * 
 */
$(document).ready(function() {
	    $("#submitFormId").hide();
	    $("#resetFormId").hide();
	    var objFechaBaja = document.getElementById("fechaBaja"); 
	    var objCurrentDate = new Date();     
	    var objCurrentMonth = objCurrentDate.getMonth()+1; 
	    objCurrentMonth = "0".padEnd(2,objCurrentMonth);
	    var objCurrentDay = objCurrentDate.getDate(); 
	    objCurrentDay = "0".padEnd(2,objCurrentDay);
	    /** The specified value 7/02/2019 does not conform to the required format, yyyy-MM-dd **/
	    objFechaBaja.value = objCurrentDate.getFullYear()+"-"+objCurrentMonth+"-"+objCurrentDay;
	 
});

function accionBajaTrabajador(pButton){
 var objAccionId = document.getElementById("accionId"); 
 var objSubmitFormId = document.getElementById("submitFormId"); 
 var objBajaTrabajadorFormId = document.getElementById("bajaTrabajadorFormId");
	
	if("Cancelar"==pButton){
		objAccionId.value="Cancelar"; 
		objBajaTrabajadorFormId.submit();
		return;
	}
	if("Actualizar"==pButton){
		objAccionId.value = "Actualizar";
		objSubmitFormId.click();
		return; 
	}
}