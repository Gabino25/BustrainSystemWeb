/**
 * 
 */
var objTablRO = null; 
$(document).ready(function() {
	  $.ajaxSetup({async: false}); 
	  
     	$.datepicker.regional['es'] = {
			closeText: "Cerrar",
			prevText: "&#x3C;Ant",
			nextText: "Sig&#x3E;",
			currentText: "Hoy",
			monthNames: [ "Enero","Febrero","Marzo","Abril","Mayo","Junio",
			"Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre" ],
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
		 
		  $('#TablRO').DataTable( {
			 	"pageLength":25,
			    "order": [[ 0, "desc" ]],
		    	"scrollY": 500,
		        "scrollX": true,
		        "language": { "url": "/BustrainSystemWeb/resources/json/Spanish.json"},
		        "columns": [
		    	    { "orderable": true },   /*(1)*/
		    	    { "orderable": false },  /*(2)*/
		    	    { "orderable": false },  /*(3)*/
		    	    { "orderable": false },  /*(4)*/
		    	    { "orderable": false }  /*(5)*/
		    	  
		    	  ]
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
		 
	
		 $("#selectUnidadId" ).combobox(); 
		 document.getElementById("showBtnselectUnidadId").hidden = true; 
		

});
$('#TableRO tbody').on( 'click', 'tr', function () {
	   if ( $(this).hasClass('danger') ) {
         $(this).removeClass('danger');
     }
     else {
     	objTablRO.$('tr.danger').removeClass('danger');
         $(this).addClass('danger');
     }
} );

function accionFallasAnalizar(pValue){

		var objAnalizarFallasForm = document.getElementById("AnalizarFallasForm");
	       var objAccionId = document.getElementById("accionId"); 
	
	if("Salir"==pValue){
		objAccionId.value = "Salir";
		objAnalizarFallasForm.submit();
		}
	
if("ExportarExcel"==pValue){
		
//		alert("entro");
		imprimeFileExcel();
		return;
	}
	// SE DETECTA CUAL BOTON FUE PRECIONADO MEDIANTE SU ID
	else if("Filtrar"==pValue){
		 var objFechaDesde = $("#desdeDatePicker").datepicker("getDate");
		 var objFechaHasta = $("#hastaDatePicker").datepicker("getDate");
		
		 var jsFechaDesdeDay = objFechaDesde.getDate(); 
		 var jsFechaDesdeMonth = objFechaDesde.getMonth(); 
		 var jsFechaDesdeFullYear = objFechaDesde.getFullYear(); 
		  
		 var jsFechaHastaDay = objFechaHasta.getDate(); 
		 var jsFechaHastaMonth = objFechaHasta.getMonth(); 
		 var jsFechaHastaFullYear = objFechaHasta.getFullYear();
		 var objUnidad=document.getElementById("selectUnidadId");
		 var objUnidadValue=objUnidad.value;
		 // SE OBTIENEN LOS VALORES DE LOS ELEMENTOS HTML DE ANALIZARFALLAS.JSP
		 var objrelizadoId=document.getElementById("realizadoId");
		 var objpendienteId=document.getElementById("pendienteId");
		 var estado=null;
		 //SE EVALUA CUAL DE LOS DOS RADIOBUTTON ESTA SELECCIONADO
		 if(objrelizadoId.checked){
			 estado=objrelizadoId.value;
		 }
		 else if(objpendienteId.checked){
			 estado=objpendienteId.value;
		 }
		 //SE MANDAN LOS PARAMETROS O VALORES A FALLASANALIZARCO.JAVA
		$.post('FallasAnalizarCO', {
			accionName:"Filtrar"
				,jsFechaDesdeDay:jsFechaDesdeDay
				,jsFechaDesdeMonth:jsFechaDesdeMonth
				,jsFechaDesdeFullYear:jsFechaDesdeFullYear
				,jsFechaHastaDay:jsFechaHastaDay
				,jsFechaHastaMonth:jsFechaHastaMonth
				,jsFechaHastaFullYear:jsFechaHastaFullYear
				,unidad:objUnidadValue
				,estado:estado
			},function(responseText){
			$('#DivTablRO').html(responseText);
			objTablRO= $('#TablRO').DataTable( {
				 	"pageLength":25,
				    "order": [[ 0, "desc" ]],
			    	"scrollY": 500,
			        "scrollX": true,
			        "language": { "url": "/BustrainSystemWeb/resources/json/Spanish.json"},
			        "columns": [
			    	    { "orderable": true },   /*(1)*/
			    	    { "orderable": false },  /*(2)*/
			    	    { "orderable": false },  /*(3)*/
			    	    { "orderable": false },  /*(4)*/
			    	    { "orderable": false }  /*(5)*/
			    	  
			    	  ]
			    } );
		}
		);
		
	
		
	}

}
function imprimeFileExcel(){
	var objDataTablRO = objTablRO.rows({search:'applied'}).data();
	
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
	outputText=outputText+'<Styles>';
	outputText=outputText+'<Style ss:ID="s1">';
	outputText=outputText+'<Font ss:FontName="Calibri" x:Family="Swiss" ss:Size="11" ss:Color="#000000" ss:Bold="1"/>';
	outputText=outputText+'</Style>';
	outputText=outputText+'<Style ss:ID="s2">';
	outputText=outputText+'<NumberFormat ss:Format="Short Date"/>'; 
	outputText=outputText+'</Style>';
	outputText=outputText+'<Style ss:ID="s3">';
	outputText=outputText+'<NumberFormat ss:Format="Standard"/>'; 
	outputText=outputText+'</Style>';
	outputText=outputText+'<Style ss:ID="s4">';
	outputText=outputText+'<NumberFormat ss:Format="General"/>'; 
	outputText=outputText+'</Style>';
	outputText=outputText+'</Styles>';
	outputText=outputText+'<Worksheet ss:Name="Fallas Analizar">';
	
	outputText = outputText+'<Table ss:ExpandedColumnCount="30" ss:ExpandedRowCount="10000" x:FullColumns="1"  x:FullRows="1"  ss:DefaultRowHeight="15">';
	outputText = outputText+'<Column ss:Index="2" ss:Width="80"/>';
	outputText = outputText+'<Column ss:Index="3" ss:Width="80"/>';
	outputText = outputText+'<Column ss:Index="4" ss:Width="300"/>';
	outputText = outputText+'<Column ss:Index="5" ss:Width="100"/>';
	outputText = outputText+'<Column ss:Index="6" ss:Width="130"/>';
//	outputText = outputText+'<Column ss:Index="7" ss:Width="90"/>';
//	outputText = outputText+'<Column ss:Index="8" ss:Width="120"/>';
//	outputText = outputText+'<Column ss:Index="9" ss:Width="120"/>';
//	outputText = outputText+'<Column ss:Index="11" ss:Width="120"/>';
//	outputText = outputText+'<Column ss:Index="12" ss:Width="120"/>';
	outputText = outputText+'<Row ss:Index="1">';
	outputText = outputText+'<Cell/>';
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">NUMERO</Data></Cell>';
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">UNIDAD</Data></Cell>';
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">REPARACION</Data></Cell>';
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">KILOMETRAJE</Data></Cell>';	
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">FECHA DE REPARACION</Data></Cell>';	
//	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">FECHA</Data></Cell>';
//	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">FECUENCIA MANNTO</Data></Cell>';
//	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">DIAS RECORRIDOS</Data></Cell>';
//	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">KMS/DIA</Data></Cell>';
//	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">FRECUENCIA EN DIASL</Data></Cell>';
//	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">DIAS PARA SIG MANNTO</Data></Cell>';	
	outputText = outputText+'</Row>';
	
	for(j=0;j<objDataTablRO.length;j++){
		var objRowDataTablRO = objDataTablRO[j]; 
		outputText = outputText+"<Row>";
		var recordOutput = "";
		recordOutput = recordOutput+'<Cell/>';
		recordOutput = recordOutput+'<Cell ss:StyleID="s4"><Data ss:Type="Number">'+objRowDataTablRO[0]+'</Data></Cell>';   /** NUMERO **/ 
//		var arrayFechaMannto = objRowDataTablRO[1].split("/");
//		recordOutput = recordOutput+'<Cell ss:StyleID="s2"><Data ss:Type="DateTime">'+arrayFechaMannto[2]+'-'+arrayFechaMannto[1]+'-'+arrayFechaMannto[0]+'T00:00:00.000</Data></Cell>';   /** FECHA MANNTO **/ 
//		recordOutput = recordOutput+'<Cell ss:StyleID="s3"><Data ss:Type="Number">'+objRowDataTablRO[2]+'</Data></Cell>';   /** KM MANNTO **/ 
//		var arrayFechaActual = objRowDataTablRO[3].split("/");
//		recordOutput = recordOutput+'<Cell ss:StyleID="s2"><Data ss:Type="DateTime">'+arrayFechaActual[2]+'-'+arrayFechaActual[1]+'-'+arrayFechaActual[0]+'T00:00:00.000</Data></Cell>';   /** FECHA ACTUAL **/ 
//		recordOutput = recordOutput+'<Cell ss:StyleID="s3"><Data ss:Type="Number">'+objRowDataTablRO[4]+'</Data></Cell>';   /** KILOMETRAJE **/ 
//		recordOutput = recordOutput+'<Cell ss:StyleID="s3"><Data ss:Type="Number">'+objRowDataTablRO[5]+'</Data></Cell>';   /** KM RECORRIDO **/ 
		recordOutput = recordOutput+'<Cell><Data ss:Type="String">'+objRowDataTablRO[1]+'</Data></Cell>';   /** UNIDAD **/ 
		recordOutput = recordOutput+'<Cell><Data ss:Type="String">'+objRowDataTablRO[2]+'</Data></Cell>';   /** REPARACION **/ 
		recordOutput = recordOutput+'<Cell ss:StyleID="s3"><Data ss:Type="Number">'+objRowDataTablRO[3]+'</Data></Cell>';   /** KILOMETRAJE **/ 
//		recordOutput = recordOutput+'<Cell><Data ss:Type="String">'+objRowDataTablRO[4]+'</Data></Cell>';   /** CANTIDAD DE ZAPATOS  **/ 
		recordOutput = recordOutput+'<Cell><Data ss:Type="String">'+objRowDataTablRO[4]+'</Data></Cell>';  /** FECHA DE REPARACION**/ 
		
		
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
	
	var downloadLink = document.createElement("a");
	downloadLink.href = uri;
	downloadLink.download = "FallasAnalizar.xls";

	document.body.appendChild(downloadLink);
	downloadLink.click();
	document.body.removeChild(downloadLink);
								

	
}