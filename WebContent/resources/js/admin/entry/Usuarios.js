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
    	"order": [[ 0, "desc" ]],
    	"language": { "url": "/BustrainSystemWeb/resources/json/Spanish.json"},
        "columns": [
    	    { "orderable": true }   /*(1)*/
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
	$("#submitUpdateForm").hide();
	
	document.getElementById("CreateDiv").style.display = "none";
	document.getElementById("ReadOnlyDiv").style.display = "none";
	document.getElementById("UpdateDiv").style.display = "none";
	
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


function accionUsuarios(pAccion){
	if("Nueva"==pAccion){
		environmentNueva();
		return;
	}else if("Cancelar"==pAccion){
		environmentCancelar();
		return;
	}else if("Guardar"==pAccion){
		document.getElementById("accionCreateForm").value = "Guardar";
		document.getElementById("submitCreateForm").click();
		return; 
	}else if("Modificar"==pAccion){
		
		if(varTablRO.$('tr.danger').length>0){
			var objNombreUsuario = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
			document.getElementById("UpdateDiv").style.display = "block";
			document.getElementById("updateUsuario").value = objNombreUsuario; 
			var objnombreUsuarioTrx = document.getElementById("nombreUsuarioUpdateTrx"); 
			objnombreUsuarioTrx.value = objNombreUsuario;
			document.getElementById("BtnDiv").style.display="none";
		}else{
			alert("Seleccionar algun registro");
		}
		
		return;
		
	}else if("Borrar"==pAccion){
		
		if(varTablRO.$('tr.danger').length>0){
		var objNombreUsuario = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
		var objnombreUsuarioTrx = document.getElementById("nombreUsuarioTrx"); 
		objnombreUsuarioTrx.value = objNombreUsuario;
		document.getElementById("accionReadOnlyForm").value="Borrar"; 
		var confirmBorrar = confirm("¿Seguro que desea borrar este registro?");
		if(confirmBorrar){
			document.getElementById("ReadOnlyForm").submit();
		}
		
		}
		else{
		alert("Seleccionar algun registro");
	    }
	   return;
    
	}else if("Salir"==pAccion){
		document.getElementById("accionReadOnlyForm").value="Salir"; 
		document.getElementById("ReadOnlyForm").submit();
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

function validaCreateForm(pForma){
	
	pForma.createUsuario.value = pForma.createUsuario.value.toUpperCase();
	pForma.createPassword.value = pForma.createPassword.value.toUpperCase();
	
	return true;
}

function validaUpdateForm(pForma){
	pForma.updateUsuario.value = pForma.updateUsuario.value.toUpperCase();
	pForma.updatePassword.value = pForma.updatePassword.value.toUpperCase();
	return true;
}

function accionUpdateUsuarios(pAccion){
	 if("Guardar"==pAccion){
		document.getElementById("accionUpdateForm").value = "Update";
		document.getElementById("submitUpdateForm").click();
		return; 
	 }else if("Cancelar"){
		 document.getElementById("UpdateDiv").style.display="none";
		 document.getElementById("BtnDiv").style.display="block";
	 }
}