package com.dini.activoscriticosnacionalesbackend.dto;

import java.util.Date;
import java.util.Set;

public class NuevoUsuario {
	private String codigo_usuario;
	private String codigo_funcionario;
	private long id_funcionario;

    private int tipo_usuario;
    private int estado_usuario;
    private int conexion_usuario;
    private String nombre_usuario;
    private String contrasena_usuario;
   
  //Auditoria de creación
    private long id_usuario_creacion;
    private Date fecha_creacion;
    private String ip_creacion;

    private Set<String> roles;

    public String getCodigo_usuario() {
		return codigo_usuario;
	}

	public void setCodigo_usuario(String codigo_usuario) {
		this.codigo_usuario = codigo_usuario;
	}

	public String getCodigo_funcionario() {
		return codigo_funcionario;
	}

	public void setCodigo_funcionario(String codigo_funcionario) {
		this.codigo_funcionario = codigo_funcionario;
	}
	
	public long getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(long id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	public int getTipo_usuario() {
		return tipo_usuario;
	}

	public void setTipo_usuario(int tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}

	public int getEstado_usuario() {
		return estado_usuario;
	}

	public void setEstado_usuario(int estado_usuario) {
		this.estado_usuario = estado_usuario;
	}
	
	public int getConexion_usuario() {
		return conexion_usuario;
	}

	public void setConexion_usuario(int conexion_usuario) {
		this.conexion_usuario = conexion_usuario;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getContrasena_usuario() {
		return contrasena_usuario;
	}

	public void setContrasena_usuario(String contrasena_usuario) {
		this.contrasena_usuario = contrasena_usuario;
	}

	public long getId_usuario_creacion() {
		return id_usuario_creacion;
	}

	public void setId_usuario_creacion(long id_usuario_creacion) {
		this.id_usuario_creacion = id_usuario_creacion;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public String getIp_creacion() {
		return ip_creacion;
	}

	public void setIp_creacion(String ip_creacion) {
		this.ip_creacion = ip_creacion;
	}

	public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}