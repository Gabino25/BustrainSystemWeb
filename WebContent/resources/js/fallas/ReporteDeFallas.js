/**
 * 
 */

 var objNuevaBtn = null; 
 var objGuardarBtn = null; 
 var objBorrarBtn = null; 
 var objBuscarBtn = null; 
 var objModificarBtn = null; 
 var objCancelarBtn = null; 
 var objSalirBtn = null; 
 
 var objselectUnidadId = null; 
 var objselectListCatTrab = null; 
 var objkilometraje = null; 
 var objdescFalla = null; 
 var objfecha = null; 
 var objhora = null;
 var varTablRO = null; 
 var objresetFormId = null; 

$(document).ready(function() {
     
	var submitButtonId = document.getElementById("submitButtonId");
	submitButtonId.style.visibility = "hidden"; 
 	
	objresetFormId = document.getElementById("resetFormId");
	objresetFormId.style.visibility = "hidden"; 
	
	varTablRO = $('#TablRO').DataTable( {
		    "order": [[ 0, "desc" ]],
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
	
	$('#TablRO tbody').on( 'click', 'tr', function () {
		   if ( $(this).hasClass('danger') ) {
	            $(this).removeClass('danger');
	        }
	        else {
	        	varTablRO.$('tr.danger').removeClass('danger');
	            $(this).addClass('danger');
	        }
	    } );
	
	objNuevaBtn = document.getElementById("NuevaBtn");
	objGuardarBtn = document.getElementById("GuardarBtn");
	objBorrarBtn = document.getElementById("BorrarBtn"); 
	objBuscarBtn = document.getElementById("BuscarBtn"); 
	objModificarBtn = document.getElementById("ModificarBtn"); 
	objCancelarBtn = document.getElementById("CancelarBtn"); 
	objSalirBtn = document.getElementById("SalirBtn"); 
	
	if(null!=objGuardarBtn)
	objGuardarBtn.disabled = true; 
	if(null!=objCancelarBtn)
	objCancelarBtn.disabled = true;
	
    
    objselectUnidadId = document.getElementById("selectUnidadId");
    objselectListCatTrab = document.getElementById("selectListCatTrab");
    objkilometraje = document.getElementById("kilometraje");
    objdescFalla = document.getElementById("descFalla");
    objfecha = document.getElementById("fecha"); 
    objhora = document.getElementById("hora"); 
    
    objselectUnidadId.disabled = true; 
    objselectListCatTrab.disabled = true; 
    objkilometraje.disabled = true; 
    objdescFalla.disabled = true; 
    objfecha.disabled = true; 
    objhora.disabled = true;
	
    objselectUnidadId.onkeydown = objselectUnidadId.onkeyup =  objselectUnidadId.onkeypress =  handleEvent; 
    objselectListCatTrab.onkeydown = objselectListCatTrab.onkeyup =  objselectListCatTrab.onkeypress =  handleEvent; 
    objkilometraje.onkeydown = objkilometraje.onkeyup =  objkilometraje.onkeypress =  handleEvent; 
    objdescFalla.onkeydown = objdescFalla.onkeyup =  objdescFalla.onkeypress =  handleEvent; 
    objfecha.onkeydown = objfecha.onkeyup =  objfecha.onkeypress =  handleEvent; 
    objhora.onkeydown = objhora.onkeyup =  objhora.onkeypress =  handleEvent; 
    
});


function accionRepDefa(pButton){
	  
	var varSubmitButtonId = document.getElementById("submitButtonId"); 
	var varAccionFormId = document.getElementById("accionFormId"); 
	var varFormReporteDeFallas = document.getElementById("formReporteDeFallas"); 
	
	if("Guardar"==pButton){
		varAccionFormId.value="Guardar";
		varSubmitButtonId.click();
	    return;
	}	
	
	if("Salir"==pButton){
		varAccionFormId.value="Salir";
		varFormReporteDeFallas.submit();
		return;
	}
	if("Nueva"==pButton){
		environmentNueva(); 
		return; 
	}
	if("Cancelar"==pButton){
		objresetFormId.click();
		environmentCancelar(); 
		return;
	}
	if("Modificar"==pButton){
		if(varTablRO.$('tr.danger').length>0){
			var objFolio = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
			var objfolioTrx = document.getElementById("folioTrx"); 
			varAccionFormId.value ="Modificar";
			objfolioTrx.value = objFolio; 
			varFormReporteDeFallas.submit();
		}else{
			alert("Seleccionar algun registro");
		}
        return;
	}
	if("Buscar"==pButton){
		if(varTablRO.$('tr.danger').length>0){
			var objFolio = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
			var objfolioTrx = document.getElementById("folioTrx"); 
			varAccionFormId.value ="Buscar";
			objfolioTrx.value = objFolio; 
			varFormReporteDeFallas.submit();
		}else{
			alert("Seleccionar algun registro");
		}
        return;
	}
	if("Borrar"==pButton){
		if(varTablRO.$('tr.danger').length>0){
			var objFolio = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
			var objfolioTrx = document.getElementById("folioTrx"); 
			varAccionFormId.value ="Borrar";
			objfolioTrx.value = objFolio; 
			var confirmBorrar = confirm("¿Seguro que desea borrar este registro?");
			if(confirmBorrar){
			 varFormReporteDeFallas.submit();
			}
		}else{
			alert("Seleccionar algun registro");
		}
        return;
	}
	
}

function environmentNueva(){
	
	objNuevaBtn.disabled = true; 
	objBuscarBtn.disabled = true; 
	objSalirBtn.disabled = true; 
	objModificarBtn.disabled = true; 
	objBorrarBtn.disabled = true; 
	
	objGuardarBtn.disabled = false;
	objGuardarBtn.style.backgroundColor="";
	objCancelarBtn.disabled = false;
	objCancelarBtn.style.backgroundColor="";
	
	 objselectUnidadId.disabled = false; 
	 objselectListCatTrab.disabled = false; 
	 objkilometraje.disabled = false; 
	 objdescFalla.disabled = false; 
	 objfecha.disabled = false; 
	 objhora.disabled = false;
	 
	  var objCurrentDate = new Date(); 
	  var objCurrentMonth = objCurrentDate.getMonth()+1; 
	    if(objCurrentMonth<10){
	    	objCurrentMonth = "0"+objCurrentMonth;
	    }
	  var objCurrentDay = objCurrentDate.getDate(); 
	    if(objCurrentDay<10){
	    	objCurrentDay = "0"+objCurrentDay; 	
	     }
	    /** The specified value 7/02/2019 does not conform to the required format, yyyy-MM-dd **/
	  objfecha.value = objCurrentDate.getFullYear()+"-"+objCurrentMonth+"-"+objCurrentDay;
	  console.log(objCurrentDate);
	  var objTime = objCurrentDate.getTime();
	  var objHours = objCurrentDate.getHours();
	  var objMinutes = objCurrentDate.getMinutes();
	  objhora.value = objHours+":"+objMinutes; 
	  
	 objselectUnidadId.focus();
}

function environmentCancelar(){
	 objNuevaBtn.disabled = false; 
	 objNuevaBtn.style.backgroundColor="";
	 objBuscarBtn.disabled = false;
	 objBuscarBtn.style.backgroundColor="";
	 objSalirBtn.disabled = false;
	 objSalirBtn.style.backgroundColor="";
	 objModificarBtn.disabled = false;
	 objModificarBtn.style.backgroundColor="";
	 objBorrarBtn.disabled = false;
	 objBorrarBtn.style.backgroundColor="";

	 objGuardarBtn.disabled = true;
	 objCancelarBtn.disabled = true;
	 
	 objselectUnidadId.disabled = true; 
	 objselectListCatTrab.disabled = true; 
	 objkilometraje.disabled = true; 
	 objdescFalla.disabled = true; 
	 objfecha.disabled = true; 
	 objhora.disabled = true;	 
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
				  objGuardarBtn.focus(); 
			  }
		  }
	}
}

function validaFormulario(pForma){
	pForma.descFalla.value = pForma.descFalla.value.toUpperCase();
	return true; 
}
