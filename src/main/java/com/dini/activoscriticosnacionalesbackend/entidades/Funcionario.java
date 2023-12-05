package com.dini.activoscriticosnacionalesbackend.entidades;


import java.util.Date;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

@Entity 
@Table ( name = " TA_ACN_PERSONAS " )

public class Funcionario {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id_funcionario;
	
	@Column(length = 20)
	private String codigo_funcionario;
	
	@Column(length = 8)
	private String dni_funcionario;
	
	@Column(length = 50)
	private String paterno_funcionario;
	
	@Column(length = 50)
	private String materno_funcionario;
	
	@Column(length = 100)
	private String nombres_funcionario;
	
	@Column(length = 150000)
	private String imagen_funcionario;
   
  //Auditoria de creación
    private long id_usuario_creacion;
    private Date fecha_creacion;
    
    @Column(length = 20)
    private String ip_creacion;
    
  //Auditoria de modificación
    private long id_usuario_modificacion;
    private Date fecha_modificacion;
    
    @Column(length = 20)
    private String ip_modificacion;
    
	public long getId_funcionario() {
		return id_funcionario;
	}
	public void setId_funcionario(long id_funcionario) {
		this.id_funcionario = id_funcionario;
	}
	public String getCodigo_funcionario() {
		return codigo_funcionario;
	}
	public void setCodigo_funcionario(String codigo_funcionario) {
		this.codigo_funcionario = codigo_funcionario;
	}
	public String getDni_funcionario() {
		return dni_funcionario;
	}
	public void setDni_funcionario(String dni_funcionario) {
		this.dni_funcionario = dni_funcionario;
	}
	public String getPaterno_funcionario() {
		return paterno_funcionario;
	}
	public void setPaterno_funcionario(String paterno_funcionario) {
		this.paterno_funcionario = paterno_funcionario;
	}
	public String getMaterno_funcionario() {
		return materno_funcionario;
	}
	public void setMaterno_funcionario(String materno_funcionario) {
		this.materno_funcionario = materno_funcionario;
	}
	public String getNombres_funcionario() {
		return nombres_funcionario;
	}
	public void setNombres_funcionario(String nombres_funcionario) {
		this.nombres_funcionario = nombres_funcionario;
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
	public long getId_usuario_modificacion() {
		return id_usuario_modificacion;
	}
	public void setId_usuario_modificacion(long id_usuario_modificacion) {
		this.id_usuario_modificacion = id_usuario_modificacion;
	}
	public Date getFecha_modificacion() {
		return fecha_modificacion;
	}
	public void setFecha_modificacion(Date fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}
	public String getIp_modificacion() {
		return ip_modificacion;
	}
	public void setIp_modificacion(String ip_modificacion) {
		this.ip_modificacion = ip_modificacion;
	}
	public String getImagen_funcionario() {
		return imagen_funcionario;
	}
	public void setImagen_funcionario(String imagen_funcionario) {
		this.imagen_funcionario = imagen_funcionario;
	} 
}