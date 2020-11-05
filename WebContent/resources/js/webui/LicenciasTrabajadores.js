/**
 * 
 */

$(document).ready(function() {
    $('#TablRO').DataTable( {
    	 "pageLength": 25,
    	 "order": [[ 0, "desc" ]],
    	 "scrollY": 700,
         "scrollX": true,
         "language": { "url": "/BustrainSystemWeb/resources/json/Spanish.json"},
         "columns": [
     	    { "orderable": true },   /*(1)*/
     	    { "orderable": false },  /*(2)*/
     	    { "orderable": false },  /*(3)*/
     	    { "orderable": false }  /*(4)*/
     	    ]
    } );
} );

function accionLicTrabaj(pValue){
	var objLicenTrabajadoresForm = document.getElementById("licenTrabajadoresForm");
	 var objAccionId = document.getElementById("accionId"); 
	if("Salir"==pValue){
		objAccionId.value = "Salir";
		objLicenTrabajadoresForm.submit();
	}
	else if("LicenVencida"==pValue){
	 
		$.post('LicenciasTrabajadoresCO', {
			accionName:"LicenVencida"
			
			},function(responseText){
				$('#DivTablRO').html(responseText);
                   
				$('#TablRO').DataTable( {
							    	 "pageLength": 25,
							    	 "order": [[ 0, "desc" ]],
							    	 "scrollY": 700,
							         "scrollX": true,
							         "language": { "url": "/BustrainSystemWeb/resources/json/Spanish.json"},
							         "columns": [
							     	    { "orderable": true },   /*(1)*/
							     	    { "orderable": false },  /*(2)*/
							     	    { "orderable": false },  /*(3)*/
							     	    { "orderable": false }  /*(4)*/
							     	    ]
							    } );
			}
		);
		
	}
	else if("ProxAvencer"==pValue){
		 
		$.post('LicenciasTrabajadoresCO', {
			accionName:"ProxAvencer"
			
			},function(responseText){
				$('#DivTablRO').html(responseText);

				$('#TablRO').DataTable( {
							    	 "pageLength": 25,
							    	 "order": [[ 0, "desc" ]],
							    	 "scrollY": 700,
							         "scrollX": true,
							         "language": { "url": "/BustrainSystemWeb/resources/json/Spanish.json"},
							         "columns": [
							     	    { "orderable": true },   /*(1)*/
							     	    { "orderable": false },  /*(2)*/
							     	    { "orderable": false },  /*(3)*/
							     	    { "orderable": false }  /*(4)*/
							     	    ]
							    } );
			}
		);
		
	}
	/*else if("Todos"==pValue){
		objAccionId.value = "Todos";
		objLicenTrabajadoresForm.submit();
	}*/
}

