/**
 * 
 */


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


$(document).ajaxSend(function() {
	 document.body.style.setProperty("cursor","wait","important");
});

$(document).ajaxComplete(function() {
	document.body.style.setProperty("cursor","default");
});

$.ajaxSetup({async: false}); /* en conflictos con el cursor wait */

var varTablRO = null; 
var sumTotal = null; 
var sumLitros = null; 
var sumKM = null; 
var sumRendimiento = null; 

$(document).ready(function() {
	 
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
		
		 
	
	
	
	    // DataTable
	    var varTablRO = $('#TablRO').DataTable({ 
	    	"pageLength": 25,
	      	 "order": [[ 0, "desc" ]],
	    	 "scrollY": 350,
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
	                                     	    ]
	                                     }); /** END DataTable({  **/
	  
	     sumTotal = varTablRO.column(3).data().sum();
	     sumLitros = varTablRO.column(4).data().sum();
	     sumKM = varTablRO.column(7).data().sum();
	     sumRendimiento = varTablRO.column(8).data().sum();
	     
	    document.getElementById("sumTotal").value = '$0.00';
	    document.getElementById("sumLitros").value = '0';
	    document.getElementById("sumKm").value = '0';
	    document.getElementById("sumRendimiento").value = '0';
	    
	    var x = document.getElementsByClassName("dataTables_scrollHead");
	    var i;
	    console.log(x.length);
	    for (i = 0; i < x.length; i++) {
	       x[i].style.backgroundColor = "red";
	    } 
	    
	    
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
		 
		 document.getElementById("inputselectUnidadId").onchange= detectaCambio;
		 
	    //var vardataTables_scrollHeadInner =  document.getElementsByClassName("dataTables_scrollHeadInner");
	    //console.log(vardataTables_scrollHeadInner);
	    //vardataTables_scrollHeadInner.style.backgroundColor = "red"; 
	    
		 $("#imprimirDiv" ).hide(); 
		 
}); /** END ready(function() { **/


function detectaCambio (){
	
	 var objFechaDesde = $("#desdeDatePicker").datepicker("getDate");
	 var objFechaHasta = $("#hastaDatePicker").datepicker("getDate");
	  
	 var jsFechaDesdeDay = objFechaDesde.getDate(); 
	 var jsFechaDesdeMonth = objFechaDesde.getMonth(); 
	 var jsFechaDesdeFullYear = objFechaDesde.getFullYear(); 
	  
	 var jsFechaHastaDay = objFechaHasta.getDate(); 
	 var jsFechaHastaMonth = objFechaHasta.getMonth(); 
	 var jsFechaHastaFullYear = objFechaHasta.getFullYear(); 
	 
	 var jsUnidad =  document.getElementById("inputselectUnidadId").value;
	 var jsGasolinera =  document.getElementById("selectGasolineraId").value;
	 
	 $.post('AnalizarCombustibleCO', {
		    accionAnalizaCombustible : "filtraPorFechas",
		    jsFechaDesdeDay: jsFechaDesdeDay,
		    jsFechaDesdeMonth:jsFechaDesdeMonth,
		    jsFechaDesdeFullYear:jsFechaDesdeFullYear,
		    jsFechaHastaDay:jsFechaHastaDay,
		    jsFechaHastaMonth:jsFechaHastaMonth,
		    jsFechaHastaFullYear:jsFechaHastaFullYear,
		    jsUnidad:jsUnidad,
		    jsGasolinera:jsGasolinera
		}, function(responseText) {
			$('#DivTablRO').html(responseText);
			varTablRO = $('#TablRO').DataTable( {
				"pageLength": 25,
				 "order": [[ 0, "desc" ]],
				 "scrollY": 350,
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
              	    ]
		    } ); 
			
			  sumTotal = varTablRO.column(3).data().sum();
		      sumLitros = varTablRO.column(4).data().sum();
		      sumKM = varTablRO.column(7).data().sum();
		      sumRendimiento = varTablRO.column(8).data().sum();
			     
			    
			
		});
	 
	
	
	document.getElementById("sumTotal").value = Number(sumTotal).toLocaleString('en-US',{ style: 'currency', currency: 'USD' }); 
    document.getElementById("sumLitros").value = Number(sumLitros).toLocaleString('en-US'); 
    document.getElementById("sumKm").value = Number(sumKM).toLocaleString('en-US'); 
    document.getElementById("sumRendimiento").value = Number(sumRendimiento).toLocaleString('en-US'); 
}

function acccionAnalizarCombustible(pButton){
	
	 var objFechaDesde = $("#desdeDatePicker").datepicker("getDate");
	 var objFechaHasta = $("#hastaDatePicker").datepicker("getDate");
	  
	 var jsFechaDesdeDay = objFechaDesde.getDate(); 
	 var jsFechaDesdeMonth = objFechaDesde.getMonth(); 
	 var jsFechaDesdeFullYear = objFechaDesde.getFullYear(); 
	  
	 var jsFechaHastaDay = objFechaHasta.getDate(); 
	 var jsFechaHastaMonth = objFechaHasta.getMonth(); 
	 var jsFechaHastaFullYear = objFechaHasta.getFullYear(); 
	 
	var objAccion = document.getElementById("accion");
	var objFormAnalizarCombustible = document.getElementById("formAnalizarCombustible"); 
	if("Salir"==pButton){
		objAccion.value="Salir"; 
		objFormAnalizarCombustible.submit();
		return;
	}
	else if("CargasAexcel"==pButton){
		
    	// $("body").addClass("waiting");
    	 var body = document.body;
    	// body.classList.add("waiting");
    	 
    	 var i = 0; 
    	 
    	 $.ajax({
    		 xhr: function () {
    		        var xhr = new window.XMLHttpRequest();
    		        xhr.addEventListener("progress", function (evt) {
    		        	console.log(evt.lengthComputable);
    		        	console.log(evt.loaded);
    		        	console.log(evt.total);
    		        	i = i+2; 
    		        	 $('.progress').css({
 		                    width: i+ '%'
 		                });
    		        	if(evt.loaded===evt.total){
    		        		 $('.progress').css({
    	 		                    width:'100%'
    	 		             });
    		        	}
    		        }, false);
    		        return xhr;
    		    },
    		 async:true,   /** elegir asicrono o barra **/
    		 type:"POST",
    		 data:"accionAnalizaCombustible=CargasAexcel&jsFechaDesdeDay="+jsFechaDesdeDay
    		                                          +"&jsFechaDesdeMonth="+jsFechaDesdeMonth
    		                                          +"&jsFechaDesdeFullYear="+jsFechaDesdeFullYear
    		                                          +"&jsFechaHastaDay="+jsFechaHastaDay
    		                                          +"&jsFechaHastaMonth="+jsFechaHastaMonth
    		                                          +"&jsFechaHastaFullYear="+jsFechaHastaFullYear
    		                                          ,
    		 beforeSend:function(){document.body.style.setProperty("cursor","wait","important"); alert("Favor de Esperar")},                                         
    		 url:"AnalizarCombustibleCO",
    		 success:function(content){
    			console.log(content);
    			alert('Hola');
    			var uri = 'data:application/vnd.ms-excel,' + encodeURIComponent(content);
 				var downloadLink = document.createElement("a");
 				downloadLink.href = uri;
 				downloadLink.download = "AnalizarCombustible.xls";

 				document.body.appendChild(downloadLink);
 				downloadLink.click();
 				document.body.removeChild(downloadLink);
    		 }
    	 });
    	 
		/* $.post('AnalizarCombustibleCO', {
			    accionAnalizaCombustible : "CargasAexcel",
			    jsFechaDesdeDay: jsFechaDesdeDay,
			    jsFechaDesdeMonth:jsFechaDesdeMonth,
			    jsFechaDesdeFullYear:jsFechaDesdeFullYear,
			    jsFechaHastaDay:jsFechaHastaDay,
			    jsFechaHastaMonth:jsFechaHastaMonth,
			    jsFechaHastaFullYear:jsFechaHastaFullYear
			}, function(responseText) {
				var uri = 'data:application/vnd.ms-excel,' + encodeURIComponent(responseText);
				var downloadLink = document.createElement("a");
				downloadLink.href = uri;
				downloadLink.download = "AnalizarCombustible.xls";

				document.body.appendChild(downloadLink);
				downloadLink.click();
				document.body.removeChild(downloadLink);
			});
		 */	

		 // body.classList.remove("waiting");
		 //$('body').removeClass('waiting');
    }else if("CargasAexcelCategoria"==pButton){
    	
    	 document.getElementById("jsFechaDesdeDay").value = jsFechaDesdeDay; 
    	 document.getElementById("jsFechaDesdeMonth").value = jsFechaDesdeMonth; 
    	 document.getElementById("jsFechaDesdeFullYear").value = jsFechaDesdeFullYear; 
    	 
    	 document.getElementById("jsFechaHastaDay").value = jsFechaHastaDay; 
    	 document.getElementById("jsFechaHastaMonth").value = jsFechaHastaMonth; 
    	 document.getElementById("jsFechaHastaFullYear").value = jsFechaHastaFullYear; 
    	 
    	objAccion.value="CargasAexcelCategoria"; 
		objFormAnalizarCombustible.submit();
    }
	else if("RendimientosAexcel"==pButton){
		
		var objTablRO =  $('#TablRO').DataTable(); 
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
		outputText=outputText+'<Styles>/n';
		outputText=outputText+'<Style ss:ID="s1">/n';
		outputText=outputText+'<Font ss:FontName="Calibri" x:Family="Swiss" ss:Size="11" ss:Color="#000000" ss:Bold="1"/>/n';
		outputText=outputText+'</Style>';
		outputText=outputText+'<Style ss:ID="s3">';
		outputText=outputText+'<NumberFormat ss:Format="Standard"/>'; 
		outputText=outputText+'</Style>';
		outputText=outputText+'<Style ss:ID="s4">';
		outputText=outputText+'<NumberFormat ss:Format="General"/>'; 
		outputText=outputText+'</Style>';
		outputText=outputText+'</Styles>/n';
		outputText=outputText+'<Worksheet ss:Name="RendimientosCombustible">';
		outputText = outputText+'<Table ss:ExpandedColumnCount="30" ss:ExpandedRowCount="10000" x:FullColumns="1"  x:FullRows="1"  ss:DefaultRowHeight="15">';
		
		outputText = outputText+'<Row>'; 
		outputText = outputText+'<Cell ss:Index="4"><Data ss:Type="String">RENDIMIENTOS BUSTRAIN</Data></Cell>'; 
		outputText = outputText+'</Row>'; 
		
		outputText = outputText+'<Row/>'; 
		
		outputText = outputText+'<Row>'; 
		outputText = outputText+'<Cell/>'; 
		outputText = outputText+'<Cell><Data ss:Type="String">UNIDAD</Data></Cell>'; 
		outputText = outputText+'<Cell><Data ss:Type="String">FECHA</Data></Cell>'; 
		outputText = outputText+'<Cell><Data ss:Type="String">LITROS</Data></Cell>'; 
		outputText = outputText+'<Cell><Data ss:Type="String">TOTAL</Data></Cell>'; 
		outputText = outputText+'<Cell><Data ss:Type="String">KMS</Data></Cell>'; 
		outputText = outputText+'<Cell><Data ss:Type="String">KM ANT</Data></Cell>'; 
		outputText = outputText+'<Cell><Data ss:Type="String">KMS REC</Data></Cell>'; 
		outputText = outputText+'<Cell><Data ss:Type="String">REND</Data></Cell>'; 
		outputText = outputText+'<Cell><Data ss:Type="String">OPERADOR</Data></Cell>'; 
		outputText = outputText+'</Row>'; 
		
		outputText = outputText+'<Row/>'; 
		
		for(j=0;j<objDataTablRO.length;j++){
		var objRowDataTablRO = objDataTablRO[j]; 
		outputText = outputText+'<Row>';
		var recordOutput = "";
		recordOutput = recordOutput+'<Cell/>'; 
		recordOutput = recordOutput+'<Cell><Data ss:Type="String">'+objRowDataTablRO[2]+'</Data></Cell>';  /*UNIDAD*/
		recordOutput = recordOutput+'<Cell><Data ss:Type="String">'+objRowDataTablRO[0]+'</Data></Cell>';  /*FECHA*/
		recordOutput = recordOutput+'<Cell ss:StyleID="s3"><Data ss:Type="Number">'+objRowDataTablRO[4]+'</Data></Cell>';  /*LITROS*/
		var totalReplace = objRowDataTablRO[3].replace(/[^\d.-]/g, '') * 1;
		recordOutput = recordOutput+'<Cell ss:StyleID="s3"><Data ss:Type="Number">'+totalReplace+'</Data></Cell>';  /*TOTAL*/
		recordOutput = recordOutput+'<Cell ss:StyleID="s3"><Data ss:Type="Number">'+objRowDataTablRO[5]+'</Data></Cell>';  /*KMS*/
		recordOutput = recordOutput+'<Cell ss:StyleID="s3"><Data ss:Type="Number">'+objRowDataTablRO[6]+'</Data></Cell>';  /*KM ANT*/
		recordOutput = recordOutput+'<Cell ss:StyleID="s3"><Data ss:Type="Number">'+objRowDataTablRO[7]+'</Data></Cell>';  /*KM REC*/
		recordOutput = recordOutput+'<Cell ss:StyleID="s3"><Data ss:Type="Number">'+objRowDataTablRO[8]+'</Data></Cell>';  /*REND*/
		recordOutput = recordOutput+'<Cell><Data ss:Type="String">'+objRowDataTablRO[9]+'</Data></Cell>';  /*OPERADOR*/
		outputText = outputText+recordOutput;
		outputText = outputText+'</Row>'; 
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
		downloadLink.download = "RendimientosCombustible.xls";

		document.body.appendChild(downloadLink);
		downloadLink.click();
		document.body.removeChild(downloadLink);
		
	}else if("CatalogarUnidades"==pButton){
		objAccion.value="CatalogarUnidades"; 
		objFormAnalizarCombustible.submit();
		return;
	}
		
}

function imprimir(){
	
	var objTablRO =  $('#TablRO').DataTable(); 
	var objDataTablRO = objTablRO.rows({search:'applied'}).data();
	
	
	var varImprimirTab = document.getElementById("imprimirTab");
	var varImpTabLength = varImprimirTab.rows.length;
	console.log(varImpTabLength);
 	for(j=1;j<varImpTabLength;j++){
      varImprimirTab.deleteRow(1); /** La tabla varia en el tiempo de ejecucion **/
	}
	
	
	for(j=0;j<objDataTablRO.length;j++){
		var objRowDataTablRO = objDataTablRO[j]; 
		var objRow = varImprimirTab.insertRow();
		var objRowIndex = objRow.rowIndex;
	    var varCell = null;
	    
	    varCell = objRow.insertCell();
	    varCell.style.paddingLeft = "10px"; 
	    varCell.style.paddingRight = "10px"; 
	    varCell.style.borderLeft="1px solid #000";
	    if(j==objDataTablRO.length-1){
	    	varCell.style.borderBottom="1px solid #000";	
	    }
	    varCell.innerHTML=objRowDataTablRO[0]; /*FECHA*/
	    
	    varCell = objRow.insertCell();
	    varCell.style.paddingLeft = "10px"; 
	    varCell.style.paddingRight = "10px"; 
	    if(j==objDataTablRO.length-1){
	    	varCell.style.borderBottom="1px solid #000";	
	    }
	    varCell.innerHTML=objRowDataTablRO[1]; /*FOLIO*/
	    
	    varCell = objRow.insertCell();
	    varCell.style.paddingLeft = "10px"; 
	    varCell.style.paddingRight = "10px"; 
	    if(j==objDataTablRO.length-1){
	    	varCell.style.borderBottom="1px solid #000";	
	    }
	    varCell.innerHTML=objRowDataTablRO[2]; /*UNIDAD*/
	    
	    varCell = objRow.insertCell();
	    varCell.style.paddingLeft = "10px"; 
	    varCell.style.paddingRight = "10px"; 
	    varCell.style.textAlign="right";
	    if(j==objDataTablRO.length-1){
	    	varCell.style.borderBottom="1px solid #000";
	    }
	    varCell.innerHTML=objRowDataTablRO[3]; /*TOTAL*/
	    
	    varCell = objRow.insertCell();
	    varCell.style.paddingLeft = "10px"; 
	    varCell.style.paddingRight = "10px"; 
	    varCell.style.textAlign="right";
	    if(j==objDataTablRO.length-1){
	    	varCell.style.borderBottom="1px solid #000";	
	    }
	    varCell.innerHTML=objRowDataTablRO[5]; /*KMS*/
	    
	    varCell = objRow.insertCell();
	    varCell.style.paddingLeft = "10px"; 
	    varCell.style.paddingRight = "10px"; 
	    varCell.style.textAlign="right";
	    if(j==objDataTablRO.length-1){
	    	varCell.style.borderBottom="1px solid #000";	
	    }
	    varCell.innerHTML=objRowDataTablRO[4]; /*LITROS*/
	    
	    varCell = objRow.insertCell();
	    varCell.style.paddingLeft = "10px"; 
	    varCell.style.paddingRight = "10px"; 
	    varCell.style.textAlign="right";
	    if(j==objDataTablRO.length-1){
	    	varCell.style.borderBottom="1px solid #000"; 	
	    }
	    varCell.innerHTML=objRowDataTablRO[8]; /*REND*/
	    
	    varCell = objRow.insertCell();
	    varCell.style.paddingLeft = "10px"; 
	    varCell.style.paddingRight = "10px"; 
	    varCell.style.borderRight="1px solid #000";
    	if(j==objDataTablRO.length-1){
	    	varCell.style.borderBottom="1px solid #000";
	    }	
	    varCell.innerHTML=objRowDataTablRO[9]; /*OPERADOR*/
	    
	 }		
	

	var divImprimir= document.getElementById('imprimirDiv');
	var ventana=window.open();
	
	ventana.document.write('<style>body{background-color:white; color:black;} table{font-size:10px;}</style>'+divImprimir.innerHTML);
	ventana.document.close();
    ventana.focus();
    ventana.print();
    ventana.close(); 

}
