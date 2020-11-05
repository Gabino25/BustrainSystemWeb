/**
 * 
 */
var objNuevaBtn = null; 
var objGuardarBtn = null; 
var objCancelarBtn = null; 

var objTrabajador=null;
var objCCamisa=null;
var objCPlayera=null;
var objCPantalon=null;
var objCZapato=null;
var objFecha=null;
var objAccionId=null;
var objTrabajadorFormId=null;
var objTablRO = null; 

$(document).ready(function() {
	
	 function disablePrev() { window.history.forward() }
	   window.onload = disablePrev();
	   window.onpageshow = function(evt) { if (evt.persisted) disablePrev() }
	   
	$("#submitFormId").hide();
	$("#resetFormId").hide();
	
	objTablRO = $('#TableRO').DataTable( {
		"pageLength": 25,
  	"order": [[ 0, "desc" ]],
  	"scrollY": 400,
      "scrollX": true,
      "language": {"url": "/BustrainSystemWeb/resources/json/Spanish.json"},
      "columns": [
  	    { "orderable": true },   /*(1)*/
  	    { "orderable": false },  /*(2)*/
  	    { "orderable": false },  /*(3)*/
  	    { "orderable": false },  /*(4)*/
  	    { "orderable": false },  /*(5)*/
  	    { "orderable": false },  /*(6)*/
  	
  	  ]
  } );
  
	 $('#TableRO tbody').on( 'click', 'tr', function () {
		   if ( $(this).hasClass('danger') ) {
	            $(this).removeClass('danger');
	        }
	        else {
	        	objTablRO.$('tr.danger').removeClass('danger');
	            $(this).addClass('danger');
	        }
	 } );
		objNuevaBtn = document.getElementById("NuevaBtn");
	    objGuardarBtn = document.getElementById("UGuardarBtn");
	    objCancelarBtn = document.getElementById("CancelarBtn");
	   
	    objTrabajador=document.getElementById("trabajador");
	    objCCamisa=document.getElementById("CCamisa");
	    objCPlayera=document.getElementById("CPlayera");
	    objCPantalon=document.getElementById("CPantalon");
	    objCZapato=document.getElementById("CZapato");
	    objFecha=document.getElementById("fechaA");
	    
	    if(null!=objGuardarBtn)
	        objGuardarBtn.disabled = true;
	        if(null!=objCancelarBtn)
	        objCancelarBtn.disabled = true;

	        objTrabajador.disabled=true;
	        objCCamisa.disabled=true;
	        objCPlayera.disabled=true;
	        objCPantalon.disabled=true;
	        objCZapato.disabled=true;
	        objFecha.disabled=true;
	        
	        
	        objTrabajador.onkeydown =objTrabajador.onkeyup =objTrabajador.onkeypress = handleEvent; 
	        objCCamisa.onkeydown =objCCamisa.onkeyup =objCCamisa.onkeypress = handleEvent; 
	        objCPlayera.onkeydown =objCPlayera.onkeyup =objCPlayera.onkeypress = handleEvent; 
	        objCPantalon.onkeydown =objCPantalon.onkeyup =objCPantalon.onkeypress = handleEvent; 
	        objCZapato.onkeydown =objCZapato.onkeyup =objCZapato.onkeypress = handleEvent; 
	        objFecha.onkeydown =objFecha.onkeyup =objFecha.onkeypress = handleEvent; 
} );

function accionCatTrabaj(pButton){
	 objAccionId = document.getElementById("accionId"); 
	 objTrabajadorFormId = document.getElementById("UniformeTrabajadorId"); 
	 
	 if("Nueva"==pButton){
		    
		    environmentNueva();
			    
		    var objDate = new Date();
		    
		    objFecha.value=objDate.toISOString().substring(0,10);
		    
		    
			return; 
	  }
	 if("Cancelar"==pButton){
		 var objResetFormId = document.getElementById("resetFormId");
		 resetFormId.click();
		
		 environmentCancelar();
		 
		 return;
		} /** END if("Cancelar"==pAccion){ **/

		if("UGuardar"==pButton){
		      var objSubmitFormId = document.getElementById("submitFormId");
		      objAccionId.value = "UGuardar";
		      objSubmitFormId.click();
		      return;
		}/** END if("Guardar"==pAccion){ **/
	if("Regresar"==pButton){
		accionId.value = "Regresar"; 
		objTrabajadorFormId.submit();
		return; 
	}
	if("Excel"==pButton){
		
//		alert("entro");
		imprimeFileExcel();
		return;
	}
	
}
function environmentNueva(){
	
	  
	
	    objNuevaBtn.disabled = true; 
		
		
		objGuardarBtn.disabled = false;
		objGuardarBtn.style.backgroundColor="";
		objCancelarBtn.disabled = false;
		objCancelarBtn.style.backgroundColor="";
		
	    objTrabajador.disabled=false;
        objCCamisa.disabled=false;
        objCPlayera.disabled=false;
        objCPantalon.disabled=false;
        objCZapato.disabled=false;
        objFecha.disabled = false; 
		
}

function environmentCancelar(){
	  
	  objGuardarBtn.disabled = true;
	  objCancelarBtn.disabled = true;
	 
	  objNuevaBtn.disabled = false; 
	  objNuevaBtn.style.backgroundColor="";
	 
	  objTrabajador.disabled=true;
      objCCamisa.disabled=true;
      objCPlayera.disabled=true;
      objCPantalon.disabled=true;
      objCZapato.disabled=true;
      objFecha.disabled = true; 
	  
	  
}
function handleEvent(e){
	if(e.code=="Enter"){
		e.preventDefault();
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
	outputText=outputText+'<Worksheet ss:Name="Uniformes Trabajadores">';
	
	outputText = outputText+'<Table ss:ExpandedColumnCount="30" ss:ExpandedRowCount="10000" x:FullColumns="1"  x:FullRows="1"  ss:DefaultRowHeight="15">';
	outputText = outputText+'<Column ss:Index="2" ss:Width="250"/>';
	outputText = outputText+'<Column ss:Index="3" ss:Width="130"/>';
	outputText = outputText+'<Column ss:Index="4" ss:Width="140"/>';
	outputText = outputText+'<Column ss:Index="5" ss:Width="130"/>';
	outputText = outputText+'<Column ss:Index="6" ss:Width="130"/>';
//	outputText = outputText+'<Column ss:Index="7" ss:Width="90"/>';
//	outputText = outputText+'<Column ss:Index="8" ss:Width="120"/>';
//	outputText = outputText+'<Column ss:Index="9" ss:Width="120"/>';
//	outputText = outputText+'<Column ss:Index="11" ss:Width="120"/>';
//	outputText = outputText+'<Column ss:Index="12" ss:Width="120"/>';
	outputText = outputText+'<Row ss:Index="1">';
	outputText = outputText+'<Cell/>';
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">NOMBRE</Data></Cell>';
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">CANTIDAD DE CAMISAS </Data></Cell>';
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">CANTIDAD DE PLAYERAS </Data></Cell>';
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">CANTIDAD DE PANTALONES </Data></Cell>';	
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">CANTIDAD DE ZAPATOS </Data></Cell>';	
	outputText = outputText+'<Cell ss:StyleID="s1"><Data ss:Type="String">FECHA</Data></Cell>';
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
		recordOutput = recordOutput+'<Cell><Data ss:Type="String">'+objRowDataTablRO[0]+'</Data></Cell>';   /** NOMBRE DEL TRABAJADOR **/ 
//		var arrayFechaMannto = objRowDataTablRO[1].split("/");
//		recordOutput = recordOutput+'<Cell ss:StyleID="s2"><Data ss:Type="DateTime">'+arrayFechaMannto[2]+'-'+arrayFechaMannto[1]+'-'+arrayFechaMannto[0]+'T00:00:00.000</Data></Cell>';   /** FECHA MANNTO **/ 
//		recordOutput = recordOutput+'<Cell ss:StyleID="s3"><Data ss:Type="Number">'+objRowDataTablRO[2]+'</Data></Cell>';   /** KM MANNTO **/ 
//		var arrayFechaActual = objRowDataTablRO[3].split("/");
//		recordOutput = recordOutput+'<Cell ss:StyleID="s2"><Data ss:Type="DateTime">'+arrayFechaActual[2]+'-'+arrayFechaActual[1]+'-'+arrayFechaActual[0]+'T00:00:00.000</Data></Cell>';   /** FECHA ACTUAL **/ 
//		recordOutput = recordOutput+'<Cell ss:StyleID="s3"><Data ss:Type="Number">'+objRowDataTablRO[4]+'</Data></Cell>';   /** KILOMETRAJE **/ 
//		recordOutput = recordOutput+'<Cell ss:StyleID="s3"><Data ss:Type="Number">'+objRowDataTablRO[5]+'</Data></Cell>';   /** KM RECORRIDO **/ 
		recordOutput = recordOutput+'<Cell ss:StyleID="s4"><Data ss:Type="Number">'+objRowDataTablRO[1]+'</Data></Cell>';   /** CANTIDAD DE CAMISAS **/ 
		recordOutput = recordOutput+'<Cell ss:StyleID="s4"><Data ss:Type="Number">'+objRowDataTablRO[2]+'</Data></Cell>';   /** CANTIDAD DE PLAYERAS **/ 
		recordOutput = recordOutput+'<Cell ss:StyleID="s4"><Data ss:Type="Number">'+objRowDataTablRO[3]+'</Data></Cell>';   /** CANTIDAD DE PANTALONES **/ 
		recordOutput = recordOutput+'<Cell><Data ss:Type="String">'+objRowDataTablRO[4]+'</Data></Cell>';   /** CANTIDAD DE ZAPATOS  **/ 
		recordOutput = recordOutput+'<Cell><Data ss:Type="String">'+objRowDataTablRO[5]+'</Data></Cell>';  /** FECHA **/ 
		
		
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
	downloadLink.download = "UniformesTrabajadores.xls";

	document.body.appendChild(downloadLink);
	downloadLink.click();
	document.body.removeChild(downloadLink);
								

	
}