

$(document).ready(function() {
   var objNombre = document.getElementById("nombre");
   var objPassword = document.getElementById("password"); 
   objNombre.onkeydown = objNombre.onkeyup = objNombre.onkeypress = handleEvent; 
   objPassword.onkeydown = objPassword.onkeyup = objPassword.onkeypress = handleEvent; 
   objNombre.value = "GABINO"; 
   objPassword.value = "BUSTRAIN";
} );

function validarFormIndex(forma){
	
	var strNombreUsuario = forma.nombreUsuario; 
	var strPasswordUsuario = forma.passwordUsuario;
	if(""==strNombreUsuario.value){
		alert("El Nombre es obligatorio.");
		return false; 
	}
	
	if(""==strPasswordUsuario.value){
		alert("La Contrase\u00f1a es obligatoria");
		return false; 
	}
	strNombreUsuario.value = strNombreUsuario.value.toUpperCase();
	strPasswordUsuario.value = strPasswordUsuario.value.toUpperCase();
	return true; 
};

function handleEvent(e){
	if(e.type=="keypress"){
	  if(e.code=="Enter"||e.code=="Tab"){
     	 e.preventDefault(); 
     	 var objectID = e.target.id; 
     	 if("nombre"==objectID){
     		 document.getElementById("password").focus(); 
     	 }else if("password"==objectID){
     		document.getElementById("submitBtn").focus(); 
     	 }
	  }
	}
}