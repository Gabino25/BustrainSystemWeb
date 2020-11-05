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
 var varTablRO             =null; 
 
 $(document).ready(function() {
	 
	    function disablePrev() { window.history.forward() }
	    window.onload = disablePrev();
	    window.onpageshow = function(evt) { if (evt.persisted) disableBack() }
	    
		$("#submitFormId").hide();
		$("#resetFormId").hide();
		
		varTablRO = $('#TablRO').DataTable( {
			"pageLength": 25,
	    	 "order": [[ 0, "desc" ]],
	    	 "scrollY": 320,
	         "scrollX": true,
	        "language": {"url": "/BustrainSystemWeb/resources/json/Spanish.json"},
	        "columns": [
	    	    { "orderable": true },   /*(1)*/
	    	    { "orderable": false },  /*(2)*/
	    	    { "orderable": false },  /*(3)*/
	    	    { "orderable": false },  /*(4)*/
	    	    { "orderable": false },  /*(5)*/
	    	    { "orderable": false },  /*(6)*/
	    	    { "orderable": false },  /*(7)*/
	    	    { "orderable": false },  /*(8)*/
	    	    { "orderable": false },  /*(9)*/
	    	    { "orderable": false },  /*(10)*/
	    	    { "orderable": false },  /*(11)*/
	    	    { "orderable": false },  /*(12)*/
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
	    
	    objSelectListEmpresas.disabled = true;     
	    objClave.disabled = true;  
	    objNombreRuta.disabled = true;                                 
	    objCosto.disabled = true;                                 
	    objDescripcion.disabled = true;                                 
	    objTipServAutobus.disabled = true;                                 
	    objTipServtaxi.disabled = true;                                 
	    objTipPagoContado.disabled = true;                                 
	    objTipPagoCredito.disabled = true;
	    objFecha.disabled = true;
	    
	    objSelectListEmpresas.onkeydown = objSelectListEmpresas.onkeyup =objSelectListEmpresas.onkeypress = handleEvent;
	    objClave.onkeydown = objClave.onkeyup =objClave.onkeypress = handleEvent;
	    objNombreRuta.onkeydown = objNombreRuta.onkeyup =objNombreRuta.onkeypress = handleEvent;
	    objCosto.onkeydown = objCosto.onkeyup =objCosto.onkeypress = handleEvent;
//	    objDescripcion.onkeydown = objDescripcion.onkeyup =objDescripcion.onkeypress = handleEvent;
	    objTipServAutobus.onkeydown = objTipServAutobus.onkeyup =objTipServAutobus.onkeypress = handleEvent;
	    objTipServtaxi.onkeydown = objTipServtaxi.onkeyup =objTipServtaxi.onkeypress = handleEvent;
	    objTipPagoContado.onkeydown = objTipPagoContado.onkeyup =objTipPagoContado.onkeypress = handleEvent;
	    objTipPagoCredito.onkeydown = objTipPagoCredito.onkeyup =objTipPagoCredito.onkeypress = handleEvent;
	    objFecha.onkeydown = objFecha.onkeyup =objFecha.onkeypress = handleEvent;
	    
	   
	    
	} );

function acccionCatRutas(pButton){
	var objAccionId = document.getElementById("accionId");
	var objCatRutasForm = document.getElementById("catRutasForm");
	
	if("Nueva"==pButton){
		
		environmentNueva();
		
		return; 
		
	}
	
	if("Guardar"==pButton){
	      var objSubmitFormId = document.getElementById("submitFormId");
	      objAccionId.value = "Guardar";
	      objSubmitFormId.click();
	      return;
	 }/** END if("Guardar"==pAccion){ **/
	
	if("Cancelar"==pButton){
		 var objResetFormId = document.getElementById("resetFormId");
		 resetFormId.click();
		
		 environmentCancelar();
		 return;
	}
	
	if("Salir"==pButton){
		objAccionId.value = "Salir";
		objCatRutasForm.submit();
		return;
	}
	
	if("Buscar"==pButton){
		
		if(varTablRO.$('tr.danger').length>0){
			var objNumeroRuta = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
			var objNumeroRutaTrx = document.getElementById("numeroRutaTrx"); 
			objNumeroRutaTrx.value = objNumeroRuta;
			objAccionId.value="Buscar"; 
			objCatRutasForm.submit();
		}else{
			alert("Seleccionar algun registro");
		}
		
		return;
		
	}
	
   if("Modificar"==pButton){
		
		if(varTablRO.$('tr.danger').length>0){
			var objNumeroRuta = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
			var objNumeroRutaTrx = document.getElementById("numeroRutaTrx"); 
			objNumeroRutaTrx.value = objNumeroRuta;
			objAccionId.value="Modificar"; 
			objCatRutasForm.submit();
		}else{
			alert("Seleccionar algun registro");
		}
		
		return;
		
	}
   
   if("Borrar"==pButton){
   	if(varTablRO.$('tr.danger').length>0){
			var objNumeroRuta = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
			var objNumeroRutaTrx = document.getElementById("numeroRutaTrx"); 
			objNumeroRutaTrx.value = objNumeroRuta;
			objAccionId.value="Borrar"; 
			var confirmBorrar = confirm("¿Seguro que desea borrar este registro?");
			if(confirmBorrar){
				objCatRutasForm.submit();
			}
		}else{
			alert("Seleccionar algun registro");
		}
		
		return;
   }

	
	
}

function environmentNueva(){
	
	 objSelectListEmpresas = document.getElementById("selectListEmpresas"); 
	
	objNuevaBtn.disabled = true; 
	objBuscarBtn.disabled = true; 
	objBorrarBtn.disabled = true; 
	objModificarBtn.disabled = true; 
	objSalirBtn.disabled = true; 
	 
	objGuardarBtn.disabled = false;
	objGuardarBtn.style.backgroundColor="";
	objCancelarBtn.disabled = false;
	objCancelarBtn.style.backgroundColor="";
	
	objSelectListEmpresas.disabled = false;
	objClave.disabled = false;  
	objNombreRuta.disabled = false;                            
	objCosto.disabled = false;                            
	objDescripcion.disabled = false;                            
	objTipServAutobus.disabled = false;                            
	objTipServtaxi.disabled = false;                            
	objTipPagoContado.disabled = false;                            
	objTipPagoCredito.disabled = false;
	objFecha.disabled = false; 
	
	objSelectListEmpresas.focus();
	
}

function environmentCancelar(){

	  objGuardarBtn.disabled = true;
	  objCancelarBtn.disabled = true;
	  
	  objNuevaBtn.disabled = false; 
	  objNuevaBtn.style.backgroundColor="";
	  objBuscarBtn.disabled = false; 
	  objBuscarBtn.style.backgroundColor="";
	  objBorrarBtn.disabled = false; 
	  objBorrarBtn.style.backgroundColor="";
	  objModificarBtn.disabled = false; 
	  objModificarBtn.style.backgroundColor="";
	  objSalirBtn.disabled = false; 
	  objSalirBtn.style.backgroundColor="";
	  
objSelectListEmpresas.disabled = true;     
  objClave.disabled = true;  
  objNombreRuta.disabled = true;                                 
  objCosto.disabled = true;                                 
  objDescripcion.disabled = true;                                 
  objTipServAutobus.disabled = true;                                 
  objTipServtaxi.disabled = true;                                 
  objTipPagoContado.disabled = true;                                 
  objTipPagoCredito.disabled = true;
  objFecha.disabled = true;
  
}

function validaFormulario(){
	
	var msg="";
	if(""==objClave.value){
		msg = msg+"El campo Clave es requerido"; 
	}
	if(""==objNombreRuta.value){
		msg = msg+"El campo Nombre Ruta es requerido"; 
	}
//	if(""==objDescripcion.value){
//		msg = msg+"El campo Descripcion Ruta es requerido"; 
//	}
	
	if(""==msg){
		objClave.value = objClave.value.toUpperCase();	
		objNombreRuta.value = objNombreRuta.value.toUpperCase();	
		objDescripcion.value = objDescripcion.value.toUpperCase();	
		return true;
	}else{
		alert(msg);
		return false; 
	}
	
}


function handleEvent(e){
	if(e.code=="Enter"||e.code=="Tab"){
		e.preventDefault();
	}
}


