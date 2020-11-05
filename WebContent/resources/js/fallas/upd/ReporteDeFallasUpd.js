/**
 * 
 */

 var objselectUnidadId = null; 
 var objselectListCatTrab = null; 
 var objkilometraje = null; 
 var objdescFalla = null; 
 var objfecha = null; 
 var objhora = null;
 
$(document).ready(function() {
	    $("#submitButtonId").hide();
	    $("#resetFormId").hide(); 
	    
	    objselectUnidadId = document.getElementById("selectUnidadId");
	    objselectListCatTrab = document.getElementById("selectListCatTrab");
	    objkilometraje = document.getElementById("kilometraje");
	    objdescFalla = document.getElementById("descFalla");
	    objfecha = document.getElementById("fecha"); 
	    objhora = document.getElementById("hora"); 
	    
	    objselectUnidadId.onkeydown = objselectUnidadId.onkeyup =  objselectUnidadId.onkeypress =  handleEvent; 
	    objselectListCatTrab.onkeydown = objselectListCatTrab.onkeyup =  objselectListCatTrab.onkeypress =  handleEvent; 
	    objkilometraje.onkeydown = objkilometraje.onkeyup =  objkilometraje.onkeypress =  handleEvent; 
	    objdescFalla.onkeydown = objdescFalla.onkeyup =  objdescFalla.onkeypress =  handleEvent; 
	    objfecha.onkeydown = objfecha.onkeyup =  objfecha.onkeypress =  handleEvent; 
	    objhora.onkeydown = objhora.onkeyup =  objhora.onkeypress =  handleEvent; 
	    
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
	pForma.descFalla.value = pForma.descFalla.value.toUpperCase();
	return true; 
}

function handleEvent(e){
	if(e.type=="keypress"){
		  if(e.code=="Enter"||e.code=="Tab"){
			  e.preventDefault(); 
			  var objectId = e.target.id; 
			  if("selectUnidadId"==objectId){
				  objselectListCatTrab.focus();
			  }else if("selectListCatTrab"==objectId){
				  objkilometraje.focus();
			  }else if("kilometraje"==objectId){
				  objdescFalla.focus(); 
			  }else if("descFalla"==objectId){
				  /* objGuardarBtn.focus(); */
				  document.getElementById("UpdateBtn").focus();
			  }
		  }
	}
}
