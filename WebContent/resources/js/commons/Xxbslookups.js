/**
 * 
 */

 var objNuevaBtn = null; 
 var objGuardarBtn = null; 
 var objBorrarBtn = null; 
 var objModificarBtn = null; 
 var objCancelarBtn = null; 
 var objSalirBtn = null; 

var varTablRO = null; 

$(document).ready(function() {
	
	varTablRO = $('#TablRO').DataTable( {
		"pageLength": 25,
    	"order": [[ 0, "desc" ]],
    	"scrollY": 500,
    	"language": { "url": "/BustrainSystemWeb/resources/json/Spanish.json"},
        "columns": [
    	    { "orderable": true },   /*(1)*/
    	    { "orderable": true },
    	    { "orderable": true }
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
	
	$("#submitCreateForm").hide();
	$("#resetCreateForm").hide();
	$("#submitUpdateForm").hide();
	$("#resetUpdateForm").hide();
	$("#CreateDiv").hide();
	$("#UpdateDiv").hide();
	
	objNuevaBtn = document.getElementById("NuevaBtn");
    objGuardarBtn = document.getElementById("GuardarBtn");
    objBorrarBtn = document.getElementById("BorrarBtn"); 
    objModificarBtn = document.getElementById("ModificarBtn"); 
    objCancelarBtn = document.getElementById("CancelarBtn"); 
    objSalirBtn = document.getElementById("SalirBtn"); 
    
    objGuardarBtn.disabled = true;
	objCancelarBtn.disabled = true;
	
	objNuevaBtn.disabled = false;  
	objModificarBtn.disabled = false; 
	objBorrarBtn.disabled = false; 
	objSalirBtn.disabled = false; 
	
});

function accionLookups(pAccion){
	if("Nueva"==pAccion){
		environmentNueva();
		return;
	}else if("Guardar"==pAccion){
		document.getElementById("accionCreateForm").value = "Guardar";
		document.getElementById("submitCreateForm").click();
		return; 
	}else if("Salir"==pAccion){
		document.getElementById("accionReadOnlyForm").value="Salir"; 
		document.getElementById("ReadOnlyForm").submit();
	}else if("Cancelar"==pAccion){
		environmentCancelar();
		return;
	}
	else if("Modificar"==pAccion){
		document.getElementById("UpdateDiv").style.display = "block";
		if(varTablRO.$('tr.danger').length>0){
			var objId = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue;
			var objName = varTablRO.$('tr.danger')[0].cells[1].childNodes[0].nodeValue;
			var objCode = varTablRO.$('tr.danger')[0].cells[2].childNodes[0].nodeValue;
			
			var objIdTrx = document.getElementById("idTrx"); 
			var objUpdateNombre = document.getElementById("UpdateNombre");
			var objUpdateCodigo = document.getElementById("UpdateCodigo");
			
			objIdTrx.value = objId;
			objUpdateNombre.value = objName; 
			objUpdateCodigo.value = objCode;

			$("#BtnDiv").hide();
			
		}else{
			alert("Seleccionar algun registro");
		}
		
		return;
	}
	else if("Borrar"==pAccion){
		if(varTablRO.$('tr.danger').length>0){
			var objId = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue;
			var objName = varTablRO.$('tr.danger')[0].cells[1].childNodes[0].nodeValue;
			console.log(objId);
			console.log(objName);
			var confirmBorrar = confirm("¿Seguro que desea borrar este registro?");
			if(confirmBorrar){
				document.getElementById("accionReadOnlyForm").value = "Borrar";
				document.getElementById("idROTrx").value = objId;  
				document.getElementById("nameROTrx").value = objName; 
				document.getElementById("ReadOnlyForm").submit();
			}
		}else{
			alert("Seleccionar algun registro");
		}
			
	}
	
}

function environmentNueva(){
	
	objNuevaBtn.disabled = true;  
	objSalirBtn.disabled = true; 
	objBorrarBtn.disabled = true; 
	objModificarBtn.disabled = true; 
	
	objGuardarBtn.disabled = false;
	objCancelarBtn.disabled = false;
	
	document.getElementById("CreateDiv").style.display = "block";
	
}

function environmentCancelar(){
	
	  objGuardarBtn.disabled = true;
	  objCancelarBtn.disabled = true;
	
	  objNuevaBtn.disabled = false;  
	  objModificarBtn.disabled = false; 
	  objBorrarBtn.disabled = false; 
	  objSalirBtn.disabled = false; 
	 
	  document.getElementById("CreateDiv").style.display = "none";	  
}

function accionUpdateLookups(pAccion){
	 if("Guardar"==pAccion){
			document.getElementById("accionUpdateForm").value = "Update";
			document.getElementById("submitUpdateForm").click();
			return; 
		 }else if("Cancelar"){
			 document.getElementById("UpdateDiv").style.display="none";
			 document.getElementById("BtnDiv").style.display="block";
	  }
}