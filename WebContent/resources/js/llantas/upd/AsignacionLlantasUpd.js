/**
 * 
 */

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
	 
	 var objselectLlantaIdTrxValue = document.getElementById("selectLlantaIdTrx").value; 
	 document.getElementById("inputselectLlantaId").value = objselectLlantaIdTrxValue; 
	 
	 var objselectOperadorIdTrxValue = document.getElementById("selectOperadorIdTrx").value; 
	 document.getElementById("inputselectOperadorId").value = objselectOperadorIdTrxValue; 
	 
	 var objselectPosicionIdTrxValue = document.getElementById("selectPosicionIdTrx").value; 
	 document.getElementById("selectPosicionId").value = objselectPosicionIdTrxValue; 
	 
	 var objselectUnidadIdTrxValue = document.getElementById("selectUnidadIdTrx").value; 
	 document.getElementById("inputselectUnidadId").value = objselectUnidadIdTrxValue; 
	
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
	var objNota = document.getElementById("nota"); 
	objNota.value = objNota.value.toUpperCase(); 
	varaccionAdministrarLlantas.value="Update";
	document.getElementById("submitFormId").click();
}