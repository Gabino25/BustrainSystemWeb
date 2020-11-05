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
 
 var objCodigo = null; 
 var objMedida = null; 
 var objMarca = null; 
 var objModelo = null; 
 var objProfundidad = null; 
 var objEstado = null; 
 var objCosto = null; 
 var objFechaAlta = null; 
 
 var varTablRO = null; 

$(document).ready(function() {
	
	$.ajaxSetup({async: false}); 
	
	  function disablePrev() { window.history.forward() }
	    window.onload = disablePrev();
	    window.onpageshow = function(evt) { if (evt.persisted) disablePrev() }
	
	$("#submitGuardar").hide();
	$("#resetFormId").hide();
	varTablRO =  $('#TablRO').DataTable( {
		   "pageLength": 25,
		    "order": [[ 0, "desc" ]],
	    	"scrollY": 300,
	        "scrollX": true,
	        "language": { "url": "/BustrainSystemWeb/resources/json/Spanish.json"},
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
	    	    { "orderable": false }  /*(15)*/
	    	  ]
	    } );
	 
	   $('#TablRO tbody').on( 'click', 'tr', function () {
		   console.log("*");
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
	    
	    
	    objCodigo = document.getElementById("codigo"); 
	    objMedida = document.getElementById("medida"); 
	    objMarca= document.getElementById("marca");
	    objModelo = document.getElementById("modelo"); 
	    objProfundidad= document.getElementById("profundidad");
	    objEstado = document.getElementById("estado"); 
	    objCosto= document.getElementById("costo");
	    objFechaAlta= document.getElementById("fechaAlta");
	    
	    objCodigo.disabled=true;
	    objMedida.disabled=true;
	    objMarca.disabled=true;
	    objModelo.disabled=true;
	    objProfundidad.disabled=true;
	    objEstado.disabled=true;
	    objCosto.disabled=true;
	    objFechaAlta.disabled=true;
	    
	    objCodigo.onkeyup = objCodigo.onkeydown = objCodigo.onkeypress = handleEvent;        
	    objMedida.onkeyup = objMedida.onkeydown = objMedida.onkeypress = handleEvent;          
	    objMarca.onkeyup = objMarca.onkeydown = objMarca.onkeypress = handleEvent;          
	    objModelo.onkeyup = objModelo.onkeydown = objModelo.onkeypress = handleEvent;          
	    objProfundidad.onkeyup = objProfundidad.onkeydown = objProfundidad.onkeypress = handleEvent;          
	    objEstado.onkeyup = objEstado.onkeydown = objEstado.onkeypress = handleEvent;          
	    objCosto.onkeyup = objCosto.onkeydown = objCosto.onkeypress = handleEvent;          
	    objFechaAlta.onkeyup = objFechaAlta.onkeydown = objFechaAlta.onkeypress = handleEvent;
	    
	    objCodigo.onchange = onchangeCodigo;
	    
	    var objCurrentDate = new Date(); 
	    
	    var objCurrentMonth = objCurrentDate.getMonth()+1; 
	    if(objCurrentMonth<10){
	    	objCurrentMonth = "0"+objCurrentMonth;
	    }
	    
	   
	    var objCurrentDay = objCurrentDate.getDate(); 
	    if(objCurrentDay<10){
	    	objCurrentDay = "0"+objCurrentDay; 	
	    }
	    /** The specified value 7/02/2019 does not conform to the required format, yyyy-MM-dd **/
	    objFechaAlta.value = objCurrentDate.getFullYear()+"-"+objCurrentMonth+"-"+objCurrentDay;
	    
	    var objOtherTransaction = document.getElementById("otherTransaction"); 
	    if("Y"==objOtherTransaction.value){
	    	var objConfirm = confirm("Desea agregar otro Registro?");
	    	if(objConfirm==true){
	    		environmentNueva();
	    	}
	    }
	 
}); /** END ready(function() { **/



function validaFormulario(pForma){
	 objCodigo = document.getElementById("codigo"); 
	 objMedida = document.getElementById("medida"); 
	 objMarca= document.getElementById("marca");
	 objModelo = document.getElementById("modelo"); 
	 objProfundidad= document.getElementById("profundidad");
	 objEstado = document.getElementById("estado"); 
	 objCosto= document.getElementById("costo");
	 objFechaAlta= document.getElementById("fechaAlta");
	 if(""==objCodigo.value){
		 alert("El campo Codigo es requerido.");
		 return false; 
	 }
	 if(""==objMedida.value){
		 alert("El campo Medida es requerido.");
		 return false; 
	 }
	 if(""==objMarca.value){
		 alert("El campo Marca es requerido.");
		 return false; 
	 }
	 if(""==objModelo.value){
		 alert("El campo Modelo es requerido.");
		 return false; 
	 }
	 if(""==objProfundidad.value){
		 alert("El campo Profundidad es requerido.");
		 return false; 
	 }
	 if(""==objEstado.value){
		 alert("El campo Estado es requerido.");
		 return false; 
	 }
	 if(""==objCosto.value){
		 alert("El campo Costo es requerido.");
		 return false; 
	 }
	 if(""==objFechaAlta.value){
		 alert("El campo Fecha es requerido.");
		 return false; 
	 }
	 
	 objCodigo.value = objCodigo.value.toUpperCase(); 
	 objMedida.value = objMedida.value.toUpperCase(); 
	 objMarca.value = objMarca.value.toUpperCase(); 
	 objModelo.value = objModelo.value.toUpperCase();  
	 var objConfirm = confirm("Son los datos correctos?");
     return objConfirm; 
		
}

function accionAdministrarLlantas(pButton){	
	
	var objFormAdministrarLlantas = document.getElementById("formAdministrarLlantas");
	var objAccion = document.getElementById("accion"); 
	
	if("Guardar"==pButton){
	var objSubmitGuardar = document.getElementById("submitGuardar");
	objAccion.value ="Guardar";
	objSubmitGuardar.click();
	}
	
	if("Salir"==pButton){
		objAccion.value ="Salir";
		objFormAdministrarLlantas.submit();
	}
	
	if("Nueva"==pButton){
		environmentNueva(); 
		return; 
	}
	
	if("Cancelar"==pButton){
		 var objResetFormId = document.getElementById("resetFormId");
		 resetFormId.click();
		
		 environmentCancelar();
		 return;
	}
	
	if("Buscar"==pButton){
		/* console.log(varTablRO.$('tr.danger')); */
		/* console.log(varTablRO.row('.danger')); */
		if(varTablRO.$('tr.danger').length>0){
			/*console.log(varTablRO.$('tr.danger')[0]);
			console.log(varTablRO.$('tr.danger')[0]);
			console.log(varTablRO.$('tr.danger')[0].cells[0]);
			console.log(varTablRO.$('tr.danger')[0].cells[0].childNodes[0]);
			console.log(varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue);*/
			var objNombre = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
			var objllantaTrx = document.getElementById("llantaTrx"); 
			objAccion.value ="Buscar";
			objllantaTrx.value = objNombre; 
			objFormAdministrarLlantas.submit();
		}else{
			alert("Seleccionar algun registro");
		}
        return; 
	}
	
	if("Modificar"==pButton){
		if(varTablRO.$('tr.danger').length>0){
			var objNombre = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
			var objllantaTrx = document.getElementById("llantaTrx"); 
			objAccion.value ="Modificar";
			objllantaTrx.value = objNombre; 
			objFormAdministrarLlantas.submit();
		}else{
			alert("Seleccionar algun registro");
		}
        return;
	}
	
	if("Borrar"==pButton){
		if(varTablRO.$('tr.danger').length>0){
			var objNombre = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
			var objllantaTrx = document.getElementById("llantaTrx"); 
			objAccion.value ="Borrar";
			objllantaTrx.value = objNombre; 
			var confirmBorrar = confirm("¿Seguro que desea borrar este registro?");
			if(confirmBorrar){
			objFormAdministrarLlantas.submit();
			}
			return; 
		}else{
			alert("Seleccionar algun registro");
		}
        return;
	}
	
	
}

function environmentNueva(){
	
	objNuevaBtn.disabled = true; 
	objBuscarBtn.disabled = true; 
	objSalirBtn.disabled = true; 
	objModificarBtn.disabled = true; 
	objBorrarBtn.disabled = true; 
	
	objGuardarBtn.disabled = false;
	objGuardarBtn.style.backgroundColor="";
	objCancelarBtn.disabled = false;
	objCancelarBtn.style.backgroundColor="";
	
	objCodigo.disabled=false;
	objMedida.disabled=false;
	objMarca.disabled=false;
	objModelo.disabled=false;
	objProfundidad.disabled=false;
	objEstado.disabled=false;
	objCosto.disabled=false;
	objFechaAlta.disabled=false;
	
	  var objCurrentDate = new Date(); 
	    
	    var objCurrentMonth = objCurrentDate.getMonth()+1; 
	    if(objCurrentMonth<10){
	    	objCurrentMonth = "0"+objCurrentMonth;
	    }
	    
	   
	    var objCurrentDay = objCurrentDate.getDate(); 
	    if(objCurrentDay<10){
	    	objCurrentDay = "0"+objCurrentDay; 	
	    }
	    /** The specified value 7/02/2019 does not conform to the required format, yyyy-MM-dd **/
	    objFechaAlta.value = objCurrentDate.getFullYear()+"-"+objCurrentMonth+"-"+objCurrentDay;
	
	    objCodigo.focus();    
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
	  objModificarBtn.disabled = false; 
	  objModificarBtn.style.backgroundColor="";
	  objBorrarBtn.disabled = false; 
	  objBorrarBtn.style.backgroundColor="";
	  
	  objCodigo.disabled=true;
	  objMedida.disabled=true;
	  objMarca.disabled=true;
	  objModelo.disabled=true;
	  objProfundidad.disabled=true;
	  objEstado.disabled=true;
	  objCosto.disabled=true;
	  objFechaAlta.disabled=true;
	
}

function handleEvent(e){
	if(e.type=="keypress"){
	  if(e.code=="Enter"||e.code=="Tab"){
		 e.preventDefault();  
		 
		 var inputs = document.querySelectorAll("input,select"); 
		 const currentNode = e.target; 
		 const currentIndex = Array.from(inputs).findIndex(el => currentNode.isEqualNode(el));
		        if( currentIndex==0
		          ||currentIndex==1
		          ||currentIndex==2
		          ||currentIndex==3
		          ||currentIndex==4
		          ||currentIndex==5
		          ||currentIndex==6
		          /**||currentIndex==7 fechaAlta **/
		          ){
		        
		        if("codigo"==e.target.id){
		        	document.getElementById("msgCodigo").textContent ="";
		        	if(""!=document.getElementById("codigo").value){
		        		 $.post('AdministrarLlantasCO', {
		        			 accionAdministrarLlantas : "validaCodigo",
		        			 pCodigo:document.getElementById("codigo").value
		        		 }, function(responseText) {
		        			 if(""!=responseText){
		        				 document.getElementById("msgCodigo").textContent = responseText; 
		        				 document.getElementById("msgCodigo").style.color="red";
		        			 }else{
		        				 environmentNueva();
		        			 }
		        		 });
		        		 if(""!=document.getElementById("msgCodigo").textContent){
		        			 environmentExisteCodigo();
		        		 }
		        	}
		        } 	
		        	
		        inputs[currentIndex + 1].focus();
		        return; 
		        }
		 
		 if("fechaAlta"==e.target.id){
			 document.getElementById("GuardarBtn").focus();
			 return; 
		 }
		 
	  }	
	}
}

function environmentExisteCodigo(){
	    objNuevaBtn = document.getElementById("NuevaBtn");
	    objGuardarBtn = document.getElementById("GuardarBtn");
	    objBorrarBtn = document.getElementById("BorrarBtn"); 
	    objBuscarBtn = document.getElementById("BuscarBtn"); 
	    objModificarBtn = document.getElementById("ModificarBtn"); 
	    objCancelarBtn = document.getElementById("CancelarBtn"); 
	    objSalirBtn = document.getElementById("SalirBtn"); 
	    
	    objGuardarBtn.disabled = true;
	    objBorrarBtn.disabled = true;
	    objModificarBtn.disabled = true;
	    objCancelarBtn.disabled = true;
	    
	    
	    objCodigo = document.getElementById("codigo"); 
	    objMedida = document.getElementById("medida"); 
	    objMarca= document.getElementById("marca");
	    objModelo = document.getElementById("modelo"); 
	    objProfundidad= document.getElementById("profundidad");
	    objEstado = document.getElementById("estado"); 
	    objCosto= document.getElementById("costo");
	    objFechaAlta= document.getElementById("fechaAlta");
	    
	    objMedida.disabled=true;
	    objMarca.disabled=true;
	    objModelo.disabled=true;
	    objProfundidad.disabled=true;
	    objEstado.disabled=true;
	    objCosto.disabled=true;
	    objFechaAlta.disabled=true;
}

function onchangeCodigo(){
	document.getElementById("msgCodigo").textContent ="";
	if(""!=document.getElementById("codigo").value){
		 $.post('AdministrarLlantasCO', {
			 accionAdministrarLlantas : "validaCodigo",
			 pCodigo:document.getElementById("codigo").value
		 }, function(responseText) {
			 if(""!=responseText){
				 document.getElementById("msgCodigo").textContent = responseText; 
				 document.getElementById("msgCodigo").style.color="red";
			 }
		 });
		 if(""!=document.getElementById("msgCodigo").textContent){
			 environmentExisteCodigo();
		 }
	}
}

