/**
 * RevisionProfunidad 
 */

 var objNuevaBtn = null; 
 var objGuardarBtn = null; 
 var objBorrarBtn = null; 
 var objBuscarBtn = null; 
 var objModificarBtn = null; 
 var objCancelarBtn = null; 
 var objSalirBtn = null; 
 
 var objFolio= null; 
 var objSelectOperadorId = null; 
 var objSelectLlantaId = null; 
 var objSelectPosicionId = null; 
 var objFecha= null; 
 var objProfundidad= null; 
 var objSelectUnidadId = null; 
 var objPresionActual= null; 
 var objKilometraje= null; 
 var objNota = null;
 var objObservaciones = null; 
 var objCosto = null; 
 
 var objinputselectOperadorId = null; 
 var objinputselectLlantaId = null; 
 var objinputselectUnidadId = null;  
 var varTablRO = null; 
 
$(document).ready(function() {
	
	  function disablePrev() { window.history.forward() }
	    window.onload = disablePrev();
	    window.onpageshow = function(evt) { if (evt.persisted) disablePrev() }
	
	 $.ajaxSetup({async: false}); 
	    
	 $("#submitGuardar").hide();
	 $("#resetFormId").hide();
	 varTablRO = $('#TablRO').DataTable( {
		   "pageLength": 25,
		    "order": [[ 0, "desc" ]],
	    	"scrollY": 300,
	        "scrollX": true,
	        "language": { "url": "/BustrainSystemWeb/resources/json/Spanish.json"},
	        "columns": [
	    	    { "orderable": true }, /*(1)*/
	    	    { "orderable": false }, /*(2)*/
	    	    { "orderable": false },  /*(3)*/
	    	    { "orderable": false }, /*(4)*/
	    	    { "orderable": false }, /*(5)*/
	    	    { "orderable": false },  /*(6)*/
	    	    { "orderable": false },  /*(7)*/
	    	    { "orderable": false },  /*(8)*/
	    	    { "orderable": false },  /*(9)*/
	    	    { "orderable": false },  /*(10)*/
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
	          .attr("required","true")
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
	 $("#selectLlantaId" ).combobox(); 
	 $("#selectUnidadId" ).combobox(); 
	 
	 objinputselectOperadorId = document.getElementById("inputselectOperadorId"); 
	 objinputselectLlantaId = document.getElementById("inputselectLlantaId"); 
	 objinputselectUnidadId = document.getElementById("inputselectUnidadId"); 
	 document.getElementById("showBtnselectOperadorId").hidden = true; 
	 document.getElementById("showBtnselectLlantaId").hidden = true; 
	 document.getElementById("showBtnselectUnidadId").hidden = true; 
	 
	 objinputselectOperadorId.size=50;
	 objinputselectOperadorId.disabled = true;
	 objinputselectLlantaId.disabled = true; 
	 objinputselectUnidadId.disabled = true; 
	 
	 
	 objNuevaBtn = document.getElementById("NuevaBtn");
     objGuardarBtn = document.getElementById("GuardarBtn");
     objBorrarBtn = document.getElementById("BorrarBtn"); 
     objBuscarBtn = document.getElementById("BuscarBtn"); 
     objModificarBtn = document.getElementById("ModificarBtn"); 
     objCancelarBtn = document.getElementById("CancelarBtn"); 
     objSalirBtn = document.getElementById("SalirBtn"); 
     
     objFolio= document.getElementById("folio");
     objSelectOperadorId = document.getElementById("selectOperadorId");
     objSelectLlantaId = document.getElementById("selectLlantaId");
     objSelectPosicionId = document.getElementById("selectPosicionId");
     objFecha= document.getElementById("fecha");
     objProfundidad= document.getElementById("profundidad");
     objSelectUnidadId = document.getElementById("selectUnidadId");
     objPresionActual= document.getElementById("presionActual");
     objKilometraje= document.getElementById("kilometraje");
     objNota = document.getElementById("nota");
     objObservaciones = document.getElementById("observaciones");
     objCosto = document.getElementById("costo");
	
  
     
         if(null!=objGuardarBtn)
         objGuardarBtn.disabled = true;
         if(null!=objCancelarBtn)
         objCancelarBtn.disabled = true;
         
         if(null!=objFolio)
         objFolio.disabled = true;
         if(null!=objSelectOperadorId)
         objSelectOperadorId.disabled = true;
         if(null!=objSelectLlantaId)
         objSelectLlantaId.disabled = true;
         if(null!=objSelectPosicionId)
         objSelectPosicionId.disabled = true;
         if(null!=objFecha)
         objFecha.disabled = true;
         if(null!=objProfundidad)
         objProfundidad.disabled = true;
         if(null!=objSelectUnidadId)
         objSelectUnidadId.disabled = true;
         if(null!=objPresionActual)
         objPresionActual.disabled = true;
         if(null!=objKilometraje)
         objKilometraje.disabled = true;
         if(null!=objNota)
         objNota.disabled = true; 
         if(null!=objObservaciones)
         objObservaciones.disabled = true; 
         if(null!=objCosto)
         objCosto.disabled = true; 
         
         if(null!=objFolio)
         objFolio.onkeyup = objFolio.onkeydown = objFolio.onkeypress = handleEvent; 
         if(null!=objSelectOperadorId)
         objSelectOperadorId.onkeyup = objSelectOperadorId.onkeydown = objSelectOperadorId.onkeypress = handleEvent; 
         if(null!=objSelectLlantaId)
         objSelectLlantaId.onkeyup = objSelectLlantaId.onkeydown = objSelectLlantaId.onkeypress = handleEvent; 
         if(null!=objSelectPosicionId)
         objSelectPosicionId.onkeyup = objSelectPosicionId.onkeydown = objSelectPosicionId.onkeypress = handleEvent;
         if(null!=objFecha)
         objFecha.onkeyup = objFecha.onkeydown = objFecha.onkeypress = handleEvent; 
         if(null!=objProfundidad)
         objProfundidad.onkeyup = objProfundidad.onkeydown = objProfundidad.onkeypress = handleEvent; 
         if(null!=objSelectUnidadId)
         objSelectUnidadId.onkeyup = objSelectUnidadId.onkeydown = objSelectUnidadId.onkeypress = handleEvent;
         if(null!=objPresionActual)
         objPresionActual.onkeyup = objPresionActual.onkeydown = objPresionActual.onkeypress = handleEvent; 
         if(null!=objKilometraje)
         objKilometraje.onkeyup = objKilometraje.onkeydown = objKilometraje.onkeypress = handleEvent; 
         if(null!=objNota)
         objNota.onkeyup = objNota.onkeydown = objNota.onkeypress = handleEvent;
         if(null!=objObservaciones)
         objObservaciones.onkeyup = objObservaciones.onkeydown = objObservaciones.onkeypress = handleEvent;
         if(null!=objCosto)
         objCosto.onkeyup = objCosto.onkeydown = objCosto.onkeypress = handleEvent;
        
         objinputselectOperadorId.onkeyup = objinputselectOperadorId.onkeydown = objinputselectOperadorId.onkeypress = eventOperadorHandle;
    	 objinputselectLlantaId.onkeyup = objinputselectLlantaId.onkeydown = objinputselectLlantaId.onkeypress = eventLlantaHandle;
    	 objinputselectUnidadId.onkeyup = objinputselectUnidadId.onkeydown = objinputselectUnidadId.onkeypress = eventUnidadHandle;
        
    	 objinputselectLlantaId.onchange = onChangeLlanta; 
    	 
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
  	    objFecha.value = objCurrentDate.getFullYear()+"-"+objCurrentMonth+"-"+objCurrentDay;
 	   
         
     var objOtherTransaction = document.getElementById("otherTransaction"); 
	    if("Y"==objOtherTransaction.value){
	    	var objConfirm = confirm("Desea agregar otro Registro?");
	    	if(objConfirm==true){
	    		environmentNueva();
	    	}
	  }
	    
}); /** END ready(function() { **/



function accionRevProfLlantas(pButton){
	
	var objAccion = document.getElementById("accion");
	var objFormMovimientosLlantas = document.getElementById("formMovimientosLlantas");
	
	if("Nueva"==pButton){
		environmentNueva();
		return; 
	}
	
	if("Guardar"==pButton){
	  var objSubmitGuardar = document.getElementById("submitGuardar");
	  objAccion.value = "Guardar";
	  objinputselectOperadorId = document.getElementById("inputselectOperadorId"); 
	  objinputselectLlantaId = document.getElementById("inputselectLlantaId"); 
	  objinputselectUnidadId = document.getElementById("inputselectUnidadId"); 
	  objinputselectOperadorId.required = true;
	  objinputselectLlantaId.required = true; 
	  objinputselectUnidadId.required = true; 
	  objSubmitGuardar.click();
	  return;
	}
	
	if("Cancelar"==pButton){
		 var objResetFormId = document.getElementById("resetFormId");
		 resetFormId.click();
		 environmentCancelar();  
		  return; 
	}
	
	if("Salir"==pButton){
		objAccion.value="Salir"; 
		objFormMovimientosLlantas.submit();
		return;
	}
	
    if("Buscar"==pButton){
		
		if(varTablRO.$('tr.danger').length>0){
			var objFolioMovLlanta = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
			var objMovLlantaTrx = document.getElementById("movLlantaTrx"); 
			objMovLlantaTrx.value = objFolioMovLlanta;
			objAccion.value="Buscar"; 
			objFormMovimientosLlantas.submit();
		}else{
			alert("Seleccionar algun registro");
		}
		
		return;
	}
    
  if("Modificar"==pButton){
		
		if(varTablRO.$('tr.danger').length>0){
			var objFolioMovLlanta = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
			var objMovLlantaTrx = document.getElementById("movLlantaTrx"); 
			objMovLlantaTrx.value = objFolioMovLlanta;
			objAccion.value="Modificar"; 
			objFormMovimientosLlantas.submit();
		}else{
			alert("Seleccionar algun registro");
		}
		
		return;
	}
  
  if("Borrar"==pButton){
		if(varTablRO.$('tr.danger').length>0){
			var objFolioMovLlanta = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
			var objMovLlantaTrx = document.getElementById("movLlantaTrx"); 
			objMovLlantaTrx.value = objFolioMovLlanta; 
			objAccion.value ="Borrar";
			var confirmBorrar = confirm("¿Seguro que desea borrar este registro?");
			if(confirmBorrar){
			  objFormMovimientosLlantas.submit();
			}
			return; 
		}else{
			alert("Seleccionar algun registro");
		}
      return;
	}
  

	
}

function environmentNueva(){
	
	 objinputselectOperadorId = document.getElementById("inputselectOperadorId"); 
	 objinputselectLlantaId = document.getElementById("inputselectLlantaId"); 
	 objinputselectUnidadId = document.getElementById("inputselectUnidadId"); 
	
	 objinputselectOperadorId.disabled = false; 
	 objinputselectLlantaId.disabled = false; 
	 objinputselectUnidadId.disabled = false; 
	 
	objNuevaBtn.disabled = true; 
	objBuscarBtn.disabled = true; 
	objSalirBtn.disabled = true; 
	objModificarBtn.disabled = true; 
	objBorrarBtn.disabled = true; 
	
	objGuardarBtn.disabled = false;
	objGuardarBtn.style.backgroundColor="";
	objCancelarBtn.disabled = false;
	objCancelarBtn.style.backgroundColor="";
	
	if(null!=objFolio)
	objFolio.disabled = false; 
	if(null!=objSelectOperadorId)
	objSelectOperadorId.disabled = false; 
	if(null!=objSelectLlantaId)
	objSelectLlantaId.disabled = false; 
	if(null!=objSelectPosicionId)
	objSelectPosicionId.disabled = false; 
	if(null!=objFecha)
	objFecha.disabled = false; 
	if(null!=objProfundidad)
	objProfundidad.disabled = false; 
	if(null!=objSelectUnidadId)
	objSelectUnidadId.disabled = false; 
	if(null!=objPresionActual)
	objPresionActual.disabled = false;
	if(null!=objKilometraje)
	objKilometraje.disabled = false; 
	if(null!=objNota)
	objNota.disabled = false; 
	if(null!=objObservaciones)
	objObservaciones.disabled = false; 
	if(null!=objCosto)
	objCosto.disabled = false; 
	
	objinputselectLlantaId = document.getElementById("inputselectLlantaId"); 
	objinputselectLlantaId.focus();
    
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
	   objFecha.value = objCurrentDate.getFullYear()+"-"+objCurrentMonth+"-"+objCurrentDay;
}

function environmentCancelar(){
	
	 objinputselectOperadorId = document.getElementById("inputselectOperadorId"); 
	 objinputselectLlantaId = document.getElementById("inputselectLlantaId"); 
	 objinputselectUnidadId = document.getElementById("inputselectUnidadId"); 
	 
	 objinputselectOperadorId.disabled = true; 
	 objinputselectLlantaId.disabled = true; 
	 objinputselectUnidadId.disabled = true; 
	 
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

	  if(null!=objFolio)
	  objFolio.disabled = true;
	  if(null!=objSelectOperadorId)
	  objSelectOperadorId.disabled = true;
	  if(null!=objSelectLlantaId)
	  objSelectLlantaId.disabled = true;
	  if(null!=objSelectPosicionId)
	  objSelectPosicionId.disabled = true;
	  if(null!=objFecha)
	  objFecha.disabled = true;
	  if(null!=objProfundidad)
	  objProfundidad.disabled = true;
	  if(null!=objSelectUnidadId)
	  objSelectUnidadId.disabled = true;
	  if(null!=objPresionActual)
	  objPresionActual.disabled = true;
	  if(null!=objKilometraje)
	  objKilometraje.disabled = true;
	  if(null!=objNota)
	  objNota.disabled = true; 
	  if(null!=objObservaciones)
	  objObservaciones.disabled = true; 
	  if(null!=objCosto)
	  objCosto.disabled = true; 
}


function handleEvent(e){
	if(e.type=="keypress"){
	  if("Enter"==e.code||"Tab"==e.code){
		e.preventDefault();  
		const varTargetId = e.target.id;
		if("fecha"==varTargetId){
			document.getElementById("kilometraje").focus();
		}else if("kilometraje"==varTargetId){
			document.getElementById("inputselectOperadorId").focus();
		}else if("selectPosicionId"==varTargetId){
			document.getElementById("profundidad").focus();
		}else if("profundidad"==varTargetId){
			document.getElementById("observaciones").focus();
		}else if("observaciones"==varTargetId){
			document.getElementById("GuardarBtn").focus();
		}
		return;
	  }	
	}
}

function eventOperadorHandle(e){
	if(e.type=="keypress"){
		 if("Enter"==e.code||"Tab"==e.code){
		 e.preventDefault(); 
		 document.getElementById("selectPosicionId").focus();
   }
  }
}

function eventLlantaHandle(e){
	if(e.type=="keypress"){
		 if("Enter"==e.code||"Tab"==e.code){
		 e.preventDefault(); 
		 
		 if(""!=document.getElementById("inputselectLlantaId").value){
		 
		 $.post('RevisionProfundidadLlantasCO', {
			 accionMovimientosLlantas : "obtieneUnidadPosicion",
			 pNombreLlanta:document.getElementById("inputselectLlantaId").value
		 }, function(responseText) {
			 if(""!=responseText){
				if(""!=responseText){
				 var varSplitResponse = responseText.split("XXBS");
				 document.getElementById("inputselectUnidadId").value = varSplitResponse[0];
				 document.getElementById("selectUnidadId").value = varSplitResponse[0];
				 document.getElementById("selectPosicionId").value = varSplitResponse[1];
				}
			 }
		 });
		 
		 } /** END  if(""!=document.getElementById("inputselectLlantaId").value){ **/
		 
		 objinputselectUnidadId = document.getElementById("inputselectUnidadId"); 
		 objinputselectUnidadId.focus();
      }
    }
}

function eventUnidadHandle(e){
	if(e.type=="keypress"){
		 if("Enter"==e.code||"Tab"==e.code){
		 e.preventDefault(); 
		 document.getElementById("fecha").focus();
    }
  }
}

function validaFormulario(pForma){
	if(null!=pForma.nota)
	pForma.nota.value = pForma.nota.value.toUpperCase();
	if(null!=pForma.observaciones)
	pForma.observaciones.value = pForma.observaciones.value.toUpperCase();
	 var objConfirm = confirm("Son los datos correctos?");
     return objConfirm; 
}

function onChangeLlanta(){
	
         if(""!=document.getElementById("inputselectLlantaId").value){
	    $.post('RevisionProfundidadLlantasCO', {
			 accionMovimientosLlantas : "obtieneUnidadPosicion",
			 pNombreLlanta:document.getElementById("inputselectLlantaId").value
		 }, function(responseText) {
			 if(""!=responseText){
				if(""!=responseText){
				 var varSplitResponse = responseText.split("XXBS");
				 document.getElementById("selectUnidadId").value = varSplitResponse[0];
				 document.getElementById("inputselectUnidadId").value = varSplitResponse[0];
				 document.getElementById("selectPosicionId").value = varSplitResponse[1];
				}
			 }
		 });
	  } /** END  if(""!=document.getElementById("inputselectLlantaId").value){ **/
         
}

