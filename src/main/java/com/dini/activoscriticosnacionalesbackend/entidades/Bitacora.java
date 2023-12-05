package com.dini.activoscriticosnacionalesbackend.entidades;

import java.util.Date;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

@Entity 
@Table ( name = " TA_ACN_BITACORAS " )

public class Bitacora {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id_bitacora;	
	
	@Column(length = 20)
	private String codigo_usuario;
	
	private int accion;
	
	@Column(length = 200)
	private String descripcion;
	
	private Date fecha;
	
	@Column(length = 20)
	private String ip;
	
    public Bitacora(){
    	
    }

	public long getId_bitacora() {
		return id_bitacora;
	}

	public void setId_bitacora(long id_bitacora) {
		this.id_bitacora = id_bitacora;
	}

	public String getCodigo_usuario() {
		return codigo_usuario;
	}

	public void setCodigo_usuario(String codigo_usuario) {
		this.codigo_usuario = codigo_usuario;
	}

	public int getAccion() {
		return accion;
	}

	public void setAccion(int accion) {
		this.accion = accion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}