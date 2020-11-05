

var objinputempresa = null; 
var objinputruta = null;
var objinputoperador = null; 
var objinputunidad = null; 
var varTablRO = null; 
var sumSubtotal = null; 

jQuery.fn.dataTable.Api.register( 'sum()', function ( ) {
    return this.flatten().reduce( function ( a, b ) {
        if ( typeof a === 'string' ) {
            a = a.replace(/[^\d.-]/g, '') * 1;
        }
        if ( typeof b === 'string' ) {
            b = b.replace(/[^\d.-]/g, '') * 1;
        }
        return a + b;
    }, 0 );
} );

$(document).ready(function() {
	
	$.ajaxSetup({async: false}); 
	  
	varTablRO = $('#TablRO').DataTable( {
    	"pageLength": 25,
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
     	    { "orderable": false }   /*(17)*/
     	    ]
    } );
    
    
    var objFechaDesde = document.getElementById("fechaDesde");
    var objFechaHasta = document.getElementById("fechaHasta");
    
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
	
	  objFechaDesde.value = objCurrentDay+"/"+objCurrentMonth+"/"+objCurrentDate.getFullYear() ;
    objFechaHasta.value = objCurrentDay+"/"+objCurrentMonth+"/"+objCurrentDate.getFullYear();
    
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

    $("#empresa" ).combobox(); 
    $("#ruta" ).combobox(); 
    $("#operador" ).combobox(); 
    $("#unidad" ).combobox(); 
    
    document.getElementById("showBtnempresa").hidden = true; 
    document.getElementById("showBtnruta").hidden = true; 
    document.getElementById("showBtnoperador").hidden = true; 
    document.getElementById("showBtnunidad").hidden = true;
    
     objinputempresa = document.getElementById("inputempresa"); 
     objinputruta = document.getElementById("inputruta"); 
     objinputoperador = document.getElementById("inputoperador"); 
     objinputunidad = document.getElementById("inputunidad");
    
     objinputempresa.size = 90;
     objinputruta.size = 90; 
     objinputoperador.size = 90; 
     
     document.getElementById("subtotal").value = '$0.00';
     
} );


function exportarExcel(){
	//alert("Comienza Exportar a Excel");
	var objTablRO =  $('#TablRO').DataTable(); 
	
	
	//console.log(objTablRO.rows());
	//console.log(objTablRO.table());
	//console.log(objTablRO.table().rows());
    //console.log(objTablRO.rows().data());
    //console.log(objTablRO.rows({search:'applied'}).data());
	//var objDataTablRO = objTablRO.rows({search:'applied'}).data(); 
	var objDataTablRO = objTablRO.rows({search:'applied'}).data();
	//console.log(objDataTablRO.length);
	
	//console.log(objTablRO.data());
	//console.log(objTablRO.cells().cache());
	//console.log(objTablRO.rows().cache());
	
	var outputText = "";
	
	outputText=outputText+'<?xml version="1.0"?>\n';
	outputText=outputText+'<?mso-application progid="Excel.Sheet"?>';
	outputText=outputText+'<Workbook xmlns="urn:schemas-microsoft-com:office:spreadsheet"';
	outputText=outputText+' xmlns:o="urn:schemas-microsoft-com:office:office"';
	outputText=outputText+' xmlns:x="urn:schemas-microsoft-com:office:excel"';
	outputText=outputText+' xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet"';
	outputText=outputText+' xmlns:html="http://www.w3.org/TR/REC-html40">\n';
	outputText=outputText+' <DocumentProperties xmlns="urn:schemas-microsoft-com:office:office">';
	outputText=outputText+'  <Version>14.00</Version>';
	outputText=outputText+' </DocumentProperties>\n';
	outputText=outputText+'<OfficeDocumentSettings xmlns="urn:schemas-microsoft-com:office:office">';
	outputText=outputText+'  <AllowPNG/>';
	outputText=outputText+' </OfficeDocumentSettings>';
	outputText=outputText+'  <ExcelWorkbook xmlns="urn:schemas-microsoft-com:office:excel">';
	outputText=outputText+'  <WindowHeight>10005</WindowHeight>';
	outputText=outputText+'  <WindowWidth>10005</WindowWidth>';
	outputText=outputText+'  <WindowTopX>120</WindowTopX>';
	outputText=outputText+'  <WindowTopY>135</WindowTopY>';
	outputText=outputText+'  <ProtectStructure>False</ProtectStructure>';
	outputText=outputText+'  <ProtectWindows>False</ProtectWindows>';
	outputText=outputText+'</ExcelWorkbook>';
	outputText=outputText+'<Styles>/n';
	outputText=outputText+'<Style ss:ID="s1">';
	outputText=outputText+'<Font ss:FontName="Calibri" x:Family="Swiss" ss:Size="11" ss:Color="#000000" ss:Bold="1"/>';
	outputText=outputText+'</Style>';
	outputText=outputText+'<Style ss:ID="s2">';
	outputText=outputText+'<NumberFormat ss:Format="[$$-80A]#,##0.00"/>';
	outputText=outputText+'</Style>';
	outputText=outputText+'</Styles>/n';
	outputText=outputText+'<Worksheet ss:Name="Listado Boletas">';
	
	outputText = outputText+'<Table ss:ExpandedColumnCount="30" ss:ExpandedRowCount="1000000" x:FullColumns="1"  x:FullRows="1"  ss:DefaultRowHeight="15">';
	outputText = outputText+'<Column ss:Index="4" ss:Width="250"/>';
	outputText = outputText+'<Column ss:Index="6" ss:Width="250"/>';
	outputText = outputText+'<Column ss:Index="12" ss:Width="250"/>';
	outputText = outputText+'<Column ss:Index="13" ss:Width="250"/>';
	outputText = outputText+'<Row ss:Index="3">';
	outputText = outputText+'<Cell/>';
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">FOLIO</Data></Cell>';
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">FECHA</Data></Cell>';
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">OPERADOR</Data></Cell>';
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">UNIDAD</Data></Cell>';	
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">EMPRESA</Data></Cell>';	
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">HR INICIAL</Data></Cell>';
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">KM INICIAL</Data></Cell>';
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">HR FINAL</Data></Cell>';
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">KM FINAL</Data></Cell>';
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">KMS REC</Data></Cell>';	
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">SERVICIO</Data></Cell>';	
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">COSTO</Data></Cell>';
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">COMENTARIO</Data></Cell>';
	outputText = outputText+'</Row>';
	
	outputText = outputText+'<Row>';
	outputText = outputText+'<Cell/>';
	outputText = outputText+'</Row>';
	for(j=0;j<objDataTablRO.length;j++){
		//console.log(objDataTablRO[j]);
		//console.log(objDataTablRO[j].innerHTML);
		var objRowDataTablRO = objDataTablRO[j]; 
		outputText = outputText+"<Row>";
		var recordOutput = "";
		recordOutput = recordOutput+'<Cell/>';
		recordOutput = recordOutput+'<Cell><Data ss:Type="Number">'+objRowDataTablRO[0]+'</Data></Cell>';   /** FOLIO **/ 
		recordOutput = recordOutput+'<Cell><Data ss:Type="String">'+objRowDataTablRO[1]+'</Data></Cell>';   /** FECHA **/
		recordOutput = recordOutput+'<Cell><Data ss:Type="String">'+objRowDataTablRO[4]+'</Data></Cell>';   /** OPERADOR **/
		recordOutput = recordOutput+'<Cell><Data ss:Type="String">'+objRowDataTablRO[2]+'</Data></Cell>';   /** UNIDAD **/
		recordOutput = recordOutput+'<Cell><Data ss:Type="String">'+objRowDataTablRO[10]+'</Data></Cell>';  /** EMPRESA **/
		recordOutput = recordOutput+'<Cell><Data ss:Type="String">'+objRowDataTablRO[6]+'</Data></Cell>';   /** HR INICIAL **/
		recordOutput = recordOutput+'<Cell><Data ss:Type="Number">'+objRowDataTablRO[7]+'</Data></Cell>';   /** KM INICIAL **/
		recordOutput = recordOutput+'<Cell><Data ss:Type="String">'+objRowDataTablRO[8]+'</Data></Cell>';   /** HR FINAL **/
		recordOutput = recordOutput+'<Cell><Data ss:Type="Number">'+objRowDataTablRO[9]+'</Data></Cell>';   /** KM FINAL **/
		
		var kmFinal = parseInt(objRowDataTablRO[9]);
		var kmInicial = parseInt(objRowDataTablRO[7]);
		var kmRecorridos = kmFinal-kmInicial;
		recordOutput = recordOutput+'<Cell><Data ss:Type="Number">'+kmRecorridos+'</Data></Cell>'; /** KMS REC **/
		recordOutput = recordOutput+'<Cell><Data ss:Type="String">'+objRowDataTablRO[3]+'</Data></Cell>';   /** SERVICIO **/
		var costoReplace = objRowDataTablRO[5].replace(/[^\d.-]/g, '') * 1;
		recordOutput = recordOutput+'<Cell ss:StyleID="s2"><Data ss:Type="Number">'+costoReplace+'</Data></Cell>';   /** COSTO **/
		/**  recordOutput = recordOutput+'<Cell><Data ss:Type="String">'+objRowDataTablRO[11]+'</Data></Cell>';  COMENTARIOS **/
		
		var arrayComentarios = objRowDataTablRO[11].split("|"); /** PARTE COMENTARIOS **/
		var i;
		for (i = 0; i < arrayComentarios.length; i++) { 
			recordOutput = recordOutput+'<Cell><Data ss:Type="String">'+arrayComentarios[i]+'</Data></Cell>';
		 } 
		outputText = outputText+recordOutput;
		outputText = outputText+"</Row>";
	}
	
	    outputText = outputText+'</Table>';
	    outputText = outputText+'<WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">';
	    outputText = outputText+'<Selected/>';
	    outputText = outputText+'<ProtectObjects>False</ProtectObjects>';
	    outputText = outputText+'<ProtectScenarios>False</ProtectScenarios>';
	    outputText = outputText+'</WorksheetOptions>';
	    outputText = outputText+'</Worksheet>';
	    
	    outputText = outputText+'</Workbook>'; 
	
	var uri = 'data:application/vnd.ms-excel,' + encodeURIComponent(outputText);
	
	//var sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(outputText));
	
	var downloadLink = document.createElement("a");
	downloadLink.href = uri;
	downloadLink.download = "ListadoBoletas.xls";

	document.body.appendChild(downloadLink);
	downloadLink.click();
	document.body.removeChild(downloadLink);
	
	/**var a = document.createElement('a');
	var data_type = 'data:application/vnd.ms-excel';
	a.href = data_type + ', ' + "Holassss";
	a.download = 'download.xlsx';
	a.click();
    ***/

	//alert("Finaliza Exportar a Excel");
	//return (sa);
}

function filtrarPorfechas(){
	 var objFechaDesde = $("#fechaDesde" ).datepicker( "getDate" );
	 var objFechaHasta = $("#fechaHasta" ).datepicker( "getDate" );
	  
	 var jsFechaDesdeDay = objFechaDesde.getDate(); 
	 var jsFechaDesdeMonth = objFechaDesde.getMonth(); 
	 var jsFechaDesdeFullYear = objFechaDesde.getFullYear(); 
	  
	 var jsFechaHastaDay = objFechaHasta.getDate(); 
	 var jsFechaHastaMonth = objFechaHasta.getMonth(); 
	 var jsFechaHastaFullYear = objFechaHasta.getFullYear(); 
	 
	 var objEmpresa = document.getElementById("empresa"); 
	 var objRuta = document.getElementById("ruta"); 
	 var objOperador = document.getElementById("operador"); 
	 var objUnidad = document.getElementById("unidad"); 
	 
	 var objimputempresaValue = objinputempresa.value; 
     var objinputrutaValue = objinputruta.value; 
     var objinputoperadorValue = objinputoperador.value; 
     var objinputunidadvalue = objinputunidad.value;
	 
	  
	 $.post('ListadoBoletasCO', {
		    accionListadoBoletas : "filtraPorFechas",
		    jsFechaDesdeDay: jsFechaDesdeDay,
		    jsFechaDesdeMonth:jsFechaDesdeMonth,
		    jsFechaDesdeFullYear:jsFechaDesdeFullYear,
		    jsFechaHastaDay:jsFechaHastaDay,
		    jsFechaHastaMonth:jsFechaHastaMonth,
		    jsFechaHastaFullYear:jsFechaHastaFullYear,
		    jsEmpresa:objimputempresaValue,
		    jsRuta:objinputrutaValue,
		    jsOperador:objinputoperadorValue,
		    jsUnidad:objinputunidadvalue
		}, function(responseText) {
			$('#DivTablRO').html(responseText);
			varTablRO = $('#TablRO').DataTable( {
				 "pageLength": 25,
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
		      	    { "orderable": false }   /*(17)*/
		      	    ]
		    } );
		});
	 
	  sumSubtotal = varTablRO.column(5).data().sum();
	  //alert("Finaliza filtrarPorfechas");
	  document.getElementById("subtotal").value = Number(sumSubtotal).toLocaleString('en-US',{ style: 'currency', currency: 'USD' }); 
	  
}

function accionListadoBoletas(pButton){
	var objAccion = document.getElementById("accion");
	var objFormListadoBoletas = document.getElementById("formListadoBoletas"); 
	if("Salir"==pButton){
		objAccion.value="Salir"; 
		objFormListadoBoletas.submit();
		return;
	}
}
