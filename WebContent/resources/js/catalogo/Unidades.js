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
 
 var objEco = null; 
 var objSerie= null;
 var objMotor= null;
 var objCarroceria= null;
 var objModelo= null;
 var objEmpresa= null;
 var objTipo= null;
 var objCombustible= null;
 var objUltimoServicio= null;
 var objKmhrs= null;
 var objAseguradora= null;
 var objFrecuencia= null;
 var objPoliza= null;
 var objVence= null;
 var objEstadoY= null;
 var objEstadoN= null;
 
 /** Parametros Nuevos **/
 var objPasajeros = null; 
 var objPlacas = null; 
 var objConcesion = null; 
 var objClaveVehicular = null; 
 var objVigUltimoReferendo = null; 
 var objAgencia = null; 
 var objTaller = null; 
 var objNinguno = null; 
 var objUnidad = null; 
 var objBroker = null; 
 var objInicio = null; 
 var objEndoso = null; 
 var objCobertura = null; 
 var objHospital = null; 
 var objServicio = null; 
 var objnota=null;

 
 var varTablRO = null; 
 
 

$(document).ready(function() {
	
	  function disablePrev() { window.history.forward() }
	    window.onload = disablePrev();
	    window.onpageshow = function(evt) { if (evt.persisted) disablePrev() }
	    
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
    	    { "orderable": false },  /*(13)*/
    	    { "orderable": false },  /*(14)*/
    	    { "orderable": false },  /*(15)*/
    	    { "orderable": false },  /*(16)*/
    	    { "orderable": false },  /*(17)*/
    	    { "orderable": false },  /*(18)*/
    	    { "orderable": false },  /*(19)*/
    	    { "orderable": false },  /*(20)*/
    	    { "orderable": false },  /*(21)*/
    	    { "orderable": false },  /*(22)*/
    	    { "orderable": false },  /*(23)*/
    	    { "orderable": false },  /*(24)*/
    	    { "orderable": false },  /*(25)*/
    	    { "orderable": false },  /*(26)*/
    	    { "orderable": false }   /*(27)*/
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
    
    
    objEco = document.getElementById("Eco");
    objSerie = document.getElementById("Serie");
    objMotor = document.getElementById("Motor");
    objCarroceria = document.getElementById("Carroceria");
    objModelo = document.getElementById("Modelo");
    objEmpresa = document.getElementById("Empresa");
    objTipo = document.getElementById("Tipo");
    objCombustible = document.getElementById("Combustible");
    objUltimoServicio = document.getElementById("UltimoServicio");
   // objKmhrs = document.getElementById("Kmhrs");
    objAseguradora = document.getElementById("Aseguradora");
    objFrecuencia = document.getElementById("Frecuencia");
    objPoliza = document.getElementById("Poliza");
    objVence = document.getElementById("Vence");
    objEstadoY = document.getElementById("EstadoY");
    objEstadoN = document.getElementById("EstadoN");
    
    objPasajeros= document.getElementById("Pasajeros");
    objPlacas = document.getElementById("Placas");
    objConcesion= document.getElementById("Concesion");
    objClaveVehicular = document.getElementById("ClaveVehicular");
    objVigUltimoReferendo = document.getElementById("VigUltimoReferendo");
    objAgencia= document.getElementById("Agencia");
    objTaller = document.getElementById("Taller");
    objNinguno= document.getElementById("Ninguno");
    objUnidad = document.getElementById("Unidad");
    objBroker = document.getElementById("Broker");
    objInicio = document.getElementById("Inicio");
    objEndoso = document.getElementById("Endoso");
    objCobertura= document.getElementById("Cobertura");
    objHospital = document.getElementById("Hospital");
    objServicio = document.getElementById("Servicio");
    objnota = document.getElementById("nota");
    
    objEco.disabled = true;           
    objSerie.disabled = true;          
    objMotor.disabled = true;          
    objCarroceria.disabled = true;     
    objModelo.disabled = true;         
    objEmpresa.disabled = true;        
    objTipo.disabled = true;           
    objCombustible.disabled = true;    
    objUltimoServicio.disabled = true; 
   // objKmhrs.disabled = true;          
    objAseguradora.disabled = true;    
    objFrecuencia.disabled = true;     
    objPoliza.disabled = true;         
    objVence.disabled = true;          
    objEstadoY.disabled = true;        
    objEstadoN.disabled = true; 
    objnota.disabled=true;
    

  
    
    if(null!=objPasajeros){objPasajeros.disabled = true;}
    if(null!=objPlacas){objPlacas.disabled = true;}
    if(null!=objConcesion){objConcesion.disabled = true;}
    if(null!=objClaveVehicular){objClaveVehicular.disabled = true;}
    if(null!=objVigUltimoReferendo){objVigUltimoReferendo.disabled = true;}
    if(null!=objAgencia){objAgencia.disabled = true;}
    if(null!=objTaller){objTaller.disabled = true;}
    if(null!=objNinguno){objNinguno.disabled = true;}
    if(null!=objUnidad){objUnidad.disabled = true;}
    if(null!=objBroker){objBroker.disabled = true;}
    if(null!=objInicio){objInicio.disabled = true;}
    if(null!=objEndoso){objEndoso.disabled = true;}
    if(null!=objCobertura){objCobertura.disabled = true;}
    if(null!=objHospital){objHospital.disabled = true;}
    if(null!=objServicio){objServicio.disabled = true;}
    
    objEco.onkeydown = objEco.onkeyup = objEco.onkeypress = handleEvent; 
    objSerie.onkeydown = objSerie.onkeyup = objSerie.onkeypress = handleEvent;
    objMotor.onkeydown = objMotor.onkeyup = objMotor.onkeypress = handleEvent;
    objCarroceria.onkeydown = objCarroceria.onkeyup = objCarroceria.onkeypress = handleEvent;
    objModelo.onkeydown = objModelo.onkeyup = objModelo.onkeypress = handleEvent;
    objEmpresa.onkeydown = objEmpresa.onkeyup = objEmpresa.onkeypress = handleEvent;
    objTipo.onkeydown = objTipo.onkeyup = objTipo.onkeypress = handleEvent;
    objCombustible.onkeydown = objCombustible.onkeyup = objCombustible.onkeypress = handleEvent;
    objUltimoServicio.onkeydown = objUltimoServicio.onkeyup = objUltimoServicio.onkeypress = handleEvent;
   // objKmhrs.onkeydown = objKmhrs.onkeyup = objKmhrs.onkeypress = handleEvent;
    objAseguradora.onkeydown = objAseguradora.onkeyup = objAseguradora.onkeypress = handleEvent;
    objFrecuencia.onkeydown = objFrecuencia.onkeyup = objFrecuencia.onkeypress = handleEvent;
    objPoliza.onkeydown = objPoliza.onkeyup = objPoliza.onkeypress = handleEvent;
    objVence.onkeydown = objVence.onkeyup = objVence.onkeypress = handleEvent;
    objEstadoY.onkeydown = objEstadoY.onkeyup = objEstadoY.onkeypress = handleEvent;
    objEstadoN.onkeydown = objEstadoN.onkeyup = objEstadoN.onkeypress = handleEvent;
    
    /*
    objPasajeros.onkeydown = objPasajeros.onkeyup = objPasajeros.onkeypress = handleEvent;
    objPlacas.onkeydown = objPlacas.onkeyup = objPlacas.onkeypress = handleEvent;
    objConcesion.onkeydown = objConcesion.onkeyup = objConcesion.onkeypress = handleEvent;
    objClaveVehicular.onkeydown = objClaveVehicular.onkeyup = objClaveVehicular.onkeypress = handleEvent;
    objVigUltimoReferendo.onkeydown = objVigUltimoReferendo.onkeyup = objVigUltimoReferendo.onkeypress = handleEvent;
    objAgencia.onkeydown = objAgencia.onkeyup = objAgencia.onkeypress = handleEvent;
    objTaller.onkeydown = objTaller.onkeyup = objTaller.onkeypress = handleEvent;
    objNinguno.onkeydown = objNinguno.onkeyup = objNinguno.onkeypress = handleEvent;
    objUnidad.onkeydown = objUnidad.onkeyup = objUnidad.onkeypress = handleEvent;
    objBroker.onkeydown = objBroker.onkeyup = objBroker.onkeypress = handleEvent;
    objInicio.onkeydown = objInicio.onkeyup = objInicio.onkeypress = handleEvent;
    objEndoso.onkeydown = objEndoso.onkeyup = objEndoso.onkeypress = handleEvent;
    objCobertura.onkeydown = objCobertura.onkeyup = objCobertura.onkeypress = handleEvent;
    objHospital.onkeydown = objHospital.onkeyup = objHospital.onkeypress = handleEvent;
    objServicio.onkeydown = objServicio.onkeyup = objServicio.onkeypress = handleEvent;
    */
  
    if(null!=objPasajeros){objPasajeros.onkeydown = objPasajeros.onkeyup = objPasajeros.onkeypress = handleEvent;}
    if(null!=objPlacas){objPlacas.onkeydown = objPlacas.onkeyup = objPlacas.onkeypress = handleEvent; }
    if(null!=objConcesion){objConcesion.onkeydown = objConcesion.onkeyup = objConcesion.onkeypress = handleEvent;}
    if(null!=objClaveVehicular){objClaveVehicular.onkeydown = objClaveVehicular.onkeyup = objClaveVehicular.onkeypress = handleEvent; }
    if(null!=objVigUltimoReferendo){objVigUltimoReferendo.onkeydown = objVigUltimoReferendo.onkeyup = objVigUltimoReferendo.onkeypress = handleEvent; }
    if(null!=objAgencia){objAgencia.onkeydown = objAgencia.onkeyup = objAgencia.onkeypress = handleEvent;}
    if(null!=objTaller){objTaller.onkeydown = objTaller.onkeyup = objTaller.onkeypress = handleEvent; }
    if(null!=objNinguno){objNinguno.onkeydown = objNinguno.onkeyup = objNinguno.onkeypress = handleEvent;}
    if(null!=objUnidad){objUnidad.onkeydown = objUnidad.onkeyup = objUnidad.onkeypress = handleEvent; }
    if(null!=objBroker){objBroker.onkeydown = objBroker.onkeyup = objBroker.onkeypress = handleEvent; }
    if(null!=objInicio){objInicio.onkeydown = objInicio.onkeyup = objInicio.onkeypress = handleEvent; }
    if(null!=objEndoso){objEndoso.onkeydown = objEndoso.onkeyup = objEndoso.onkeypress = handleEvent; }
    if(null!=objCobertura){objCobertura.onkeydown = objCobertura.onkeyup = objCobertura.onkeypress = handleEvent;}
    if(null!=objHospital){objHospital.onkeydown = objHospital.onkeyup = objHospital.onkeypress = handleEvent; }
    if(null!=objServicio){objServicio.onkeydown = objServicio.onkeyup = objServicio.onkeypress = handleEvent; }
  
   
} );

function acccionCatUnidades(pBotonValue){
	
	var objCatUnidadesForm = document.getElementById("catUnidadesForm");
	var objAccionId = document.getElementById("accionId");
	
	if("Nueva"==pBotonValue){
		
		environmentNueva();
		
		return; 
	}else if("Guardar"==pBotonValue){
	      var objSubmitFormId = document.getElementById("submitFormId");
	      objAccionId.value = "Guardar";
	      objSubmitFormId.click();
	      return;
	 }/** END if("Guardar"==pAccion){ **/
	else if("Cancelar"==pBotonValue){
		 var objResetFormId = document.getElementById("resetFormId");
		 resetFormId.click();
		  environmentCancelar();
		  
		 return;
	}else if("Salir"==pBotonValue){
		objAccionId.value = "Salir";
		objCatUnidadesForm.submit();
	}else if("Buscar"==pBotonValue){
		if(varTablRO.$('tr.danger').length>0){
			var objFolioUnidad = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
			var objFolioUnidadTrx = document.getElementById("folioUnidadTrx"); 
			objFolioUnidadTrx.value = objFolioUnidad;
			objAccionId.value="Buscar"; 
			objCatUnidadesForm.submit();
		}else{
			alert("Seleccionar algun registro");
		}
		return;
	}else if("Modificar"==pBotonValue){
		if(varTablRO.$('tr.danger').length>0){
			var objFolioUnidad = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
			var objFolioUnidadTrx = document.getElementById("folioUnidadTrx"); 
			objFolioUnidadTrx.value = objFolioUnidad;
			objAccionId.value="Modificar"; 
			objCatUnidadesForm.submit();
		}else{
			alert("Seleccionar algun registro");
		}
		return;
	}else if("Borrar"==pBotonValue){
		if(varTablRO.$('tr.danger').length>0){
			var objFolioUnidad = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
			var objFolioUnidadTrx = document.getElementById("folioUnidadTrx"); 
			objFolioUnidadTrx.value = objFolioUnidad;
			objAccionId.value="Borrar"; 
			var confirmBorrar = confirm("¿Seguro que desea borrar este registro?");
			if(confirmBorrar){
			objCatUnidadesForm.submit();
		    }
		}else{
			alert("Seleccionar algun registro");
		}
		return;
	 }else if("AgregarUnidades"==pBotonValue){
			objAccionId.value = "AgregarUnidades";
			objCatUnidadesForm.submit();
	 }
	
}



function environmentNueva(){
	objEco = document.getElementById("Eco");
	 
	objNuevaBtn.disabled = true; 
	objBuscarBtn.disabled = true; 
	objBorrarBtn.disabled = true; 
	objModificarBtn.disabled = true; 
	objSalirBtn.disabled = true; 
	 
	objGuardarBtn.disabled = false;
	objGuardarBtn.style.backgroundColor="";
	objCancelarBtn.disabled = false;
	objCancelarBtn.style.backgroundColor="";
	
	objEco.disabled = false;           
	objSerie.disabled = false;          
	objMotor.disabled = false;          
	objCarroceria.disabled = false;     
	objModelo.disabled = false;         
	objEmpresa.disabled = false;        
	objTipo.disabled = false;           
	objCombustible.disabled = false;    
	objUltimoServicio.disabled = false; 
	//objKmhrs.disabled = false;          
	objAseguradora.disabled = false;    
	objFrecuencia.disabled = false;     
	objPoliza.disabled = false;         
	objVence.disabled = false;          
	objEstadoY.disabled = false;        
	objEstadoN.disabled = false;  
	objnota.disabled = false;
	
	if(null!=objPasajeros){objPasajeros.disabled = false;}
	if(null!=objPlacas){objPlacas.disabled = false;}
	if(null!=objConcesion){objConcesion.disabled = false;}
	if(null!=objClaveVehicular){objClaveVehicular.disabled = false;}
	if(null!=objVigUltimoReferendo){objVigUltimoReferendo.disabled = false;}
	if(null!=objAgencia){objAgencia.disabled = false;}
	if(null!=objTaller){objTaller.disabled = false;}
	if(null!=objNinguno){objNinguno.disabled = false;}
	if(null!=objUnidad){objUnidad.disabled = false;}
	if(null!=objBroker){objBroker.disabled = false;}
	if(null!=objInicio){objInicio.disabled = false;}
	if(null!=objEndoso){objEndoso.disabled = false;}
	if(null!=objCobertura){objCobertura.disabled = false;}
	if(null!=objHospital){objHospital.disabled = false;}
	if(null!=objServicio){objServicio.disabled = false;}
	
	objEco.focus();
	
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
		
	   objEco.disabled = true;           
	   objSerie.disabled = true;          
	   objMotor.disabled = true;          
	   objCarroceria.disabled = true;     
	   objModelo.disabled = true;         
	   objEmpresa.disabled = true;        
	   objTipo.disabled = true;           
	   objCombustible.disabled = true;    
	   objUltimoServicio.disabled = true; 
	   //objKmhrs.disabled = true;          
	   objAseguradora.disabled = true;    
	   objFrecuencia.disabled = true;     
	   objPoliza.disabled = true;         
	   objVence.disabled = true;          
	   objEstadoY.disabled = true;        
	   objEstadoN.disabled = true; 
	   objnota.disabled = true;
	   
	   
	   if(null!=objPasajeros){objPasajeros.disabled = true;}
	    if(null!=objPlacas){objPlacas.disabled = true;}
	    if(null!=objConcesion){objConcesion.disabled = true;}
	    if(null!=objClaveVehicular){objClaveVehicular.disabled = true;}
	    if(null!=objVigUltimoReferendo){objVigUltimoReferendo.disabled = true;}
	    if(null!=objAgencia){objAgencia.disabled = true;}
	    if(null!=objTaller){objTaller.disabled = true;}
	    if(null!=objNinguno){objNinguno.disabled = true;}
	    if(null!=objUnidad){objUnidad.disabled = true;}
	    if(null!=objBroker){objBroker.disabled = true;}
	    if(null!=objInicio){objInicio.disabled = true;}
	    if(null!=objEndoso){objEndoso.disabled = true;}
	    if(null!=objCobertura){objCobertura.disabled = true;}
	    if(null!=objHospital){objHospital.disabled = true;}
	    if(null!=objServicio){objServicio.disabled = true;}
	 
}

function validaFormulario(){
	
	var msg =""; 
	Endoso, frecuencia,  servicio, hospital, placas, concesión, referendo

	if(""==objEco.value){
		msg = msg+"El campo Eco es requerido.\n"; 
	}
	
	if(""==objSerie.value){
		msg = msg+"El campo Serie es requerido.\n"; 
	}
	
	if(""==objMotor.value){
		msg = msg+"El campo Motor es requerido.\n"; 
	}
	
	if(""==objCarroceria.value){
		msg = msg+"El campo Carroceria es requerido.\n"; 
	}
	
	if(""==objModelo.value){
		msg = msg+"El campo Modelo es requerido.\n"; 
	}
	
	if(""==objEmpresa.value){
		msg = msg+"El campo Empresa es requerido.\n"; 
	}
	
	if(""==objTipo.value){
		msg = msg+"El campo Tipo es requerido.\n"; 
	}
	
	if(""==objCombustible.value){
		msg = msg+"El campo Combustible es requerido.\n"; 
	}
	
//	if(""==objUltimoServicio.value){
//		msg = msg+"El campo Ultimo Servicio es requerido.\n"; 
//	}
	
//	if(""==objKmhrs.value){
//		msg = msg+"El campo Km Hrs es requerido.\n"; 
//	}
	
//	if(""==objAseguradora.value){
//		msg = msg+"El campo Aseguradora es requerido.\n"; 
//	}
	
//	if(""==objFrecuencia.value){
//		msg = msg+"El campo Frecuencia es requerido.\n"; 
//	}
	
	if(""==objPoliza.value){
		msg = msg+"El campo Poliza es requerido.\n"; 
	}
	
	if(""==objVence.value){
		msg = msg+"El campo Vence es requerido.\n"; 
	}
	
	if(""==msg){ 
	objEco.value = objEco.value.toUpperCase();		
	objSerie.value = objSerie.value.toUpperCase();	
	objMotor.value = objMotor.value.toUpperCase();	
	objCarroceria.value = objCarroceria.value.toUpperCase();	
	objModelo.value = objModelo.value.toUpperCase();
	objEmpresa.value = objEmpresa.value.toUpperCase();
	objTipo.value = objTipo.value.toUpperCase();
	objCombustible.value = objCombustible.value.toUpperCase();
	objUltimoServicio.value = objUltimoServicio.value.toUpperCase();
	//objKmhrs.value = objKmhrs.value.toUpperCase();
	objAseguradora.value = objAseguradora.value.toUpperCase();
	objFrecuencia.value = objFrecuencia.value.toUpperCase();
	objPoliza.value = objPoliza.value.toUpperCase();
	objVence.value = objVence.value.toUpperCase();
	objPlacas.value=objPlacas.value.toUpperCase();
	objConcesion.value=objConcesion.value.toUpperCase();
	objHospital.value=objHospital.value.toUpperCase();
	objClaveVehicular.value = objClaveVehicular.value.toUpperCase();
	objCobertura.value = objCobertura.value.toUpperCase();
	objServicio.value = objServicio.value.toUpperCase();
	objBroker.value = objBroker.value.toUpperCase();
	objnota.value = objBroker.value.toUpperCase();
   
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
