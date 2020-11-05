/**
 * 
 */

var objTablRO = null; 
var objTotalRegistros = 0; 
var objTotalUnidEnTiempoMantenimiento = 0; 
var objUltimaFechaCaptura = new Date("1","0","1");

$(document).ready(function() {
	objTablRO = $('#TablRO').DataTable( {
		"pageLength": 25,
    	 "order": [[ 0, "asc" ]],
    	 "scrollY": 700,
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
     	    { "orderable": false, "visible":false }   /*(12)*/
     	    ],
     	   "createdRow": function ( row, data, index ) {
     		   var objKmRecorrido =  data[5].replace(/[\$,]/g, '');
     		   var objFrecuenciaMannto = data[6].replace(/[\$,]/g, ''); 
     		   var objDifKm = objFrecuenciaMannto-objKmRecorrido;
     		   var objCategoriaFiltro = data[11];
     		   $('td', row).eq(5).addClass(objCategoriaFiltro);
     		     objTotalRegistros = objTotalRegistros +1; 
     		     if(objDifKm<0){
     		    	objTotalUnidEnTiempoMantenimiento = objTotalUnidEnTiempoMantenimiento +1;
     		     }
     		   var objFechaMannto =   data[1].split("/");
     		   var dateFechaMannto = new Date(objFechaMannto[2],objFechaMannto[1]-1,objFechaMannto[0]); 
     		   if(dateFechaMannto.getTime()>objUltimaFechaCaptura.getTime()){
     			  objUltimaFechaCaptura = dateFechaMannto; 
     		   }
     		   
            } 
    } );
	
	  var objUFDia = objUltimaFechaCaptura.getDate(); 
	  if(objUFDia<10){
		  objUFDia = "0"+objUFDia;
	  }
	  var objUFMes = objUltimaFechaCaptura.getMonth(); 
	  objUFMes = objUFMes +1; 
	  if(objUFMes<10){
		  objUFMes = "0"+objUFMes; 
	  }
	  var objUFFullYear = objUltimaFechaCaptura.getFullYear(); 
	 
	  document.getElementById("ultimaFechaCaptura").innerText = "Ultima Fecha de Captura:"+objUFDia+"/"+objUFMes+"/"+objUFFullYear;
	  document.getElementById("cantidadDeRegistros").innerText = "Cantidad de registros:"+objTotalRegistros;
	  document.getElementById("unidadesEnTiempoManteniento").innerText = "Unidades en Tiempo de Mantenimiento:"+objTotalUnidEnTiempoMantenimiento;	  
	  
	  document.getElementById("CapturaSemaforoAgenciaDiv").style.display="none"; 
	  
	  $("#submitCaptura").hide(); 
	  $("#resetCaptura").hide(); 
	
	
} );

function fPendientesXkms(pRadio){
	if("pendientesXKms"==pRadio.id){
		objTablRO.search("xxbsbg-danger").draw();
	}else if("proximosXKms"==pRadio.id){
		objTablRO.search("xxbsbg-warning").draw();
	}else if("pendientesXTiempo"==pRadio.id){
		objTablRO.search("xxbsbg-primary").draw();
	}else if("proximosXTiempo"==pRadio.id){
		objTablRO.search("xxbsbg-pink").draw();
	}
	else if("todos"==pRadio.id){
		objTablRO.search("").draw();
	}
	
	var objDataTablRO = objTablRO.rows({search:'applied'});
	objTotalRegistros =  objDataTablRO.count();
	
	document.getElementById("cantidadDeRegistros").innerText = "Cantidad de registros:"+objTotalRegistros;
	if("pendientesXKms"==pRadio.id||"todos"==pRadio.id){
     document.getElementById("unidadesEnTiempoManteniento").innerText = "Unidades en Tiempo de Mantenimiento:"+objTotalUnidEnTiempoMantenimiento;
	}else{
	 document.getElementById("unidadesEnTiempoManteniento").innerText = "Unidades en Tiempo de Mantenimiento:0";	  
	}
}

function accionSemaforoAgencia(pButton){
	var objformSemaforoAgenciaId = document.getElementById("formSemaforoAgenciaId"); 
	var objaccionSemaforoAgencia = document.getElementById("accionSemaforoAgencia"); 
	
	if("Salir"==pButton){
		objaccionSemaforoAgencia.value = "Salir"; 
		objformSemaforoAgenciaId.submit();
	}
	if("Captura"==pButton){
		document.getElementById("SemaforoAgenciaDiv").style.display="none"; 
	    document.getElementById("CapturaSemaforoAgenciaDiv").style.display=""; 
	    document.getElementById("selectUnidadId").focus(); 
	}
	if("Excel"==pButton){
		imprimeFileExcel();
		return;
	}
}

function accionCaptura(pButton){
	if("Salir"==pButton){
		document.getElementById("resetCaptura").click();
		document.getElementById("SemaforoAgenciaDiv").style.display=""; 
	    document.getElementById("CapturaSemaforoAgenciaDiv").style.display="none"; 
	}
	if("Guardar"==pButton){
		document.getElementById("accionFormId").value="CapturaDesdeSemaforoAgencia";
	    document.getElementById("submitCaptura").click();
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
	outputText=outputText+'<Worksheet ss:Name="Semaforo Agencia">';
	
	outputText = outputText+'<Table ss:ExpandedColumnCount="30" ss:ExpandedRowCount="10000" x:FullColumns="1"  x:FullRows="1"  ss:DefaultRowHeight="15">';
	outputText = outputText+'<Column ss:Index="3" ss:Width="160"/>';
	outputText = outputText+'<Column ss:Index="4" ss:Width="150"/>';
	outputText = outputText+'<Column ss:Index="5" ss:Width="90"/>';
	outputText = outputText+'<Column ss:Index="6" ss:Width="90"/>';
	outputText = outputText+'<Column ss:Index="7" ss:Width="90"/>';
	outputText = outputText+'<Column ss:Index="8" ss:Width="120"/>';
	outputText = outputText+'<Column ss:Index="9" ss:Width="120"/>';
	outputText = outputText+'<Column ss:Index="11" ss:Width="120"/>';
	outputText = outputText+'<Column ss:Index="12" ss:Width="120"/>';
	outputText = outputText+'<Row ss:Index="1">';
	outputText = outputText+'<Cell/>';
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">UNIDAD</Data></Cell>';
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">FECHA ULTIMO MANTENIMIENTO</Data></Cell>';
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">KM ULTIMO MANTENIMIENTO</Data></Cell>';
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">FECHA ACTUAL</Data></Cell>';	
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">KM ACTUAL</Data></Cell>';	
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">KMS RECORRIDOS</Data></Cell>';
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">FECUENCIA MANNTO</Data></Cell>';
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">DIAS RECORRIDOS</Data></Cell>';
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">KMS/DIA</Data></Cell>';
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">FRECUENCIA EN DIASL</Data></Cell>';
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">DIAS PARA SIG MANNTO</Data></Cell>';	
	outputText = outputText+'</Row>';
	
	for(j=0;j<objDataTablRO.length;j++){
		var objRowDataTablRO = objDataTablRO[j]; 
		outputText = outputText+"<Row>";
		var recordOutput = "";
		recordOutput = recordOutput+'<Cell/>';
		recordOutput = recordOutput+'<Cell><Data ss:Type="String">'+objRowDataTablRO[0]+'</Data></Cell>';   /** UNIDAD **/ 
		var arrayFechaMannto = objRowDataTablRO[1].split("/");
		recordOutput = recordOutput+'<Cell ss:StyleID="s2"><Data ss:Type="DateTime">'+arrayFechaMannto[2]+'-'+arrayFechaMannto[1]+'-'+arrayFechaMannto[0]+'T00:00:00.000</Data></Cell>';   /** FECHA MANNTO **/ 
		recordOutput = recordOutput+'<Cell ss:StyleID="s3"><Data ss:Type="Number">'+objRowDataTablRO[2]+'</Data></Cell>';   /** KM MANNTO **/ 
		var arrayFechaActual = objRowDataTablRO[3].split("/");
		recordOutput = recordOutput+'<Cell ss:StyleID="s2"><Data ss:Type="DateTime">'+arrayFechaActual[2]+'-'+arrayFechaActual[1]+'-'+arrayFechaActual[0]+'T00:00:00.000</Data></Cell>';   /** FECHA ACTUAL **/ 
		recordOutput = recordOutput+'<Cell ss:StyleID="s3"><Data ss:Type="Number">'+objRowDataTablRO[4]+'</Data></Cell>';   /** KILOMETRAJE **/ 
		recordOutput = recordOutput+'<Cell ss:StyleID="s3"><Data ss:Type="Number">'+objRowDataTablRO[5]+'</Data></Cell>';   /** KM RECORRIDO **/ 
		recordOutput = recordOutput+'<Cell ss:StyleID="s4"><Data ss:Type="Number">'+objRowDataTablRO[6]+'</Data></Cell>';   /** FRECUENCIA MANNTO **/ 
		recordOutput = recordOutput+'<Cell ss:StyleID="s4"><Data ss:Type="Number">'+objRowDataTablRO[7]+'</Data></Cell>';   /** DIAS RECORRIODOS **/ 
		recordOutput = recordOutput+'<Cell ss:StyleID="s4"><Data ss:Type="Number">'+objRowDataTablRO[8]+'</Data></Cell>';   /** KM/DIA **/ 
		recordOutput = recordOutput+'<Cell ss:StyleID="s4"><Data ss:Type="Number">'+objRowDataTablRO[9]+'</Data></Cell>';   /** FRECUENCIA DIAS **/ 
		recordOutput = recordOutput+'<Cell><Data ss:Type="String">'+objRowDataTablRO[10]+'</Data></Cell>';  /** DIAS P/SIGUIENTE **/ 
		
		
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
	downloadLink.download = "SemaforoAgencia.xls";

	document.body.appendChild(downloadLink);
	downloadLink.click();
	document.body.removeChild(downloadLink);
								

	
}
