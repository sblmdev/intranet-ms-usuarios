package com.dini.activoscriticosnacionalesbackend.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

@Entity 
@Table ( name = " TA_ACN_ACCIONISTAS " )

public class Accionista {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id_accionista;
	
	@Column(length = 20)
	private String codigo_operador;
	
	private long id_operador;
	
	@Column(length = 100)
	private String nombre_accionista;
	
	private float porcentaje_accionista;
	private int nacionalidad_accionista;
	private int estado_accionista;
    
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
 
    public Accionista(){
    }
    
	public long getId_operador() {
		return id_operador;
	}

	public void setId_operador(long id_operador) {
		this.id_operador = id_operador;
	}


	public long getId_accionista() {
		return id_accionista;
	}

	public void setId_accionista(long id_accionista) {
		this.id_accionista = id_accionista;
	}

	public String getNombre_accionista() {
		return nombre_accionista;
	}

	public void setNombre_accionista(String nombre_accionista) {
		this.nombre_accionista = nombre_accionista;
	}

	public int getNacionalidad_accionista() {
		return nacionalidad_accionista;
	}

	public void setNacionalidad_accionista(int nacionalidad_accionista) {
		this.nacionalidad_accionista = nacionalidad_accionista;
	}

	public int getEstado_accionista() {
		return estado_accionista;
	}

	public void setEstado_accionista(int estado_accionista) {
		this.estado_accionista = estado_accionista;
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

	public String getCodigo_operador() {
		return codigo_operador;
	}

	public void setCodigo_operador(String codigo_operador) {
		this.codigo_operador = codigo_operador;
	}

	public float getPorcentaje_accionista() {
		return porcentaje_accionista;
	}

	public void setPorcentaje_accionista(float porcentaje_accionista) {
		this.porcentaje_accionista = porcentaje_accionista;
	}
	
}