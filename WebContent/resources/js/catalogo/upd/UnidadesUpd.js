/**
 * 
 */

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

$(document).ready(function() {
	$("#submitFormId").hide();
	$("#resetFormId").hide();
	
	var objEmpresaTrx = document.getElementById("EmpresaTrx"); 
	var objEmpresa = document.getElementById("Empresa"); 
	
	objEmpresa.value = objEmpresaTrx.value;
	
	var objAgenciaTrx = document.getElementById("AgenciaTrx"); 
	var objAgenciaTrxValue = AgenciaTrx.value; 
	
	
	if("0"==objAgenciaTrxValue){
		document.getElementById("Agencia").checked=true; 
	}else if("1"==objAgenciaTrxValue){
		document.getElementById("Taller").checked=true; 
	}else if("2"==objAgenciaTrxValue){
		document.getElementById("Ninguno").checked=true; 
	}
	
	
	var objTipoTrxValue = document.getElementById("TipoTrx").value; 
	document.getElementById("Tipo").value = objTipoTrxValue; 
	
	var objCombustibleTrxValue = document.getElementById("CombustibleTrx").value; 
	document.getElementById("Combustible").value = objCombustibleTrxValue; 
	
	var objEstadoTrxValue = document.getElementById("EstadoTrx").value; 
	
	if("ACTIVO"==objEstadoTrxValue){
		document.getElementById("EstadoY").checked = true;
	}else if("INACTIVO"==objEstadoTrxValue){
		document.getElementById("EstadoN").checked = true;
	}
	
	var objtipounidadTrxValue = document.getElementById("tipounidadTrx").value; 
	document.getElementById("Unidad").value = objtipounidadTrxValue;  
	
	
	 objEco = document.getElementById("Eco");
     objSerie = document.getElementById("Serie");
     objMotor = document.getElementById("Motor");
     objCarroceria = document.getElementById("Carroceria");
     objModelo = document.getElementById("Modelo");
     objEmpresa = document.getElementById("Empresa");
     objTipo = document.getElementById("Tipo");
     objCombustible = document.getElementById("Combustible");
     objUltimoServicio = document.getElementById("UltimoServicio");
     objKmhrs = document.getElementById("Kmhrs");
     objAseguradora = document.getElementById("Aseguradora");
     objFrecuencia = document.getElementById("Frecuencia");
     objPoliza = document.getElementById("Poliza");
     objVence = document.getElementById("Vence");
     objEstadoY = document.getElementById("EstadoY");
     objEstadoN = document.getElementById("EstadoN");
    /** Parametros Nuevos **/
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
	    
    objEco.onkeydown = objEco.onkeyup = objEco.onkeypress = handleEvent; 
    objSerie.onkeydown = objSerie.onkeyup = objSerie.onkeypress = handleEvent;
    objMotor.onkeydown = objMotor.onkeyup = objMotor.onkeypress = handleEvent;
    objCarroceria.onkeydown = objCarroceria.onkeyup = objCarroceria.onkeypress = handleEvent;
    objModelo.onkeydown = objModelo.onkeyup = objModelo.onkeypress = handleEvent;
    objEmpresa.onkeydown = objEmpresa.onkeyup = objEmpresa.onkeypress = handleEvent;
    objTipo.onkeydown = objTipo.onkeyup = objTipo.onkeypress = handleEvent;
    objCombustible.onkeydown = objCombustible.onkeyup = objCombustible.onkeypress = handleEvent;
    objUltimoServicio.onkeydown = objUltimoServicio.onkeyup = objUltimoServicio.onkeypress = handleEvent;
    objKmhrs.onkeydown = objKmhrs.onkeyup = objKmhrs.onkeypress = handleEvent;
    objAseguradora.onkeydown = objAseguradora.onkeyup = objAseguradora.onkeypress = handleEvent;
    objFrecuencia.onkeydown = objFrecuencia.onkeyup = objFrecuencia.onkeypress = handleEvent;
    objPoliza.onkeydown = objPoliza.onkeyup = objPoliza.onkeypress = handleEvent;
    objVence.onkeydown = objVence.onkeyup = objVence.onkeypress = handleEvent;
    objEstadoY.onkeydown = objEstadoY.onkeyup = objEstadoY.onkeypress = handleEvent;
    objEstadoN.onkeydown = objEstadoN.onkeyup = objEstadoN.onkeypress = handleEvent;
    
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
	
}


function handleEvent(e){
	if(e.code=="Enter"||e.code=="Tab"){
		e.preventDefault();
	}
}
