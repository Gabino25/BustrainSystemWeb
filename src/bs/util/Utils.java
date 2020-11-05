package bs.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import bs.ap.dao.ConceptosDao;
import bs.ap.dao.CuentasOperacionDao;
import bs.ap.dao.FacturasDao;
import bs.ap.jdbc.ConceptosDaoImpl;
import bs.ap.jdbc.CuentasOperacionDaoImpl;
import bs.ap.jdbc.FacturasDaoImpl;
import bs.catalogo.dao.CatAutobusDao;
import bs.catalogo.dao.CatCelularesDao;
import bs.catalogo.dao.CatClientesDao;
import bs.catalogo.dao.CatProveedoresDao;
import bs.catalogo.dao.CatRutasDao;
import bs.catalogo.dao.CatTrabajadoresDao;
import bs.catalogo.dao.GasolineraDao;
import bs.catalogo.jdbc.CatAutobusDaoImpl;
import bs.catalogo.jdbc.CatCelularesDaoImpl;
import bs.catalogo.jdbc.CatClientesDaoImpl;
import bs.catalogo.jdbc.CatProveedoresDaoImpl;
import bs.catalogo.jdbc.CatRutasDaoImpl;
import bs.catalogo.jdbc.CatTrabajadoresDaoImpl;
import bs.catalogo.jdbc.GasolineraDaoImpl;
import bs.fallas.entry.dao.FallasDao;
import bs.fallas.entry.jdbc.FallasDaoImpl;
import bs.llantas.entry.dao.LlantasDao;
import bs.llantas.entry.dao.MovimientosLlantasDao;
import bs.llantas.entry.jdbc.LlantasDaoImpl;
import bs.llantas.entry.jdbc.MovimientosLlantasDaoImpl;
import bs.util.dao.XxbslookupsDao;
import bs.util.jdbc.XxbslookupsDaoImpl;
import bs.admin.entry.dao.PermisosDao;
import bs.admin.entry.dao.UsuarioDao;
import bs.admin.entry.dto.Pantallas;
import bs.admin.entry.jdbc.PermisosDaoImpl;
import bs.admin.entry.jdbc.UsuarioDaoImpl;
import bs.vales.combust.entry.dao.DieselDao;
import bs.vales.combust.entry.jdbc.DieselDaoImpl;
import bs.vales.transpt.assign.dao.AsignacionValesDao;
import bs.vales.transpt.assign.jdbc.AsignacionValesDaoImpl;
import bs.vales.transpt.entry.dao.ValesDao;
import bs.vales.transpt.entry.jdbc.ValesDaoImpl;


public class Utils {
	
	 /*Utilizamos el patron singleton, solo existen un objeto de tipo UsuarioService en Memoria */
	 private static String ddMMyyyyPattern = "dd-MM-yyyy";
	 private static String ddMMyyyyPatternV2 = "dd/MM/yyyy";
	 private static String ddMMyyyyPatternV3 = "dd/MM/yyyy HH:mm:ss";
	 private static String ddMMyyyyPatternV4 = "dd'-'MMMM'-'yyyy"; 
	 private static String yyyyMMddPattern = "yyyy-MM-dd"; 
	 private static String ddMMMyyyyPattern = "dd-MMM-yyyy";
	 private static String hhmmssaPatter="hh:mm:ss a";
	 private static String hhmmPatter="hh:mm";
	 private static SimpleDateFormat ddMMyyyysdfInstance;  
	 private static SimpleDateFormat ddMMyyyysdfInstanceV2;  
	 private static SimpleDateFormat ddMMyyyysdfInstanceV3;  
	 private static SimpleDateFormat ddMMyyyysdfInstanceV4;  
	 private static SimpleDateFormat yyyyMMddsdfInstance; 
	 private static SimpleDateFormat hhmmssasdfInstance;
	 private static SimpleDateFormat hhmmsdfInstance;
	 private static SimpleDateFormat ddMMMyyyysdfInstance;
	 private static CatTrabajadoresDao CatTrabajadoresInstance;
	 private static CatCelularesDao CatCelularesInstance;
	 private static UsuarioDao UsuarioInstance;
	 private static PermisosDao PermisosInstance;
	 private static CatClientesDao  CatClientesInstance;
	 private static CatProveedoresDao CatProveedoresInstance;
	 private static CatAutobusDao CatAutobusInstance;
	 private static CatRutasDao CatRutasInstance; 
	 private static AsignacionValesDao AsignacionValesInstance;
	 private static ValesDao ValesInstance;
	 private static DieselDao DieselInstance;
	 private static GasolineraDao GasolineraInstance; 
	 private static LlantasDao LlantasInstance;
	 private static MovimientosLlantasDao MovimientosLlantasInstance;
	 private static FallasDao FallasInstance; 
	 private static FacturasDao FacturasInstance; 
	 private static ConceptosDao ConceptosFacturasInstance; 
	 private static CuentasOperacionDao CuentasOperacionInstance;
	 private static XxbslookupsDao  XxbslookupsInstance; 
	 
	 private static Locale enUSLocaleInstance;
	 private static NumberFormat enUSNumberFormatInstance; 
	 private final static String enUSPattern = "###,###.00";
	 private final static String enUSPatternV2 = "###,###.000";
	 private final static String enUSPatternV3 = "###,##0,000.00";
	 private final static String enUSPatternV4 = "###,###,000.00";
	
     private static DecimalFormat enUSDecimalFormatInstance; 
     private static DecimalFormat enUSDecimalFormatInstanceV2; 
     private static DecimalFormat enUSDecimalFormatInstanceV3; 
     private static DecimalFormat enUSDecimalFormatInstanceV4;
     
     
     private static List<Pantallas> listPantallas;
     
     public static DecimalFormat getenUSDecimalFormatInstance() {
    	 if(null==enUSDecimalFormatInstance) {
			  synchronized(Utils.class) {
			  if(null==enUSDecimalFormatInstance) {
				  NumberFormat numberFormat = getenUSFormatInstance(); 
				  enUSDecimalFormatInstance = (DecimalFormat)numberFormat;
				  enUSDecimalFormatInstance.applyPattern(enUSPattern);
			  }
			 }
		 }
    	 return enUSDecimalFormatInstance; 
     }
     
     public static DecimalFormat getenUSDecimalFormatInstanceV2() {
    	 if(null==enUSDecimalFormatInstanceV2) {
			  synchronized(Utils.class) {
			  if(null==enUSDecimalFormatInstanceV2) {
				  NumberFormat numberFormat = getenUSFormatInstance(); 
				  enUSDecimalFormatInstanceV2 = (DecimalFormat)numberFormat;
				  enUSDecimalFormatInstanceV2.applyPattern(enUSPatternV2);
			  }
			 }
		 }
    	 return enUSDecimalFormatInstanceV2; 
     }
     
     public static DecimalFormat getenUSDecimalFormatInstanceV3() {
    	 if(null==enUSDecimalFormatInstanceV3) {
			  synchronized(Utils.class) {
			  if(null==enUSDecimalFormatInstanceV3) {
				  NumberFormat numberFormat = getenUSFormatInstance(); 
				  enUSDecimalFormatInstanceV3 = (DecimalFormat)numberFormat;
				  enUSDecimalFormatInstanceV3.applyPattern(enUSPatternV3);
			  }
			 }
		 }
    	 return enUSDecimalFormatInstanceV3; 
     }
	 
     public static DecimalFormat getenUSDecimalFormatInstanceV4() {
    	 if(null==enUSDecimalFormatInstanceV4) {
			  synchronized(Utils.class) {
			  if(null==enUSDecimalFormatInstanceV4) {
				  NumberFormat numberFormat = getenUSFormatInstance(); 
				  enUSDecimalFormatInstanceV4 = (DecimalFormat)numberFormat;
				  enUSDecimalFormatInstanceV4.applyPattern(enUSPatternV4);
			  }
			 }
		 }
    	 return enUSDecimalFormatInstanceV4; 
     }
     
	 public static NumberFormat getenUSFormatInstance() {
		 if(null==enUSNumberFormatInstance) {
			  synchronized(Utils.class) {
			  if(null==enUSNumberFormatInstance) {
				  
				  if(null==enUSLocaleInstance) {
					  synchronized(Utils.class) {
					  if(null==enUSLocaleInstance) {
						  enUSLocaleInstance = new Locale("en", "US");
					  }
					 }
				   }
				  
				  enUSNumberFormatInstance = NumberFormat.getInstance(enUSLocaleInstance); 
			  }
			 }
		 }
		 return enUSNumberFormatInstance; 
	 }
	 
	 
	 public static SimpleDateFormat getddMMyyyysdfInstance() {
		 if(null==ddMMyyyysdfInstance) {
			  synchronized(Utils.class) {
			  if(null==ddMMyyyysdfInstance) {
				  ddMMyyyysdfInstance = new SimpleDateFormat(ddMMyyyyPattern);
			  }
			 }
		 }
		 return ddMMyyyysdfInstance; 
	 }
	
	 public static SimpleDateFormat getddMMyyyysdfInstanceV2() {
		 if(null==ddMMyyyysdfInstanceV2) {
			  synchronized(Utils.class) {
			  if(null==ddMMyyyysdfInstanceV2) {
				  ddMMyyyysdfInstanceV2 = new SimpleDateFormat(ddMMyyyyPatternV2);
			  }
			 }
		 }
		 return ddMMyyyysdfInstanceV2; 
	 }
	 
	 public static SimpleDateFormat getddMMyyyysdfInstanceV3() {
		 if(null==ddMMyyyysdfInstanceV3) {
			  synchronized(Utils.class) {
			  if(null==ddMMyyyysdfInstanceV3) {
				  ddMMyyyysdfInstanceV3 = new SimpleDateFormat(ddMMyyyyPatternV3);
			  }
			 }
		 }
		 return ddMMyyyysdfInstanceV3; 
	 }
	 
	 public static SimpleDateFormat getddMMyyyysdfInstanceV4() {
		 if(null==ddMMyyyysdfInstanceV4) {
			  synchronized(Utils.class) {
			  if(null==ddMMyyyysdfInstanceV4) {
				  ddMMyyyysdfInstanceV4 = new SimpleDateFormat(ddMMyyyyPatternV4, new Locale("ES"));
			  }
			 }
		 }
		 return ddMMyyyysdfInstanceV4; 
	 }
	 
	 public static SimpleDateFormat getyyyyMMddsdfInstance() {
		 if(null==yyyyMMddsdfInstance) {
			  synchronized(Utils.class) {
			  if(null==yyyyMMddsdfInstance) {
				  yyyyMMddsdfInstance = new SimpleDateFormat(yyyyMMddPattern);
			  }
			 }
		 }
		 return yyyyMMddsdfInstance; 
	 }
	 public static SimpleDateFormat gethhmmssasdfInstance() {
		 if(null==hhmmssasdfInstance) {
			  synchronized(Utils.class) {
			  if(null==hhmmssasdfInstance) {
				  hhmmssasdfInstance = new SimpleDateFormat(hhmmssaPatter);
			  }
			 }
		 }
		 return hhmmssasdfInstance; 
	 }
	 public static SimpleDateFormat gethhmmsdfInstance() {
		 if(null==hhmmsdfInstance) {
			  synchronized(Utils.class) {
			  if(null==hhmmsdfInstance) {
				  hhmmsdfInstance = new SimpleDateFormat(hhmmPatter);
			  }
			 }
		 }
		 return hhmmsdfInstance; 
	 }
	 public static SimpleDateFormat getddMMMyyyysdfInstance() {
		 if(null==ddMMMyyyysdfInstance) {
			  synchronized(Utils.class) {
			  if(null==ddMMMyyyysdfInstance) {
				  ddMMMyyyysdfInstance = new SimpleDateFormat(ddMMMyyyyPattern);
			  }
			 }
		 }
		 return ddMMMyyyysdfInstance; 
	 }
   /*Creamos una nueva y unica instancia si es que no existe */
     public static CatTrabajadoresDao getCatTrabajadoresInstance() {
       if (CatTrabajadoresInstance == null) {
    	   synchronized(Utils.class) {
    		if(CatTrabajadoresInstance==null) {
    			CatTrabajadoresInstance = new CatTrabajadoresDaoImpl();
    		}
    	   }
       }
       return CatTrabajadoresInstance;
     }
   
     public static CatCelularesDao getCatCelularesInstance() {
         if (CatCelularesInstance == null) {
      	   synchronized(Utils.class) {
      		if(CatCelularesInstance==null) {
    			CatCelularesInstance = new CatCelularesDaoImpl();
      		}
      	   }
         }
         return CatCelularesInstance;
       }
     
     
     public static UsuarioDao getUsuarioInstance() {
    	 if(UsuarioInstance==null) {
    		 synchronized(Utils.class) {
    			 if(UsuarioInstance==null) {
    				 UsuarioInstance = new UsuarioDaoImpl();
    			 }
    		 }
    	 }
    	return  UsuarioInstance; 
     }
     
     public static PermisosDao getPermisosInstance() {
    	 if(PermisosInstance==null) {
    		 synchronized(Utils.class) {
    			 if(PermisosInstance==null) {
    				 PermisosInstance = new PermisosDaoImpl();
    			 }
    		 }
    	 }
    	return  PermisosInstance; 
     }
     
     public static CatClientesDao getCatClientesInstance() {
    	 if(CatClientesInstance==null) {
    		synchronized(Utils.class) {
    		 if(CatClientesInstance==null) {
    			 CatClientesInstance = new CatClientesDaoImpl();
    		 }
    		} 
    	 }
    	 return CatClientesInstance;
     }
     
     public static CatProveedoresDao getCatProveedoresInstance() {
    	 if(CatProveedoresInstance==null) {
    		synchronized(Utils.class) {
    		 if(CatProveedoresInstance==null) {
    			 CatProveedoresInstance = new CatProveedoresDaoImpl();
    		 }
    		} 
    	 }
    	 return CatProveedoresInstance;
     }
     
     public static CatAutobusDao getCatAutobusInstance() {
    	 if(CatAutobusInstance==null) {
    		synchronized(Utils.class) {
    		 if(CatAutobusInstance==null) {
    			 CatAutobusInstance = new CatAutobusDaoImpl();
    		 }
    		} 
    	 }
    	 return CatAutobusInstance;
     }
     
     public static CatRutasDao getCatRutasInstance() {
    	 if(CatRutasInstance==null) {
    		synchronized(Utils.class) {
    		 if(CatRutasInstance==null) {
    			 CatRutasInstance = new CatRutasDaoImpl();
    		 }
    		} 
    	 }
    	 return CatRutasInstance;
     }
     
     public static AsignacionValesDao getAsignacionValesInstance() {
    	 if(AsignacionValesInstance==null) {
    		synchronized(Utils.class) {
    		 if(AsignacionValesInstance==null) {
    			 AsignacionValesInstance = new AsignacionValesDaoImpl();
    		 }
    		} 
    	 }
    	 return AsignacionValesInstance;
     }
     
     public static ValesDao getValesInstance() {
    	 if(ValesInstance==null) {
    		synchronized(Utils.class) {
    		 if(ValesInstance==null) {
    			 ValesInstance = new ValesDaoImpl();
    		 }
    		} 
    	 }
    	 return ValesInstance;
     }
     
     public static DieselDao getDieselInstance() {
    	 if(DieselInstance==null) {
    		synchronized(Utils.class) {
    		 if(DieselInstance==null) {
    			 DieselInstance = new DieselDaoImpl();
    		 }
    		} 
    	 }
    	 return DieselInstance;
     }
     
     
     public static GasolineraDao getGasolineraInstance() {
    	 if(GasolineraInstance==null) {
    		synchronized(Utils.class) {
    		 if(GasolineraInstance==null) {
    			 GasolineraInstance = new GasolineraDaoImpl();
    		 }
    		} 
    	 }
    	 return GasolineraInstance;
     }
     
     public static LlantasDao getLlantasInstance() {
    	 if(LlantasInstance==null) {
    		synchronized(Utils.class) {
    		 if(LlantasInstance==null) {
    			 LlantasInstance = new LlantasDaoImpl();
    		 }
    		} 
    	 }
    	 return LlantasInstance;
     }
     
     public static MovimientosLlantasDao getMovimientosLlantasInstance() {
    	 if(MovimientosLlantasInstance==null) {
    		synchronized(Utils.class) {
    		 if(MovimientosLlantasInstance==null) {
    			 MovimientosLlantasInstance = new MovimientosLlantasDaoImpl();
    		 }
    		} 
    	 }
    	 return MovimientosLlantasInstance;
     }
     
     public static FallasDao getFallasInstance() {
    	 if(FallasInstance==null) {
    		synchronized(Utils.class) {
    		 if(FallasInstance==null) {
    			 FallasInstance = new FallasDaoImpl();
    		 }
    		} 
    	 }
    	 return FallasInstance;
     }
     
     public static FacturasDao getFacturasInstance() {
    	 if(FacturasInstance==null) {
    		synchronized(Utils.class) {
    		 if(FacturasInstance==null) {
    			 FacturasInstance = new FacturasDaoImpl();
    		 }
    		} 
    	 }
    	 return FacturasInstance;
     }
     
     public static ConceptosDao getConceptosFacturasInstance() {
    	 if(ConceptosFacturasInstance==null) {
    		synchronized(Utils.class) {
    		 if(ConceptosFacturasInstance==null) {
    			 ConceptosFacturasInstance = new ConceptosDaoImpl();
    		 }
    		} 
    	 }
    	 return ConceptosFacturasInstance;
     }
     
     public static CuentasOperacionDao getCuentasOperacionInstance() {
    	 if(CuentasOperacionInstance==null) {
    		synchronized(Utils.class) {
    		 if(CuentasOperacionInstance==null) {
    			 CuentasOperacionInstance = new CuentasOperacionDaoImpl();
    		 }
    		} 
    	 }
    	 return CuentasOperacionInstance;
     }
     
     public static XxbslookupsDao getXxbslookupsInstance() {
    	 if(XxbslookupsInstance==null) {
    		synchronized(Utils.class) {
    		 if(XxbslookupsInstance==null) {
    			 XxbslookupsInstance = new XxbslookupsDaoImpl();
    		 }
    		} 
    	 }
    	 return XxbslookupsInstance;
     }

 	public static List<Pantallas> getPantallas() {
 		if(null==listPantallas) {
 			synchronized(Utils.class) {
 				if(listPantallas==null) {
 					listPantallas = new ArrayList<Pantallas>();
 			 		listPantallas.add(new Pantallas("LICENCIAS TRABAJADORES"));
 			 		listPantallas.add(new Pantallas("SEMAFORO TALLER"));
 			 		listPantallas.add(new Pantallas("SEMAFORO AGENCIA"));
 			 		
 			 		listPantallas.add(new Pantallas("CATALOGO CLIENTES"));
 			 		listPantallas.add(new Pantallas("CATALOGO PROVEEDORES"));
 			 		listPantallas.add(new Pantallas("CATALOGO TRABAJADORES"));
 			 		listPantallas.add(new Pantallas("CATALOGO CELULARES"));
 			 		listPantallas.add(new Pantallas("CATALOGO UNIDADES"));
 			 		listPantallas.add(new Pantallas("CATALOGO RUTAS"));
 			 		
 			 		listPantallas.add(new Pantallas("VALES CAPTURA"));
 			 		listPantallas.add(new Pantallas("VALES LISTADO"));
 			 		
 			 		listPantallas.add(new Pantallas("COMBUSTIBLE ANALIZAR"));
 			 		listPantallas.add(new Pantallas("COMBUSTIBLE CARGAS"));
 			 		listPantallas.add(new Pantallas("COMBUSTIBLE RENDIMIENTOS"));
 			 		
 			 		listPantallas.add(new Pantallas("SERVICIO PREVENTIVO"));
 			 		listPantallas.add(new Pantallas("SERVICIO CORRECTIVO"));
 			 		listPantallas.add(new Pantallas("SERVICIO REPORTE DE FALLAS"));
 			 		listPantallas.add(new Pantallas("SERVICIO ANALIZAR"));
 			 		
 			 		listPantallas.add(new Pantallas("COMPRAS CAPTURAR FACTURAS"));
 			 		listPantallas.add(new Pantallas("COMPRAS FILTRADO FACTURAS"));
 			 		listPantallas.add(new Pantallas("COMPRAS CAPTURAR CUENTAS"));
 			 		
 			 		listPantallas.add(new Pantallas("ASIGNACION VALES"));
 			 		
 			 		listPantallas.add(new Pantallas("LLANTAS ADMINISTRAR"));
 			 		listPantallas.add(new Pantallas("LLANTAS ASIGNACION"));
 			 		listPantallas.add(new Pantallas("LLANTAS PROFUNDIDAD"));
 			 		listPantallas.add(new Pantallas("LLANTAS REPARACIONES"));
 			 		listPantallas.add(new Pantallas("LLANTAS ANALIZAR"));
 			 		listPantallas.add(new Pantallas("LLANTAS BAJAS"));
 			 		
 			 		listPantallas.add(new Pantallas("ADMINISTRAR USUARIOS"));
 			 		listPantallas.add(new Pantallas("ADMINISTRAR PERMISOS"));
 			 		
 			 	}
 			}
 		}
 		return listPantallas; 
 	}
    
 	public static String getMes(int pIntMes) {
 		String strRetval = ""; 
 		if(1==pIntMes) {
 			strRetval = "Ene"; 
 		}else if(2==pIntMes) {
 			strRetval = "Feb"; 
 		}else if(3==pIntMes) {
 			strRetval = "Mar"; 
 		}else if(4==pIntMes) {
 			strRetval = "Abr"; 
 		}else if(5==pIntMes) {
 			strRetval = "May"; 
 		}else if(6==pIntMes) {
 			strRetval = "Jun"; 
 		}else if(7==pIntMes) {
 			strRetval = "Jul"; 
 		}else if(8==pIntMes) {
 			strRetval = "Ago"; 
 		}else if(9==pIntMes) {
 			strRetval = "Sep"; 
 		}else if(10==pIntMes) {
 			strRetval = "Oct"; 
 		}else if(11==pIntMes) {
 			strRetval = "Nov"; 
 		}else if(12==pIntMes) {
 			strRetval = "Dic"; 
 		}
 		return strRetval; 
 	}
 	
}
