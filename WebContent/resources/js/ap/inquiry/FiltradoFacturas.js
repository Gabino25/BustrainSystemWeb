/**
 * 
 */

$(document).ready(function() {
	 $.ajaxSetup({async: false}); 
	  
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
	     
	     $("#desdeDatePicker" ).datepicker();
		 $("#hastaDatePicker" ).datepicker();
	     
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
		 
	     
		 $("#proveedor" ).combobox(); 
		 document.getElementById("showBtnproveedor").hidden = true; 

		 $("#unidad").combobox(); 
		 document.getElementById("showBtnunidad").hidden = true; 
		 
		 
});


function accionFiltradoFacturas(pButton){
	if("Salir"==pButton){
		document.getElementById("accion").value = "Salir";
		document.getElementById("formFiltradoFacturas").submit();
	    return;
	}
	if("Filtrar"==pButton){
		filtrarPorParametros();
	    return; 
	}
	if("GastoMensualExcel"==pButton){
		gastoMensualExcel();
		return; 
	}
}

function detectaCambio(){
	
}

function filtrarPorParametros(){
	
	 var objFechaDesde = $("#desdeDatePicker").datepicker("getDate");
	 var objFechaHasta = $("#hastaDatePicker").datepicker("getDate");
	  
	 var jsFechaDesdeDay = objFechaDesde.getDate(); 
	 var jsFechaDesdeMonth = objFechaDesde.getMonth(); 
	 var jsFechaDesdeFullYear = objFechaDesde.getFullYear(); 
	  
	 var jsFechaHastaDay = objFechaHasta.getDate(); 
	 var jsFechaHastaMonth = objFechaHasta.getMonth(); 
	 var jsFechaHastaFullYear = objFechaHasta.getFullYear(); 
	 
	 var objUnidad = document.getElementById("unidad").value;
	 var objProveedor = document.getElementById("proveedor").value; 
	 var objDescripcion = document.getElementById("descripcion").value; 
	
	 $.post('FiltradoFacturasCO', {
		    accion : "filtrarFacturas",
		    jsFechaDesdeDay: jsFechaDesdeDay,
		    jsFechaDesdeMonth:jsFechaDesdeMonth,
		    jsFechaDesdeFullYear:jsFechaDesdeFullYear,
		    jsFechaHastaDay:jsFechaHastaDay,
		    jsFechaHastaMonth:jsFechaHastaMonth,
		    jsFechaHastaFullYear:jsFechaHastaFullYear,
		    jsUnidad:objUnidad,
		    jsProveedor:objProveedor,
		    jsDescripcion:objDescripcion
		  }, function(responseText) {
			$('#DivTablRO').html(responseText);
		  }
		);
	
}


function gastoMensualExcel(){
	 var objFechaDesde = $("#desdeDatePicker").datepicker("getDate");
	 var objFechaHasta = $("#hastaDatePicker").datepicker("getDate");
	  
	 var jsFechaDesdeDay = objFechaDesde.getDate(); 
	 var jsFechaDesdeMonth = objFechaDesde.getMonth(); 
	 var jsFechaDesdeFullYear = objFechaDesde.getFullYear(); 
	  
	 var jsFechaHastaDay = objFechaHasta.getDate(); 
	 var jsFechaHastaMonth = objFechaHasta.getMonth(); 
	 var jsFechaHastaFullYear = objFechaHasta.getFullYear(); 
	 
	 var objUnidad = document.getElementById("unidad").value;
	 var objProveedor = document.getElementById("proveedor").value; 
	 var objDescripcion = document.getElementById("descripcion").value; 
	
	 $.post('FiltradoFacturasCO', {
		    accion : "gastoMensualExcel",
		    jsFechaDesdeDay: jsFechaDesdeDay,
		    jsFechaDesdeMonth:jsFechaDesdeMonth,
		    jsFechaDesdeFullYear:jsFechaDesdeFullYear,
		    jsFechaHastaDay:jsFechaHastaDay,
		    jsFechaHastaMonth:jsFechaHastaMonth,
		    jsFechaHastaFullYear:jsFechaHastaFullYear,
		    jsUnidad:objUnidad,
		    jsProveedor:objProveedor,
		    jsDescripcion:objDescripcion
		  }, function(responseText) {
		    
			  
			var uri = 'data:application/vnd.ms-excel,' + encodeURIComponent(responseText);
			var downloadLink = document.createElement("a");
			downloadLink.href = uri;
			downloadLink.download = "RporteDeGastosRefacciones.xls";
			document.body.appendChild(downloadLink);
			downloadLink.click();
			document.body.removeChild(downloadLink);
			
		  }
		);
}
