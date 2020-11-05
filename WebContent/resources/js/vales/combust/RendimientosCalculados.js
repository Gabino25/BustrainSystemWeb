/**
 * 
 */

$(document).ready(function() {
	
	 $('#TablRO').DataTable( {
		 "order": [[ 0, "asc" ],[ 1, "asc" ]],
        "language": { "url": "/BustrainSystemWeb/resources/json/Spanish.json"},
        "columns": [
    	    { orderable: true,searchable:true  },   /*(1)*/
    	    { orderable: true,searchable:false },  /*(2)*/
    	    { orderable: false,searchable:false },  /*(3)*/
    	    { orderable: false,searchable:false },  /*(4)*/
    	    { orderable: false,searchable:false },  /*(5)*/
    	    { orderable: false,searchable:false }  /*(6)*/
    	  ]
 });
	
});

