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
    	    { "orderable": true },   /*(1)*/
    	    { "orderable": true },
    	    { "orderable": false }
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

function accionCuentas(pAccion){
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
	}else if("Salir"==pAccion){
		document.getElementById("accionReadOnlyForm").value="Salir"; 
		document.getElementById("ReadOnlyForm").submit();
	}else if("Modificar"==pAccion){
		document.getElementById("UpdateDiv").style.display = "block";
		if(varTablRO.$('tr.danger').length>0){
			var objId = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue;
			var objCuenta = varTablRO.$('tr.danger')[0].cells[1].childNodes[0].nodeValue;
			var objNombreCuenta = varTablRO.$('tr.danger')[0].cells[2].childNodes[0].nodeValue;
			
			var objIdTrx = document.getElementById("idTrx"); 
			var objupdateCuenta = document.getElementById("updateCuenta");
			var objupdateNombreCuenta = document.getElementById("updateNombreCuenta");
			
			objIdTrx.value = objId;
			objupdateCuenta.value = objCuenta; /** "AXAXAXAXAXAX" **/
			objupdateNombreCuenta.value = objNombreCuenta
			
			
			$("#BtnDiv").hide();
			
		}else{
			alert("Seleccionar algun registro");
		}
		
		return;
	}else if("Borrar"==pAccion){
		if(varTablRO.$('tr.danger').length>0){
			var objId = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue;
			var confirmBorrar = confirm("¿Seguro que desea borrar este registro?");
			if(confirmBorrar){
				document.getElementById("accionReadOnlyForm").value = "Borrar";
				document.getElementById("idROTrx").value = objId;  
				document.getElementById("ReadOnlyForm").submit();
			}
		}else{
			alert("Seleccionar algun registro");
		}
			
	}
	
	
}


function accionUpdateCuentas(pAccion){
	 if("Guardar"==pAccion){
			document.getElementById("accionUpdateForm").value = "Update";
			document.getElementById("submitUpdateForm").click();
			return; 
		 }else if("Cancelar"){
			 document.getElementById("UpdateDiv").style.display="none";
			 document.getElementById("BtnDiv").style.display="block";
	  }
}

function validaCreateForm(pForma){
	pForma.createNombreCuenta.value = pForma.createNombreCuenta.value.toUpperCase();
	return true;
}

function validaUpdateForm(pForma){
	pForma.updateNombreCuenta.value = pForma.updateNombreCuenta.value.toUpperCase();
	return true;
}
