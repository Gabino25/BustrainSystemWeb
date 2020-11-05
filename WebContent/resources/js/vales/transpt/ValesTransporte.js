/**
 * 
 */

var objSelectListCatTrab = null; 
var objFolioInicial = null;
var objFolioFinal = null; 
var varTablRO = null; 
$(document).ready(function() {
	
	$("#submitFormId").hide();
	
	varTablRO = $('#TablRO').dataTable(
			{  "order": [[ 2, "desc" ]],
			   "scrollY": 320,
			   "language": { "url": "/BustrainSystemWeb/resources/json/Spanish.json"},
		       "columns": [
		    	    { "orderable": false },  //(1)
		    	    { "orderable": false },  //(2)
		    	    { "orderable": true },   //(3)
		    	    { "orderable": false },  //(4)
		    	    { "orderable": false }   //(5)
		    	  ]
			  }
			);
	
	 $('#TablRO tbody').on( 'click', 'tr', function () {
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
	          .attr("title", "")
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
	
	 $("#selectListCatTrab" ).combobox(); 
	
	document.getElementById("showBtnselectListCatTrab").hidden = true; 
	document.getElementById("inputselectListCatTrab").size=90;
	var objinputselectListCatTrab = document.getElementById("inputselectListCatTrab"); 
	 
	objSelectListCatTrab = document.getElementById("selectListCatTrab"); 
	objFolioInicial = document.getElementById("folioInicial"); 
	objFolioFinal = document.getElementById("folioFinal"); 
	
	objSelectListCatTrab.onkeyup = objSelectListCatTrab.onkeydown = objSelectListCatTrab.onkeypress = handleEvent;
	objFolioInicial.onkeyup = objFolioInicial.onkeydown = objFolioInicial.onkeypress = handleEvent; 
	objFolioFinal.onkeyup = objFolioFinal.onkeydown = objFolioFinal.onkeypress = handleEvent; 
	
	objFolioInicial.onchange = onchageFolioInicial; 
	objFolioFinal.onchange = onchangeFolioFinal;
	
	objinputselectListCatTrab.onkeyup = objinputselectListCatTrab.onkeydown = objinputselectListCatTrab.onkeypress = onKeyUpDownPress;
	
  } );

function valFormAssignValesCombust(pBotonValue){
	var formAssignTranspt = document.getElementById("formAssignTranspt");
	var folioInicial = document.getElementById("folioInicial");
	var folioFinal = document.getElementById("folioFinal");
	var objAccion = document.getElementById("accion");
	var objSeleccione = document.getElementsByName('seleccione');   
	var objFolios = document.getElementById("folios");
   
	
	if("Guardar"==pBotonValue){
		  var objSubmitFormId = document.getElementById("submitFormId");
		  objAccion.value = "Guardar";
	      objSubmitFormId.click();
	} 
	/** END if("Guardar"==boton.value){ **/
	
	if("Salir"==pBotonValue){
		objAccion.value = "Salir";	
		formAssignTranspt.submit();
	}
	
	 var strFolios="";
	if("Eliminar"==pBotonValue){
		
		if(varTablRO.$('tr.danger').length>0){
			var objNumero = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
			var objfolios = document.getElementById("folios"); 
			objfolios.value = objNumero;
			objAccion.value="Eliminar"; 
			var confirmBorrar = confirm("¿Seguro que desea borrar este registro?");
			if(confirmBorrar){
				formAssignTranspt.submit();
			}
		}else{
			alert("Seleccionar algun registro");
		}
		return;
		
 	}
	
}

function validaFormulario(pForma){
	    if(""==pForma.folioInicial.value){
			alert("Campo Folio Inicial es requerido");
			return false;
		}else if(""==pForma.folioFinal.value){
			alert("Campo Folio Final es requerido");
			return false; 
		 }
	    
	    var intFolioIni = pForma.folioInicial.value; 
	    var intFolioFin = pForma.folioFinal.value; 
	    
	    var intDiferecia = intFolioFin-intFolioIni; 
	    if(intDiferecia!=49){
	    	alert("Los folios deben ingresarse de con una diferencia de 50");
			return false; 
	    }
	    
	    pForma.accion.value = "Guardar";	
		return true; 
}

function handleEvent(e){
  if(e.type=="keypress"){
		if(e.code=="Enter"){
			e.preventDefault();
			var container = document.querySelector("#formAssignTranspt");
			var inputs = container.querySelectorAll("input,select");
			 /*Isolate the node that we're after*/
			  const currentNode = e.target;
				 /*Find the current tab index.*/
		      const currentIndex = Array.from(inputs).findIndex(el => currentNode.isEqualNode(el))
		      if(currentIndex==0
		        ||currentIndex==1){
		    	  inputs[currentIndex+1].focus(); 
		      }else if(currentIndex==2){
		    	  var objGuardarBtn = document.getElementById("GuardarBtn"); 
		    	  objGuardarBtn.focus(); 
		      }
		}
	}
}

function onchageFolioInicial(){
	if(""!=document.getElementById("folioInicial").value){
		document.getElementById("msgFolioInicial").textContent ="";
	 $.post('ValesAssignMainCO', {
		 formAssignAccion : "validaFolioInicial",
		 pFolioInicial:document.getElementById("folioInicial").value
	 }, function(responseText) {
		 if(""!=responseText){
			 document.getElementById("msgFolioInicial").textContent = responseText; 
			 document.getElementById("msgFolioInicial").style.color="red";
		 }
	 });
	} 
}

function onchangeFolioFinal(){
	if(""!=document.getElementById("folioFinal").value){
		document.getElementById("msgFolioFinal").textContent ="";
	 $.post('ValesAssignMainCO', {
		 formAssignAccion : "validaFolioFinal",
		 pFolioFinal:document.getElementById("folioFinal").value
	 }, function(responseText) {
		 if(""!=responseText){
			 document.getElementById("msgFolioFinal").textContent = responseText; 
			 document.getElementById("msgFolioFinal").style.color="red";
		 }
	 });
	} 
}

function onKeyUpDownPress(e){
	if(e.type=="keypress"){
		if(e.code=="Enter"||e.code=="Tab"){
			document.getElementById("folioInicial").focus();
			e.preventDefault();
		}
	}	
}
