/**
 * 
 */

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

$(document).ready(function() {

	 $("#submitFormId").hide();
	 $("#resetFormId").hide();
	 
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
   
     
});

function Regresar(){
	var varformUpd = document.getElementById("formUpd"); 
	var varaccionMovimientosLlantas = document.getElementById("accionMovimientosLlantas"); 
	varaccionMovimientosLlantas.value="Regresar";
	varformUpd.submit();
}

function Actualizar(){
	
	var objLlanta = document.getElementById("llanta"); 
	
	var varformUpd = document.getElementById("formUpd"); 
	var varaccionAdministrarLlantas = document.getElementById("accionMovimientosLlantas"); 
	varaccionAdministrarLlantas.value="Update";
	var objObservaciones = document.getElementById("observaciones");
	objObservaciones.value = objObservaciones.value.toUpperCase(); 
	document.getElementById("submitFormId").click();
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
			/* document.getElementById("GuardarBtn").focus(); */
			document.getElementById("UpdateBtn").focus();
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

