package com.dini.activoscriticosnacionalesbackend.entidades;

import java.util.Date;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

@Entity 
@Table ( name = " TA_ACN_ACNALTERNATIVOS " )

public class ActivoCriticoNacionalAlternativo{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id_alternativo_acn;
	
	private String existencia_alternativo;
	private String tiempo_reposicion_alternativo;
	private String recibir_apoyo_alternativo;
	
	private String codigo_acn;
	private long id_acn;
	private int estado_acn_alternativo;
    
  //Auditoria de creación
    private long id_usuario_creacion;
    private Date fecha_creacion;
    private String ip_creacion;
    
  //Auditoria de modificación
    private long id_usuario_modificacion;
    private Date fecha_modificacion;
    private String ip_modificacion;
    
    public ActivoCriticoNacionalAlternativo() {
    }
    
	public long getId_acn() {
		return id_acn;
	}

	public void setId_acn(long id_acn) {
		this.id_acn = id_acn;
	}


	public long getId_alternativo_acn() {
		return id_alternativo_acn;
	}

	public void setId_alternativo_acn(long id_alternativo_acn) {
		this.id_alternativo_acn = id_alternativo_acn;
	}

	public String getExistencia_alternativo() {
		return existencia_alternativo;
	}

	public void setExistencia_alternativo(String existencia_alternativo) {
		this.existencia_alternativo = existencia_alternativo;
	}

	public String getTiempo_reposicion_alternativo() {
		return tiempo_reposicion_alternativo;
	}

	public void setTiempo_reposicion_alternativo(String tiempo_reposicion_alternativo) {
		this.tiempo_reposicion_alternativo = tiempo_reposicion_alternativo;
	}

	public String getRecibir_apoyo_alternativo() {
		return recibir_apoyo_alternativo;
	}

	public void setRecibir_apoyo_alternativo(String recibir_apoyo_alternativo) {
		this.recibir_apoyo_alternativo = recibir_apoyo_alternativo;
	}

	public String getCodigo_acn() {
		return codigo_acn;
	}

	public void setCodigo_acn(String codigo_acn) {
		this.codigo_acn = codigo_acn;
	}

	public int getEstado_acn_alternativo() {
		return estado_acn_alternativo;
	}

	public void setEstado_acn_alternativo(int estado_acn_alternativo) {
		this.estado_acn_alternativo = estado_acn_alternativo;
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

}