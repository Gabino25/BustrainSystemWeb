/**
 * 
 */

var varTablRO = null; 

$(document).ready(function() {
    varTablRO = $('#TablRO').DataTable({ 
    	"pageLength": 25,
        "order": [[ 0, "desc" ]],
   	    "scrollY": 350,
        "scrollX": true,
        "language": { "url": "/BustrainSystemWeb/resources/json/Spanish.json"},
        "columns": [
     	    { "searchable": false,"orderable": false },   /*(1)*/
     	    { "orderable": true },  /*(2)*/
     	    { "orderable": false },  /*(3)*/
     	    { "orderable": false }  /*(4)*/
     	    ]
    }); /** END DataTable({  **/
    
    $('#TablRO tbody').on( 'click', 'tr', function () {
		   if ( $(this).hasClass('danger') ) {
	            $(this).removeClass('danger');
	        }
	        else {
	        	varTablRO.$('tr.danger').removeClass('danger');
	            $(this).addClass('danger');
	        }
	 } );
    
    $("#submitForm").hide();
    $("#resetForm").hide();
    $("#updIdDiv").hide();
    
});


function accionCatalogarUnidades(pButton){
	if("Buscar"==pButton){
		if(varTablRO.$('tr.danger').length>0){
			var objFolioTrx = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
			var objUnidadTrx = varTablRO.$('tr.danger')[0].cells[1].childNodes[0].nodeValue;
			if(typeof(varTablRO.$('tr.danger')[0].cells[2].childNodes[0])!="undefined"){
			var objCategoria1Trx = varTablRO.$('tr.danger')[0].cells[2].childNodes[0].nodeValue;
			var objcategoria1 = document.getElementById("categoria1"); 
			objcategoria1.value = objCategoria1Trx; 
			}
			if(typeof(varTablRO.$('tr.danger')[0].cells[3].childNodes[0])!="undefined"){
			var objCategoria2Trx = varTablRO.$('tr.danger')[0].cells[3].childNodes[0].nodeValue; 
			var objcategoria2 = document.getElementById("categoria2"); 
			objcategoria2.value = objCategoria2Trx; 
			}
			
			var objfolio = document.getElementById("folio"); 
			var objunidad = document.getElementById("unidad"); 
			
			
			
			objfolio.value = objFolioTrx; 
			objunidad.value = objUnidadTrx; 
			
			$("#updIdDiv").show();
			
			
		}else{
			alert("Seleccionar algun registro");
		}
		return;
	}else if("Actualizar"==pButton){
	   document.getElementById("accionCatalogarUnidades").value = "Actualizar"; 
	   document.getElementById("submitForm").click();
	}
}
