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
 
 
 var objfecha = null; 
 var objselectUnidadId = null; 
 var objfechaRealizacion = null; 
 var objkilometraje = null; 
 var objTipoServicio = null; 
 var objdescServicio = null; 
 
 var objresetFormId = null; 
 
 var varTablRO = null; 
 
$(document).ready(function() {
     
	var submitButtonId = document.getElementById("submitButtonId");
	objresetFormId = document.getElementById("resetFormId"); 
	submitButtonId.style.visibility = "hidden"; 
	resetFormId.style.visibility = "hidden"; 
	
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
	
	
    objfecha = document.getElementById("fecha"); 
    objselectUnidadId = document.getElementById("selectUnidadId"); 
    objfechaRealizacion = document.getElementById("fechaRealizacion"); 
    objkilometraje = document.getElementById("kilometraje"); 
    objTipoServicio = document.getElementById("TipoServicio"); 
    objdescServicio = document.getElementById("descServicio"); 
    
    objfecha.disabled = true; 
    objselectUnidadId.disabled = true; 
    objfechaRealizacion.disabled = true; 
    objkilometraje.disabled = true;
    objTipoServicio.disabled = true;
    objdescServicio.disable = true; 
    
    objfecha.onkeyup = objfecha.onkeydown = objfecha.onkeypress = handleEvent;  
    objselectUnidadId.onkeyup = objselectUnidadId.onkeydown = objselectUnidadId.onkeypress = handleEvent; 
    objfechaRealizacion.onkeyup = objfechaRealizacion.onkeydown = objfechaRealizacion.onkeypress = handleEvent; 
    objkilometraje.onkeyup = objkilometraje.onkeydown = objkilometraje.onkeypress = handleEvent; 
    objTipoServicio.onkeyup = objTipoServicio.onkeydown = objTipoServicio.onkeypress = handleEvent;
    objdescServicio.onkeyup = objdescServicio.onkeydown = objdescServicio.onkeypress = handleEvent; 
    
});


function accionServPrev(pButton){
	
	var varSubmitButtonId = document.getElementById("submitButtonId"); 
	var varAccionFormId = document.getElementById("accionFormId"); 
	var varFormServPrev = document.getElementById("formServPrev"); 
	
	objresetFormId = document.getElementById("resetFormId"); 
	
	if("Guardar"==pButton){
		varAccionFormId.value="Guardar";
		varSubmitButtonId.click();
	}	
	if("Salir"==pButton){
		varAccionFormId.value="Salir";
		varFormServPrev.submit();
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
			varFormServPrev.submit();
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
			varFormServPrev.submit();
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
			varFormServPrev.submit();
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
	
	 objfecha.disabled = false; 
	 objselectUnidadId.disabled = false; 
	 objfechaRealizacion.disabled = false; 
	 objkilometraje.disabled = false;
	 objTipoServicio.disabled = false;
	
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
	    objfechaRealizacion.value = objCurrentDate.getFullYear()+"-"+objCurrentMonth+"-"+objCurrentDay;
	    
	    objfecha.focus();    
}

function environmentCancelar(){
	
	 objfecha.disabled = true; 
	 objselectUnidadId.disabled = true; 
	 objfechaRealizacion.disabled = true; 
	 objkilometraje.disabled = true; 
	 objTipoServicio.disabled = true; 
	 
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

}

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
				 objGuardarBtn.focus();
			 }
			 
		  }
	}
	
}

function validaFormulario(pForma){
	pForma.descServicio.value = pForma.descServicio.value.toUpperCase();
	return true; 
}
