/**
 * 
 */

var objfecha = null; 
var objselectUnidadId = null; 
var objfechaRealizacion = null; 
var objkilometraje = null; 
var objTipoServicio = null; 
var objdescServicio = null; 

$(document).ready(function() {
	    $("#submitButtonId").hide();
	    $("#resetFormId").hide(); 
	    
	    $("#TipoServicio").val($("#tipoTrx").val());
	    $("#selectUnidadId").val($("#ecoTrx").val());
	    
	    objfecha = document.getElementById("fecha"); 
	    objselectUnidadId = document.getElementById("selectUnidadId");   
	    objfechaRealizacion = document.getElementById("fechaRealizacion");   
	    objkilometraje = document.getElementById("kilometraje");   
	    objTipoServicio = document.getElementById("TipoServicio");   
	    objdescServicio = document.getElementById("descServicio");  
	    
	    objfecha.onkeydown = objfecha.onkeyup = objfecha.onkeypress = handleEvent; 
	    objselectUnidadId.onkeydown = objselectUnidadId.onkeyup = objselectUnidadId.onkeypress = handleEvent; 
	    objfechaRealizacion.onkeydown = objfechaRealizacion.onkeyup = objfechaRealizacion.onkeypress = handleEvent;
	    objkilometraje.onkeydown = objkilometraje.onkeyup = objkilometraje.onkeypress = handleEvent; 
	    objTipoServicio.onkeydown = objTipoServicio.onkeyup = objTipoServicio.onkeypress = handleEvent; 
	    objdescServicio.onkeydown = objdescServicio.onkeyup = objdescServicio.onkeypress = handleEvent; 
	    
	    
});

function Regresar(){
	var varformUpd = document.getElementById("formUpd"); 
	var varaccionFormId = document.getElementById("accionFormId"); 
	varaccionFormId.value="Regresar";
	varformUpd.submit();
}

function Actualizar(){
	var varaccionFormId = document.getElementById("accionFormId"); 
	var varsubmitButtonId = document.getElementById("submitButtonId"); 
	varaccionFormId.value="Update";
	varsubmitButtonId.click();
}

function validaFormulario(pForma){
	/* pForma.descFalla.value = pForma.descFalla.value.toUpperCase(); */
	pForma.descServicio.value = pForma.descServicio.value.toUpperCase();
	return true; 
}


var objfecha = null; 
var objselectUnidadId = null; 
var objfechaRealizacion = null; 
var objkilometraje = null; 
var objTipoServicio = null; 
var objdescServicio = null; 

function handleEvent(e){
	if(e.type=="keypress"){
		  if(e.code=="Enter"||e.code=="Tab"){
			  e.preventDefault(); 
			  var objectId = e.target.id; 
			  if("fecha"==objectId){
				  objselectUnidadId.focus();
			  }else if("selectUnidadId"==objectId){
				  objfechaRealizacion.focus();
			  }else if("fechaRealizacion"==objectId){
				  objkilometraje.focus(); 
			  }else if("kilometraje"==objectId){
				  objTipoServicio.focus(); 
			  }else if("TipoServicio"==objectId){
				  objdescServicio.focus(); 
			  }else if("descServicio"==objectId){
				  document.getElementById("UpdateBtn").focus(); 
			  }
		  }
	}
}
