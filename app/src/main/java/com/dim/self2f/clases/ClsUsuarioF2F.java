package com.dim.self2f.clases;

public class ClsUsuarioF2F {
	static private ClsUsuarioF2F instancia;
	private int idUsuario;
	private int idBroker;
	private String nombreDeUsuario;
	private String password;
	private String pNombre;
	private String sNombre;
	private String apPaterno;
	private String apMaterno;
	private String email;
	private int idAgencia;
	private int idPerfil;

	public ClsUsuarioF2F(){
		setIdUsuario(0);
		setIdBroker(0);
		setNombreDeUsuario("");
		setPassword("");
		setpNombre("");
		setsNombre("");
		setApPaterno("");
		setApMaterno("");
		setEmail("");
		setIdAgencia(0);
		setIdPerfil(0);
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdBroker() {
		return idBroker;
	}

	public void setIdBroker(int idBroker) {
		this.idBroker = idBroker;
	}

	public String getNombreDeUsuario() {
		return nombreDeUsuario;
	}

	public void setNombreDeUsuario(String nombreDeUsuario) {
		this.nombreDeUsuario = nombreDeUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getpNombre() {
		return pNombre;
	}

	public void setpNombre(String pNombre) {
		this.pNombre = pNombre;
	}

	public String getsNombre() {
		return sNombre;
	}

	public void setsNombre(String sNombre) {
		this.sNombre = sNombre;
	}

	public String getApPaterno() {
		return apPaterno;
	}

	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}

	public String getApMaterno() {
		return apMaterno;
	}

	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}
	
	public int getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(int idAgencia) {
		this.idAgencia = idAgencia;
	}
	
	static public ClsUsuarioF2F getInstancia() {
		if (instancia == null) {
			instancia = new ClsUsuarioF2F();
		}
		return instancia;
	}



}
