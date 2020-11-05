/**
 * MovimientosLlantas
 */

 var objNuevaBtn = null; 
 var objGuardarBtn = null; 
 var objBorrarBtn = null; 
 var objBuscarBtn = null; 
 var objModificarBtn = null; 
 var objCancelarBtn = null; 
 var objSalirBtn = null; 
 
 var objFolio= null; 
 var objSelectOperadorId = null; 
 var objSelectLlantaId = null; 
 var objSelectPosicionId = null; 
 var objFecha= null; 
 var objProfundidad= null; 
 var objSelectUnidadId = null; 
 var objPresionActual= null; 
 var objKilometraje= null; 
 var objNota = null;
 var objObservaciones = null; 
 var objCosto = null; 
 
$(document).ready(function() {
	
	  function disablePrev() { window.history.forward() }
	    window.onload = disablePrev();
	    window.onpageshow = function(evt) { if (evt.persisted) disablePrev() }
	
	 $("#submitGuardar").hide();
	 $("#resetFormId").hide();
	 $('#TablRO').DataTable( {
		    "order": [[ 0, "desc" ]],
	    	"scrollY": 300,
	        "scrollX": true,
	        "language": { "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"}
	    } );
	 
	 objNuevaBtn = document.getElementById("NuevaBtn");
     objGuardarBtn = document.getElementById("GuardarBtn");
     objBorrarBtn = document.getElementById("BorrarBtn"); 
     objBuscarBtn = document.getElementById("BuscarBtn"); 
     objModificarBtn = document.getElementById("ModificarBtn"); 
     objCancelarBtn = document.getElementById("CancelarBtn"); 
     objSalirBtn = document.getElementById("SalirBtn"); 
     
     objFolio= document.getElementById("folio");
     objSelectOperadorId = document.getElementById("selectOperadorId");
     objSelectLlantaId = document.getElementById("selectLlantaId");
     objSelectPosicionId = document.getElementById("selectPosicionId");
     objFecha= document.getElementById("fecha");
     objProfundidad= document.getElementById("profundidad");
     objSelectUnidadId = document.getElementById("selectUnidadId");
     objPresionActual= document.getElementById("presionActual");
     objKilometraje= document.getElementById("kilometraje");
     objNota = document.getElementById("nota");
     objObservaciones = document.getElementById("observaciones");
     objCosto = document.getElementById("costo");
	
  
     
         if(null!=objGuardarBtn)
         objGuardarBtn.disabled = true;
         if(null!=objBorrarBtn)
         objBorrarBtn.disabled = true;
         if(null!=objModificarBtn)
         objModificarBtn.disabled = true;
         if(null!=objCancelarBtn)
         objCancelarBtn.disabled = true;
         if(null!=objGuardarBtn)
         objGuardarBtn.style.backgroundColor = "white"; 
         if(null!=objBorrarBtn)
         objBorrarBtn.style.backgroundColor = "white"; 
         if(null!=objModificarBtn)
         objModificarBtn.style.backgroundColor = "white"; 
         if(null!=objCancelarBtn)
         objCancelarBtn.style.backgroundColor = "white"; 
         
         if(null!=objFolio)
         objFolio.disabled = true;
         if(null!=objSelectOperadorId)
         objSelectOperadorId.disabled = true;
         if(null!=objSelectLlantaId)
         objSelectLlantaId.disabled = true;
         if(null!=objSelectPosicionId)
         objSelectPosicionId.disabled = true;
         if(null!=objFecha)
         objFecha.disabled = true;
         if(null!=objProfundidad)
         objProfundidad.disabled = true;
         if(null!=objSelectUnidadId)
         objSelectUnidadId.disabled = true;
         if(null!=objPresionActual)
         objPresionActual.disabled = true;
         if(null!=objKilometraje)
         objKilometraje.disabled = true;
         if(null!=objNota)
         objNota.disabled = true; 
         if(null!=objObservaciones)
         objObservaciones.disabled = true; 
         if(null!=objCosto)
         objCosto.disabled = true; 
         
         if(null!=objFolio)
         objFolio.onkeyup = objFolio.onkeydown = objFolio.onkeypress = handleEvent; 
         if(null!=objSelectOperadorId)
         objSelectOperadorId.onkeyup = objSelectOperadorId.onkeydown = objSelectOperadorId.onkeypress = handleEvent; 
         if(null!=objSelectLlantaId)
         objSelectLlantaId.onkeyup = objSelectLlantaId.onkeydown = objSelectLlantaId.onkeypress = handleEvent; 
         if(null!=objSelectPosicionId)
         objSelectPosicionId.onkeyup = objSelectPosicionId.onkeydown = objSelectPosicionId.onkeypress = handleEvent;
         if(null!=objFecha)
         objFecha.onkeyup = objFecha.onkeydown = objFecha.onkeypress = handleEvent; 
         if(null!=objProfundidad)
         objProfundidad.onkeyup = objProfundidad.onkeydown = objProfundidad.onkeypress = handleEvent; 
         if(null!=objSelectUnidadId)
         objSelectUnidadId.onkeyup = objSelectUnidadId.onkeydown = objSelectUnidadId.onkeypress = handleEvent;
         if(null!=objPresionActual)
         objPresionActual.onkeyup = objPresionActual.onkeydown = objPresionActual.onkeypress = handleEvent; 
         if(null!=objKilometraje)
         objKilometraje.onkeyup = objKilometraje.onkeydown = objKilometraje.onkeypress = handleEvent; 
         if(null!=objNota)
         objNota.onkeyup = objNota.onkeydown = objNota.onkeypress = handleEvent;
         if(null!=objObservaciones)
         objObservaciones.onkeyup = objObservaciones.onkeydown = objObservaciones.onkeypress = handleEvent;
         if(null!=objCosto)
         objCosto.onkeyup = objCosto.onkeydown = objCosto.onkeypress = handleEvent;
         
     var objOtherTransaction = document.getElementById("otherTransaction"); 
	    if("Y"==objOtherTransaction.value){
	    	var objConfirm = confirm("Desea agregar otro Registro?");
	    	if(objConfirm==true){
	    		environmentNueva();
	    	}
	  }
	    
}); /** END ready(function() { **/



function accionAsignacionLlantas(pButton){
	
	var objAccion = document.getElementById("accion");
	var objFormMovimientosLlantas = document.getElementById("formMovimientosLlantas");
	
	if("Nueva"==pButton){
		environmentNueva();
		return; 
	}
	
	if("Guardar"==pButton){
	  var objSubmitGuardar = document.getElementById("submitGuardar");
	  objAccion.value = "Guardar";
	  objSubmitGuardar.click();
	  return;
	}
	
	if("Cancelar"==pButton){
		 var objResetFormId = document.getElementById("resetFormId");
		 resetFormId.click();
		 environmentCancelar();  
		  return; 
	}
	
	if("Salir"==pButton){
		objAccion.value="Salir"; 
		objFormMovimientosLlantas.submit();
		return;
	}
	
}

function environmentNueva(){
	
	objNuevaBtn.disabled = true; 
	objNuevaBtn.style.backgroundColor="white";
	objBuscarBtn.disabled = true; 
	objBuscarBtn.style.backgroundColor="white";
	objSalirBtn.disabled = true; 
	objSalirBtn.style.backgroundColor="white";
	 
	objGuardarBtn.disabled = false;
	objGuardarBtn.style.backgroundColor="";
	objCancelarBtn.disabled = false;
	objCancelarBtn.style.backgroundColor="";
	
	if(null!=objFolio)
	objFolio.disabled = false; 
	if(null!=objSelectOperadorId)
	objSelectOperadorId.disabled = false; 
	if(null!=objSelectLlantaId)
	objSelectLlantaId.disabled = false; 
	if(null!=objSelectPosicionId)
	objSelectPosicionId.disabled = false; 
	if(null!=objFecha)
	objFecha.disabled = false; 
	if(null!=objProfundidad)
	objProfundidad.disabled = false; 
	if(null!=objSelectUnidadId)
	objSelectUnidadId.disabled = false; 
	if(null!=objPresionActual)
	objPresionActual.disabled = false;
	if(null!=objKilometraje)
	objKilometraje.disabled = false; 
	if(null!=objNota)
	objNota.disabled = false; 
	if(null!=objObservaciones)
	objObservaciones.disabled = false; 
	if(null!=objCosto)
	objCosto.disabled = false; 
}

function environmentCancelar(){
	
	  objGuardarBtn.disabled = true;
	  objGuardarBtn.style.backgroundColor="white";
	  objCancelarBtn.disabled = true;
	  objCancelarBtn.style.backgroundColor="white";
	
	  objNuevaBtn.disabled = false; 
	  objNuevaBtn.style.backgroundColor="";
	  objBuscarBtn.disabled = false; 
	  objBuscarBtn.style.backgroundColor="";
	  objSalirBtn.disabled = false; 
	  objSalirBtn.style.backgroundColor="";

	  if(null!=objFolio)
	  objFolio.disabled = true;
	  if(null!=objSelectOperadorId)
	  objSelectOperadorId.disabled = true;
	  if(null!=objSelectLlantaId)
	  objSelectLlantaId.disabled = true;
	  if(null!=objSelectPosicionId)
	  objSelectPosicionId.disabled = true;
	  if(null!=objFecha)
	  objFecha.disabled = true;
	  if(null!=objProfundidad)
	  objProfundidad.disabled = true;
	  if(null!=objSelectUnidadId)
	  objSelectUnidadId.disabled = true;
	  if(null!=objPresionActual)
	  objPresionActual.disabled = true;
	  if(null!=objKilometraje)
	  objKilometraje.disabled = true;
	  if(null!=objNota)
	  objNota.disabled = true; 
	  if(null!=objObservaciones)
	  objObservaciones.disabled = true; 
	  if(null!=objCosto)
	  objCosto.disabled = true; 
}


function handleEvent(e){
	if(e.type=="keypress"){
	  if(e.code=="Enter"){
		e.preventDefault();  
	  }	
	}
}

function validaFormulario(pForma){
	if(null!=pForma.nota)
	pForma.nota.value = pForma.nota.value.toUpperCase();
	if(null!=pForma.observaciones)
	pForma.observaciones.value = pForma.observaciones.value.toUpperCase();
	 var objConfirm = confirm("Son los datos correctos?");
     return objConfirm; 
}