package com.dim.self2f.clases;


public class ClsSolicitud {
	static private ClsSolicitud instancia;
	private int pIDAgencia;
	private String pApellidoPaterno;
	private String pApellidoMaterno;
	private String pPrimerNombre;
	private String pSegundoNombre;
	private String pCEstadoCivil;
	private String pCSexo;
	private String pAñoNacimiento;
	private String pMesNacimiento;
	private String pDiaNacimiento;
	private String pCNacionalidad;
	private String pCFormaMigratoria;
	private String pRFC;
	private String pCalleDomicilio;
	private String pNumeroExteriorDomicilio;
	private String pNumeroInteriorDomicilio;
	private String pClaveLocalidadDomicilio;
	private String pClaveLadaDomicilio;
	private String pTelefonoDomicilio;
	private String pCTipoProducto;
	private boolean isCapturaBasicaCompleta;
	private String mensajeCapturaBasica;
	private String folioInteligente;
	private String iDFolio;
	private boolean pExisteTarjeta;
	private int pIdBanco;
	private String pUltimosCuatroDigitos;
	private boolean pExisteCreditoHipotecario;
	private boolean pExisteCreditoAutomotriz;
	private int pNumeroIntento;
	private boolean hayIntentosRestantes;
	private boolean isCapturaAutenticacionCompleta;
	private boolean isCapturaAutenticacionValida;
	private String mensajeCapAutenticacion;
	private String tipoVivienda;
	private String añosAntiguedad;
	private String ingresoNetoFijo;
	private String ingresoNetoVariable;
	private String ventasAnuales;
	private String profesionistasIndependientes;
	private String ocupacion;
	private String valorOcupacion;
	private String universidad;
	private String campus;
	private String carrera;
	private String tipoFinanciamiento;
	private String beca;
	private String gradoEscolar;
	private String ladaEmpleo;
	private String telEmpleo;
	private String extension;
	private String añosAntiguedadEmpleo;
	private String correoElectronico;
	private String telCelular;
	private boolean isCapturaEvaluacionCompleta;
	private boolean isCapturaEvaluacionValida;
	private String mensajeCapEvaluacion;
	private String nombreEmpresa;
	private String calleEmpresa;
	private String numExteriorEmpresa;
	private String numInteriorEmpresa;
	private String claveLocalidadEmpleo;
	private String apPaternoRef1;
	private String apMaternoRef1;
	private String pNombreRef1;
	private String sNombreRef1;
	private String ladaDomRef1;
	private String telDomRef1;
	private String ladaEmpRef1;
	private String telEmpRef1;
	private String extensionRef1;
	private String telCelRef1;
	private String apPaternoRef2;
	private String apMaternoRef2;
	private String pNombreRef2;
	private String sNombreRef2;
	private String ladaDomRef2;
	private String telDomRef2;
	private String ladaEmpRef2;
	private String telEmpRef2;
	private String extensionRef2;
	private String telCelRef2;
	private boolean isCapturaCierreCompleta;
	private boolean isCapturaCierreValida;
	private String mensajeCapturaCierre;
	private String estatusCapturaBasica;
	private String estatusCapturaAutenticacion;
	private String estatusCapturaEvaluacion;
	private String estatusCapturaCierreVenta;
	/**********************/
	private String fechaCaptura;
	private String folioAgencia;
	private String fechaNacimiento;
	/**********************/
	
	public ClsSolicitud() {
		setPIDAgencia(0);
		setpApellidoPaterno("");
		setpApellidoMaterno("");
		setpPrimerNombre("");
		setpSegundoNombre("");
		setpCEstadoCivil("");
		setpCSexo("");
		setpAñoNacimiento("");
		setpMesNacimiento("");
		setpDiaNacimiento("");
		setpCNacionalidad("");
		setpCFormaMigratoria("");
		setpRFC("");
		setpCalleDomicilio("");
		setpNumeroExteriorDomicilio("");
		setpNumeroInteriorDomicilio("");
		setpClaveLocalidadDomicilio("");
		setpClaveLadaDomicilio("");
		setpTelefonoDomicilio("");
		setpCTipoProducto("");
		isCapturaBasicaCompleta = false;
		setFolioInteligente("");
		setIDFolio("");
		pExisteTarjeta = false;
		setpIdBanco(0);
		setpUltimosCuatroDigitos("");
		pExisteCreditoHipotecario = false;
		pExisteCreditoAutomotriz = false;
		setpNumeroIntento(0);
		hayIntentosRestantes = false; 
		isCapturaAutenticacionCompleta = false;
		isCapturaAutenticacionValida = false;
		setMensajeCapAutenticacion("");
		setTipoVivienda("");
		setAñosAntiguedad("");
		setIngresoNetoFijo("");
		setIngresoNetoVariable("");
		setVentasAnuales("");
		setProfesionistasIndependientes("");
		setOcupacion("");
		setValorOcupacion("");
		setUniversidad("");
		setCampus("");
		setCarrera("");
		setTipoFinanciamiento("");
		setBeca("");
		setGradoEscolar("");
		setLadaEmpleo("");
		setTelEmpleo("");
		setExtension("");
		setAñosAntiguedadEmpleo("");
		setCorreoElectronico("");
		setTelCelular("");
		isCapturaEvaluacionCompleta = false;
		isCapturaEvaluacionValida = false;
		setMensajeCapEvaluacion("");
		setNombreEmpresa("");
		setCalleEmpresa("");
		setNumExteriorEmpresa("");
		setNumInteriorEmpresa("");
		setClaveLocalidadEmpleo("");
		setApPaternoRef1("");
		setApMaternoRef1("");
		setpNombreRef1("");
		setsNombreRef1("");
		setLadaDomRef1("");
		setTelDomRef1("");
		setLadaEmpRef1("");
		setTelEmpRef1("");
		setExtensionRef1("");
		setTelCelRef1("");
		setApPaternoRef2("");
		setApMaternoRef2("");
		setpNombreRef2("");
		setsNombreRef2("");
		setLadaDomRef2("");
		setTelDomRef2("");
		setLadaEmpRef2("");
		setTelEmpRef2("");
		setExtensionRef2("");
		setTelCelRef2("");
		isCapturaCierreCompleta = false;;
		isCapturaCierreValida = false;
		setMensajeCapturaCierre("");
		setEstatusCapturaBasica("");
		setEstatusCapturaAutenticacion("");
		setEstatusCapturaEvaluacion("");
		setEstatusCapturaCierreVenta("");
	}

	public int getPIDAgencia() {
		return pIDAgencia;
	}

	public void setPIDAgencia(int pIDAgencia) {
		this.pIDAgencia = pIDAgencia;
	}

	public String getpApellidoPaterno() {
		return pApellidoPaterno;
	}

	public void setpApellidoPaterno(String pApellidoPaterno) {
		this.pApellidoPaterno = pApellidoPaterno;
	}

	public String getpApellidoMaterno() {
		return pApellidoMaterno;
	}

	public void setpApellidoMaterno(String pApellidoMaterno) {
		this.pApellidoMaterno = pApellidoMaterno;
	}

	public String getpPrimerNombre() {
		return pPrimerNombre;
	}

	public void setpPrimerNombre(String pPrimerNombre) {
		this.pPrimerNombre = pPrimerNombre;
	}

	public String getpSegundoNombre() {
		return pSegundoNombre;
	}

	public void setpSegundoNombre(String pSegundoNombre) {
		this.pSegundoNombre = pSegundoNombre;
	}

	public String getpCEstadoCivil() {
		return pCEstadoCivil;
	}

	public void setpCEstadoCivil(String pCEstadoCivil) {
		this.pCEstadoCivil = pCEstadoCivil;
	}

	public String getpCSexo() {
		return pCSexo;
	}

	public void setpCSexo(String pCSexo) {
		this.pCSexo = pCSexo;
	}

	public String getpAñoNacimiento() {
		return pAñoNacimiento;
	}

	public void setpAñoNacimiento(String pAñoNacimiento) {
		this.pAñoNacimiento = pAñoNacimiento;
	}

	public String getpMesNacimiento() {
		return pMesNacimiento;
	}

	public void setpMesNacimiento(String pMesNacimiento) {
		this.pMesNacimiento = pMesNacimiento;
	}

	public String getpDiaNacimiento() {
		return pDiaNacimiento;
	}

	public void setpDiaNacimiento(String pDiaNacimiento) {
		this.pDiaNacimiento = pDiaNacimiento;
	}

	public String getpCNacionalidad() {
		return pCNacionalidad;
	}

	public void setpCNacionalidad(String pCNacionalidad) {
		this.pCNacionalidad = pCNacionalidad;
	}

	public String getpCFormaMigratoria() {
		return pCFormaMigratoria;
	}

	public void setpCFormaMigratoria(String pCFormaMigratoria) {
		this.pCFormaMigratoria = pCFormaMigratoria;
	}

	public String getpRFC() {
		return pRFC;
	}

	public void setpRFC(String pRFC) {
		this.pRFC = pRFC;
	}

	public String getpCalleDomicilio() {
		return pCalleDomicilio;
	}

	public void setpCalleDomicilio(String pCalleDomicilio) {
		this.pCalleDomicilio = pCalleDomicilio;
	}

	public String getpNumeroExteriorDomicilio() {
		return pNumeroExteriorDomicilio;
	}

	public void setpNumeroExteriorDomicilio(String pNumeroExteriorDomicilio) {
		this.pNumeroExteriorDomicilio = pNumeroExteriorDomicilio;
	}

	public String getpNumeroInteriorDomicilio() {
		return pNumeroInteriorDomicilio;
	}

	public void setpNumeroInteriorDomicilio(String pNumeroInteriorDomicilio) {
		this.pNumeroInteriorDomicilio = pNumeroInteriorDomicilio;
	}

	public String getpClaveLocalidadDomicilio() {
		return pClaveLocalidadDomicilio;
	}

	public void setpClaveLocalidadDomicilio(String pClaveLocalidadDomicilio) {
		this.pClaveLocalidadDomicilio = pClaveLocalidadDomicilio;
	}

	public String getpClaveLadaDomicilio() {
		return pClaveLadaDomicilio;
	}

	public void setpClaveLadaDomicilio(String pClaveLadaDomicilio) {
		this.pClaveLadaDomicilio = pClaveLadaDomicilio;
	}

	public String getpTelefonoDomicilio() {
		return pTelefonoDomicilio;
	}

	public void setpTelefonoDomicilio(String pTelefonoDomicilio) {
		this.pTelefonoDomicilio = pTelefonoDomicilio;
	}

	public String getpCTipoProducto() {
		return pCTipoProducto;
	}

	public void setpCTipoProducto(String pCTipoProducto) {
		this.pCTipoProducto = pCTipoProducto;
	}

	public boolean isCapturaBasicaCompleta() {
		return isCapturaBasicaCompleta;
	}

	public void setCapturaBasicaCompleta(boolean isCapturaBasicaCompleta) {
		this.isCapturaBasicaCompleta = isCapturaBasicaCompleta;
	}
	
	public String getFolioInteligente() {
		return folioInteligente;
	}

	public void setFolioInteligente(String folioInteligente) {
		this.folioInteligente = folioInteligente;
	}

	public String getIDFolio() {
		return iDFolio;
	}

	public void setIDFolio(String iDFolio) {
		this.iDFolio = iDFolio;
	}

	public boolean ispExisteTarjeta() {
		return pExisteTarjeta;
	}

	public void setpExisteTarjeta(boolean pExisteTarjeta) {
		this.pExisteTarjeta = pExisteTarjeta;
	}

	public int getpIdBanco() {
		return pIdBanco;
	}

	public void setpIdBanco(int pIdBanco) {
		this.pIdBanco = pIdBanco;
	}

	public String getpUltimosCuatroDigitos() {
		return pUltimosCuatroDigitos;
	}

	public void setpUltimosCuatroDigitos(String pUltimosCuatroDigitos) {
		this.pUltimosCuatroDigitos = pUltimosCuatroDigitos;
	}

	public boolean ispExisteCreditoHipotecario() {
		return pExisteCreditoHipotecario;
	}

	public void setpExisteCreditoHipotecario(boolean pExisteCreditoHipotecario) {
		this.pExisteCreditoHipotecario = pExisteCreditoHipotecario;
	}

	public boolean ispExisteCreditoAutomotriz() {
		return pExisteCreditoAutomotriz;
	}

	public void setpExisteCreditoAutomotriz(boolean pExisteCreditoAutomotriz) {
		this.pExisteCreditoAutomotriz = pExisteCreditoAutomotriz;
	}

	public int getpNumeroIntento() {
		return pNumeroIntento;
	}

	public void setpNumeroIntento(int pNumeroIntento) {
		this.pNumeroIntento = pNumeroIntento;
	}

	public boolean isCapturaAutenticacionCompleta() {
		return isCapturaAutenticacionCompleta;
	}

	public void setCapturaAutenticacionCompleta(
			boolean isCapturaAutenticacionCompleta) {
		this.isCapturaAutenticacionCompleta = isCapturaAutenticacionCompleta;
	}

	public boolean isCapturaAutenticacionValida() {
		return isCapturaAutenticacionValida;
	}

	public void setCapturaAutenticacionValida(boolean isCapturaAutenticacionValida) {
		this.isCapturaAutenticacionValida = isCapturaAutenticacionValida;
	}

	public String getMensajeCapAutenticacion() {
		return mensajeCapAutenticacion;
	}

	public void setMensajeCapAutenticacion(String mensajeCapAutenticacion) {
		this.mensajeCapAutenticacion = mensajeCapAutenticacion;
	}

	public boolean isHayIntentosRestantes() {
		return hayIntentosRestantes;
	}

	public void setHayIntentosRestantes(boolean hayIntentosRestantes) {
		this.hayIntentosRestantes = hayIntentosRestantes;
	}

	public String getTipoVivienda() {
		return tipoVivienda;
	}

	public void setTipoVivienda(String tipoVivienda) {
		this.tipoVivienda = tipoVivienda;
	}

	public String getAñosAntiguedad() {
		return añosAntiguedad;
	}

	public void setAñosAntiguedad(String añosAntiguedad) {
		this.añosAntiguedad = añosAntiguedad;
	}

	public String getIngresoNetoFijo() {
		return ingresoNetoFijo;
	}

	public void setIngresoNetoFijo(String ingresoNetoFijo) {
		this.ingresoNetoFijo = ingresoNetoFijo;
	}

	public String getIngresoNetoVariable() {
		return ingresoNetoVariable;
	}

	public void setIngresoNetoVariable(String ingresoNetoVariable) {
		this.ingresoNetoVariable = ingresoNetoVariable;
	}

	public String getVentasAnuales() {
		return ventasAnuales;
	}

	public void setVentasAnuales(String ventasAnuales) {
		this.ventasAnuales = ventasAnuales;
	}

	public String getProfesionistasIndependientes() {
		return profesionistasIndependientes;
	}

	public void setProfesionistasIndependientes(
			String profesionistasIndependientes) {
		this.profesionistasIndependientes = profesionistasIndependientes;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getValorOcupacion() {
		return valorOcupacion;
	}

	public void setValorOcupacion(String valorOcupacion) {
		this.valorOcupacion = valorOcupacion;
	}

	public String getUniversidad() {
		return universidad;
	}

	public void setUniversidad(String universidad) {
		this.universidad = universidad;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public String getTipoFinanciamiento() {
		return tipoFinanciamiento;
	}

	public void setTipoFinanciamiento(String tipoFinanciamiento) {
		this.tipoFinanciamiento = tipoFinanciamiento;
	}

	public String getBeca() {
		return beca;
	}

	public void setBeca(String beca) {
		this.beca = beca;
	}

	public String getGradoEscolar() {
		return gradoEscolar;
	}

	public void setGradoEscolar(String gradoEscolar) {
		this.gradoEscolar = gradoEscolar;
	}

	public String getLadaEmpleo() {
		return ladaEmpleo;
	}

	public void setLadaEmpleo(String ladaEmpleo) {
		this.ladaEmpleo = ladaEmpleo;
	}

	public String getTelEmpleo() {
		return telEmpleo;
	}

	public void setTelEmpleo(String telEmpleo) {
		this.telEmpleo = telEmpleo;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getAñosAntiguedadEmpleo() {
		return añosAntiguedadEmpleo;
	}

	public void setAñosAntiguedadEmpleo(String añosAntiguedadEmpleo) {
		this.añosAntiguedadEmpleo = añosAntiguedadEmpleo;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getTelCelular() {
		return telCelular;
	}

	public void setTelCelular(String telCelular) {
		this.telCelular = telCelular;
	}
	
	public boolean isCapturaEvaluacionCompleta() {
		return isCapturaEvaluacionCompleta;
	}

	public void setCapturaEvaluacionCompleta(boolean isCapturaEvaluacionCompleta) {
		this.isCapturaEvaluacionCompleta = isCapturaEvaluacionCompleta;
	}

	public boolean isCapturaEvaluacionValida() {
		return isCapturaEvaluacionValida;
	}

	public void setCapturaEvaluacionValida(boolean isCapturaEvaluacionValida) {
		this.isCapturaEvaluacionValida = isCapturaEvaluacionValida;
	}

	public String getMensajeCapEvaluacion() {
		return mensajeCapEvaluacion;
	}

	public void setMensajeCapEvaluacion(String mensajeCapEvaluacion) {
		this.mensajeCapEvaluacion = mensajeCapEvaluacion;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getCalleEmpresa() {
		return calleEmpresa;
	}

	public void setCalleEmpresa(String calleEmpresa) {
		this.calleEmpresa = calleEmpresa;
	}

	public String getNumExteriorEmpresa() {
		return numExteriorEmpresa;
	}

	public void setNumExteriorEmpresa(String numExteriorEmpresa) {
		this.numExteriorEmpresa = numExteriorEmpresa;
	}

	public String getNumInteriorEmpresa() {
		return numInteriorEmpresa;
	}

	public void setNumInteriorEmpresa(String numInteriorEmpresa) {
		this.numInteriorEmpresa = numInteriorEmpresa;
	}

	public String getClaveLocalidadEmpleo() {
		return claveLocalidadEmpleo;
	}

	public void setClaveLocalidadEmpleo(String claveLocalidadEmpleo) {
		this.claveLocalidadEmpleo = claveLocalidadEmpleo;
	}

	public String getApPaternoRef1() {
		return apPaternoRef1;
	}

	public void setApPaternoRef1(String apPaternoRef1) {
		this.apPaternoRef1 = apPaternoRef1;
	}

	public String getApMaternoRef1() {
		return apMaternoRef1;
	}

	public void setApMaternoRef1(String apMaternoRef1) {
		this.apMaternoRef1 = apMaternoRef1;
	}

	public String getpNombreRef1() {
		return pNombreRef1;
	}

	public void setpNombreRef1(String pNombreRef1) {
		this.pNombreRef1 = pNombreRef1;
	}

	public String getsNombreRef1() {
		return sNombreRef1;
	}

	public void setsNombreRef1(String sNombreRef1) {
		this.sNombreRef1 = sNombreRef1;
	}

	public String getLadaDomRef1() {
		return ladaDomRef1;
	}

	public void setLadaDomRef1(String ladaDomRef1) {
		this.ladaDomRef1 = ladaDomRef1;
	}

	public String getTelDomRef1() {
		return telDomRef1;
	}

	public void setTelDomRef1(String telDomRef1) {
		this.telDomRef1 = telDomRef1;
	}

	public String getLadaEmpRef1() {
		return ladaEmpRef1;
	}

	public void setLadaEmpRef1(String ladaEmpRef1) {
		this.ladaEmpRef1 = ladaEmpRef1;
	}

	public String getTelEmpRef1() {
		return telEmpRef1;
	}

	public void setTelEmpRef1(String telEmpRef1) {
		this.telEmpRef1 = telEmpRef1;
	}

	public String getExtensionRef1() {
		return extensionRef1;
	}

	public void setExtensionRef1(String extensionRef1) {
		this.extensionRef1 = extensionRef1;
	}

	public String getTelCelRef1() {
		return telCelRef1;
	}

	public void setTelCelRef1(String telCelRef1) {
		this.telCelRef1 = telCelRef1;
	}

	public String getApPaternoRef2() {
		return apPaternoRef2;
	}

	public void setApPaternoRef2(String apPaternoRef2) {
		this.apPaternoRef2 = apPaternoRef2;
	}

	public String getApMaternoRef2() {
		return apMaternoRef2;
	}

	public void setApMaternoRef2(String apMaternoRef2) {
		this.apMaternoRef2 = apMaternoRef2;
	}

	public String getpNombreRef2() {
		return pNombreRef2;
	}

	public void setpNombreRef2(String pNombreRef2) {
		this.pNombreRef2 = pNombreRef2;
	}

	public String getsNombreRef2() {
		return sNombreRef2;
	}

	public void setsNombreRef2(String sNombreRef2) {
		this.sNombreRef2 = sNombreRef2;
	}

	public String getLadaDomRef2() {
		return ladaDomRef2;
	}

	public void setLadaDomRef2(String ladaDomRef2) {
		this.ladaDomRef2 = ladaDomRef2;
	}

	public String getTelDomRef2() {
		return telDomRef2;
	}

	public void setTelDomRef2(String telDomRef2) {
		this.telDomRef2 = telDomRef2;
	}

	public String getLadaEmpRef2() {
		return ladaEmpRef2;
	}

	public void setLadaEmpRef2(String ladaEmpRef2) {
		this.ladaEmpRef2 = ladaEmpRef2;
	}

	public String getTelEmpRef2() {
		return telEmpRef2;
	}

	public void setTelEmpRef2(String telEmpRef2) {
		this.telEmpRef2 = telEmpRef2;
	}

	public String getExtensionRef2() {
		return extensionRef2;
	}

	public void setExtensionRef2(String extensionRef2) {
		this.extensionRef2 = extensionRef2;
	}

	public String getTelCelRef2() {
		return telCelRef2;
	}

	public void setTelCelRef2(String telCelRef2) {
		this.telCelRef2 = telCelRef2;
	}
	
	static public ClsSolicitud getInstancia() {
		if (instancia == null) {
			instancia = new ClsSolicitud();
		}
		return instancia;
	}

	public boolean isCapturaCierreCompleta() {
		return isCapturaCierreCompleta;
	}

	public void setCapturaCierreCompleta(boolean isCapturaCierreCompleta) {
		this.isCapturaCierreCompleta = isCapturaCierreCompleta;
	}

	public boolean isCapturaCierreValida() {
		return isCapturaCierreValida;
	}

	public void setCapturaCierreValida(boolean isCapturaCierreValida) {
		this.isCapturaCierreValida = isCapturaCierreValida;
	}

	public String getMensajeCapturaCierre() {
		return mensajeCapturaCierre;
	}

	public void setMensajeCapturaCierre(String mensajeCapturaCierre) {
		this.mensajeCapturaCierre = mensajeCapturaCierre;
	}

	public String getMensajeCapturaBasica() {
		return mensajeCapturaBasica;
	}

	public void setMensajeCapturaBasica(String mensajeCapturaBasica) {
		this.mensajeCapturaBasica = mensajeCapturaBasica;
	}

	public String getEstatusCapturaBasica() {
		return estatusCapturaBasica;
	}

	public void setEstatusCapturaBasica(String statusCapturaBasica) {
		this.estatusCapturaBasica = statusCapturaBasica;
	}

	public String getEstatusCapturaAutenticacion() {
		return estatusCapturaAutenticacion;
	}

	public void setEstatusCapturaAutenticacion(String estatusCapturaAutenticacion) {
		this.estatusCapturaAutenticacion = estatusCapturaAutenticacion;
	}

	public String getEstatusCapturaEvaluacion() {
		return estatusCapturaEvaluacion;
	}

	public void setEstatusCapturaEvaluacion(String estatusCapturaEvaluacion) {
		this.estatusCapturaEvaluacion = estatusCapturaEvaluacion;
	}

	public String getEstatusCapturaCierreVenta() {
		return estatusCapturaCierreVenta;
	}

	public void setEstatusCapturaCierreVenta(String estatusCapturaCierreVenta) {
		this.estatusCapturaCierreVenta = estatusCapturaCierreVenta;
	}

	public String getFechaCaptura() {
		return fechaCaptura;
	}

	public void setFechaCaptura(String fechaCaptura) {
		this.fechaCaptura = fechaCaptura;
	}

	public String getFolioAgencia() {
		return folioAgencia;
	}

	public void setFolioAgencia(String folioAgencia) {
		this.folioAgencia = folioAgencia;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
}