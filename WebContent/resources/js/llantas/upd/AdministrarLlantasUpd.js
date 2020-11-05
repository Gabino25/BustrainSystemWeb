/**
 * 
 */

$(document).ready(function() {
  var objtipoTrxValue = document.getElementById("tipoTrx").value; 
  document.getElementById("tipo").value = objtipoTrxValue; 
});

function Regresar(){
	var varformUpd = document.getElementById("formUpd"); 
	var varaccionAdministrarLlantas = document.getElementById("accionAdministrarLlantas"); 
	varaccionAdministrarLlantas.value="Regresar";
	varformUpd.submit();
}

function Actualizar(){
	
	 var objCodigo = document.getElementById("codigo"); 
	 var objMedida = document.getElementById("medida"); 
	 var objMarca= document.getElementById("marca");
	 var objModelo = document.getElementById("modelo"); 
	 var objProfundidad= document.getElementById("profundidad");
	 var objTipo = document.getElementById("tipo"); 
	 var objCosto= document.getElementById("costo");
	 var objFechaAlta= document.getElementById("fechaAlta");
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
	 if(""==objTipo.value){
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
	
	var varformUpd = document.getElementById("formUpd"); 
	var varaccionAdministrarLlantas = document.getElementById("accionAdministrarLlantas"); 
	varaccionAdministrarLlantas.value="Update";
	varformUpd.submit();
}