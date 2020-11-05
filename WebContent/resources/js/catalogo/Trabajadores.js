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
var objBuscarBtn = null; 
var objModificarBtn = null; 
var objCancelarBtn = null; 
var objSalirBtn = null; 
var objBajaBtn = null; 

var objUniformesBtn=null;

var objVigenciaLicencia = null; 
var objVigenciaLicenciaF=null
var objStatus=null;
var objNombre = null; 
var objNoSeguro = null; 
var objPuesto = null; 
var objDireccion = null; 
var objTelefono = null; 
var objFechaIngreso = null; 
var objNotas = null; 
var fileFoto = null; 
var objSelectAreaId = null;
var objRfc = null; 
var objCurp = null; 
var objSelectEstadoCivilId = null; 
var objEstatura = null; 
var objPeso = null; 
var varTablRO = null; 

var objNtrabajador=null;
var objFechaNacimiento=null;
var objCaminsa=null;
var objPlayera=null;
var objPantalon=null;
var objZapatos=null;
var objReingreso=null;
var objGafete=null;
var objFechaGafete=null;


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
    	    { "orderable": false },  /*(8)*/
    	    { "orderable": false }  /*(9) NSS*/
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
    objBuscarBtn = document.getElementById("BuscarBtn"); 
    objModificarBtn = document.getElementById("ModificarBtn"); 
    objCancelarBtn = document.getElementById("CancelarBtn"); 
    objSalirBtn = document.getElementById("SalirBtn"); 
    objBajaBtn = document.getElementById("BajaBtn");
    objUniformesBtn = document.getElementById("UniformesBtn");
    
    objVigenciaLicencia = document.getElementById("vigenciaLicencia"); 
    objVigenciaLicenciaF = document.getElementById("vigenciaLicenciaF");
    objStatus = document.getElementById("status");
    objNombre = document.getElementById("nombre"); 
    objNoSeguro = document.getElementById("noSeguro"); 
    objPuesto = document.getElementById("puesto"); 
    objDireccion = document.getElementById("direccion"); 
    objTelefono = document.getElementById("telefono"); 
    objFechaIngreso = document.getElementById("fechaIngreso"); 
    objNotas = document.getElementById("notas"); 
    objFileFoto = document.getElementById("fileFoto"); 
    objSelectAreaId = document.getElementById("selectAreaId");
    objRfc = document.getElementById("rfc");
    objCurp = document.getElementById("curp");
    objSelectEstadoCivilId = document.getElementById("selectEstadoCivilId"); 
    objEstatura = document.getElementById("estatura");
    objPeso = document.getElementById("peso");
    objNtrabajador = document.getElementById("Ntrabajador");
    objFechaNacimiento=document.getElementById("fechaNacimiento");
    objCamisa= document.getElementById("Camisa");
    objPlayera= document.getElementById("Playera");
    objPantalon= document.getElementById("Pantalon");
    objZapatos= document.getElementById("Zapatos");
    objReingreso=document.getElementById("reingreso")
    objGafete=document.getElementById("gafete");
    objFechaGafete=document.getElementById("fechaGafete");
    
     
    
     
     
    if(null!=objGuardarBtn)
    objGuardarBtn.disabled = true;
    if(null!=objCancelarBtn)
    objCancelarBtn.disabled = true;
    
    
    objVigenciaLicencia.disabled = true; 
    objVigenciaLicenciaF.disabled = true; 
    objStatus.disabled = true; 
    objNombre.disabled = true; 
    objNoSeguro.disabled = true; 
    objPuesto.disabled = true; 
    objDireccion.disabled = true; 
    objTelefono.disabled = true; 
    objFechaIngreso.disabled = true; 
    objNotas.disabled = true;
    objFileFoto.disabled = true;
   objSelectAreaId.disabled = true;
   objRfc.disabled = true;
   objCurp.disabled = true;
   objSelectEstadoCivilId.disabled = true;
   objEstatura.disabled = true;
   objPeso.disabled = true;
   
   objNtrabajador.disabled=true;
   objFechaNacimiento.disabled=true;
   objCamisa.disabled=true;
   objPlayera.disabled=true;
   objPantalon.disabled=true;
   objZapatos.disabled=true;
   objFechaGafete.disabled=true;
   objGafete.disabled=true;
   //objReingresoSI.disabled=true;
   //objReingresoNO.disabled=true;
   
 
   if(objReingreso !=null){
	   objReingreso.disabled = true;
  }
 
   
   objVigenciaLicencia.onkeydown =objVigenciaLicencia.onkeyup =objVigenciaLicencia.onkeypress = handleEvent; 
   objVigenciaLicenciaF.onkeydown =objVigenciaLicenciaF.onkeyup =objVigenciaLicenciaF.onkeypress = handleEvent; 
   objStatus.onkeydown =objStatus.onkeyup =objStatus.onkeypress = handleEvent; 
   objNombre.onkeydown =objNombre.onkeyup =objNombre.onkeypress = handleEvent; 
   objNoSeguro.onkeydown =objNoSeguro.onkeyup =objNoSeguro.onkeypress = handleEvent; 
   objPuesto.onkeydown =objPuesto.onkeyup =objPuesto.onkeypress = handleEvent; 
   objDireccion.onkeydown =objDireccion.onkeyup =objDireccion.onkeypress = handleEvent; 
   objTelefono.onkeydown =objTelefono.onkeyup =objTelefono.onkeypress = handleEvent; 
   objFechaIngreso.onkeydown =objFechaIngreso.onkeyup =objFechaIngreso.onkeypress = handleEvent; 
//   objNotas.onkeydown =objNotas.onkeyup =objNotas.onkeypress = handleEvent; 
   objFileFoto.onkeydown =objFileFoto.onkeyup =objFileFoto.onkeypress = handleEvent; 
   objSelectAreaId.onkeydown =objSelectAreaId.onkeyup =objSelectAreaId.onkeypress = handleEvent; 
   objRfc.onkeydown =objRfc.onkeyup =objRfc.onkeypress = handleEvent; 
   objCurp.onkeydown =objCurp.onkeyup =objCurp.onkeypress = handleEvent; 
   objSelectEstadoCivilId.onkeydown =objSelectEstadoCivilId.onkeyup =objSelectEstadoCivilId.onkeypress = handleEvent; 
   objEstatura.onkeydown =objEstatura.onkeyup =objEstatura.onkeypress = handleEvent; 
   objPeso.onkeydown =objPeso.onkeyup =objPeso.onkeypress = handleEvent; 
   
   objNtrabajador.onkeydown =objNtrabajador.onkeyup =objNtrabajador.onkeypress = handleEvent; 
   objFechaNacimiento.onkeydown =objFechaNacimiento.onkeyup =objFechaNacimiento.onkeypress = handleEvent;
   objCamisa.onkeydown =objCamisa.onkeyup =objCamisa.onkeypress = handleEvent;
   objPlayera.onkeydown =objPlayera.onkeyup =objPlayera.onkeypress = handleEvent;
   objPantalon.onkeydown =objPantalon.onkeyup =objPantalon.onkeypress = handleEvent;
   objZapatos.onkeydown =objZapatos.onkeyup =objZapatos.onkeypress = handleEvent;
   objReingreso.onkeydown =objReingreso.onkeyup =objReingreso.onkeypress = handleEvent;

   
   
   
} );

//-------------------------------------------------------------------------------------------------

function implementarOnLoad(){
		  /*alert("Implementar OnLoad");*/	
		  console.log(myWindow.document.getElementById("OK"));
		  myWindow.document.getElementById("OK").addEventListener("click", function(){
			  var objNoEmpleadoBuscar = myWindow.document.getElementById("noEmpleadoBuscar");
			  objAccionId.value="Buscar";
			  noEmpBuscar.value=objNoEmpleadoBuscar.value;
			  objCatTrabajForm.submit();
			  myWindow.close();
			  return;
		  });
		  myWindow.document.getElementById("Cancel").addEventListener("click", function(){
			  myWindow.close();
			  return;
		  });
}

function accionCatTrabaj(pButton){

   objCatTrabajForm = document.getElementById("catTrabajForm");            /** Se hace Global **/
   objNumero = document.getElementById("numero");                          /** Se hace Global **/
   objAccionId = document.getElementById("accionId");                      /** Se hace Global **/
	  
  if("Nueva"==pButton){
	    
	    environmentNueva();
		    
	    var objDate = new Date();
//	    objVigenciaLicencia.value = objDate.toISOString().substring(0,10);
//	    objVigenciaLicenciaF.value = objDate.toISOString().substring(0,10);
	    objFechaIngreso.value=objDate.toISOString().substring(0,10);
	    objFechaNacimiento.value=objDate.toISOString().substring(0,10);
	   // objFechaGafete.value=objDate.toISOString().substring(0,10);
	    
	    
		return; 
  }

  
	if("Guardar"==pButton){
	      var objSubmitFormId = document.getElementById("submitFormId");
	      objAccionId.value = "Guardar";
	      objSubmitFormId.click();
	      return;
	}/** END if("Guardar"==pAccion){ **/
	
	   if("Uniformes"==pButton){
	        objAccionId = document.getElementById("accionId");
	     	 var objnoEmpBuscar = document.getElementById("noEmpBuscar"); 
			 objAccionId.value = "Uniformes";
			 objCatTrabajForm.submit();
			 return; 
}
	   
	   
	if("Cancelar"==pButton){
		 var objResetFormId = document.getElementById("resetFormId");
		 resetFormId.click();
		
		 environmentCancelar();
		 
		 return;
		} /** END if("Cancelar"==pAccion){ **/
 
	if("Salir"==pButton){
		objAccionId.value="Salir";	
		objCatTrabajForm.submit();
		return; 
	}
	
	
	if("Buscar"==pButton){
		 /******************************************************************
		  noEmpBuscar = document.getElementById("noEmpBuscar");
		  alert("Dentro:"+pButton);
		  if(null==myWindow||myWindow.closed){
		  myWindow = window.open("", "_blank", "top=250,left=300,width=600,height=100"); 
		  console.log(myWindow);
		  myWindow.document.write('<label>Introduce el numero del trabajador</label><br><br>');
		  myWindow.document.write('<input id="noEmpleadoBuscar" type="number"/>');
		  myWindow.document.write('<button id="OK">OK</button>');
		  myWindow.document.write('<button id="Cancel">Cancel</button>');
		  myWindow.onload = implementarOnLoad();
		  }else{
			  myWindow.focus(); 
		  }
		  return;
		  ******************************************************************/
		if(varTablRO.$('tr.danger').length>0){
			var objNumeroTrabajador = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
			var objnoEmpBuscar = document.getElementById("noEmpBuscar"); 
			objnoEmpBuscar.value = objNumeroTrabajador;
			objAccionId.value="Buscar"; 
			objCatTrabajForm.submit();
		}else{
			alert("Seleccionar algun registro");
		}
		return;
		
	  }
	
	 if("Baja"==pButton){
		 /************************************
		 var objectsSel = document.getElementsByName("sel");
		 var i;
		 var validaChecked = "";
		 for (i = 0; i < objectsSel.length; i++) {
			 if(objectsSel[i].checked){
				 validaChecked =  objectsSel[i].value;
			 }
		 } 
		 if(""==validaChecked){
			 alert("No se ha seleccionado ningun trabajador.");
		 }
		 *********************************************/
		      if(varTablRO.$('tr.danger').length>0){
			  var objNumeroTrabajador = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
		      var confirmBaja = confirm("Desea ir a la pantalla de baja con este numero de trabajador:"+objNumeroTrabajador+" ?");
		  	   if(confirmBaja){
				 objAccionId = document.getElementById("accionId");
				 var objNoEmpBaja = document.getElementById("noEmpBaja"); 
				 objAccionId.value = "Baja";
				 objNoEmpBaja.value = objNumeroTrabajador; 
				 objCatTrabajForm.submit();
				 return; 
			  }
		      }else{
		    	  alert("Seleccionar algun registro");
		    	  return;
		      }
	 } /** END if("Baja"==pButton){ **/
    
	 if("Modificar"==pButton){
		     if(varTablRO.$('tr.danger').length>0){
			     var objNumeroTrabajador = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
		     	 objAccionId = document.getElementById("accionId");
		     	 var objnoEmpBuscar = document.getElementById("noEmpBuscar"); 
				 objAccionId.value = "Modificar";
				 objnoEmpBuscar.value = objNumeroTrabajador; 
				 objCatTrabajForm.submit();
				 return; 
			  }else{
		    	  alert("Seleccionar algun registro");
		    	  return;
		      }
	 }
	 
	 if("Borrar"==pButton){
	     if(varTablRO.$('tr.danger').length>0){
		     var objNumeroTrabajador = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
	     	 objAccionId = document.getElementById("accionId");
	     	 var objnoEmpBuscar = document.getElementById("noEmpBuscar"); 
			 objAccionId.value = "Borrar";
			 objnoEmpBuscar.value = objNumeroTrabajador; 
			 var confirmBorrar = confirm("¿Seguro que desea borrar este registro?");
			 if(confirmBorrar){
			 objCatTrabajForm.submit();
			 }
			 return; 
		  }
	  
	     
	   
	     else{
	    	  alert("Seleccionar algun registro");
	    	  return;
	      }
    }
	 
}/** END function accionCatTrabaj(pButton){ **/

function environmentNueva(){
	
	   objVigenciaLicencia = document.getElementById("vigenciaLicencia"); 
	   objVigenciaLicenciaF = document.getElementById("vigenciaLicenciaF"); 
	
	    objNuevaBtn.disabled = true; 
		objBuscarBtn.disabled = true; 
		objSalirBtn.disabled = true;     
		objBajaBtn.disabled = true; 
		objModificarBtn.disabled = true; 
		objBorrarBtn.disabled = true; 
		
		objGuardarBtn.disabled = false;
		objGuardarBtn.style.backgroundColor="";
		objCancelarBtn.disabled = false;
		objCancelarBtn.style.backgroundColor="";
		
		objVigenciaLicencia.disabled = false;
		objVigenciaLicenciaF.disabled = false;
		objStatus.disabled = false;
	    objNombre.disabled = false; 
	    objNoSeguro.disabled = false;
	    objPuesto.disabled = false;
	    objDireccion.disabled = false;
	    objTelefono.disabled = false;
	    objFechaIngreso.disabled = false; 
	    objNotas.disabled = false;
	    objFileFoto.disabled = false;
	    objSelectAreaId.disabled = false;
		objRfc.disabled = false;
		objCurp.disabled = false;
		objSelectEstadoCivilId.disabled = false;
		objEstatura.disabled = false;
		objPeso.disabled = false;
		
		objNtrabajador.disabled=false;
		   objFechaNacimiento.disabled=false;
		   objCamisa.disabled=false;
		   objPlayera.disabled=false;
		   objPantalon.disabled=false;
		   objZapatos.disabled=false;
		   objReingreso.disabled=false;
		   objFechaGafete.disabled=false;
		   objGafete.disabled=false;
		   
	   
		objVigenciaLicencia.focus();
}

	

function environmentCancelar(){
	  
	  objGuardarBtn.disabled = true;
	  objCancelarBtn.disabled = true;
	 
	  objNuevaBtn.disabled = false; 
	  objNuevaBtn.style.backgroundColor="";
	  objBuscarBtn.disabled = false; 
	  objBuscarBtn.style.backgroundColor="";
	  objSalirBtn.disabled = false; 
	  objSalirBtn.style.backgroundColor="";
	  objBajaBtn.disabled = false; 
	  objBajaBtn.style.backgroundColor="";
	  objModificarBtn.disabled = false; 
	  objModificarBtn.style.backgroundColor="";
	  objBorrarBtn.disabled = false; 
	  objBorrarBtn.style.backgroundColor="";
	  
	  objVigenciaLicencia.disabled = true; 
	  objVigenciaLicenciaF.disabled = true; 
	  objStatus.disabled = true; 
	  objNombre.disabled = true; 
	  objNoSeguro.disabled = true; 
	  objPuesto.disabled = true; 
	  objDireccion.disabled = true; 
	  objTelefono.disabled = true; 
	  objFechaIngreso.disabled = true; 
	  objNotas.disabled = true; 
	  objFileFoto.disabled = true; 
	  objSelectAreaId.disabled = true; 
	  objRfc.disabled = true;
	  objCurp.disabled = true;
	  objSelectEstadoCivilId.disabled = true;
	  objEstatura.disabled = true;
	  objPeso.disabled = true;
	  
	  objNtrabajador.disabled=true;
	   objFechaNacimiento.disabled=true;
	   objCamisa.disabled=true;
	   objPlayera.disabled=true;
	   objPantalon.disabled=true;
	   objZapatos.disabled=true;
	   objReingreso.disabled=true;
	   objFechaGafete.disabled=true;
	   objGafete.disabled=true;
	  
	  
}

function openFile(event){
	var input = event.target;
	var reader = new FileReader();	
	 reader.onload = function(){
		  var dataURL = reader.result;
		  var output = document.getElementById("output");
	      output.src = dataURL;
	      };
	 reader.readAsDataURL(input.files[0]);      
}/** END function openFile(event){ **/

function validaFormulario(){
	var msg="";
	  
//	  if(""==objVigenciaLicencia.value){
//		  msg = msg+"El campo Vigencia Licencia es Requerido\n";
//	  }
	   if(""==objStatus.value){
		  msg = msg+"El campo Status del trabajador es Requerido\n";
	  }
	  if(""==objNombre.value){
		  msg = msg+"El campo Nombre es Requerido\n";
	  }
	  
	  if(""==objNoSeguro.value){
		  msg = msg+"El campo No. Seguro es Requerido\n";
	  }
	  
	  if(""==objPuesto.value){
		  msg = msg+"El campo Puesto es Requerido\n";
	  }
	  
	  if(""==objDireccion.value){
		  msg = msg+"El campo Direccion es Requerido\n";
	  }
	  
	  if(""==objTelefono.value){
		  msg = msg+"El campo Telefono es Requerido\n";
	  }
	  
	  if(""==objFechaIngreso.value){
		  msg = msg+"El campo Fecha Ingreso es Requerido\n";
	  }
	  
	  if(""==objNotas.value){
		  msg = msg+"El campo Notas es Requerido\n";
	  }
	  
	  if(""==objSelectAreaId.value){
		  msg = msg+"El campo Notas es Requerido\n";
	  }
	  if(""==objRfc.value){
		  msg = msg+"El campo RFC es Requerido\n";
	  }
	  if(""==objCurp.value){
		  msg = msg+"El campo CURP es Requerido\n";
	  }
	  if(""==objSelectEstadoCivilId.value){
		  msg = msg+"El campo Estado Civil es Requerido\n";
	  }
	  if(""==objEstatura.value){
		  msg = msg+"El campo Estatura es Requerido\n";
	  }
	  if(""==objPeso.value){
		  msg = msg+"El campo Peso es Requerido\n";
	  }
	 
	  
	  
	  if(""!=msg){
		  alert(msg); 
		  return false; 
	  }else{
		  //CONVERSION A MAYUSCULAS
		  objNombre.value = objNombre.value.toUpperCase();   
		  objPuesto.value = objPuesto.value.toUpperCase(); 
		  objDireccion.value = objDireccion.value.toUpperCase(); 
		  objNotas.value = objNotas.value.toUpperCase();
		  objRfc.value = objRfc.value.toUpperCase();
		  objCurp.value = objCurp.value.toUpperCase();
		  objCamisa.value=objCamisa.value.toUpperCase();
		  objPlayera.value=objPlayera.value.toUpperCase();
		  objPantalon.value=objPantalon.value.toUpperCase();
		  objZapatos.value=objZapatos.value.toUpperCase();
          return true; 
         
	  }
}

function handleEvent(e){
	if(e.code=="Enter"){
		e.preventDefault();
	}
}

