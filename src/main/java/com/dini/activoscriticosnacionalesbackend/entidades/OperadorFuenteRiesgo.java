package com.dini.activoscriticosnacionalesbackend.entidades;

import java.util.Date;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

@Entity 
@Table ( name = " TA_ACN_OPEACNFUENTESRIESGO " )

public class OperadorFuenteRiesgo {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id_riesgo_operador;
	private String codigo_operador;
	private String codigo_acn;
	private long id_acn;
	private long id_operador;
	
    private String natural_riesgo_operador;
    private String seguridad_riesgo_operador;
    private String paz_riesgo_operador;
    private String digital_riesgo_operador;
    private String mantenimiento_riesgo_operador;
    private String pandemia_riesgo_operador;
    private String obsolescencia_riesgo_operador;
    private String otro_riesgo_operador;
    
    private int estado_riesgo_operador;
    
  //Auditoria de creación
    private long id_usuario_creacion;
    private Date fecha_creacion;
    private String ip_creacion;
    
  //Auditoria de modificación
    private long id_usuario_modificacion;
    private Date fecha_modificacion;
    private String ip_modificacion;
    
    public OperadorFuenteRiesgo() {
    }

	

	public long getId_acn() {
		return id_acn;
	}



	public void setId_acn(long id_acn) {
		this.id_acn = id_acn;
	}



	public long getId_operador() {
		return id_operador;
	}



	public void setId_operador(long id_operador) {
		this.id_operador = id_operador;
	}



	public long getId_riesgo_operador() {
		return id_riesgo_operador;
	}

	public void setId_riesgo_operador(long id_riesgo_operador) {
		this.id_riesgo_operador = id_riesgo_operador;
	}

	public String getCodigo_operador() {
		return codigo_operador;
	}

	public void setCodigo_operador(String codigo_operador) {
		this.codigo_operador = codigo_operador;
	}

	public String getCodigo_acn() {
		return codigo_acn;
	}

	public void setCodigo_acn(String codigo_acn) {
		this.codigo_acn = codigo_acn;
	}

	public String getNatural_riesgo_operador() {
		return natural_riesgo_operador;
	}

	public void setNatural_riesgo_operador(String natural_riesgo_operador) {
		this.natural_riesgo_operador = natural_riesgo_operador;
	}

	public String getSeguridad_riesgo_operador() {
		return seguridad_riesgo_operador;
	}

	public void setSeguridad_riesgo_operador(String seguridad_riesgo_operador) {
		this.seguridad_riesgo_operador = seguridad_riesgo_operador;
	}

	public String getPaz_riesgo_operador() {
		return paz_riesgo_operador;
	}

	public void setPaz_riesgo_operador(String paz_riesgo_operador) {
		this.paz_riesgo_operador = paz_riesgo_operador;
	}

	public String getDigital_riesgo_operador() {
		return digital_riesgo_operador;
	}

	public void setDigital_riesgo_operador(String digital_riesgo_operador) {
		this.digital_riesgo_operador = digital_riesgo_operador;
	}

	public String getMantenimiento_riesgo_operador() {
		return mantenimiento_riesgo_operador;
	}

	public void setMantenimiento_riesgo_operador(String mantenimiento_riesgo_operador) {
		this.mantenimiento_riesgo_operador = mantenimiento_riesgo_operador;
	}

	public String getPandemia_riesgo_operador() {
		return pandemia_riesgo_operador;
	}

	public void setPandemia_riesgo_operador(String pandemia_riesgo_operador) {
		this.pandemia_riesgo_operador = pandemia_riesgo_operador;
	}

	public String getObsolescencia_riesgo_operador() {
		return obsolescencia_riesgo_operador;
	}

	public void setObsolescencia_riesgo_operador(String obsolescencia_riesgo_operador) {
		this.obsolescencia_riesgo_operador = obsolescencia_riesgo_operador;
	}

	public String getOtro_riesgo_operador() {
		return otro_riesgo_operador;
	}

	public void setOtro_riesgo_operador(String otro_riesgo_operador) {
		this.otro_riesgo_operador = otro_riesgo_operador;
	}

	public int getEstado_riesgo_operador() {
		return estado_riesgo_operador;
	}

	public void setEstado_riesgo_operador(int estado_riesgo_operador) {
		this.estado_riesgo_operador = estado_riesgo_operador;
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