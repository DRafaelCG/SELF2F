package com.dim.self2f.clases;

public class ClsSolicitudesPendientes {
	static private ClsSolicitudesPendientes instancia;
	private int iDFolio;
	private String FechaCaptura;
	private String FolioAgencia;
	private String FolioInteligente;
	private String NombreCompleto;
	private String ApellidoPaterno;
	private String ApellidoMaterno;
	private String PrimerNombre;
	private String SegundoNombre;
	private String CEstadoCivil;
	private String CSexo;
	private String FechaNacimiento;
	private String CNacionalidad;
	private String CFormaMigratoria;
	private String RFC;
	private String CalleDomicilio;
	private String NumeroExteriorDomicilio;
	private String NumeroInteriorDomicilio;
	private String Localidad;
	private String CTipovivienda;
	private String ClaveLadaDomicilio;
	private String TelefonoDomicilio;
	private String CTipoProducto;
	private String AntiguedadDomicilio;
	private String CUniversidad;
	private String Campus;
	private String CCarrera;
	private String CFinanciamiento;
	private String PorcentajeBeca;
	private String CGradoEscolar;
	private String IngresoNetoFijo;
	private String IngresoNetoVariable;
	private String ProfesionistasIndependientes;
	private String VentasAnuales;
	private String ClaveLadaEmpleo;
	private String TelefonoEmpleo;
	private String Extension;
	private String COcupacion;
	private String AntiguedadEmpleo;
	private String Celular;
	private String CorreoElectronico;
	//private String SucursalPaperless;
	private String NombreEmpresa;
	private String CalleEmpresa;
	private String NumeroExteriorEmpresa;
	private String NumeroInteriorEmpresa;
	private String LocalidadEmpresa;
	private String EstatusCapturaBasica;
	private String EstatusAutenticacion;
	private String EstatusEvaluacion;
	private String EstatusCapturaReferencias;

	public ClsSolicitudesPendientes() {
		setIDFolio(0);
		setFechaCaptura("");
		setFolioAgencia("");
		setFolioInteligente("");
		setNombreCompleto("");
		setApellidoPaterno("");
		setApellidoMaterno("");
		setPrimerNombre("");
		setSegundoNombre("");
		setCEstadoCivil("");
		setCSexo("");
		setFechaNacimiento("");
		setCNacionalidad("");
		setCFormaMigratoria("");
		setRFC("");
		setCalleDomicilio("");
		setNumeroExteriorDomicilio("");
		setNumeroInteriorDomicilio("");
		setLocalidad("");
		setCTipovivienda("");
		setClaveLadaDomicilio("");
		setTelefonoDomicilio("");
		setCTipoProducto("");
		setAntiguedadDomicilio("");
		setCUniversidad("");
		setCampus("");
		setCCarrera("");
		setCFinanciamiento("");
		setPorcentajeBeca("");
		setCGradoEscolar("");
		setIngresoNetoFijo("");
		setIngresoNetoVariable("");
		setProfesionistasIndependientes("");
		setVentasAnuales("");
		setClaveLadaEmpleo("");
		setTelefonoEmpleo("");
		setExtension("");
		setCOcupacion("");
		setAntiguedadEmpleo("");
		setCelular("");
		setCorreoElectronico("");
		//setSucursalPaperless("");
		setNombreEmpresa("");
		setCalleEmpresa("");
		setNumeroExteriorEmpresa("");
		setNumeroInteriorEmpresa("");
		setLocalidadEmpresa("");
		setEstatusCapturaBasica("");
		setEstatusAutenticacion("");
		setEstatusEvaluacion("");
		setEstatusCapturaReferencias("");	
	}
	
	static public ClsSolicitudesPendientes getInstancia() {
		if (instancia == null) {
			instancia = new ClsSolicitudesPendientes();
		}
		return instancia;
	}

	public int getIDFolio() {
		return iDFolio;
	}

	public void setIDFolio(int iDFolio) {
		this.iDFolio = iDFolio;
	}

	public String getFechaCaptura() {
		return FechaCaptura;
	}

	public void setFechaCaptura(String fechaCaptura) {
		FechaCaptura = fechaCaptura;
	}

	public String getFolioAgencia() {
		return FolioAgencia;
	}

	public void setFolioAgencia(String folioAgencia) {
		FolioAgencia = folioAgencia;
	}

	public String getFolioInteligente() {
		return FolioInteligente;
	}

	public void setFolioInteligente(String folioInteligente) {
		FolioInteligente = folioInteligente;
	}

	public String getNombreCompleto() {
		return NombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		NombreCompleto = nombreCompleto;
	}

	public String getApellidoPaterno() {
		return ApellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		ApellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return ApellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		ApellidoMaterno = apellidoMaterno;
	}

	public String getPrimerNombre() {
		return PrimerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		PrimerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return SegundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		SegundoNombre = segundoNombre;
	}

	public String getCEstadoCivil() {
		return CEstadoCivil;
	}

	public void setCEstadoCivil(String cEstadoCivil) {
		CEstadoCivil = cEstadoCivil;
	}

	public String getCSexo() {
		return CSexo;
	}

	public void setCSexo(String cSexo) {
		CSexo = cSexo;
	}

	public String getFechaNacimiento() {
		return FechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		FechaNacimiento = fechaNacimiento;
	}

	public String getCNacionalidad() {
		return CNacionalidad;
	}

	public void setCNacionalidad(String cNacionalidad) {
		CNacionalidad = cNacionalidad;
	}

	public String getCFormaMigratoria() {
		return CFormaMigratoria;
	}

	public void setCFormaMigratoria(String cFormaMigratoria) {
		CFormaMigratoria = cFormaMigratoria;
	}

	public String getRFC() {
		return RFC;
	}

	public void setRFC(String rFC) {
		RFC = rFC;
	}

	public String getCalleDomicilio() {
		return CalleDomicilio;
	}

	public void setCalleDomicilio(String calleDomicilio) {
		CalleDomicilio = calleDomicilio;
	}

	public String getNumeroExteriorDomicilio() {
		return NumeroExteriorDomicilio;
	}

	public void setNumeroExteriorDomicilio(String numeroExteriorDomicilio) {
		NumeroExteriorDomicilio = numeroExteriorDomicilio;
	}

	public String getNumeroInteriorDomicilio() {
		return NumeroInteriorDomicilio;
	}

	public void setNumeroInteriorDomicilio(String numeroInteriorDomicilio) {
		NumeroInteriorDomicilio = numeroInteriorDomicilio;
	}

	public String getLocalidad() {
		return Localidad;
	}

	public void setLocalidad(String localidad) {
		Localidad = localidad;
	}

	public String getCTipovivienda() {
		return CTipovivienda;
	}

	public void setCTipovivienda(String cTipovivienda) {
		CTipovivienda = cTipovivienda;
	}

	public String getClaveLadaDomicilio() {
		return ClaveLadaDomicilio;
	}

	public void setClaveLadaDomicilio(String claveLadaDomicilio) {
		ClaveLadaDomicilio = claveLadaDomicilio;
	}

	public String getTelefonoDomicilio() {
		return TelefonoDomicilio;
	}

	public void setTelefonoDomicilio(String telefonoDomicilio) {
		TelefonoDomicilio = telefonoDomicilio;
	}

	public String getCTipoProducto() {
		return CTipoProducto;
	}

	public void setCTipoProducto(String cTipoProducto) {
		CTipoProducto = cTipoProducto;
	}

	public String getAntiguedadDomicilio() {
		return AntiguedadDomicilio;
	}

	public void setAntiguedadDomicilio(String antiguedadDomicilio) {
		AntiguedadDomicilio = antiguedadDomicilio;
	}

	public String getCUniversidad() {
		return CUniversidad;
	}

	public void setCUniversidad(String cUniversidad) {
		CUniversidad = cUniversidad;
	}

	public String getCampus() {
		return Campus;
	}

	public void setCampus(String campus) {
		Campus = campus;
	}

	public String getCCarrera() {
		return CCarrera;
	}

	public void setCCarrera(String cCarrera) {
		CCarrera = cCarrera;
	}

	public String getCFinanciamiento() {
		return CFinanciamiento;
	}

	public void setCFinanciamiento(String cFinanciamiento) {
		CFinanciamiento = cFinanciamiento;
	}

	public String getPorcentajeBeca() {
		return PorcentajeBeca;
	}

	public void setPorcentajeBeca(String porcentajeBeca) {
		PorcentajeBeca = porcentajeBeca;
	}

	public String getCGradoEscolar() {
		return CGradoEscolar;
	}

	public void setCGradoEscolar(String cGradoEscolar) {
		CGradoEscolar = cGradoEscolar;
	}

	public String getIngresoNetoFijo() {
		return IngresoNetoFijo;
	}

	public void setIngresoNetoFijo(String ingresoNetoFijo) {
		IngresoNetoFijo = ingresoNetoFijo;
	}

	public String getIngresoNetoVariable() {
		return IngresoNetoVariable;
	}

	public void setIngresoNetoVariable(String ingresoNetoVariable) {
		IngresoNetoVariable = ingresoNetoVariable;
	}

	public String getProfesionistasIndependientes() {
		return ProfesionistasIndependientes;
	}

	public void setProfesionistasIndependientes(
			String profesionistasIndependientes) {
		ProfesionistasIndependientes = profesionistasIndependientes;
	}

	public String getVentasAnuales() {
		return VentasAnuales;
	}

	public void setVentasAnuales(String ventasAnuales) {
		VentasAnuales = ventasAnuales;
	}

	public String getClaveLadaEmpleo() {
		return ClaveLadaEmpleo;
	}

	public void setClaveLadaEmpleo(String claveLadaEmpleo) {
		ClaveLadaEmpleo = claveLadaEmpleo;
	}

	public String getTelefonoEmpleo() {
		return TelefonoEmpleo;
	}

	public void setTelefonoEmpleo(String telefonoEmpleo) {
		TelefonoEmpleo = telefonoEmpleo;
	}

	public String getExtension() {
		return Extension;
	}

	public void setExtension(String extension) {
		Extension = extension;
	}

	public String getCOcupacion() {
		return COcupacion;
	}

	public void setCOcupacion(String cOcupacion) {
		COcupacion = cOcupacion;
	}

	public String getAntiguedadEmpleo() {
		return AntiguedadEmpleo;
	}

	public void setAntiguedadEmpleo(String antiguedadEmpleo) {
		AntiguedadEmpleo = antiguedadEmpleo;
	}

	public String getCelular() {
		return Celular;
	}

	public void setCelular(String celular) {
		Celular = celular;
	}

	public String getCorreoElectronico() {
		return CorreoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		CorreoElectronico = correoElectronico;
	}

	/*public String getSucursalPaperless() {
		return SucursalPaperless;
	}

	public void setSucursalPaperless(String sucursalPaperless) {
		SucursalPaperless = sucursalPaperless;
	}*/

	public String getNombreEmpresa() {
		return NombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		NombreEmpresa = nombreEmpresa;
	}

	public String getCalleEmpresa() {
		return CalleEmpresa;
	}

	public void setCalleEmpresa(String calleEmpresa) {
		CalleEmpresa = calleEmpresa;
	}

	public String getNumeroExteriorEmpresa() {
		return NumeroExteriorEmpresa;
	}

	public void setNumeroExteriorEmpresa(String numeroExteriorEmpresa) {
		NumeroExteriorEmpresa = numeroExteriorEmpresa;
	}

	public String getNumeroInteriorEmpresa() {
		return NumeroInteriorEmpresa;
	}

	public void setNumeroInteriorEmpresa(String numeroInteriorEmpresa) {
		NumeroInteriorEmpresa = numeroInteriorEmpresa;
	}

	public String getLocalidadEmpresa() {
		return LocalidadEmpresa;
	}

	public void setLocalidadEmpresa(String localidadEmpresa) {
		LocalidadEmpresa = localidadEmpresa;
	}

	public String getEstatusCapturaBasica() {
		return EstatusCapturaBasica;
	}

	public void setEstatusCapturaBasica(String estatusCapturaBasica) {
		EstatusCapturaBasica = estatusCapturaBasica;
	}

	public String getEstatusAutenticacion() {
		return EstatusAutenticacion;
	}

	public void setEstatusAutenticacion(String estatusAutenticacion) {
		EstatusAutenticacion = estatusAutenticacion;
	}

	public String getEstatusEvaluacion() {
		return EstatusEvaluacion;
	}

	public void setEstatusEvaluacion(String estatusEvaluacion) {
		EstatusEvaluacion = estatusEvaluacion;
	}

	public String getEstatusCapturaReferencias() {
		return EstatusCapturaReferencias;
	}

	public void setEstatusCapturaReferencias(String estatusCapturaReferencias) {
		EstatusCapturaReferencias = estatusCapturaReferencias;
	}
}