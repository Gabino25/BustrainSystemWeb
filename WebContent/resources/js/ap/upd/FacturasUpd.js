/**
 * 
 */

var objTablUpd = null; 
var objunidadDemo = null; 
var objgastoDemo = null; 

$(document).ready(function() {
	$("#submitFormFactura").hide();
	$("#resetFormFactura").hide();
	$("#unidadDemo").hide();
	$("#gastoDemo").hide();
	
	
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
	          .attr( "required", "true" )
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
	
	
	 var objproveedorFrom = document.getElementById("proveedorFrom"); 
	 document.getElementById("proveedor").value = objproveedorFrom.value;
	
	 document.getElementById("proveedor").required = false; 
	 
	$("#proveedor").combobox(); 
	 document.getElementById("showBtnproveedor").hidden = true; 
	 document.getElementById("inputproveedor").onkeyup =  document.getElementById("inputproveedor").onkeydown =  document.getElementById("inputproveedor").onkeypress = handleEvent; 
	 document.getElementById("inputproveedor").onchange = onChangeProveedor; 
	 
	 var objSelectedIndex = document.getElementById("proveedor").selectedIndex; 
	 if(objSelectedIndex>0){
		 document.getElementById("inputproveedor").value = objproveedorFrom.value;
		 document.getElementById("inputproveedor").size = objproveedorFrom.value.length +10;
	 }
	 
	 objTablUpd = document.getElementById("TablUpd"); 
	 objunidadDemo = document.getElementById("unidadDemo"); 
	 objgastoDemo = document.getElementById("gastoDemo"); 
	
	
	
	for (var i = 1, row; row = objTablUpd.rows[i]; i++) {
		   //iterate through rows
		   //rows would be accessed using the "row" variable assigned in the for loop
		
		   for (var j = 0, col; col = row.cells[j]; j++) {
		     //iterate through columns
		     //columns would be accessed using the "col" variable assigned in the for loop
			   var objcolInnerHTMLValue = null; 
			   if(0==j){
			   
			   var objConcepto = document.createElement("INPUT");
			   objConcepto.setAttribute("id","concepto"+i);
			   objConcepto.setAttribute("name","concepto"+i);
			   objConcepto.setAttribute("type", "text");
			   objConcepto.setAttribute("value",col.innerHTML);
			   objConcepto.setAttribute("required","true");
			   objConcepto.setAttribute("size",col.innerHTML.length+15);
			   col.innerHTML="";
			   col.appendChild(objConcepto);
			   }else if(1==j){
				  var objUnidad = document.createElement("SELECT");
				  objUnidad.setAttribute("id","unidad"+i);
				  objUnidad.setAttribute("name","unidad"+i);
				  objUnidad.setAttribute("required","true");
				  objcolInnerHTMLValue = col.innerHTML;
				  col.innerHTML="";
				  col.appendChild(objUnidad);
				  document.getElementById("unidad"+i).innerHTML = objunidadDemo.innerHTML;
				  document.getElementById("unidad"+i).value = objcolInnerHTMLValue;
				  
				   $("#"+"unidad"+i).combobox(); 
					document.getElementById("showBtn"+"unidad"+i).hidden = true; 
					document.getElementById("input"+"unidad"+i).size = objcolInnerHTMLValue.length +10;
				  
			   }
			   else if(2==j){
			   var objPrecio = document.createElement("INPUT");
			   objPrecio.setAttribute("id","precio"+i);
			   objPrecio.setAttribute("name","precio"+i);
			   objPrecio.setAttribute("type", "number");
			   objPrecio.setAttribute("min", "0");
			   objPrecio.setAttribute("step", "0.01");
			   objPrecio.setAttribute("value",col.innerHTML);
			   objPrecio.setAttribute("required","true");
			   col.innerHTML="";
			   col.appendChild(objPrecio);
			   
			   
			   }else if(3==j){
				  var objGasto = document.createElement("SELECT");
				      objGasto.setAttribute("id","gasto"+i);
				      objGasto.setAttribute("name","gasto"+i);
				      objGasto.setAttribute("value",col.innerHTML);
				      objGasto.setAttribute("required","true");
				      objcolInnerHTMLValue = col.innerHTML;
					  col.innerHTML="";
					  col.appendChild(objGasto);
					  document.getElementById("gasto"+i).innerHTML = objgastoDemo.innerHTML;
					  document.getElementById("gasto"+i).value = objcolInnerHTMLValue;
					  
					  $("#"+"gasto"+i).combobox(); 
					  document.getElementById("showBtn"+"gasto"+i).hidden = true; 
					  document.getElementById("input"+"gasto"+i).size = objcolInnerHTMLValue.length +10;
					  
			   }else if(4==j){
				   var objnumconcepto = document.createElement("INPUT");
				   objnumconcepto.setAttribute("id","numconcepto"+i); 
				   objnumconcepto.setAttribute("name","numconcepto"+i); 
				   objnumconcepto.setAttribute("type", "number");
				   objnumconcepto.setAttribute("min", "0");
				   objnumconcepto.setAttribute("value",col.innerHTML);
				   objnumconcepto.setAttribute("readonly","true");
				   col.innerHTML="";
				   col.appendChild(objnumconcepto);
				   document.getElementById("numconcepto"+i).style.display="none";
				   col.style.display="none";
			   }
		   }  
		}
	
	
	 var objtipoTrx = document.getElementById("tipoTrx");
	 
	 var objTipos = document.getElementsByName("tipo");
	    for (var i = 0, length = objTipos.length; i < length; i++)
	    {
	     if(objTipos[i].value == objtipoTrx.value)
	     {
	      objTipos[i].checked = true; 
	      break;
	     }
	    }
	    
	
});

function Regresar(){
	var varformUpd = document.getElementById("formUpd"); 
	var objaccionFactura = document.getElementById("accionFactura"); 
	objaccionFactura.value="Regresar";
	varformUpd.submit();
}

function Actualizar(){
	
	var varformUpd = document.getElementById("formUpd"); 
	var objaccionFactura = document.getElementById("accionFactura"); 
	objaccionFactura.value="Update";
	document.getElementById("folio").value = document.getElementById("folio").value.toUpperCase();
	document.getElementById("submitFormFactura").click();
	
}

function validaFormulario(pForma){
	var objTablUpd = document.getElementById("TablUpd");
	var objCountRows = objTablUpd.rows.length;
	var objinputCountRows = document.getElementById("inputCountRows"); 
	objinputCountRows.value = objCountRows; 
	return true;
}	

function onChangeProveedor(e){
	var objectID = e.target.id; 
	var objectIDValue = document.getElementById(objectID).value; 
	var objectIDValueLength = objectIDValue.length+10; 
	document.getElementById(objectID).size = objectIDValueLength;
}

function handleEvent(e){
	if(e.type=="keypress"){
		  if(e.code=="Enter"||e.code=="Tab"){
			  e.preventDefault(); 
			  var objectId = e.target.id; 
			  if("selectUnidadId"==objectId){
				  objselectListCatTrab.focus();
			  }else if("selectListCatTrab"==objectId){
				  objkilometraje.focus();
			  }else if("kilometraje"==objectId){
				  objdescFalla.focus(); 
			  }else if("descFalla"==objectId){
				  objGuardarBtn.focus(); 
			  }
		  }
	}
}