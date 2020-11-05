/**
 * 
 */

$(document).ready(function() {
	$("#submitFormId").hide();
	$("#resetFormId").hide();
	
	var objselectOperadorIdTrxValue = document.getElementById("selectOperadorIdTrx").value; 
	document.getElementById("selectOperadorId").value = objselectOperadorIdTrxValue; 
	
	var objselectUnidadIdTrxValue = document.getElementById("selectUnidadIdTrx").value; 
	document.getElementById("selectUnidadId").value = objselectUnidadIdTrxValue; 
	
	var objselectEmpresaIdTrxValue = document.getElementById("selectEmpresaIdTrx").value; 
	document.getElementById("selectEmpresaId").value = objselectEmpresaIdTrxValue; 
	
	var objtipoDeViajeTrxValue = document.getElementById("tipoDeViajeTrx").value;
	if("SENSILLO"==objtipoDeViajeTrxValue){
		document.getElementById("sensilloId").checked = true; 
		document.getElementById("redondoId").checked = false; 
	}else if("REDONDO"==objtipoDeViajeTrxValue){
		document.getElementById("sensilloId").checked = false; 
		document.getElementById("redondoId").checked = true; 
	}
	
	var objtipoDeCobroTrxValue = document.getElementById("tipoDeCobroTrx").value; 
	if("CONTADO"==objtipoDeCobroTrxValue){
		document.getElementById("contadoId").checked = true; 
		document.getElementById("creditoId").checked = false; 
	}else if("CREDITO"==objtipoDeCobroTrxValue){
		document.getElementById("contadoId").checked = false; 
		document.getElementById("creditoId").checked = true; 
	}
	
	var objtipoDeUnidadTrxValue = document.getElementById("tipoDeUnidadTrx").value; 
	if("AUTOBUS"==objtipoDeUnidadTrxValue){
		document.getElementById("autobusId").checked = true; 
		document.getElementById("taxiId").checked = false; 
	}else if("TAXI"==objtipoDeUnidadTrxValue){
		document.getElementById("autobusId").checked = false; 
		document.getElementById("taxiId").checked = true; 
	}
	
});

function Regresar(){
	var varformUpd = document.getElementById("formUpd"); 
	var objaccion = document.getElementById("accion"); 
	objaccion.value="Regresar";
	varformUpd.submit();
}

function Actualizar(){
	var varformUpd = document.getElementById("formUpd"); 
	var objaccion = document.getElementById("accion"); 
	var objSubmitFormId = document.getElementById("submitFormId");
	objaccion.value="Update";
    objSubmitFormId.click();
}

function validaFormulario(pForma){
	
	
	if(""==pForma.folio.value){return false;}
	if(""==pForma.fecha.value){return false;}
	if(""==pForma.selectOperadorId.value){return false;}
	if(""==pForma.selectUnidadId.value){return false;}
	if(""==pForma.selectEmpresaId.value){return false;}
	if(""==pForma.selectRutaId.value){return false;}
	if(""==pForma.kmInicial.value){return false;}
	if(""==pForma.hrInicial.value){return false;}
	if(""==pForma.kmFinal.value){return false;}
	if(""==pForma.hrFinal.value){return false;}
	if(""==pForma.sensilloId.value){return false;}
	if(""==pForma.redondoId.value){return false;}
	if(""==pForma.contadoId.value){return false;}
	if(""==pForma.creditoId.value){return false;}
	if(""==pForma.autobusId.value){return false;}
	if(""==pForma.taxiId.value){return false;}
	if(""==pForma.costoViaje.value){return false;}
	/*if(""==pForma.observaciones.value){return false;} CampoNoObligatorio*/
	
	if(pForma.kmInicial.value > pForma.kmFinal.value){
		alert("El kilometraje final es mayor que el inicial.");
		return false; 
	}
	
	if(pForma.kmInicial.value == pForma.kmFinal.value){
		alert("Los Kilometrajes son los mismos.");
		return false; 
	}
	
	pForma.observaciones.value = pForma.observaciones.value.toUpperCase();
	
	
}
