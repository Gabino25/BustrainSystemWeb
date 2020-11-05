/**
 * 
 */


var varFolio = null; 
var varSelectGasolineraId= null; 
var varSelectUnidadId= null; 
var varSelectOperadorId= null; 
var objfechaC=null;
var varLitros= null; 
var varTotal = null; 
var varKmanterior= null; 
var varKmactual= null; 
var varCombustible = null; 
var varSubmitFormId = null; 
var varResetFormId = null; 
var objinputselectOperadorId = null; 
var objinputselectUnidadId = null; 
var objEliminar= null;
var varTablRO = null; 

$(document).ready(function() {
	$.ajaxSetup({async: false}); 
	  
	$("#submitFormId").hide();
	$("#resetFormId").hide();
	
$.datepicker.regional['es'] = {
		closeText: "Cerrar",
		prevText: "&#x3C;Ant",
		nextText: "Sig&#x3E;",
		currentText: "Hoy",
		monthNames: [ "enero","febrero","marzo","abril","mayo","junio",
		"julio","agosto","septiembre","octubre","noviembre","diciembre" ],
		monthNamesShort: [ "ene","feb","mar","abr","may","jun",
		"jul","ago","sep","oct","nov","dic" ],
		dayNames: [ "domingo","lunes","martes","miércoles","jueves","viernes","sábado" ],
		dayNamesShort: [ "dom","lun","mar","mié","jue","vie","sáb" ],
		dayNamesMin: [ "D","L","M","X","J","V","S" ],
		weekHeader: "Sm",
		dateFormat: "dd/mm/yy",
		firstDay: 1,
		isRTL: false,
		showMonthAfterYear: false,
	    yearSuffix: ""
	    };
    
	 $.datepicker.setDefaults($.datepicker.regional['es']);
	 $("#fechaDatePicker" ).datepicker();
	

	  $('#TablRO').DataTable( {
		 "pageLength": 25,
		 "order": [[ 0, "desc" ]],
    	 "scrollY": 350,
         "scrollX": true,
        "language": { "url": "/BustrainSystemWeb/resources/json/Spanish.json"},
        "columns": [
    	    { orderable: true  },   /*(1)*/
    	    { orderable: false },  /*(2)*/
    	    { orderable: false },  /*(3)*/
    	    { orderable: false },  /*(4)*/
    	    { orderable: false },  /*(5)*/
    	    { orderable: false },  /*(6)*/
    	  ],
        "footerCallback": function( tfoot, data, start, end, display ) {
        	
        	 var api = this.api(), data;
             /* Remove the formatting to get integer data for summation */
             var intVal = function ( i ) {
                 return typeof i === 'string' ?
                     i.replace(/[\$,]/g, '')*1 :
                     typeof i === 'number' ?
                         i : 0;
             };
        	
             /* Total over all pages */

             total = api
                 .column(2)
                 .data()
                 .reduce( function (a, b) {
                     return intVal(a) + intVal(b);
                 }, 0 );
             
             totalLitros = api
             .column(3)
             .data()
             .reduce( function (a, b) {
                 return intVal(a) + intVal(b);
             }, 0 );
             
             /* Total over this page */

             pageTotal = api
                 .column( 4, { page: 'current'} )
                 .data()
                 .reduce( function (a, b) {
                     return intVal(a) + intVal(b);
                 }, 0 );
             
            $(tfoot).find('th').eq(0).html("");
            $(tfoot).find('th').eq(1).html("");
            $(tfoot).find('th').eq(2).html(total.toLocaleString('en-US', { style: 'currency', currency: 'USD' }));
            $(tfoot).find('th').eq(3).html(totalLitros.toLocaleString('en-US'));
            $(tfoot).find('th').eq(4).html("");
            $(tfoot).find('th').eq(5).html("");
          } /** End footerCallback **/
        
    } );
	 
	 
	 $.widget( "custom.combobox", {
	      _create: function() {
	    	this.wrapper = $( "<span>" )
	          .addClass( "custom-combobox" )
	          .insertAfter( this.element );
	        this.element.hide();
	        this._createAutocomplete();
	        this._createShowAllButton();
	      },
	      _createAutocomplete: function() {
	        var selected = this.element.children( ":selected" ),
	        value = selected.val() ? selected.text() : "";
	        this.input = $( "<input>" )
	          .appendTo( this.wrapper )
	          .val( value )
	          .attr("id","input"+this.element[0].id)
	          .attr( "title", "" )
	          .addClass( "custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left" )
	          .autocomplete({
	            delay: 0,
	            minLength: 2,
	            source: $.proxy( this, "_source" )
	          })
	          .tooltip({
	        	classes: {
	              "ui-tooltip": "ui-state-highlight"
	            }
	          });
	 
	        this._on( this.input, {
	          autocompleteselect: function( event, ui ) {
	            ui.item.option.selected = true;
	            this._trigger( "select", event, {
	              item: ui.item.option
	            });
	          },
	 
	          autocompletechange: "_removeIfInvalid"
	        });
	      },
	 
	      _createShowAllButton: function() {
	        var input = this.input,
	          wasOpen = false;
	 
	        $( "<a>" )
	          .attr( "tabIndex", -1 )
	          .attr( "title", "Show All Items" )
	          .attr( "id","showBtn"+this.element[0].id)
	          .tooltip()
	          .appendTo( this.wrapper )
	          .button({
	            icons: {
	              primary: "ui-icon-triangle-1-s"
	            },
	            text: false
	          })
	          .removeClass( "ui-corner-all" )
	          .addClass( "custom-combobox-toggle ui-corner-right" )
	          .on( "mousedown", function() {
	            wasOpen = input.autocomplete( "widget" ).is( ":visible" );
	          })
	          .on( "click", function() {
	            input.trigger( "focus" );
	 
	            /** Close if already visible **/
	            if ( wasOpen ) {
	              return;
	            }
	 
	            /** Pass empty string as value to search for, displaying all results **/
	            input.autocomplete( "search", "" );
	          });
	      },
	 
	      _source: function( request, response ) {
	        var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
	        response( this.element.children( "option" ).map(function() {
	          var text = $( this ).text();
	          if ( this.value && ( !request.term || matcher.test(text) ) )
	            return {
	              label: text,
	              value: text,
	              option: this
	            };
	        }) );
	      },
	 
	      _removeIfInvalid: function( event, ui ) {
	 
	        /** Selected an item, nothing to do **/
	        if ( ui.item ) {
	          return;
	        }
	 
	        /** Search for a match (case-insensitive) **/
	        var value = this.input.val(),
	          valueLowerCase = value.toLowerCase(),
	          valid = false;
	        this.element.children( "option" ).each(function() {
	          if ( $( this ).text().toLowerCase() === valueLowerCase ) {
	            this.selected = valid = true;
	            return false;
	          }
	        });
	 
	        /** Found a match, nothing to do **/
	        if ( valid ) {
	          return;
	        }
	 
	        /** Remove invalid value **/
	        this.input
	          .val( "" )
	          .attr( "title", value + " no coincidio con ningun elemento " )
	          .tooltip( "open" );
	    
	        this.element.val( "" );
	        this._delay(function() {
	          this.input.tooltip( "close" ).attr( "title", "" );
	        }, 2500 );
	        this.input.autocomplete( "instance" ).term = "";
	      },
	 
	      _destroy: function() {
	        this.wrapper.remove();
	        this.element.show();
	      }
	    }); /** END  $.widget( **/
	
	
	 $("#selectOperadorId" ).combobox(); 
	 $("#selectUnidadId" ).combobox(); 
	
	 objinputselectOperadorId = document.getElementById("inputselectOperadorId"); 
	 objinputselectUnidadId = document.getElementById("inputselectUnidadId"); 
	 document.getElementById("showBtnselectOperadorId").hidden = true; 
	 document.getElementById("showBtnselectUnidadId").hidden = true; 
	 
	 objinputselectOperadorId.size=50;
	 
	 varFolio= document.getElementById("folio");                                  
	 varSelectGasolineraId = document.getElementById("selectGasolineraId");       
	 varSelectUnidadId = document.getElementById("selectUnidadId");               
	 varSelectOperadorId = document.getElementById("selectOperadorId");           
	 varLitros = document.getElementById("litros");                               
	 varTotal= document.getElementById("total");  
	 objfechaC= document.getElementById("fechaC");
	 varKmanterior = document.getElementById("kmanterior");                       
	 varKmactual = document.getElementById("kmactual");                           
	 varCombustible= document.getElementById("combustible");     
	 varSubmitFormId = document.getElementById("submitFormId");   
	 varResetFormId = document.getElementById("resetFormId");   
	                                                                  
	 document.getElementById("selectGasolineraId").disabled = true; 
	 document.getElementById("selectUnidadId").disabled = true; 
	 document.getElementById("selectOperadorId").disabled = true; 
	 document.getElementById("litros").disabled = true; 
	 document.getElementById("total").disabled = true; 
	 document.getElementById("fechaC").disabled = true; 
	 document.getElementById("kmanterior").disabled = true; 
	 document.getElementById("kmactual").disabled = true; 
	 document.getElementById("combustible").disabled = true; 
	 document.getElementById("submitFormId").disabled = true; 
	 document.getElementById("resetFormId").disabled = true; 
	 document.getElementById("GuardarBtn").disabled = true; 
	
	 
	 varFolio.onkeyup = varFolio.onkeydown = varFolio.onkeypress = eventHandle;
	 varSelectGasolineraId.onkeyup = varSelectGasolineraId.onkeydown = varSelectGasolineraId.onkeypress = eventHandle;
	 varSelectUnidadId.onkeyup = varSelectUnidadId.onkeydown = varSelectUnidadId.onkeypress = eventHandle;
	 varSelectOperadorId.onkeyup = varSelectOperadorId.onkeydown = varSelectOperadorId.onkeypress = eventHandle;
	 varLitros.onkeyup = varLitros.onkeydown = varLitros.onkeypress = eventHandle;
	 varTotalonkeyup = varTotalonkeydown = varTotalonkeypress = eventHandle;
	 varKmanterior.onkeyup = varKmanterior.onkeydown = varKmanterior.onkeypress = eventHandle;
	 varKmactual.onkeyup = varKmactual.onkeydown = varKmactual.onkeypress = eventHandle;
	 varCombustibleonkeyup = varCombustibleonkeydown = varCombustibleonkeypress = eventHandle;
	 
	 objinputselectOperadorId.onkeyup = objinputselectOperadorId.onkeydown = objinputselectOperadorId.onkeypress = eventOperadorHandle;
 	 objinputselectUnidadId.onkeyup = objinputselectUnidadId.onkeydown = objinputselectUnidadId.onkeypress = eventUnidadHandle;
 	objinputselectUnidadId.onchange = onchangeUnidad;
	 
} );


function detectaCambio(){
	
	  var currentDate = $( "#fechaDatePicker" ).datepicker( "getDate" );

	
	  var jsDay = currentDate.getDate(); 
	  var jsMonth = currentDate.getMonth(); 
	  var jsFullYear = currentDate.getFullYear(); 
	  
	  $.post('EntryCombustibleCO', {
		    accionEntryCombustible : "filtraPorFecha",
		    jsDay: jsDay,
		    jsMonth:jsMonth,
		    jsFullYear:jsFullYear
		}, function(responseText) {
			 $('#DivTablRO').html(responseText);
			 varTablRO = $('#TablRO').DataTable( {
				 "pageLength": 25,
				 "order": [[ 0, "desc" ]],
		    	 "scrollY": 450,
		         "scrollX": true,
		        "language": { "url": "/BustrainSystemWeb/resources/json/Spanish.json"},
		        "columns": [
		    	    { "orderable": true },   /*(1)*/
		    	    { "orderable": false },  /*(2)*/
		    	    { "orderable": false },  /*(3)*/
		    	    { "orderable": false },  /*(4)*/
		    	    { "orderable": false },  /*(5)*/
		    	    { "orderable": false },  /*(6)*/
		    	  ],
		        "footerCallback": function( tfoot, data, start, end, display ) {
		        	
		        	 var api = this.api(), data;
		             /* Remove the formatting to get integer data for summation */
		             var intVal = function ( i ) {
		                 return typeof i === 'string' ?
		                     i.replace(/[\$,]/g, '')*1 :
		                     typeof i === 'number' ?
		                         i : 0;
		             };
		        	
		             /* Total over all pages */

		             total = api
		                 .column(2)
		                 .data()
		                 .reduce( function (a, b) {
		                     return intVal(a) + intVal(b);
		                 }, 0 );
		             
		             totalLitros = api
		             .column(3)
		             .data()
		             .reduce( function (a, b) {
		                 return intVal(a) + intVal(b);
		             }, 0 );
		             
		             /* Total over this page */

		             pageTotal = api
		                 .column( 4, { page: 'current'} )
		                 .data()
		                 .reduce( function (a, b) {
		                     return intVal(a) + intVal(b);
		                 }, 0 );
		             
		            $(tfoot).find('th').eq(0).html("");
		            $(tfoot).find('th').eq(1).html("");
		            $(tfoot).find('th').eq(2).html(total.toLocaleString('en-US', { style: 'currency', currency: 'USD' }));
		            $(tfoot).find('th').eq(3).html(totalLitros.toLocaleString('en-US'));
		            $(tfoot).find('th').eq(4).html("");
		            $(tfoot).find('th').eq(5).html("");
		          } /** End footerCallback **/
		    } ); 
		});

	  $('#TablRO tbody').on( 'click', 'tr', function () {
		   if ( $(this).hasClass('danger') ) {
	            $(this).removeClass('danger');
	        }
	        else {
	        	varTablRO.$('tr.danger').removeClass('danger');
	            $(this).addClass('danger');
	        }
	 } );
	
};

function acccionEntryCombustible(pButton){
	var objFormEntryCombustible = document.getElementById("formEntryCombustible"); 
	var objAccion = document.getElementById("accion");
	
	if("Guardar"==pButton){
		 varSubmitFormId = document.getElementById("submitFormId");   
		 objAccion.value="Guardar";
		 objinputselectOperadorId = document.getElementById("inputselectOperadorId"); 
		  objinputselectUnidadId = document.getElementById("inputselectUnidadId"); 
		  objinputselectOperadorId.required = true;
		  objinputselectUnidadId.required = true; 
		  
		  if(""!=document.getElementById("folioMsg").textContent){
			document.getElementById("folio").focus();
      		return;   
      	  }
		  
		  if(""==document.getElementById("kmanterior").value){
			  alert("El campo Km Anterior es obligatorio.");
			  return;
		  }
		  
		  if(""==document.getElementById("combustible").value){
			  alert("El campo Combustible es obligatorio.");
			  return;
		  }
		  
		  document.getElementById("kmanterior").disabled = false;
		  document.getElementById("combustible").disabled = false; 
		  
		  varSubmitFormId.click();
		  return; 
	}
	
	if("Eliminar"==pButton){
		if(varTablRO.$('tr.danger').length>0){
			var objFolioVale = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
			var objFolioValeTrx = document.getElementById("folioVale"); 
			objFolioValeTrx.value = objFolioVale;
			objAccion.value="Eliminar"; 
			var confirmBorrar = confirm("¿Seguro que desea borrar este registro?");
			if(confirmBorrar){
				objFormEntryCombustible.submit();
		    }
		}else{
			alert("Seleccionar algun registro");
		}
		return;
	 }
	if("Salir"==pButton){
		objAccion.value = "Salir";
		objFormEntryCombustible.submit();
		return; 
	}
	
	
	
}
function handleEvent(e){
	if(e.code=="Enter"||e.code=="Tab"){
		e.preventDefault();
	}
}

function eventHandle(e){

	if(e.type=="keypress"){
		  if(e.code=="Enter"||e.code=="Tab"){
			  e.preventDefault();
			  var objTarget = e.target; 
	          var objTargetId = objTarget.id; 
	          if("folio"==objTargetId){
	        	  
	        	  document.getElementById("folioMsg").textContent="";
	        	  if(""!=document.getElementById("folio").value){
	        	  $.post('EntryCombustibleCO', {
	        		  accionEntryCombustible : "validaFolio",
	 	    		  pFolioValue:document.getElementById("folio").value
	 	 		     }, function(responseText) {
	 	 		    	
	 	 			     if(""!=responseText){
	 	 			    	 document.getElementById("folioMsg").textContent=responseText;
	 	 			    	 document.getElementById("folioMsg").style.color="red"; 
	 	 			    	 document.getElementById("selectGasolineraId").disabled = true; 
	 	 			    	 document.getElementById("selectUnidadId").disabled = true; 
	 	 			    	 document.getElementById("selectOperadorId").disabled = true; 
	 	 			    	 document.getElementById("litros").disabled = true; 
	 	 			    	 document.getElementById("total").disabled = true; 
	 	 			    	 document.getElementById("kmactual").disabled = true; 
	 	 			    	 document.getElementById("submitFormId").disabled = true; 
	 	 			    	 document.getElementById("resetFormId").disabled = true; 
	 	 			    	 document.getElementById("GuardarBtn").disabled = true; 
	 	 			    	 document.getElementById("fechaC").disabled = true; 
	 	 			    	 
	 	 			     }else{
	 	 			    	document.getElementById("selectGasolineraId").disabled = false; 
	 	 			    	document.getElementById("selectUnidadId").disabled = false; 
	 	 			    	document.getElementById("selectOperadorId").disabled = false; 
	 	 			    	document.getElementById("litros").disabled = false; 
	 	 			    	document.getElementById("fechaC").disabled = false; 
	 	 			    	document.getElementById("total").disabled = false; 
	 	 			    	document.getElementById("kmactual").disabled = false; 
	 	 			    	document.getElementById("submitFormId").disabled = false; 
	 	 			    	document.getElementById("resetFormId").disabled = false; 
	 	 			    	document.getElementById("GuardarBtn").disabled = false; 
	 	 			    	document.getElementById("GuardarBtn").style.backgroundColor = "";
	 	 			    	
	 	 			     }
	 	 		     });
	        	  }/** END  if(""!=document.getElementById("folio").value){ **/
	        	  
	        	  
	        	  document.getElementById("selectGasolineraId").focus(); 
	        	  return; 
	          }else if("selectGasolineraId"==objTargetId){
	        	  document.getElementById("inputselectUnidadId").focus(); 
	        	  return; 
	          }else if("litros"==objTargetId){
	        	  document.getElementById("total").focus(); 
	        	  return; 
	          }else if("total"==objTargetId){
	        	  document.getElementById("kmanterior").focus(); 
	        	  return; 
	          }
	          
		  }
	}
}


function eventOperadorHandle(e){
	if(e.type=="keypress"){
		  if(e.code=="Enter"){
			  e.preventDefault();
			  var objTargetId = e.target.id; 
			  if("inputselectOperadorId"==objTargetId){
	        	  document.getElementById("litros").focus(); 
	        	  return; 
	          }
		  }
    }
}


function eventUnidadHandle(e){
	if(e.type=="keypress"){
		  if(e.code=="Enter"||e.code=="Tab"){
			  e.preventDefault();
			  var objTargetId = e.target.id; 
			  if("inputselectUnidadId"==objTargetId){
				  
				  if(""!=document.getElementById("inputselectUnidadId").value){
				  $.post('EntryCombustibleCO', {
	        		  accionEntryCombustible : "obtieneKilometrajeAnterior",
	 	    		  pUnidadValue:document.getElementById("inputselectUnidadId").value
	 	 		     }, function(responseText) {
	 	 		       document.getElementById("kmanterior").value =responseText; 
	 	 		     }); 	 
				  }
				  
				  if(""!=document.getElementById("inputselectUnidadId").value){
				  $.post('UnidadesCO', {
					  accionName : "obtieneCombustible",
	 	    		  pUnidadValue:document.getElementById("inputselectUnidadId").value
	 	 		     }, function(responseText) {
	 	 		       document.getElementById("combustible").value =responseText; 
	 	 		     }); 	 
				  }
				  
	        	  document.getElementById("inputselectOperadorId").focus(); 
	        	  return; 
	          }
		  }
  }
}

function validaFormulario(pForma){
	
	 varKmanterior = document.getElementById("kmanterior").value;                       
	 varKmactual = document.getElementById("kmactual").value;       
	 var varDiferenciaKms = varKmactual-varKmanterior; 
	 if(varDiferenciaKms<=0){
		 document.getElementById("kmanterior").disabled = true; 
		 document.getElementById("combustible").disabled = true; 
		 alert("La diferencia de kilometros no es mayor a 0");
		 return false;
	 }
	 return true; 
}

function onchangeUnidad(){
	
	 if(""!=document.getElementById("inputselectUnidadId").value){
		  $.post('EntryCombustibleCO', {
   		  accionEntryCombustible : "obtieneKilometrajeAnterior",
    		  pUnidadValue:document.getElementById("inputselectUnidadId").value
 		     }, function(responseText) {
 		       document.getElementById("kmanterior").value =responseText; 
 		     }); 	 
		  }
		  
		  if(""!=document.getElementById("inputselectUnidadId").value){
		  $.post('UnidadesCO', {
			  accionName : "obtieneCombustible",
    		  pUnidadValue:document.getElementById("inputselectUnidadId").value
 		     }, function(responseText) {
 		       document.getElementById("combustible").value =responseText; 
 		     }); 	 
		  }
		  
   	  document.getElementById("inputselectOperadorId").focus(); 
   	  return; 
	
}


