/**
 * 
 */

 var objSelectListEmpresas =null; 
 var objClave              =null;  
 var objNombreRuta         =null; 
 var objCosto              =null; 
 var objDescripcion        =null; 
 var objTipServAutobus     =null; 
 var objTipServtaxi        =null; 
 var objTipPagoContado     =null; 
 var objTipPagoCredito     =null; 
 var objFecha              =null; 

 
$(document).ready(function() {
	$("#submitFormId").hide();
	$("#resetFormId").hide();
	
	var objtipounidadTrxValue = document.getElementById("tipounidadTrx").value; 
	var objtipopagoTrxValue = document.getElementById("tipopagoTrx").value; 
	
	if("AUTOBUS"==objtipounidadTrxValue){
		document.getElementById("tipServAutobus").checked = true; 
		document.getElementById("tipServtaxi").checked = false; 
	}else if("TAXI"==objtipounidadTrxValue){
		document.getElementById("tipServAutobus").checked = false; 
		document.getElementById("tipServtaxi").checked = true; 
	}
	
	if("CONTADO"==objtipopagoTrxValue){
		document.getElementById("tipPagoContado").checked = true; 
		document.getElementById("tipPagoCredito").checked = false; 
	}else if("CREDITO"==objtipopagoTrxValue){
		document.getElementById("tipPagoContado").checked = false; 
		document.getElementById("tipPagoCredito").checked = true; 
	}
	
	var objclienteTrxValue = document.getElementById("clienteTrx").value; 
	document.getElementById("selectListEmpresas").value = objclienteTrxValue;
	
	 objSelectListEmpresas = document.getElementById("selectListEmpresas"); 
     objClave = document.getElementById("clave"); 
     objNombreRuta = document.getElementById("nombreRuta"); 
     objCosto = document.getElementById("costo"); 
     objDescripcion = document.getElementById("descripcion"); 
     objTipServAutobus = document.getElementById("tipServAutobus"); 
     objTipServtaxi = document.getElementById("tipServtaxi"); 
     objTipPagoContado = document.getElementById("tipPagoContado"); 
     objTipPagoCredito = document.getElementById("tipPagoCredito"); 
     objFecha =  document.getElementById("fecha"); 
     
     objSelectListEmpresas.onkeydown = objSelectListEmpresas.onkeyup =objSelectListEmpresas.onkeypress = handleEvent;
     objClave.onkeydown = objClave.onkeyup =objClave.onkeypress = handleEvent;
     objNombreRuta.onkeydown = objNombreRuta.onkeyup =objNombreRuta.onkeypress = handleEvent;
     objCosto.onkeydown = objCosto.onkeyup =objCosto.onkeypress = handleEvent;
     objDescripcion.onkeydown = objDescripcion.onkeyup =objDescripcion.onkeypress = handleEvent;
     objTipServAutobus.onkeydown = objTipServAutobus.onkeyup =objTipServAutobus.onkeypress = handleEvent;
     objTipServtaxi.onkeydown = objTipServtaxi.onkeyup =objTipServtaxi.onkeypress = handleEvent;
     objTipPagoContado.onkeydown = objTipPagoContado.onkeyup =objTipPagoContado.onkeypress = handleEvent;
     objTipPagoCredito.onkeydown = objTipPagoCredito.onkeyup =objTipPagoCredito.onkeypress = handleEvent;
     objFecha.onkeydown = objFecha.onkeyup =objFecha.onkeypress = handleEvent;
	
});

function Regresar(){
	var varformUpd = document.getElementById("formUpd"); 
	var objaccionId = document.getElementById("accionId"); 
	objaccionId.value="Regresar";
	varformUpd.submit();
}

function Actualizar(){
	var varformUpd = document.getElementById("formUpd"); 
	var objaccionId = document.getElementById("accionId"); 
	var objSubmitFormId = document.getElementById("submitFormId");
	objaccionId.value="Update";
    objSubmitFormId.click();
}

function validaFormulario(){
	var objClave = document.getElementById("clave"); 
	var objNombreRuta = document.getElementById("nombreRuta"); 
	var objDescripcion = document.getElementById("descripcion"); 
	objClave.value = objClave.value.toUpperCase();	
	objNombreRuta.value = objNombreRuta.value.toUpperCase();	
	objDescripcion.value = objDescripcion.value.toUpperCase();	
	return true; 
}

function handleEvent(e){
	if(e.code=="Enter"||e.code=="Tab"){
		e.preventDefault();
	}
}