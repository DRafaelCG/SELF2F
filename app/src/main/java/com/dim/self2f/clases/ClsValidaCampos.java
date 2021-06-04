package com.dim.self2f.clases;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.util.Log;

public class ClsValidaCampos {
	ClsSolicitud solicitud = ClsSolicitud.getInstancia();
	ClsUsuarioF2F usuario = ClsUsuarioF2F.getInstancia();
	ClsMensaje mensaje;
	private int year;
	private int month;
	private int day;
	public ClsValidaCampos() {
		 mensaje = ClsMensaje.getInstancia();
	}

	public Boolean validaCampoVacioYReguexNombresApellidos(String campo, String nombreCampo) {
		boolean valido = false;
		if (((campo.toString().matches("")) || (campo.toString().equals(""))) 
				&& ((nombreCampo.toString().matches("Segundo Nombre")) 
						|| (nombreCampo.toString().matches("Apellido Materno"))) ) {
			valido = true;
		}else if ((campo.toString().matches("")) || (campo.toString().equals(""))) {
			mensaje.setMensaje("El Campo \""+ nombreCampo + "\" es obligatorio");
		}else {
			if (campo.length() > 2) {
				String reguexValido = "^[A-Z ]{3,20}$";
				if (campo.toString().matches(reguexValido)) {
					valido = true;
					if (nombreCampo.toString().matches("Apellido Paterno")) {
						solicitud.setpApellidoPaterno(campo.toString().toUpperCase());
					}else if (nombreCampo.toString().matches("Apellido Materno")) {
						solicitud.setpApellidoMaterno(campo.toString().toUpperCase());
					}else if(nombreCampo.toString().matches("Primer Nombre")){
						solicitud.setpPrimerNombre(campo.toString().toUpperCase());
					}else if (nombreCampo.toString().matches("Segundo Nombre")) {
						solicitud.setpSegundoNombre(campo.toString().toUpperCase());
					}
				}else {
					mensaje.setMensaje("El Campo \"" + nombreCampo + "\" sólo permite letras y espacios, no introducir caracteres especiales");
				}
			}	
		}
		return valido;
	}
	
	public Boolean validaUsuarioContrasenia(String usuario, String contrasenia) {
		boolean valido = false;
		if (usuario.toString().matches("") && contrasenia.toString().matches("")) {
			mensaje.setMensaje("Es necesario introducir nombre de usuario y contraseña asociada");
		}else {
			if (usuario.toString().matches("")) {
				mensaje.setMensaje("Es necesario introducir nombre de usuario");
			}else {
				if (contrasenia.toString().matches("")) {
					mensaje.setMensaje("Es necesario introducir su contraseña asociada");
				}else {
					valido = true;
/*					if (!usuario.toString().matches("^[a-zA-Z0-9][a-zA-Z0-9.]{6,19}$")) {
						mensaje.setMensaje(" El campo \"Usuario\" sólo permite letras, números y puntos ");
					}else {
						if (!contrasenia.toString().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@.()])[a-zA-Z0-9@.()]{8,20}$")) {
							mensaje.setMensaje("El campo \"Contraseña\" sólo permite letras, números y \"@\", \".\", \")\", \"(\"");
						}else {
							valido = true;		
						}
					}*/
				}
			}
		}
		return valido;
	}
	
	public Boolean validaEdad(String fechaNacimiento) {
		setFechaActual();
		boolean valido = false;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		fechaNacimiento = fechaNacimiento.toString().replaceAll("/", "-");
		String fechaActual = "";
		if (month < 10) {
			if (day < 10) {
				fechaActual =  String.valueOf(year) + "-0" + String.valueOf(month + 1) + "-" + "0"+String.valueOf(day);
			}else {
				fechaActual =  String.valueOf(year) + "-0" + String.valueOf(month + 1) + "-" + String.valueOf(day);
			}
		}else {
			if (day < 10) {
				fechaActual =  String.valueOf(year)  + "-" + String.valueOf(month + 1) + "-" + "0"+String.valueOf(day);
			}else {
				fechaActual =  String.valueOf(year) + "-" + String.valueOf(month + 1) + "-" + String.valueOf(day);
			}
		}
		try {
			Date date = format.parse(fechaNacimiento);
			Date date2 = format.parse(fechaActual); 
			Calendar fNac = getCalendar(date);
			Calendar fActual = getCalendar(date2);
			int diffYear = fActual.get(Calendar.YEAR) - fNac.get(Calendar.YEAR);
			int diffMonth = fActual.get(Calendar.MONTH) - fNac.get(Calendar.MONTH);
			int diffDay = fActual.get(Calendar.DAY_OF_MONTH) - fNac.get(Calendar.DAY_OF_MONTH);
			if (fNac.get(Calendar.MONTH) > fActual.get(Calendar.MONTH) || 
					fNac.get(Calendar.MONTH) == fActual.get(Calendar.MONTH) && fNac.get(Calendar.DATE) > fActual.get(Calendar.DATE)) {
				diffYear--;
			}
			if (diffYear > 70) {
					mensaje.setMensaje("Debe ser menor de 70 años");
				valido = false;
			}else if (diffYear < 18) {
				mensaje.setMensaje("Debe ser mayor de edad");
				valido = false;
			}else if (diffYear == 18) {
				if (diffMonth < 0) {
					mensaje.setMensaje("Debe ser mayor de edad");
					valido = false;
				}else{
					if (diffDay < 0) {
						mensaje.setMensaje("Debe ser mayor de edad");
						valido = false;
					}else {
						valido = true;
						solicitud.setpAñoNacimiento(fechaNacimiento.substring(0, 4).toString());
						solicitud.setpMesNacimiento(fechaNacimiento.substring(5, 7).toString());
						solicitud.setpDiaNacimiento(fechaNacimiento.substring(8, 10).toString());
					}
				}
			}else {
				valido = true;
			}
		} catch (Exception e) {
			Log.d("Error validando edad", e.getMessage());
		}
		return valido;
	}
	
	public void setFechaActual(){
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
	}
	
	public static Calendar getCalendar(Date date){
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(date);
		return cal;
	}
	
	public Boolean validaRFC(String rfc) {
		boolean  valido = false;
		if (!rfc.toString().matches("")) {
			if (rfc.toString().matches("^[A-Z]{3,4}(\\d{6})((\\D|\\d){3})?$")) {
				valido = true;
				solicitud.setpRFC(rfc.toString().toUpperCase());
			}else {
				mensaje.setMensaje("El Campo \"RFC\" no tiene formato correcto");
			}
		}else {
			mensaje.setMensaje("El campo \"RFC\" es obligatorio");
		}
		return valido;
	}
	
	public Boolean validaCalle(String calle) {
		boolean valido = false;
		if (!calle.toString().matches("")) {
			if (calle.toString().matches("^[A-Z 0-9]{2,34}$")) {
				valido = true;
				solicitud.setpCalleDomicilio(calle.toString().toUpperCase());
			}else {
				mensaje.setMensaje("El campo \"Calle\" sólo permite letras, números y espacios, no introducir caracteres especiales");
			}
		}else {
			mensaje.setMensaje("El campo \"Calle\" es obligatorio");
		}
		return valido;
	}
	
	public Boolean validaNumExterior(String numExterior) {
		boolean valido = false;
		if (!numExterior.toString().matches("")) {
			if (numExterior.toString().matches("^[A-Z 0-9]{1,8}$")) {
				valido = true;
				solicitud.setpNumeroExteriorDomicilio(numExterior.toString().toUpperCase());
			}else {
				mensaje.setMensaje("El campo \"Número Exterior\" sólo permite letras, números y espacios, no introducir caracteres especiales");
			}
		}else {
			mensaje.setMensaje("El campo \"Número Exterior\" es obligatorio");
		}
		
		return valido;
	}
	
	public Boolean validaNumInterior(String numInterior) {
		boolean valido = false;
		if (!numInterior.toString().matches("")) {
			if (numInterior.toString().matches("^[A-Z 0-9]{1,6}$")) {
				valido = true;
				solicitud.setpNumeroInteriorDomicilio(numInterior.toString().toUpperCase());
			}else {
				mensaje.setMensaje("El campo \"Número Interior\" sólo permite letras, números y espacios, no introducir caracteres especiales");
			}
		}else {
			valido = true;
		}
		
		return valido;
	}
	
	public Boolean validaCodigoPostal(String cp) {
		boolean valido = false;
		if (!cp.toString().matches("")) {
			if (cp.toString().matches("^[0-9]{5}$")) {
				valido = true;
			}else {
				mensaje.setMensaje("El campo \"Código Postal\" debe ser numérico de 5 dígitos");
			}
		}else {
			mensaje.setMensaje("El campo \"Código Postal\" es obligatorio");
		}
		return valido;
	}
	
	public Boolean validaLadaDom(String ladaDom) {
		boolean valido = false;
		if (!ladaDom.toString().matches("")) {
			if (ladaDom.toString().matches("^[1-9][0-9]{2}$")) {
				valido = true;
				solicitud.setpClaveLadaDomicilio(ladaDom);
			}else {
				mensaje.setMensaje("El campo \"Lada Domicilio\" debe ser numérico de 3 dígitos");
			}
		}else {
			mensaje.setMensaje("El campo \"Lada Domicilio\" es obligatorio");
		}
		
		return valido;
	}
	
	public Boolean validaTelDom(String telDom) {
		boolean valido = false;
		if (!telDom.toString().matches("")) {
			if (telDom.toString().matches("^[0-9]{7}$")) {
				valido = true;
				solicitud.setpTelefonoDomicilio(telDom);
			}else {
				mensaje.setMensaje("El campo \"Teléfono Domicilio\" debe ser numérico de 7 dígitos");
			}
		}else {
			mensaje.setMensaje("El campo \"Teléfono Domicilio\" es obligatorio");
		}
		return valido;
	}
	
	public Boolean validaSinItemSpinner(String item, String nombreItem) {
		boolean valido = false;
		if (item.toString().matches("")) {
			mensaje.setMensaje("El campo \"" +  nombreItem + "\" es obligatorio");
		}else {
			valido = true;
		}
		return valido;
	}
	
	public Boolean validaUltimosCuatroDigitos(String ultimosCuatroDigitos) {
		boolean valido = false;
		if (!ultimosCuatroDigitos.toString().matches("")) {
			if (ultimosCuatroDigitos.toString().matches("^[0-9]{4}$")) {
				valido = true;
			}else {
				mensaje.setMensaje("El campo \"Últimos cuatro Dígitos\" debe ser numérico de 4 dígitos");
			}
		}else {
			mensaje.setMensaje("El campo \"Últimos Cuatro Dígitos\" es obligatorio mientras \"TDC\" sea si");
		}
		return valido;
	}
	
	public Boolean validaNuevaContrasenia(String contraseniaActual, String contraseniaNueva, String confirmaContrasenia) {
		boolean valido = false;
		if (contraseniaActual.toString().matches("")) {
			mensaje.setMensaje("El campo \"Contraseña Anterior\" es obligatorio");
		}else {
			if (contraseniaNueva.toString().matches("")) {
				mensaje.setMensaje("El campo \"Contraseña Nueva\" es obligatorio");
			}else {
				if (confirmaContrasenia.toString().matches("")) {
					mensaje.setMensaje("El campo \"Confirmar Contraseña \" es obligatorio");
				}else {
					if (!usuario.getPassword().toString().matches(contraseniaActual)) {
						mensaje.setMensaje("El campo \"Contraseña Anteior\" no concuerda con la contraseña actual");
					}else {
						if (contraseniaActual.toString().matches(contraseniaNueva.toString())) {
							mensaje.setMensaje("La contraseña a cambiar no puede ser la misma a la actual");
						}else {
							if (!contraseniaNueva.toString().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@.()])[a-zA-Z0-9@.()]{8,20}$")) {
								mensaje.setMensaje("El formato de la contraseña no es el adecuado");
							}else {
								if (!contraseniaNueva.toString().matches(confirmaContrasenia.toString())) {
									mensaje.setMensaje("La contraseña nueva y su confirmación no coinciden");
								}else {
									valido = true;
								}
							}
						}
					}			
				}		
			}	
		}
		return valido;
	}
	
	public Boolean validaAntiguedadDomEmp(String antiguedad, String nombreCampoAntiguedad) {
		boolean valido = false;
		if (antiguedad.toString().matches("")) {
			valido = true;
		}else {
			if (antiguedad.toString().matches("^[1-6][0-9]$|^[0-9]$")) {
				if (nombreCampoAntiguedad.toString().matches("Antigüedad Domicilio")) {
					solicitud.setAñosAntiguedad(antiguedad.toString());
				}else {
					solicitud.setAñosAntiguedadEmpleo(antiguedad.toString());
				}
				valido = true;
			}else {
				if (nombreCampoAntiguedad.toString().matches("Antigüedad Domicilio")) {
					mensaje.setMensaje("El campo \"Antigüedad Domicilio\" no puede ser negativo");	
				}else {
					mensaje.setMensaje("El campo \"Años Antigüedad Empleo\" debe ser numérico dentro del rango [0, 99]");
				}
			}	
		}
		return valido;
	}
	
	public Boolean validaIngresosFijoVariableVentasProf(String ingresoFijoVariableVentasProf, String nombreCampo) {
		boolean valido = false;
		if (ingresoFijoVariableVentasProf.toString().matches("") && nombreCampo.toString().matches("Ingreso Neto Fijo")) {
			mensaje.setMensaje("El campo \"Ingreso Neto Fijo\" es obligatorio");
		}else {
			if (ingresoFijoVariableVentasProf.toString().matches("")) {
				valido = true;
			}else {
				if (!ingresoFijoVariableVentasProf.toString().matches("^[1-9][0-9]{2,5}$")) {
					mensaje.setMensaje("El campo \"" + nombreCampo + "\" debe ser numérico de 3 a 6 dígitos  (no introducir decimales)");
				}else {
					if (nombreCampo.toString().matches("Ingreso Neto Fijo")) {
						solicitud.setIngresoNetoFijo(ingresoFijoVariableVentasProf.toString());
					}
					if (nombreCampo.toString().matches("Ingreso Neto Variable")) {
						solicitud.setIngresoNetoVariable(ingresoFijoVariableVentasProf.toString());
					}
					if (nombreCampo.toString().matches("Ventas Anuales")) {
						solicitud.setVentasAnuales(ingresoFijoVariableVentasProf.toString());
					}
					if (nombreCampo.toString().matches("Profesionistas Independientes")) {
						solicitud.setProfesionistasIndependientes(ingresoFijoVariableVentasProf.toString());
					}
					valido = true;
				}	
			}
		}
		return valido;
	}
	
	public Boolean validaPorcentajeBeca(String porcentajeBeca) {
		boolean valido = false;
		if (porcentajeBeca.toString().matches("")) {
			mensaje.setMensaje("El campo \"Porcentaje Beca\" es obligatorio");
		}else {
			if (!porcentajeBeca.toString().matches("[0-9]+")) {
				mensaje.setMensaje("El campo \"Porcentaje Beca\" debe ser numérico");
			}else {
				if (!porcentajeBeca.toString().matches("^[1-9][0-9]$|^[1-9]$|^100$")) {
					mensaje.setMensaje("El campo \"Porcentaje Beca\" no está dentro del rango (0, 100)");
				}else {
					solicitud.setBeca(porcentajeBeca.toString());
					valido = true;
				}
			}
		}
		return valido;
	}
	
	public Boolean validaExtensionEmp(String extensionEmp) {
		boolean valido = false;
		if (extensionEmp.toString().matches("")) {
			valido = true;
		}else {
			if (!extensionEmp.toString().matches("^[0-9]{1,8}$")) {
				mensaje.setMensaje("El campo \"Extensión Empleo\" debe ser numérico de máximo 8 dígitos");
			}else {
				solicitud.setExtension(extensionEmp.toString());
				valido = true;
			}	
		}
		return valido;
	}
	
	public Boolean validaCorreoElectronico(String correoElectronico) {
		boolean valido = false;
		if (correoElectronico.toString().matches("")) {
			mensaje.setMensaje("El campo \"Correo Eleectrónico\" es obligatorio");
		}else {
			if (!correoElectronico.toString().matches("^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$")) {
				mensaje.setMensaje("El campo \"Correo Electrónico\" tiene formato incorrecto");
			}else {
				solicitud.setCorreoElectronico(correoElectronico.toString());
				valido = true;
			}
		}
		return valido;
	}
	
	public Boolean validaNumeroCelular(String numeroCelular) {
		boolean valido = false;
		if (numeroCelular.toString().matches("")) {
			mensaje.setMensaje("El campo \"Teléfono Celular\" es obligatorio");
		}else {
			if (!numeroCelular.toString().matches("^[1-9][0-9]{9}$")) {
				mensaje.setMensaje("El campo \"Teléfono Celular\" debe ser numérico de 10 posiciones");
			}else {
				solicitud.setTelCelular(numeroCelular.toString());
				valido = true;
			}
		}
		return valido;
	}
	
	public Boolean validaNombreEmpresa(String camposEmpresa, String nombreCampo) {
		boolean valido = false;
		if (camposEmpresa.toString().matches("") && !nombreCampo.toString().matches("Número Interior")) {
			mensaje.setMensaje("El campo \"" + nombreCampo.toString() +"\" es obligatorio mientras \"Ocupación\" es Asalariado");
		}else {
			if (nombreCampo.toString().matches("Nombre Empresa")) {
				if (!camposEmpresa.toString().matches("^[0-9A-Z ]{3,40}$")) {
					mensaje.setMensaje("El campo \"Nombre Empresa\" sólo permite letras, números y espacios, no introducir caracteres especiales");
				}else {
					solicitud.setNombreEmpresa(camposEmpresa.toString());
					valido = true;
				}	
			}else if (nombreCampo.toString().matches("Calle de la Empresa")) {
				if (!camposEmpresa.toString().matches("^[0-9A-Z ]{3,34}$")) {
					mensaje.setMensaje("El campo \"Calle de la Empresa\" sólo permite letras, números y espacios, no introducir caracteres especiales");
				}else {
					solicitud.setCalleEmpresa(camposEmpresa.toString());
					valido = true;
				}
			}else if (nombreCampo.toString().matches("Número Exterior")) {
				if (!camposEmpresa.toString().matches("^[A-Z 1-9]{1,8}$")) {
					mensaje.setMensaje("El campo \"Número Exterior\" sólo permite letras, números y espacios, no introducir caracteres especiales ");
				}else {
					solicitud.setNumExteriorEmpresa(camposEmpresa.toString());
					valido = true;
				}
			}else if (nombreCampo.toString().matches("Número Interior")) {
				if (!camposEmpresa.toString().matches("^[A-Z 0-9]{1,6}$")) {
					mensaje.setMensaje("El campo \"Número Interior\" sólo permite letras, números y espacios, no introducir caracteres especiales");
				}else {
					solicitud.setNumInteriorEmpresa(camposEmpresa.toString());
					valido = true;
				}
			}else if (nombreCampo.toString().matches("Código Postal")) {
				if (!camposEmpresa.toString().matches("^[0-9]{5}$")) {
					mensaje.setMensaje("El campo \"Código Postal\" debe ser numérico de 5 dígitos");
				}else {
					valido = true;
				}
			}
		}
		return valido;
	}
	
	public Boolean validaCamposVaciosReguexReferencias(String campo, String nombreCampo, int idReferencia) {
		boolean valido = false;
		if (campo.toString().matches("") && (nombreCampo.toString().matches("Apellido Materno")
				|| nombreCampo.toString().matches("Segundo Nombre"))) {
			valido = true;
		}else if ((campo.toString().matches("")) || (campo.toString().equals(""))) {
			mensaje.setMensaje("El Campo \""+ nombreCampo + "\" referencia "
					+ String.valueOf(idReferencia) + " es obligatorio");
		}else {
			if (campo.length() > 2) {
				String reguexValido = "^[A-Z ]{3,20}$";
				if (campo.toString().matches(reguexValido)) {
					valido = true;
					if (idReferencia == 1) {
						if (nombreCampo.toString().matches("Apellido Paterno")) {
							solicitud.setApPaternoRef1(campo.toString().toUpperCase());	
						}else if(nombreCampo.toString().matches("Apellido Materno")) {
							solicitud.setApMaternoRef1(campo.toString().toUpperCase());
						}else if (nombreCampo.toString().matches("Primer Nombre")) {
							solicitud.setpNombreRef1(campo.toString().toUpperCase());
						}else if (nombreCampo.toString().matches("Segundo Nombre")) {
							solicitud.setsNombreRef1(campo.toString().toUpperCase());
						}
					}else {
						if (nombreCampo.toString().matches("Apellido Paterno")) {
							solicitud.setApPaternoRef2(campo.toString().toUpperCase());	
						}else if(nombreCampo.toString().matches("Apellido Materno")) {
							solicitud.setApMaternoRef2(campo.toString().toUpperCase());
						}else if (nombreCampo.toString().matches("Primer Nombre")) {
							solicitud.setpNombreRef2(campo.toString().toUpperCase());
						}else if (nombreCampo.toString().matches("Segundo Nombre")) {
							solicitud.setsNombreRef2(campo.toString().toUpperCase());
						}
					}
				}else {
					mensaje.setMensaje("El Campo \"" + nombreCampo + "\""
							+ " referencia " + String.valueOf(idReferencia) +  " sólo permite letras y espacios, no introducir caracteres especiales");
				}
			}	
		}
		return valido;
	}
	
	public Boolean validaLadaRefs(String ladaRefs, int idReferencia ) {
		boolean valido = false;
		if (ladaRefs.toString().matches("")) {
			mensaje.setMensaje("El campo \"Lada Domicilio\" referencia "+ String.valueOf(idReferencia)  
					+ " es obligatorio");
		}else {
			if (!ladaRefs.toString().matches("^[1-9][0-9]{2}$")) {
				mensaje.setMensaje("El campo \"Lada Domicilio\" referencia "+ String.valueOf(idReferencia) 
						+" debe ser numérico de 3 dígitos");
			}else {
				if (idReferencia == 1) {
					solicitud.setLadaDomRef1(ladaRefs.toString());
				}else {
					solicitud.setLadaDomRef2(ladaRefs.toString());
				}
				valido = true;
			}
		}
		return valido;
	}
	
	public Boolean validaTelRefs(String telDom, int idReferencia) {
		boolean valido = false;
		if (telDom.toString().matches("")) {
			mensaje.setMensaje("El campo \"Teléfono Domicilio\" referencia " + String.valueOf(idReferencia) + 
					" es obligatorio");
		}else {
			if (!telDom.toString().matches("^[0-9]{7}$")) {
				mensaje.setMensaje("El campo \"Teléfono Domicilio\" referencia " + String.valueOf(idReferencia) + 
						" debe ser numérico de 7 dígitos");
			}else {
				if (idReferencia == 1) {
					solicitud.setTelDomRef1(telDom.toString());
				}else {
					solicitud.setTelDomRef2(telDom.toString());
				}
				valido = true;
			}
		}
		return valido;
	}	
	
	public Boolean validaExtensionRefs(String extensionEmp, int idReferencia) {
		boolean valido = false;
		if (!extensionEmp.toString().matches("")) {
			if (!extensionEmp.toString().matches("^[0-9]{1,8}$")) {
				mensaje.setMensaje("El campo \"Extensión \" referencia " + String.valueOf(idReferencia) + 
						" debe ser numérico de máximo 8 dígitos");
			}else {
				if (idReferencia == 1) {
					solicitud.setExtensionRef1(extensionEmp.toString());
				}else {
					solicitud.setExtensionRef2(extensionEmp.toString());
				}
				valido = true;
			}	
		}else {
			valido = true;
		}
		return valido;
	}	
	
	public Boolean validaNumeroCelularRefs(String numeroCelular, int idReferencia) {
		boolean valido = false;
		if (!numeroCelular.toString().matches("")) {
			if (!numeroCelular.toString().matches("^[1-9][0-9]{9}$")) {
				mensaje.setMensaje("El campo \"Teléfono Celular\" referencia " + String.valueOf(idReferencia) +
						" debe ser numérico de 10 posiciones");
			}else {
				if (idReferencia == 1) {
					solicitud.setTelCelRef1(numeroCelular.toString());
				}else {
					solicitud.setTelCelRef2(numeroCelular.toString());
				}
				valido = true;
			}	
		}else {
			valido = true;
		}
		return valido;
	}	
	
	public Boolean validaSinItemSpinnerOcupacion(String item, String nombreItem) {
		boolean valido = false;
		if (item.toString().matches("")) {
			mensaje.setMensaje("El campo \"" +  nombreItem + "\" es obligatorio mientras \"Ocupacion\" "
					+ " sea \"Universitario\"");
		}else {
			valido = true;
		}
		return valido;
	}
	
	public Boolean validaCampus(String campus) {
		boolean valido = false;
		if (campus.toString().matches("")) {
			mensaje.setMensaje("El campo \"Campus\" es obligatorio mientas \"Ocupación\" sea Universitario");
		}else {
			if (!campus.toString().matches("^[A-Z0-9 ]{3,20}$")) {
				mensaje.setMensaje("El campo \"Campus\" sólo permite letras, números y espacios, no introducir caracteres especiales");
			}else {
				solicitud.setCampus(campus.toString());
				valido = true;
			}
		}
		return valido;
	}
	
	public Boolean validaLadaEmpleo(String ladaEmpleo) {
		boolean valido = false;
		if (ladaEmpleo.toString().matches("")) {
			mensaje.setMensaje("El campo \"Lada Empleo\" es obligatorio mientas \"Ocupación\" es \"Asalariado\"");
		}else {
			if (!ladaEmpleo.toString().matches("^[1-9][0-9]{2}$")) {
				mensaje.setMensaje("El campo \"Lada Empleo\" debe ser numérico de 3 dígitos");
			}else {
				solicitud.setLadaEmpleo(ladaEmpleo.toString());
				valido = true;
			}
		}
		return valido;
	}
	
	public Boolean validaTelEmpleo(String telEmpleo) {
		boolean valido = false;
		if (telEmpleo.toString().matches("")) {
			mensaje.setMensaje("El campo \"Teléfono Empleo\" es obligatorio mientras \"Ocupación\" es \"Asalariado\"");
		}else {
			if (!telEmpleo.toString().matches("^[0-9]{7}$")) {
				mensaje.setMensaje("El campo \"Teléfono Empleo\" debe ser numérico de 7 dígitos");
			}else {
				solicitud.setTelEmpleo(telEmpleo.toString());
				valido = true;
			}	
		}
		return valido;
	}
	
	public Boolean validaLadaEmpRefs(String ladaEmpRef, int idReferencia) {
		boolean valido = false;
		if (!ladaEmpRef.toString().matches("")) {
			if (ladaEmpRef.toString().matches("^[1-9][0-9]{2}$")) {
				if (idReferencia == 1) {
					solicitud.setLadaEmpRef1(ladaEmpRef.toString());	
				}else {
					solicitud.setLadaEmpRef2(ladaEmpRef.toString());
				}
				valido = true;
			}else {
				mensaje.setMensaje("El campo \"Lada Empleo\" referencia " + String.valueOf(idReferencia) + 
			" debe ser numérico de 3 dígitos");
			}
		}else {
			valido = true;
		}
		return valido;
	}
	
	public Boolean validaTelEmpRefs(String telEmpRef, int idReferencia) {
		boolean valido = false;
		if (!telEmpRef.toString().matches("")) {
			if (telEmpRef.toString().matches("^[0-9]{7}$")) {
				if (idReferencia == 1) {
					solicitud.setTelEmpRef1(telEmpRef.toString());
				}else {
					solicitud.setTelEmpRef2(telEmpRef.toString());
				}
				valido = true;
			}else {
				mensaje.setMensaje("El campo \"Teléfono Empleo\" referencia " + String.valueOf(idReferencia)
			 + " debe ser numérico de 7 dígitos");
			}
		}else {
			valido = true;
		}
		return valido;
	}
	
	
}
