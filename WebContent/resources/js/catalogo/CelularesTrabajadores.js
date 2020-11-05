/**
 * Proyecto BustrainSystemWeb
 */

var myWindow = null; 
var objCatTrabajForm = null;
var objAccionId = null; 
var noEmpBuscar = null; 

var objNuevaBtn = null; 
var objGuardarBtn = null; 
var objBorrarBtn = null; 
//var objBuscarBtn = null; 
var objModificarBtn = null; 
var objCancelarBtn = null; 
var objSalirBtn = null; 
var objBajaBtn = null; 


var objFecha=null;
var objTrabajador=null;
var objMarca=null;
var objModelo=null;
var objnumeroSerie=null;
var objnumero=null;
var objNota=null;
var varTablRO = null; 




$(document).ready(function() {
	
	  function disablePrev() { window.history.forward() }
	   window.onload = disablePrev();
	   window.onpageshow = function(evt) { if (evt.persisted) disablePrev() }
	   
	$("#submitFormId").hide();
	$("#resetFormId").hide();
	
	varTablRO = $('#TableRO').DataTable( {
		"pageLength": 25,
    	"order": [[ 0, "desc" ]],
    	"scrollY": 200,
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
//    	    { "orderable": false },  /*(8)*/
//    	    { "orderable": false }  /*(9) NSS*/
    	  ]
    } );
    
	 $('#TableRO tbody').on( 'click', 'tr', function () {
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
//    objBuscarBtn = document.getElementById("BuscarBtn"); 
    objModificarBtn = document.getElementById("ModificarBtn"); 
    objCancelarBtn = document.getElementById("CancelarBtn"); 
    objSalirBtn = document.getElementById("SalirBtn"); 
    objBajaBtn = document.getElementById("BajaBtn");

    objFecha=document.getElementById("fecha");
    objTrabajador=document.getElementById("trabajador");
    objMarca=document.getElementById("marca");
    objModelo=document.getElementById("modelo");
    objnumeroSerie=document.getElementById("numeroSerie");
    objnumero=document.getElementById("numero");
    objNota=document.getElementById("nota");
     
     
    if(null!=objGuardarBtn)
    objGuardarBtn.disabled = true;
    if(null!=objCancelarBtn)
    objCancelarBtn.disabled = true;
    
    
    objFecha.disabled = true; 
    objTrabajador.disabled = true; 
    objMarca.disabled = true; 
    objModelo.disabled = true; 
    objnumeroSerie.disabled = true; 
    objnumero.disabled = true; 
    objNota.disabled=true;
   
 
   
    objFecha.onkeydown =objFecha.onkeyup =objFecha.onkeypress = handleEvent; 
    objTrabajador.onkeydown =objTrabajador.onkeyup =objTrabajador.onkeypress = handleEvent; 
    objMarca.onkeydown =objMarca.onkeyup =objMarca.onkeypress = handleEvent; 
    objModelo.onkeydown =objModelo.onkeyup =objModelo.onkeypress = handleEvent; 
    objnumeroSerie.onkeydown =objnumeroSerie.onkeyup =objnumeroSerie.onkeypress = handleEvent; 
    objnumero.onkeydown =objnumero.onkeyup =objnumero.onkeypress = handleEvent; 
     
} );

//-------------------------------------------------------------------------------------------------
//function implementarOnLoad(){
//		  /*alert("Implementar OnLoad");*/	
//		  console.log(myWindow.document.getElementById("OK"));
//		  myWindow.document.getElementById("OK").addEventListener("click", function(){
//			  var objNoEmpleadoBuscar = myWindow.document.getElementById("noEmpleadoBuscar");
//			  objAccionId.value="Buscar";
//			  noEmpBuscar.value=objNoEmpleadoBuscar.value;
//			  objCatTrabajForm.submit();
//			  myWindow.close();
//			  return;
//		  });
//		  myWindow.document.getElementById("Cancel").addEventListener("click", function(){
//			  myWindow.close();
//			  return;
//		  });
//}

function accionCatTrabaj(pButton){

   objCatTrabajForm = document.getElementById("catTrabajForm");            /** Se hace Global **/
//   objNumero = document.getElementById("numero");                          /** Se hace Global **/
   objAccionId = document.getElementById("accionId");                      /** Se hace Global **/
	  
  if("Nueva"==pButton){
	    
	    environmentNueva();
		    
	    var objDate = new Date();
	  
	    objFecha.value=objDate.toISOString().substring(0,10);
	    
	    
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
		} /** END if("Cancelar"==pAccion){ **/
 
//	if("Salir"==pButton){
//		objAccionId.value="Salir";	
//		objCatTrabajForm.submit();
//		return; 
//	}
	
//	
//	if("Buscar"==pButton){
//		 /******************************************************************
//		  noEmpBuscar = document.getElementById("noEmpBuscar");
//		  alert("Dentro:"+pButton);
//		  if(null==myWindow||myWindow.closed){
//		  myWindow = window.open("", "_blank", "top=250,left=300,width=600,height=100"); 
//		  console.log(myWindow);
//		  myWindow.document.write('<label>Introduce el numero del trabajador</label><br><br>');
//		  myWindow.document.write('<input id="noEmpleadoBuscar" type="number"/>');
//		  myWindow.document.write('<button id="OK">OK</button>');
//		  myWindow.document.write('<button id="Cancel">Cancel</button>');
//		  myWindow.onload = implementarOnLoad();
//		  }else{
//			  myWindow.focus(); 
//		  }
//		  return;
//		  ******************************************************************/
//		if(varTablRO.$('tr.danger').length>0){
//			var objNumeroTrabajador = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
//			var objnoEmpBuscar = document.getElementById("noEmpBuscar"); 
//			objnoEmpBuscar.value = objNumeroTrabajador;
//			objAccionId.value="Buscar"; 
//			objCatTrabajForm.submit();
//		}else{
//			alert("Seleccionar algun registro");
//		}
//		return;
//		
//	  }
	
//	 if("Baja"==pButton){
//		 /************************************
//		 var objectsSel = document.getElementsByName("sel");
//		 var i;
//		 var validaChecked = "";
//		 for (i = 0; i < objectsSel.length; i++) {
//			 if(objectsSel[i].checked){
//				 validaChecked =  objectsSel[i].value;
//			 }
//		 } 
//		 if(""==validaChecked){
//			 alert("No se ha seleccionado ningun trabajador.");
//		 }
//		 *********************************************/
//		      if(varTablRO.$('tr.danger').length>0){
//			  var objNumeroTrabajador = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
//		      var confirmBaja = confirm("Desea ir a la pantalla de baja con este numero de trabajador:"+objNumeroTrabajador+" ?");
//		  	   if(confirmBaja){
//				 objAccionId = document.getElementById("accionId");
//				 var objNoEmpBaja = document.getElementById("noEmpBaja"); 
//				 objAccionId.value = "Baja";
//				 objNoEmpBaja.value = objNumeroTrabajador; 
//				 objCatTrabajForm.submit();
//				 return; 
//			  }
//		      }else{
//		    	  alert("Seleccionar algun registro");
//		    	  return;
//		      }
//	 } /** END if("Baja"==pButton){ **/
//    
//	 if("Modificar"==pButton){
//		     if(varTablRO.$('tr.danger').length>0){
//			     var objNumeroTrabajador = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
//		     	 objAccionId = document.getElementById("accionId");
//		     	 var objnoEmpBuscar = document.getElementById("noEmpBuscar"); 
//				 objAccionId.value = "Modificar";
//				 objnoEmpBuscar.value = objNumeroTrabajador; 
//				 objCatTrabajForm.submit();
//				 return; 
//			  }else{
//		    	  alert("Seleccionar algun registro");
//		    	  return;
//		      }
//	 }
	 
//	 if("Borrar"==pButton){
//	     if(varTablRO.$('tr.danger').length>0){
//		     var objNumeroTrabajador = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
//	     	 objAccionId = document.getElementById("accionId");
//	     	 var objnoEmpBuscar = document.getElementById("noEmpBuscar"); 
//			 objAccionId.value = "Borrar";
//			 objnoEmpBuscar.value = objNumeroTrabajador; 
//			 var confirmBorrar = confirm("¿Seguro que desea borrar este registro?");
//			 if(confirmBorrar){
//			 objCatTrabajForm.submit();
//			 }
//			 return; 
//		  }
//	  
//	     
//	   
//	     else{
//	    	  alert("Seleccionar algun registro");
//	    	  return;
//	      }
//    }
	 
}/** END function accionCatTrabaj(pButton){ **/

function environmentNueva(){
	

	
	    objNuevaBtn.disabled = true; 
//		objBuscarBtn.disabled = true; 
		objSalirBtn.disabled = true;     
		objModificarBtn.disabled = true; 
		objBorrarBtn.disabled = true; 
		
		objGuardarBtn.disabled = false;
		objGuardarBtn.style.backgroundColor="";
		objCancelarBtn.disabled = false;
		objCancelarBtn.style.backgroundColor="";
		
		objFecha.disabled = false;
		objTrabajador.disabled = false;
		objMarca.disabled = false;
	    objModelo.disabled = false; 
	    objnumeroSerie.disabled = false;
	    objnumero.disabled = false;
	    objNota.disabled=false;
	  
}

function environmentCancelar(){
	  
	  objGuardarBtn.disabled = true;
	  objCancelarBtn.disabled = true;
	 
	  objNuevaBtn.disabled = false; 
	  objNuevaBtn.style.backgroundColor="";
//	  objBuscarBtn.disabled = false; 
//	  objBuscarBtn.style.backgroundColor="";
	  objSalirBtn.disabled = false; 
	  objSalirBtn.style.backgroundColor="";
	
	  objModificarBtn.disabled = false; 
	  objModificarBtn.style.backgroundColor="";
	  objBorrarBtn.disabled = false; 
	  objBorrarBtn.style.backgroundColor="";
	  
	  objFecha.disabled = true;
		objTrabajador.disabled = true;
		objMarca.disabled = true;
	    objModelo.disabled = true; 
	    objnumeroSerie.disabled = true;
	    objnumero.disabled = true;
	    objNota.disabled=true;
	  
	  
	  
}

//function openFile(event){
//	var input = event.target;
//	var reader = new FileReader();	
//	 reader.onload = function(){
//		  var dataURL = reader.result;
//		  var output = document.getElementById("output");
//	      output.src = dataURL;
//	      };
//	 reader.readAsDataURL(input.files[0]);      
//}/** END function openFile(event){ **/

function validaFormulario(){
	var msg="";
	
	
	  if(""==objTrabajador.value){
		  msg = msg+"El campo Nombre del Trabajador es Requerido\n";
	  }
	   if(""==objMarca.value){
		  msg = msg+"El campo Marca es Requerido\n";
	  }
	  if(""==objModelo.value){
		  msg = msg+"El campo Modelo es Requerido\n";
	  }
	  
	  if(""==objnumeroSerie.value){
		  msg = msg+"El campo No. Serie es Requerido\n";
	  }
	  
	  if(""==objnumero.value){
		  msg = msg+"El campo Numero es Requerido\n";
	  }
	  
	   
	  
	  if(""!=msg){
		  alert(msg); 
		  return false; 
	  }else{
		   //CONVERSION A MAYUSCULAS
		    objTrabajador.value = objTrabajador.value.toUpperCase();   
		    objMarca.value = objMarca.value.toUpperCase(); 
		    objModelo.value = objModelo.value.toUpperCase(); 
		    objnumeroSerie.value = objnumeroSerie.value.toUpperCase();
		    objnumero.value = objnumero.value.toUpperCase();
		    objNota.value=objNota.value.toUpperCase();
		 
          return true; 
         
	  }
}

function handleEvent(e){
	if(e.code=="Enter"){
		e.preventDefault();
	}
}

