/**
 * 
 */
$(document).ready(function() {
	$("#submitFormId").hide();
	$("#resetFormId").hide();
});

function Regresar(){
	var varformUpd = document.getElementById("formUpd"); 
	var objaccionId = document.getElementById("accionId"); 
	objaccionId.value="Regresar";
	varformUpd.submit();
}

function Actualizar(){
	var objfileFoto = document.getElementById("fileFoto");
	var objoutput = document.getElementById("output");
	var srcobjoutput = objoutput.src;
	console.log(srcobjoutput);
	if(""==srcobjoutput||null==srcobjoutput){
		objfileFoto.required = true;
	}
	
	
	var varformUpd = document.getElementById("formUpd"); 
	var objaccionId = document.getElementById("accionId"); 
	var objSubmitFormId = document.getElementById("submitFormId");
	objaccionId.value="Update";
    
	
	objSubmitFormId.click();
}

function validaFormulario(){
	    var objNombre = document.getElementById("nombre"); 
	    var objPuesto = document.getElementById("puesto"); 
	    var objDireccion = document.getElementById("direccion"); 
	    var objNotas = document.getElementById("notas"); 
	    var objRfc = document.getElementById("rfc");
	    var objCurp = document.getElementById("curp");
	    
	  objNombre.value = objNombre.value.toUpperCase();   
	  objPuesto.value = objPuesto.value.toUpperCase(); 
	  objDireccion.value = objDireccion.value.toUpperCase(); 
	  objNotas.value = objNotas.value.toUpperCase();
	  objRfc.value = objRfc.value.toUpperCase();
	  objCurp.value = objCurp.value.toUpperCase();
	  return true;
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