package com.dini.activoscriticosnacionalesbackend.entidades;

import java.util.Date;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity 
@Table ( name = " TA_ACN_USUARIOS" )

public class Usuario {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id_usuario;
	
	@Column(length = 20)
	private String codigo_usuario;
	
	@Column(length = 20)
	private String codigo_funcionario;
	
	private long id_funcionario;

    private int tipo_usuario;
    private int estado_usuario;
    private int conexion_usuario;
    
    @Column ( name = " nombre_usuario", length=50 )
    private String nombreUsuario;
    
    @Column(length = 1000)
    private String contrasena_usuario;
    
    @NotNull
    @ManyToMany
    @JoinTable(name = "TA_ACN_USUROLES", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private Set<Rol> roles = new HashSet<>();
    
  //Auditoria de creación
    private long id_usuario_creacion;
    private Date fecha_creacion;
    
    @Column(length = 20)
    private String ip_creacion;
    
    public Usuario(){
    }

	public Usuario(String codigo_usuario, String codigo_funcionario, long id_funcionario,
			int tipo_usuario, int estado_usuario, int conexion_usuario, String nombreUsuario, String contrasena_usuario,
			long id_usuario_creacion, Date fecha_creacion, String ip_creacion) {
		super();
		this.codigo_usuario = codigo_usuario;
		this.codigo_funcionario = codigo_funcionario;
		this.id_funcionario = id_funcionario;
		this.tipo_usuario = tipo_usuario;
		this.estado_usuario = estado_usuario;
		this.conexion_usuario = conexion_usuario;
		this.nombreUsuario = nombreUsuario;
		this.contrasena_usuario = contrasena_usuario;
		this.id_usuario_creacion = id_usuario_creacion;
		this.fecha_creacion = fecha_creacion;
		this.ip_creacion = ip_creacion;
	}

	public long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}

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

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasena_usuario() {
		return contrasena_usuario;
	}

	public void setContrasena_usuario(String contrasena_usuario) {
		this.contrasena_usuario = contrasena_usuario;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
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

}