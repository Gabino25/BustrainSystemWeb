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
	
	  function disablePrev() { window.history.forward() }
	    window.onload = disablePrev();
	    window.onpageshow = function(evt) { if (evt.persisted) disableBack() }
	
	$("#submitFormId").hide();
	$("#resetFormId").hide();
	
    $('#TablRO').DataTable( {
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
    
	objRfc = document.getElementById("rfc");
	objNombreEmpresa = document.getElementById("nombreEmpresa");
	objNombreRepresentante = document.getElementById("nombreRepresentante"); 
	objDireccion = document.getElementById("direccion");
	objTelefono = document.getElementById("telefono");
	objFecha = document.getElementById("fecha");
	objNotas = document.getElementById("notas");
	objEstadoY = document.getElementById("estadoY");
	objEstadoN = document.getElementById("estadoN");
    
    objGuardarBtn.disabled = true;
    objBorrarBtn.disabled = true;
    objModificarBtn.disabled = true;
    objCancelarBtn.disabled = true;
    
    objGuardarBtn.style.backgroundColor = "white"; 
    objBorrarBtn.style.backgroundColor = "white"; 
    objModificarBtn.style.backgroundColor = "white"; 
    objCancelarBtn.style.backgroundColor = "white"; 
   
    objRfc.disabled = true;
    objNombreEmpresa.disabled = true;
    objNombreRepresentante.disabled = true;
    objDireccion.disabled = true;
    objTelefono.disabled = true;
    objFecha.disabled = true;
    objNotas.disabled = true;
    
    if(objEstadoY !=null){
    	 objEstadoY.disabled = true;
    }
   
    if(objEstadoN !=null){
    	  objEstadoN.disabled = true;
    }
    
   
    objRfc.onkeydown = objRfc.onkeyup = objRfc.onkeypress =handleEvent;
    objNombreEmpresa.onkeydown = objNombreEmpresa.onkeyup = objNombreEmpresa.onkeypress =handleEvent;
    objNombreRepresentante.onkeydown = objNombreRepresentante.onkeyup = objNombreRepresentante.onkeypress =handleEvent;
    objDireccion.onkeydown = objDireccion.onkeyup = objDireccion.onkeypress =handleEvent;
    objTelefono.onkeydown = objTelefono.onkeyup = objTelefono.onkeypress =handleEvent;
    objFecha.onkeydown = objFecha.onkeyup = objFecha.onkeypress =handleEvent;
    objNotas.onkeydown = objNotas.onkeyup = objNotas.onkeypress =handleEvent;
    objEstadoY.onkeydown = objEstadoY.onkeyup = objEstadoY.onkeypress =handleEvent;
    objEstadoN.onkeydown = objEstadoN.onkeyup = objEstadoN.onkeypress =handleEvent;
    
  
    
} );

function accionCatClie(pAccion){
	var objAccionId = document.getElementById("accionId"); 
	var objCatClientesForm = document.getElementById("catClientesForm");
	
	if("Salir"==pAccion){
		objAccionId.value="Regresar";	
		objCatClientesForm.submit();
		return;
	}
	
	if("Nueva"==pAccion){
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
		
		objRfc.disabled = false;
	    objNombreEmpresa.disabled = false;
	    objNombreRepresentante.disabled = false;
	    objDireccion.disabled = false;
	    objTelefono.disabled = false;
	    objFecha.disabled = false;
	    objNotas.disabled = false;
	    if(objEstadoN !=null){
	    objEstadoY.disabled = false;
	    }
	    if(objEstadoN !=null){
	    objEstadoN.disabled = false;
	    }
	    
	    var objDate = new Date();
	    objFecha.value=objDate.toISOString().substring(0,10);
	    
	    objFecha.readOnly = true;
	    
	    return; 
	}
	
	if("Guardar"==pAccion){
      var objSubmitFormId = document.getElementById("submitFormId");
      objAccionId.value = "Guardar";
      objSubmitFormId.click();
      return;
	}/** END if("Guardar"==pAccion){ **/
	
	if("Cancelar"==pAccion){
	 var objResetFormId = document.getElementById("resetFormId");
	 resetFormId.click();
	
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
		
	  objRfc.disabled = true;
	  objNombreEmpresa.disabled = true;
	  objNombreRepresentante.disabled = true;
	  objDireccion.disabled = true;
	  objTelefono.disabled = true;
	  objFecha.disabled = true;
	  objNotas.disabled = true;
	  
	  if(objEstadoY !=null){
	  objEstadoY.disabled = true;
	  }
	  
	  if(objEstadoN !=null){
	  objEstadoN.disabled = true;
	  }
	 
	 return;
	} /** END if("Cancelar"==pAccion){ **/
	 
   } /** function accion(pAccion){ CatalogoClientes **/
	 
	function accionCatProv(pAccion){
		
		var objAccionId = document.getElementById("accionId"); 
		var objCatProveedoresForm = document.getElementById("catProveedoresForm");
		
		if("Nueva"==pAccion){
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
			
			objRfc.disabled = false;
		    objNombreEmpresa.disabled = false;
		    objNombreRepresentante.disabled = false;
		    objDireccion.disabled = false;
		    objTelefono.disabled = false;
		    objFecha.disabled = false;
		    objNotas.disabled = false;
		    
		    var objDate = new Date();
		    objFecha.value=objDate.toISOString().substring(0,10);
		    
		    objFecha.readOnly = true;
		    
		    return; 
		}
		
		if("Guardar"==pAccion){
		      var objSubmitFormId = document.getElementById("submitFormId");
		      objAccionId.value = "Guardar";
		      objSubmitFormId.click();
		      return;
		 }/** END if("Guardar"==pAccion){ **/
		
		if("Cancelar"==pAccion){
			 var objResetFormId = document.getElementById("resetFormId");
			 resetFormId.click();
			
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
				
			  objRfc.disabled = true;
			  objNombreEmpresa.disabled = true;
			  objNombreRepresentante.disabled = true;
			  objDireccion.disabled = true;
			  objTelefono.disabled = true;
			  objFecha.disabled = true;
			  objNotas.disabled = true;
			 
			 return;
		}
		
		if("Salir"==pAccion){
			objAccionId.value="Regresar";	
			objCatProveedoresForm.submit();
			return;
		}
		
		
	} /** END function accionCatProv(pAccion){ CatalogoProveedores **/
	
function validaFormulario(){
   
	var msg = ""; 
	
	if(objRfc.value==""){
		msg = msg+"El campo RFC es requerido:\n"; 		
	}
	
	if(objNombreEmpresa.value==""){
		msg = msg+"El campo Nombre Empresa es requerido:\n"; 
	}
	
	if(objNombreRepresentante.value==""){
		msg = msg+"El campo Nombre Representante es requerido:\n"; 
	}
	
	if(objDireccion.value==""){
		msg = msg+"El campo Direccion es requerido:\n"; 
	}
	
	if(objTelefono.value==""){
		msg = msg+"El campo Telefono es requerido:\n"; 
	 }
	
	 if(!msg==""){
			alert(msg);
			return false; 
	 }else{
		    objRfc.value = objRfc.value.toUpperCase(); 
		    objNombreEmpresa.value = objNombreEmpresa.value.toUpperCase(); 
		    objNombreRepresentante.value = objNombreRepresentante.value.toUpperCase();
		    objDireccion.value = objDireccion.value.toUpperCase();
			objTelefono.value = objTelefono.value.toUpperCase();			
			objNotas.value = objNotas.value.toUpperCase();
			return true;
	 }
	
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