/**
 * 
 */

 var objRfc = null; 
 var objNombreEmpresa = null; 
 var objNombreRepresentante = null; 
 var objDireccion = null; 
 var objTelefono = null; 
 var objFecha = null; 
 var objNotas = null; 
 var objEstadoY = null; 
 var objEstadoN = null; 
 
$(document).ready(function() {
	$("#submitFormId").hide();
	$("#resetFormId").hide();
	
	var objestadoTrxValue = document.getElementById("estadoTrx").value; 
	if("ACTIVO"==objestadoTrxValue){
	  document.getElementById("estadoY").checked = true; 
	  document.getElementById("estadoN").checked = false; 
	}else if("INACTIVO"==objestadoTrxValue){
	  document.getElementById("estadoY").checked = false; 
	  document.getElementById("estadoN").checked = true; 
	}
	
	objRfc = document.getElementById("rfc");
	objNombreEmpresa = document.getElementById("nombreEmpresa");
	objNombreRepresentante = document.getElementById("nombreRepresentante"); 
	objDireccion = document.getElementById("direccion");
	objTelefono = document.getElementById("telefono");
	objFecha = document.getElementById("fecha");
	objNotas = document.getElementById("notas");
	objEstadoY = document.getElementById("estadoY");
	objEstadoN = document.getElementById("estadoN");
	
	 objRfc.onkeydown = objRfc.onkeyup = objRfc.onkeypress =handleEvent;
     objNombreEmpresa.onkeydown = objNombreEmpresa.onkeyup = objNombreEmpresa.onkeypress =handleEvent;
     objNombreRepresentante.onkeydown = objNombreRepresentante.onkeyup = objNombreRepresentante.onkeypress =handleEvent;
     objDireccion.onkeydown = objDireccion.onkeyup = objDireccion.onkeypress =handleEvent;
     objTelefono.onkeydown = objTelefono.onkeyup = objTelefono.onkeypress =handleEvent;
     objFecha.onkeydown = objFecha.onkeyup = objFecha.onkeypress =handleEvent;
     objNotas.onkeydown = objNotas.onkeyup = objNotas.onkeypress =handleEvent;
     objEstadoY.onkeydown = objEstadoY.onkeyup = objEstadoY.onkeypress =handleEvent;
     objEstadoN.onkeydown = objEstadoN.onkeyup = objEstadoN.onkeypress =handleEvent;
    
	
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
	
	var objRfc = document.getElementById("rfc");
	var objNombreEmpresa = document.getElementById("nombreEmpresa");
	var objNombreRepresentante = document.getElementById("nombreRepresentante"); 
	var objDireccion = document.getElementById("direccion");
	var objTelefono = document.getElementById("telefono");
	var objFecha = document.getElementById("fecha");
	var objNotas = document.getElementById("notas");
	var objEstadoY = document.getElementById("estadoY");
	var objEstadoN = document.getElementById("estadoN");
	
	objRfc.value = objRfc.value.toUpperCase(); 
    objNombreEmpresa.value = objNombreEmpresa.value.toUpperCase(); 
    objNombreRepresentante.value = objNombreRepresentante.value.toUpperCase();
    objDireccion.value = objDireccion.value.toUpperCase();
	objTelefono.value = objTelefono.value.toUpperCase();			
	objNotas.value = objNotas.value.toUpperCase();
	
	var objSubmitFormId = document.getElementById("submitFormId");
	objaccionId.value="Update";
    objSubmitFormId.click();
}

function validaFormulario(){
	
}

function handleEvent(e){
	if(e.type=="keypress"){
	if(e.code=="Enter"){
		e.preventDefault();
		 var inputs = document.querySelectorAll("input,textarea"); 
			/*Isolate the node that we're after*/
		    const currentNode = e.target;
			 /*Find the current tab index.*/
	        const currentIndex = Array.from(inputs).findIndex(el => currentNode.isEqualNode(el))
		        /*focus the following element*/
		        if(currentIndex==2
		          ||currentIndex==3
		          ||currentIndex==4
		          ||currentIndex==5
		          ||currentIndex==6
		          ){
		        inputs[currentIndex + 1].focus();
		        }else if(currentIndex==7){
		        	inputs[10].focus();
		        }
	} /** END if(e.code=="Enter"){ **/
  } /** END if(e.type=="keypress"){ **/
}