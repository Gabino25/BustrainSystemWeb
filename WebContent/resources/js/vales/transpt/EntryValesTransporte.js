/**
 * 
 */

 var objNuevaBtn = null; 
 var objGuardarBtn = null; 
 var objBorrarBtn = null; 
 var objBuscarBtn = null; 
 //var objModificarBtn = null; 
 var objCancelarBtn = null; 
 var objSalirBtn = null; 
 
var objFolio = null;
var objFecha = null;
var objSelectOperadorId = null;
var objSelectUnidadId = null;
var objSelectEmpresaId = null;
var objSelectRutaId = null;
var objKmInicial = null;
var objHrInicial = null;
var objKmFinal = null;
var objHrFinal = null;
var objSensilloId = null;
var objRedondoId = null;
var objContadoId = null;
var objCreditoId = null;
var objAutobusId = null;
var objTaxiId = null;
var objCostoViaje = null;
var objObservaciones = null;

var objinputselectOperadorId = null; 
var objinputselectUnidadId = null; 
var objinputselectEmpresaId = null; 

var objcontra=null;
var objBtn=null;
var objEntrar=null;
var objcontra2=null;
var objEntrar2=null;

var varTablRO = null; 

$(document).ready(function() {
	  $.ajaxSetup({async: false}); 
	  
	  function disablePrev() { window.history.forward() }
	    window.onload = disablePrev();
	    window.onpageshow = function(evt) { if (evt.persisted) disablePrev() }
	    
	$(document).find('*').off('keyup keydown keypressed');
	
	$("#submitFormId").hide();
	$("#resetFormId").hide();
	
	varTablRO = $('#TablRO').DataTable( {
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
     	    { "orderable": false },  /*(7)*/
     	    { "orderable": false },  /*(8)*/
     	    { "orderable": false },  /*(9)*/
     	    { "orderable": false },  /*(10)*/
     	    { "orderable": false },  /*(11)*/
     	    { "orderable": false },  /*(12)*/
     	    { "orderable": false },  /*(13)*/
     	    { "orderable": false },  /*(14)*/
     	    { "orderable": false },  /*(15)*/
     	    { "orderable": false },  /*(16)*/
     	    { "orderable": false },  /*(17)*/
     	    { "orderable": false },  /*(18)*/
     	    { "orderable": false },  /*(19)*/
     	    { "orderable": false }   /*(20)*/
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
	 $("#selectUnidadId" ).combobox(); 
	 $("#selectEmpresaId" ).combobox(); 
	
	 document.getElementById("showBtnselectOperadorId").hidden = true; 
	 document.getElementById("showBtnselectUnidadId").hidden = true; 
	 document.getElementById("showBtnselectEmpresaId").hidden = true;
    
	  
	 objinputselectOperadorId =  document.getElementById("inputselectOperadorId"); 
	 objinputselectUnidadId =  document.getElementById("inputselectUnidadId"); 
	 objinputselectEmpresaId =  document.getElementById("inputselectEmpresaId"); 
	 
    objNuevaBtn = document.getElementById("NuevaBtn");
    objGuardarBtn = document.getElementById("GuardarBtn");
    objBorrarBtn = document.getElementById("BorrarBtn"); 
    objBuscarBtn = document.getElementById("BuscarBtn"); 
    //objModificarBtn = document.getElementById("ModificarBtn"); 
    objCancelarBtn = document.getElementById("CancelarBtn"); 
    objSalirBtn = document.getElementById("SalirBtn"); 
    
    if(null!=objNuevaBtn)
    objGuardarBtn.disabled = true;
    if(null!=objCancelarBtn)
    objCancelarBtn.disabled = true;
    
    objBtn = document.getElementById("Btn");
    objcontra= document.getElementById("contra");
    objEntrar = document.getElementById("Entrar");
    objcontra2= document.getElementById("contra2");
    objEntrar2 = document.getElementById("Entrar2");
    
    
    objFolio = document.getElementById("folio");
    objFecha = document.getElementById("fecha");
    objSelectOperadorId = document.getElementById("selectOperadorId");
    objSelectUnidadId = document.getElementById("selectUnidadId");
    objSelectEmpresaId = document.getElementById("selectEmpresaId");
    objSelectRutaId = document.getElementById("selectRutaId");
    objKmInicial = document.getElementById("kmInicial");
    objHrInicial = document.getElementById("hrInicial");
    objKmFinal = document.getElementById("kmFinal");
    objHrFinal = document.getElementById("hrFinal");
    objSensilloId = document.getElementById("sensilloId");
    objRedondoId = document.getElementById("redondoId");
    objContadoId = document.getElementById("contadoId");
    objCreditoId = document.getElementById("creditoId");
    objAutobusId = document.getElementById("autobusId");
    objTaxiId = document.getElementById("taxiId");
    objCostoViaje = document.getElementById("costoViaje");
    objObservaciones = document.getElementById("observaciones");
    
    objFolio.disabled = true; 
    objFecha.disabled = true; 
    objSelectOperadorId.disabled = true; 
    objSelectUnidadId.disabled = true; 
    objSelectEmpresaId.disabled = true; 
    objSelectRutaId.disabled = true; 
    objKmInicial.disabled = true; 
    objHrInicial.disabled = true; 
    objKmFinal.disabled = true; 
    objHrFinal.disabled = true; 
    objSensilloId.disabled = true; 
    objRedondoId.disabled = true; 
    objContadoId.disabled = true; 
    objCreditoId.disabled = true; 
    objAutobusId.disabled = true; 
    objTaxiId.disabled = true; 
    objCostoViaje.disabled = true; 
    objObservaciones.disabled = true; 
    
    objinputselectOperadorId.disabled = true; 
	objinputselectUnidadId.disabled = true; 
	objinputselectEmpresaId.disabled = true; 
    
    
//  objcontra.disabled=true;----------------------------------------------------------
    objcontra.style.visibility  = 'hidden'; // No se ve
    objcontra.style.display = 'none'; // No ocupa espacio
    objEntrar.style.visibiity = 'hidden';
    objEntrar.style.display = 'none';
    
    objcontra2.style.visibility  = 'hidden'; // No se ve
    objcontra2.style.display = 'none'; // No ocupa espacio
    objEntrar2.style.visibiity = 'hidden';
    objEntrar2.style.display = 'none';   
    //--------------------------------------------------------------------------
    folio.onkeydown = folio.onkeyup = folio.onkeypress = folioHandle;
    fecha.onkeydown = fecha.onkeyup = fecha.onkeypress= noFolioHandle;
    selectOperadorId.onkeydown = selectOperadorId.onkeyup = selectOperadorId.onkeypress= noFolioHandle;
    selectUnidadId.onkeydown = selectUnidadId.onkeyup = selectUnidadId.onkeypress= noFolioHandle;
    selectEmpresaId.onkeydown = selectEmpresaId.onkeyup = selectEmpresaId.onkeypress= noFolioHandle;
    selectRutaId.onkeydown = selectRutaId.onkeyup = selectRutaId.onkeypress= noFolioHandle;
    kmInicial.onkeydown = kmInicial.onkeyup = kmInicial.onkeypress= noFolioHandle;
    hrInicial.onkeydown = hrInicial.onkeyup = hrInicial.onkeypress= noFolioHandle;
    kmFinal.onkeydown = kmFinal.onkeyup = kmFinal.onkeypress= noFolioHandle;
    hrFinal.onkeydown = hrFinal.onkeyup = hrFinal.onkeypress= noFolioHandle;
    sensilloId.onkeydown = sensilloId.onkeyup = sensilloId.onkeypress= noFolioHandle;
    redondoId.onkeydown = redondoId.onkeyup = redondoId.onkeypress= noFolioHandle;
    contadoId.onkeydown = contadoId.onkeyup = contadoId.onkeypress= noFolioHandle;
    creditoId.onkeydown = creditoId.onkeyup = creditoId.onkeypress= noFolioHandle;
    autobusId.onkeydown = autobusId.onkeyup = autobusId.onkeypress= noFolioHandle;
    taxiId.onkeydown = taxiId.onkeyup = taxiId.onkeypress= noFolioHandle;
    costoViaje.onkeydown = costoViaje.onkeyup = costoViaje.onkeypress= noFolioHandle;
    observaciones.onkeydown = observaciones.onkeyup = observaciones.onkeypress= noFolioHandle;
    
    objinputselectOperadorId.onkeydown =  objinputselectOperadorId.onkeyup = objinputselectOperadorId.onkeypress = noFolioHandle; 
	objinputselectUnidadId.onkeydown = objinputselectUnidadId.onkeyup = objinputselectUnidadId.onkeypress = noFolioHandle; 
	objinputselectEmpresaId.onkeydown = objinputselectEmpresaId.onkeyup = objinputselectEmpresaId.onkeypress = noFolioHandle; 
    
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
    
    objinputselectOperadorId.size = 90;  
    objinputselectEmpresaId.size = 90;
    
    objinputselectEmpresaId.onchange = selectRuta;
    
} );
function modificar(){
	
    
	   
    objcontra.style.visibility  = 'visible';
    objcontra.style.display = '';
    objEntrar.style.visibility = 'visible';
    objEntrar.style.display = '';
	if(null!=objCancelarBtn){objCancelarBtn.disabled = false;}
	if(null!=objCancelarBtn){objCancelarBtn.style.backgroundColor="";}
	}


function eliminar(){
	  
    objcontra2.style.visibility  = 'visible';
    objcontra2.style.display = '';
    objEntrar2.style.visibility = 'visible';
    objEntrar2.style.display = '';
	if(null!=objCancelarBtn){objCancelarBtn.disabled = false;}
	if(null!=objCancelarBtn){objCancelarBtn.style.backgroundColor="";}
	}
function environmentNueva(){
	
	if(null!=objNuevaBtn){objNuevaBtn.disabled = true;}
	if(null!=objBuscarBtn){objBuscarBtn.disabled = true;}
	if(null!=objBorrarBtn){objBorrarBtn.disabled = true;}
//	if(null!=objModificarBtn){objModificarBtn.disabled = true;}
	if(null!=objBtn){objBtn.disabled = true;}
	if(null!=objSalirBtn){objSalirBtn.disabled = true;} 
	 
	if(null!=objGuardarBtn){objGuardarBtn.disabled = false;}
	if(null!=objGuardarBtn){objGuardarBtn.style.backgroundColor="";}
	if(null!=objCancelarBtn){objCancelarBtn.disabled = false;}
	if(null!=objCancelarBtn){objCancelarBtn.style.backgroundColor="";}
	
	objFolio.disabled = false;
	
	objFolio = document.getElementById("folio");
	
	
	objFolio.focus();
	
}

function environmentCancelar(){
	
	  objGuardarBtn.disabled = true;
	  objCancelarBtn.disabled = true;
	 
	  objNuevaBtn.disabled = false; 
	  objNuevaBtn.style.backgroundColor="";
	  objBuscarBtn.disabled = false; 
	  objBuscarBtn.style.backgroundColor="";
	  objBorrarBtn.disabled = false; 
	  objBorrarBtn.style.backgroundColor="";
//	  objModificarBtn.disabled = false; 
//	  objModificarBtn.style.backgroundColor="";
	  objSalirBtn.disabled = false; 
	  objSalirBtn.style.backgroundColor="";
	
	  objFolio.disabled = true; 
	  objFecha.disabled = true; 
	  objSelectOperadorId.disabled = true; 
	  objSelectUnidadId.disabled = true; 
	  objSelectEmpresaId.disabled = true; 
	  objSelectRutaId.disabled = true; 
	  objKmInicial.disabled = true; 
	  objHrInicial.disabled = true; 
	  objKmFinal.disabled = true; 
	  objHrFinal.disabled = true; 
	  objSensilloId.disabled = true; 
	  objRedondoId.disabled = true; 
	  objContadoId.disabled = true; 
	  objCreditoId.disabled = true; 
	  objAutobusId.disabled = true; 
	  objTaxiId.disabled = true; 
	  objCostoViaje.disabled = true; 
	  objObservaciones.disabled = true; 
	
	  objinputselectOperadorId.disabled = true; 
	  objinputselectUnidadId.disabled = true; 
	  objinputselectEmpresaId.disabled = true; 
	    objcontra.style.visibility  = 'hidden'; // No se ve
	    objcontra.style.display = 'none'; // No ocupa espacio
	    objEntrar.style.visibiity = 'hidden';
	    objEntrar.style.display = 'none';
	    
	    objcontra2.style.visibility  = 'hidden'; // No se ve
	    objcontra2.style.display = 'none'; // No ocupa espacio
	    objEntrar2.style.visibiity = 'hidden';
	    objEntrar2.style.display = 'none';
	  
}


function acccionEntryVales(pButton){
	
	var objFormEntryVales = document.getElementById("formEntryVales");
	var objAccion = document.getElementById("accion");
	
	if("Nueva"==pButton){
		
		environmentNueva();
		return; 
	}
 if("Btn"==pButton){
		
		modificar();
		return;
	}
 if("Borrar"==pButton)
	 {
	 eliminar();
	 return;
	 }
 
	if("Guardar"==pButton){
	   var objSubmitFormId = document.getElementById("submitFormId");
	   objAccion.value = "Guardar";
       objSubmitFormId.click();
       return;
	}
	
	if("Cancelar"==pButton){
		 var objResetFormId = document.getElementById("resetFormId");
		 resetFormId.click();
		 
		 environmentCancelar();
		
		  return; 
	}
	if("Entrar"==pButton){
		if(objcontra.value=="12bustrain12")
		{
//		alert("listo");
		if(varTablRO.$('tr.danger').length>0){
			var objNumVale = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
			var objNumvaleTrx = document.getElementById("numValeTrx"); 
			objNumvaleTrx.value = objNumVale;
			objAccion.value="Modificar"; 
			objFormEntryVales.submit();
			objcontra.value=null;
		}else{
			alert("Seleccionar algun registro");
			objcontra.value=null;
		}
		return;
	
		}
		else if (objcontra.value!="12bustrain12"){
			alert("contraseña incorrecta");
			objcontra.value=null;
		}
		
	}
	
	if("Salir"==pButton){
		objAccion.value = "Salir"; 
		objFormEntryVales.submit();
		return;
	}
	
	 if("Buscar"==pButton){
			if(varTablRO.$('tr.danger').length>0){
				var objNumVale = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
				var objNumvaleTrx = document.getElementById("numValeTrx"); 
				objNumvaleTrx.value = objNumVale;
				objAccion.value="Buscar"; 
				objFormEntryVales.submit();
			}else{
				alert("Seleccionar algun registro");
			}
			return;
	 }
	 
	 if("Modificar"==pButton){
			if(varTablRO.$('tr.danger').length>0){
				var objNumVale = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
				var objNumvaleTrx = document.getElementById("numValeTrx"); 
				objNumvaleTrx.value = objNumVale;
				objAccion.value="Modificar"; 
				objFormEntryVales.submit();
			}else{
				alert("Seleccionar algun registro");
			}
			return;
	 }
	 
		if("Entrar2"==pButton){
			if(objcontra2.value=="12bustrain12")
			{
//			alert("listo");
				 if(varTablRO.$('tr.danger').length>0){
						var objNumVale = varTablRO.$('tr.danger')[0].cells[0].childNodes[0].nodeValue; 
						var objNumvaleTrx = document.getElementById("numValeTrx"); 
						objNumvaleTrx.value = objNumVale;
						objAccion.value="Borrar"; 
						var confirmBorrar = confirm("¿Seguro que desea borrar este registro?");
						if(confirmBorrar){
						objFormEntryVales.submit();
						}
					}else{
						alert("Seleccionar algun registro");
						objcontra2.value=null;
					}
		
			objcontra2.value=null;
			}
			else if (objcontra2.value!="12bustrain12"){
				alert("contraseña incorrecta");
				objcontra2.value=null;
			}
			
		}
	 

}	



function validaFormulario(pForma){
	
	var objAccion = document.getElementById("accion");
	objAccion.value = "Guardar"; /** para eventos tipo Enter **/
	 
	if(""==pForma.folio.value){return false;}
	if(""==pForma.fecha.value){return false;}
	if(""==pForma.selectOperadorId.value){return false;}
	if(""==pForma.selectUnidadId.value){return false;}
	if(""==pForma.selectEmpresaId.value){return false;}
	if(""==pForma.selectRutaId.value){return false;}
	if(""==pForma.kmInicial.value){return false;}
	if(""==pForma.hrInicial.value){return false;}
	if(""==pForma.kmFinal.value){return false;}
	if(""==pForma.hrFinal.value){return false;}
	if(""==pForma.sensilloId.value){return false;}
	if(""==pForma.redondoId.value){return false;}
	if(""==pForma.contadoId.value){return false;}
	if(""==pForma.creditoId.value){return false;}
	if(""==pForma.autobusId.value){return false;}
	if(""==pForma.taxiId.value){return false;}
	if(""==pForma.costoViaje.value){return false;}
	/*if(""==pForma.observaciones.value){return false;} CampoNoObligatorio*/
	
	if(objKmInicial.value > objKmFinal.value){
		alert("El kilometraje final es mayor que el inicial.");
		return false; 
	}
	
	if(objKmInicial.value == objKmFinal.value){
		alert("Los Kilometrajes son los mismos.");
		return false; 
	}
	
	pForma.observaciones.value = pForma.observaciones.value.toUpperCase();
	
	var objConfirm = confirm("Son los datos correctos?");
	return objConfirm; 
	
}


function folioHandle(e) {
	
	if("keydown"==e.type){
		return; /*e.preventDefault();*/
	}
	if("keypress"==e.type){
		if("Enter"==e.code||"Tab"==e.code){
			
			if(""==objFolio.value){
				alert("Ingresar Folio");
				return; 
			}else{
			
	    	 $.post('EntryTransporteValesCO', {
	    		 accionEntryVales : "obtieneOperador",
	    		 pFolioValue:objFolio.value
	 		}, function(responseText) {
	 			objSelectOperadorId.value = responseText;
	 			if("Ya se capturo vale"==responseText
	 			   ||"Folio aun no asignado"==responseText
	 			   ||responseText.includes("EXCEPTION")){
	 				document.getElementById("folioMsg").textContent=responseText;
	 				document.getElementById("folioMsg").style.color="red"; 
	 				
	 				    objFecha.disabled = true; 
	 				    objSelectOperadorId.disabled = true; 
	 				    objSelectUnidadId.disabled = true; 
	 				    objSelectEmpresaId.disabled = true; 
	 				    objSelectRutaId.disabled = true; 
	 				    objKmInicial.disabled = true; 
	 				    objHrInicial.disabled = true; 
	 				    objKmFinal.disabled = true; 
	 				    objHrFinal.disabled = true; 
	 				    objSensilloId.disabled = true; 
	 				    objRedondoId.disabled = true; 
	 				    objContadoId.disabled = true; 
	 				    objCreditoId.disabled = true; 
	 				    objAutobusId.disabled = true; 
	 				    objTaxiId.disabled = true; 
	 				    objCostoViaje.disabled = true; 
	 				    objObservaciones.disabled = true; 
	 				    
	 				    objinputselectOperadorId.disabled = true; 
	 				    objinputselectUnidadId.disabled = true; 
	 					objinputselectEmpresaId.disabled = true; 
	 					
	 				    return; 
	 				    
	 			}else{
	 				document.getElementById("folioMsg").textContent="";
	 				objFecha.disabled = false;
			    	objSelectOperadorId.disabled = false;
			    	objSelectUnidadId.disabled = false;
			    	objSelectEmpresaId.disabled = false;
			    	objSelectRutaId.disabled = false;
			    	objKmInicial.disabled = false;
			    	objHrInicial.disabled = false;
			    	objKmFinal.disabled = false;
			    	objHrFinal.disabled = false;
			    	objSensilloId.disabled = false;
			    	objRedondoId.disabled = false;
			    	objContadoId.disabled = false;
			    	objCreditoId.disabled = false;
			    	objAutobusId.disabled = false;
			    	objTaxiId.disabled = false;
			    	objCostoViaje.disabled = false;
			    	objObservaciones.disabled = false;
			    	
			    	objinputselectOperadorId.disabled = false; 
			    	objinputselectUnidadId.disabled = false; 
			    	objinputselectEmpresaId.disabled = false; 
			    	
			    	objinputselectOperadorId.value = responseText;
			    	objSelectOperadorId.value = responseText;
			    	
			    	return; 
	 			}
	 			
	 		});
	    	 
	    	 
	    	 return; 
			} /** END IF **/
	    	 
			 
		}else{
			return; /*e.preventDefault();*/
		}
	}
	if("keyup"==e.type){
		return; /*e.preventDefault();*/
	}
}

function noFolioHandle(e){
	if(e.type=="keypress"){
	if(e.code=="Enter"||e.code=="Tab"){
	 e.preventDefault();
	   const varTargetId = e.target.id;
	   if("folio"==varTargetId){
		  document.getElementById("fecha").focus();
	   }else if("fecha"==varTargetId){
		  document.getElementById("inputselectOperadorId").focus();
	   }else if("inputselectOperadorId"==varTargetId){
		   document.getElementById("inputselectUnidadId").focus();
	   }else if("inputselectUnidadId"==varTargetId){
		   document.getElementById("inputselectEmpresaId").focus();
	   }else if("inputselectEmpresaId"==varTargetId){
		   document.getElementById("selectRutaId").focus();
	   }else if("selectRutaId"==varTargetId){
		   document.getElementById("kmInicial").focus();
	   }else if("kmInicial"==varTargetId){
		   document.getElementById("kmFinal").focus(); 
	   }else if("kmFinal"==varTargetId){
		   document.getElementById("hrInicial").focus(); 
	   }else if("hrInicial"==varTargetId){
		   document.getElementById("hrFinal").focus(); 
	   }else if("hrFinal"==varTargetId){
		   document.getElementById("costoViaje").focus(); 
	   }else if("costoViaje"==varTargetId){
		   document.getElementById("observaciones").focus(); 
	   }else if("observaciones"==varTargetId){
		   document.getElementById("GuardarBtn").focus(); 
	   }
	   return;
	 } /** END if(e.code=="Enter"){ **/
	} /** END if(e.type=="keypress"){ **/
}

function selectRuta(){
	 $.post('EntryTransporteValesCO', {
		 accionEntryVales : "selectRuta",
		 empresa:objSelectEmpresaId.value
	 }, function(responseText) {
		 selectRutaDiv.innerHTML =responseText; 
	 });
	  objSelectRutaId = document.getElementById("selectRutaId");
	  objSelectRutaId.onkeydown = objSelectRutaId.onkeyup = objSelectRutaId.onkeypress= noFolioHandle;
}

function selectCosto(){
	 objSelectRutaId = document.getElementById("selectRutaId");
	 objSelectRutaId.onkeydown = objSelectRutaId.onkeyup = objSelectRutaId.onkeypress= noFolioHandle;
	 $.post('EntryTransporteValesCO', {
		 accionEntryVales : "selectCosto",
		 ruta:objSelectRutaId.value
	 }, function(responseText) {
		 console.log("responseText:"+responseText);
		 objCostoViaje.value =responseText; 
	 });
}
