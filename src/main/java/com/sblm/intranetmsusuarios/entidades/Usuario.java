package com.sblm.intranetmsusuarios.entidades;

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
@Table ( name = " TA_SBLM_USUARIOS" )

public class Usuario {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(length = 8)
	private String dni;
	
    private int tipo;
    private int estado;

    @Column (length=50)
    private String usuario;
    
    @Column(length = 1000)
    private String contrasena;
    
    @NotNull
    @ManyToMany
    @JoinTable(name = "TA_SBLM_USUROLES", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private Set<Rol> roles = new HashSet<>();
    
    private Date fechaCreacion;
    
    public Usuario(){
    }

	public Usuario(String dni, int tipo, int estado, String usuario, String contrasena, Date fechaCreacion) {
		super();
		this.dni = dni;
		this.tipo = tipo;
		this.estado = estado;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.fechaCreacion = fechaCreacion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
    
}