/**
 * 
 */

 var objNuevaBtn = null; 
 var objGuardarBtn = null; 
 var objBorrarBtn = null; 
 var objBuscarBtn = null; 
 var objModificarBtn = null; 
 var objCancelarBtn = null; 
 var objSalirBtn = null; 
 
var objLinFactTab = null; 
var varTablRO = null; 

var objproveedor = null; 
var objfolio = null; 
var objorden = null; 
var objfecha = null; 
var objcontado = null; 
var objcredito = null; 
var objcosto = null; 


var objconcepto2 = null; 
var objunidad2 = null; 
var objprecio2 = null; 
var objgasto2 = null; 

var objinputproveedor = null; 

$(document).ready(function() {
	
	  function disablePrev() { window.history.forward() }
	    window.onload = disablePrev();
	    window.onpageshow = function(evt) { if (evt.persisted) disablePrev() }
	
	$("#lineasFacturaDiv").hide();
	$("#submitFormFactura").hide();
	$("#resetFormFactura").hide();
	$("#submitFormLineas").hide();
	$("#resetFormLineas").hide();
	
	varTablRO = $('#TablRO').DataTable( {
    	"order": [[ 0, "desc" ]],
    	"scrollY": 250,
        "scrollX": true,
        "language": { "url": "/BustrainSystemWeb/resources/json/Spanish.json"},
        "columns": [
    	    { "orderable": true },   /*(1)*/
    	    { "orderable": false },  /*(2)*/
    	    { "orderable": false },  /*(3)*/
    	    { "orderable": false },  /*(4)*/
    	    { "orderable": false },  /*(5)*/
    	    { "orderable": false },  /*(6)*/
    	    { "orderable": false }   /*(7)*/
    	  ]
    } );
	
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
	          .attr("title", "" )
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
	 
	 $("#proveedor" ).combobox(); 
	 document.getElementById("showBtnproveedor").hidden = true; 

	 $("#unidad2").combobox(); 
	 document.getElementById("showBtnunidad2").hidden = true; 
	 
	 $("#gasto2").combobox(); 
	 document.getElementById("showBtngasto2").hidden = true; 
	 
		 
	objNuevaBtn = document.getElementById("NuevaBtn");
	objGuardarBtn = document.getElementById("GuardarBtn");
	objBorrarBtn = document.getElementById("BorrarBtn"); 
	objBuscarBtn = document.getElementById("BuscarBtn"); 
	objModificarBtn = document.getElementById("ModificarBtn"); 
	objCancelarBtn = document.getElementById("CancelarBtn"); 
	objSalirBtn = document.getElementById("SalirBtn"); 
	
	if(null!=objNuevaBtn)
	objGuardarBtn.disabled = true; 
	if(null!=objCancelarBtn)
	objCancelarBtn.disabled = true; 
	
	
    objproveedor = document.getElementById("proveedor");
    objfolio = document.getElementById("folio");
    objorden = document.getElementById("orden");
    objfecha = document.getElementById("fecha");
    objcontado = document.getElementById("contado");
    objcredito = document.getElementById("credito");
    objcosto = document.getElementById("costo"); 
    objinputproveedor = document.getElementById("inputproveedor");
    objinputgasto2 = document.getElementById("inputgasto2");

    objproveedor.disabled = true; 
    objfolio.disabled = true; 
    objorden.disabled = true; 
    objfecha.disabled = true; 
    objcontado.disabled = true; 
    objcredito.disabled = true;
    objcosto.disabled = true; 
    objinputproveedor.disabled = true; 
    
    objproveedor.onkeyup = objproveedor.onkeydown = objproveedor.onkeypress = handleEvent; 
    objfolio.onkeyup = objfolio.onkeydown = objfolio.onkeypress = handleEvent; 
    objorden.onkeyup = objorden.onkeydown = objorden.onkeypress = handleEvent; 
    objfecha.onkeyup = objfecha.onkeydown = objfecha.onkeypress = handleEvent; 
    objcontado.onkeyup = objcontado.onkeydown = objcontado.onkeypress = handleEvent; 
    objcredito.onkeyup = objcredito.onkeydown = objcredito.onkeypress = handleEvent; 
    objcosto.onkeyup = objcosto.onkeydown = objcosto.onkeypress = handleEvent; 
    objinputproveedor.onkeyup = objinputproveedor.onkeydown = objinputproveedor.onkeypress = handleEvent; 
    

    objconcepto2 = document.getElementById("concepto2");
    objunidad2 = document.getElementById("unidad2");
    objprecio2 = document.getElementById("precio2");
    objgasto2 = document.getElementById("gasto2");
    
    

    objconcepto2.onkeyup = objconcepto2.onkeydown = objconcepto2.onkeypress = handleEventLinFact; 
    objunidad2.onkeyup = objunidad2.onkeydown = objunidad2.onkeypress = handleEventLinFact; 
    objprecio2.onkeyup = objprecio2.onkeydown = objprecio2.onkeypress = handleEventLinFact; 
    objgasto2.onkeyup = objgasto2.onkeydown = objgasto2.onkeypress = handleEventLinFact; 
    
    document.getElementById("inputunidad2").onkeyup = document.getElementById("inputunidad2").onkeydown = document.getElementById("inputunidad2").onkeypress = handleEventLinFact;
    document.getElementById("inputgasto2").onkeyup = document.getElementById("inputgasto2").onkeydown = document.getElementById("inputgasto2").onkeypress = handleEventLinFact;
    
    objinputproveedor.onchange = onChangeProveedor;
    objinputgasto2.onchange  = onChangeGasto;
   
    
});


function environmentNueva(){
	
	objNuevaBtn.disabled = true; 
	objBuscarBtn.disabled = true; 
	objSalirBtn.disabled = true; 
	objModificarBtn.disabled = true; 
	objBorrarBtn.disabled = true; 
	
	objGuardarBtn.disabled = false;
	objGuardarBtn.style.backgroundColor="";
	objCancelarBtn.disabled = false;
	objCancelarBtn.style.backgroundColor="";
	
	objproveedor.disabled = false; 
	objfolio.disabled = false;
	objorden.disabled = false;
	objfecha.disabled = false;
	objcontado.disabled = false; 
	objcredito.disabled = false;
	objcosto.disabled = false;
	objinputproveedor.disabled = false; 
	
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
	    objfecha.value = objCurrentDate.getFullYear()+"-"+objCurrentMonth+"-"+objCurrentDay;
	
	document.getElementById("inputproveedor").focus();
	
}

function environmentCancelar(){
	
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

	 objGuardarBtn.disabled = true;
	 objCancelarBtn.disabled = true;
	 
	  objproveedor.disabled = true; 
      objfolio.disabled = true; 
      objorden.disabled = true; 
      objfecha.disabled = true; 
      objcontado.disabled = true; 
      objcredito.disabled = true;
      objcosto.disabled = true; 
      objinputproveedor.disabled = true; 
	
}

function accionFactura(pButton){
	
	var objformFactura = document.getElementById("formFactura"); 
	var objaccionFactura = document.getElementById("accionFactura"); 
	
	if("Nueva"==pButton){
		environmentNueva(); 
		return; 
	}
	
	if("Cancelar"==pButton){
		document.getElementById("resetFormFactura").click();
		environmentCancelar(); 
		return; 
	}
	
	if("Guardar"==pButton){
		document.getElementById("submitFormFactura").click();
	}
	
	if("Buscar"==pButton){
		
		if(varTablRO.$('tr.danger').length>0){
			var objNumfactura = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
			var objfacturaTrx = document.getElementById("facturaTrx"); 
			objfacturaTrx.value = objNumfactura;
			objaccionFactura.value="Buscar"; 
			objformFactura.submit();
		}else{
			alert("Seleccionar algun registro");
		}
		
		return;
	}
	
     if("Modificar"==pButton){
		
		if(varTablRO.$('tr.danger').length>0){
			var objNumfactura = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
			var objfacturaTrx = document.getElementById("facturaTrx"); 
			objfacturaTrx.value = objNumfactura;
			objaccionFactura.value="Modificar"; 
			objformFactura.submit();
		}else{
			alert("Seleccionar algun registro");
		}
		
		return;
	}
	
    if("Salir"==pButton){
    	objaccionFactura.value="Salir"; 
		objformFactura.submit();
		return; 
    } 
     
    
    if("Borrar"==pButton){
		
		if(varTablRO.$('tr.danger').length>0){
			var objNumfactura = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
			var objfacturaTrx = document.getElementById("facturaTrx"); 
			objfacturaTrx.value = objNumfactura;
			objaccionFactura.value="Borrar"; 
			var confirmAccion = confirm("¿Esta seguro que desea borrar este registro?");
			if(confirmAccion){
			objformFactura.submit();
			}else{
				return;
			}	
		}else{
			alert("Seleccionar algun registro");
		}
		
		return;
	}
	
}

function agregarLinea(){
	
	var booleanValCostoVsPrecios = validaCostoVsPrecios();
	if(!booleanValCostoVsPrecios){
		return;
	}
	
	objLinFactTab = document.getElementById("linFactTab"); 
	var objRow = objLinFactTab.insertRow();
	var objRowIndex = objRow.rowIndex;
	
	objRow.insertCell().innerHTML = "<input id='concepto"+objRowIndex+"' name='concepto"+objRowIndex+"' type='text'  required>";	
	
	var objunidad2 = document.getElementById("unidad2"); 
	var objunidad2InnerHTML = objunidad2.innerHTML; 
	
	objRow.insertCell().innerHTML = "<select id='unidad"+objRowIndex+"' name='unidad"+objRowIndex+"' >"
	                                +objunidad2InnerHTML+"</select>";	
	objRow.insertCell().innerHTML = "<input id='precio"+objRowIndex+"' name='precio"+objRowIndex+"' type='number' min='0'  step='0.01' required>";
	var objgasto2 = document.getElementById("gasto2"); 
	var objgasto2InnerHTML = objgasto2.innerHTML;
	objRow.insertCell().innerHTML = "<select id='gasto"+objRowIndex+"' name='gasto"+objRowIndex+"' >"
                                      +objgasto2InnerHTML+"</select>";	
	objRow.insertCell().innerHTML = "<button class='glyphicon glyphicon-trash' style='font-size:20px!important' onclick='deleteLinRow(this);'></button>";
	
	document.getElementById("concepto"+objRowIndex).onkeyup = document.getElementById("concepto"+objRowIndex).onkeydown = document.getElementById("concepto"+objRowIndex).onkeypress = handleEventLinFact; 
	document.getElementById("unidad"+objRowIndex).onkeyup = document.getElementById("unidad"+objRowIndex).onkeydown = document.getElementById("unidad"+objRowIndex).onkeypress = handleEventLinFact;
	document.getElementById("precio"+objRowIndex).onkeyup = document.getElementById("precio"+objRowIndex).onkeydown = document.getElementById("precio"+objRowIndex).onkeypress = handleEventLinFact;
	document.getElementById("gasto"+objRowIndex).onkeyup = document.getElementById("gasto"+objRowIndex).onkeydown = document.getElementById("gasto"+objRowIndex).onkeypress = handleEventLinFact;
	
	
	 $("#unidad"+objRowIndex).combobox(); 
	 document.getElementById("showBtnunidad"+objRowIndex).hidden = true; 
	 document.getElementById("inputunidad"+objRowIndex).onkeyup = document.getElementById("inputunidad"+objRowIndex).onkeydown = document.getElementById("inputunidad"+objRowIndex).onkeypress = handleEventLinFact;
	
	 
	 $("#gasto"+objRowIndex).combobox(); 
	 document.getElementById("showBtngasto"+objRowIndex).hidden = true; 
	 document.getElementById("inputgasto"+objRowIndex).onchange = onChangeGasto;
	 document.getElementById("inputgasto"+objRowIndex).onkeyup = document.getElementById("inputgasto"+objRowIndex).onkeydown = document.getElementById("inputgasto"+objRowIndex).onkeypress = handleEventLinFact;
		
	 
	 document.getElementById("concepto"+objRowIndex).focus();
	 
}

function validaFormulario(){
	objLinFactTab = document.getElementById("linFactTab");
	var objCountRows = objLinFactTab.rows.length;
	/**
	var objinputCountRows = document.getElementById("inputCountRows"); 
	objinputCountRows.value = objCountRows; 
	**/
	
	document.getElementById("proveedorV2").value = document.getElementById("proveedor").value;
	document.getElementById("folioV2").value = document.getElementById("folio").value;
	document.getElementById("costoV2").value = document.getElementById("costo").value;
	document.getElementById("fechaV2").value = document.getElementById("fecha").value;
	document.getElementById("ordenV2").value = document.getElementById("orden").value;
	
    var objTipos = document.getElementsByName("tipo");
    
    for (var i = 0, length = objTipos.length; i < length; i++)
    {
     if (objTipos[i].checked)
     {
    	 document.getElementById("tipoV2").value = objTipos[i].value;
      break;
     }
    }
	document.getElementById("folioV2").value = document.getElementById("folio").value.toUpperCase();
    guardarFacturaV2();
    
 	return false;
}

function deleteLinRow(pRow){
	var objRowIndex = pRow.parentNode.parentNode.rowIndex; 
	objLinFactTab = document.getElementById("linFactTab"); 
	objLinFactTab.deleteRow(objRowIndex); 
}

function handleEvent(e){
	if(e.type=="keypress"){
		  if(e.code=="Enter"||e.code=="Tab"){
			  e.preventDefault(); 
			  var objectId = e.target.id; 
			  if("inputproveedor"==objectId){
			    document.getElementById("folio").focus();
			  }else if("folio"==objectId){
			    document.getElementById("costo").focus();
			  }else if("costo"==objectId){
			    document.getElementById("orden").focus(); 
			  }else if("orden"==objectId){
				  document.getElementById("fecha").focus(); 
			  }else if("fecha"==objectId){
				  document.getElementById("contado").focus(); 
			  }else if("contado"==objectId||"credito"==objectId){
				  document.getElementById("GuardarBtn").focus(); 
			  }
		  }
	}
}

function handleEventLinFact(e){
	if(e.type=="keypress"){
		  if(e.code=="Enter"||e.code=="Tab"){
			  e.preventDefault(); 
			  var objectId = e.target.id;
			  var objectIdLength = objectId.length;
			  var objectIdIndex = objectId.substr(objectIdLength-1,1);
			  if(objectId.includes("concepto")){
				  document.getElementById("inputunidad"+objectIdIndex).focus();
			  }else if(objectId.includes("inputunidad")){
				  document.getElementById("precio"+objectIdIndex).focus();
			  }else if(objectId.includes("precio")){
				  document.getElementById("inputgasto"+objectIdIndex).focus();
			  }else if(objectId.includes("inputgasto")){
				  document.getElementById("AgregarBtn").focus();
			  }
		  }
	}
}

function validaFormFactura(pForma){
	objproveedor = document.getElementById("proveedor");
    objfolio = document.getElementById("folio");
    objorden = document.getElementById("orden");
    objfecha = document.getElementById("fecha");
    objcontado = document.getElementById("contado");
    objcredito = document.getElementById("credito");
    objcosto = document.getElementById("costo"); 
    
    objGuardarBtn = document.getElementById("GuardarBtn");
	objCancelarBtn = document.getElementById("CancelarBtn"); 
	
	objGuardarBtn.disabled = true;
	objCancelarBtn.disabled = true;
	 
    objproveedor.disabled = true; 
    objfolio.readOnly = true; 
    objorden.disabled = true; 
    objfecha.readOnly = true; 
    objcontado.readOnly = true; 
    objcredito.readOnly = true; 
    objcosto.readOnly = true; 
    
	$("#lineasFacturaDiv").show();
	document.getElementById("concepto2").focus();
	return false; 
}

function guardarFactura(){
	/**
	validaFormulario();
	guardarFacturaV2();
	**/
	
	document.getElementById("accionFacturaL").value="GuardarFactura";
	document.getElementById("submitFormLineas").click(); 
}

function guardarFacturaV2(){
	var objidFormTrx = document.getElementById("idFormTrx"); 
	objLinFactTab = document.getElementById("linFactTab");
	var objCountRows = objLinFactTab.rows.length;
	
	var objinputCountRows = document.getElementById("inputCountRows"); 
	objinputCountRows.value = objCountRows; 
	
	var countRectab = 2; /** Indice comienza en 2 **/ 

	for(var i =2;i<=objCountRows-1;i++){
		var objLinFacTab = objLinFactTab.rows[i]; 
		console.log(objLinFacTab);
		
		console.log(countRectab);
		
		for(var j=0;col = objLinFacTab.cells[j];j++){
			console.log(col);
			console.log(col.firstElementChild);
			var concepto = document.createElement("input"); 
			concepto.type ="text";
			concepto.style ="display:none;";
			if(0==j){ /** Concepto **/
				concepto.name ="concepto"+countRectab; 
				concepto.value = col.firstElementChild.value; 
			}else if(1==j){  /** Unidad **/
				concepto.name ="unidad"+countRectab; 
				concepto.value = col.firstElementChild.value; 
			}else if(2==j){  /** Precio **/
				concepto.name ="precio"+countRectab; 
				concepto.value = col.firstElementChild.value; 
			}else if(3==j){  /** Gasto **/
				concepto.name ="gasto"+countRectab; 
				concepto.value = col.firstElementChild.value; 
			}
			objidFormTrx.appendChild(concepto);
		}
		countRectab = countRectab +1; 
	}
	
	document.getElementById("accionFormTrx").value="GuardarFactura";
	objidFormTrx.submit();
	
}

function cancelarFactura(){
	document.getElementById("resetFormLineas").click(); 
	
	objLinFactTab = document.getElementById("linFactTab");
	var objCountRows = objLinFactTab.rows.length;
	for(var i=objCountRows-1;i>2;i--){
		var rowLinFactTab = objLinFactTab.rows[i]; 
		objLinFactTab.deleteRow(i);
	}
	
	$("#lineasFacturaDiv").hide();
	
	environmentNueva();
	
}


function onChangeProveedor(e){
	var objectID = e.target.id; 
	var objectIDValue = document.getElementById(objectID).value; 
	var objectIDValueLength = objectIDValue.length+10; 
	document.getElementById(objectID).size = objectIDValueLength;
}

function onChangeGasto(e){
	var objectID = e.target.id; 
	var objectIDValue = document.getElementById(objectID).value; 
	var objectIDValueLength = objectIDValue.length+10; 
	document.getElementById(objectID).size = objectIDValueLength;
}

function validaCostoVsPrecios(){
	var valCosto = document.getElementById("costo").value; 
	var valPrecios = 0; 
	objLinFactTab = document.getElementById("linFactTab");
	var objCountRows = objLinFactTab.rows.length;
	
	for(var i =2;i<=objCountRows-1;i++){
		var objLinFacTab = objLinFactTab.rows[i]; 
		valPrecios = valPrecios + Number(document.getElementById("precio"+i).value);
	}
	
	if ( valCosto <valPrecios){
		if(confirm("La Suma del los precios en las lineas:"+valPrecios+" es mayor al costo:"+valCosto+" ¿Desea continuar?")){
			return true;
		}else{
		  return false;
		}
		
	}else{
		return true;
	}
	
}
