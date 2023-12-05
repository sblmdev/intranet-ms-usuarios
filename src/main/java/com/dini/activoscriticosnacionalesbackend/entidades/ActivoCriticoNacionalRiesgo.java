package com.dini.activoscriticosnacionalesbackend.entidades;

import java.util.Date;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

@Entity 
@Table ( name = " TA_ACN_ACNRIESGOS " )

public class ActivoCriticoNacionalRiesgo{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id_riesgo_acn;
	
	private String natural_riesgo;
    private String seguridad_riesgo;
    private String paz_riesgo;
    private String digital_riesgo;
    private String mantenimiento_riesgo;
    private String pandemia_riesgo;
    private String obsolescencia_riesgo;
    private String otro_riesgo;
    
    private String gravedad_riesgo;
    private String consecuencia_riesgo;
    private String efecto_riesgo;
    private String area_influencia_riesgo;
	
	private String codigo_acn;
	private long id_acn;
	private int estado_acn_riesgo;
    
  //Auditoria de creación
    private long id_usuario_creacion;
    private Date fecha_creacion;
    private String ip_creacion;
    
  //Auditoria de modificación
    private long id_usuario_modificacion;
    private Date fecha_modificacion;
    private String ip_modificacion;
    
    public ActivoCriticoNacionalRiesgo() {
    }
    
	public long getId_acn() {
		return id_acn;
	}

	public void setId_acn(long id_acn) {
		this.id_acn = id_acn;
	}

	public long getId_riesgo_acn() {
		return id_riesgo_acn;
	}

	public void setId_riesgo_acn(long id_riesgo_acn) {
		this.id_riesgo_acn = id_riesgo_acn;
	}

	public String getNatural_riesgo() {
		return natural_riesgo;
	}

	public void setNatural_riesgo(String natural_riesgo) {
		this.natural_riesgo = natural_riesgo;
	}

	public String getSeguridad_riesgo() {
		return seguridad_riesgo;
	}

	public void setSeguridad_riesgo(String seguridad_riesgo) {
		this.seguridad_riesgo = seguridad_riesgo;
	}

	public String getPaz_riesgo() {
		return paz_riesgo;
	}

	public void setPaz_riesgo(String paz_riesgo) {
		this.paz_riesgo = paz_riesgo;
	}

	public String getDigital_riesgo() {
		return digital_riesgo;
	}

	public void setDigital_riesgo(String digital_riesgo) {
		this.digital_riesgo = digital_riesgo;
	}

	public String getMantenimiento_riesgo() {
		return mantenimiento_riesgo;
	}

	public void setMantenimiento_riesgo(String mantenimiento_riesgo) {
		this.mantenimiento_riesgo = mantenimiento_riesgo;
	}

	public String getPandemia_riesgo() {
		return pandemia_riesgo;
	}

	public void setPandemia_riesgo(String pandemia_riesgo) {
		this.pandemia_riesgo = pandemia_riesgo;
	}

	public String getObsolescencia_riesgo() {
		return obsolescencia_riesgo;
	}

	public void setObsolescencia_riesgo(String obsolescencia_riesgo) {
		this.obsolescencia_riesgo = obsolescencia_riesgo;
	}

	public String getOtro_riesgo() {
		return otro_riesgo;
	}

	public void setOtro_riesgo(String otro_riesgo) {
		this.otro_riesgo = otro_riesgo;
	}

	public String getGravedad_riesgo() {
		return gravedad_riesgo;
	}

	public void setGravedad_riesgo(String gravedad_riesgo) {
		this.gravedad_riesgo = gravedad_riesgo;
	}

	public String getConsecuencia_riesgo() {
		return consecuencia_riesgo;
	}

	public void setConsecuencia_riesgo(String consecuencia_riesgo) {
		this.consecuencia_riesgo = consecuencia_riesgo;
	}

	public String getEfecto_riesgo() {
		return efecto_riesgo;
	}

	public void setEfecto_riesgo(String efecto_riesgo) {
		this.efecto_riesgo = efecto_riesgo;
	}

	public String getArea_influencia_riesgo() {
		return area_influencia_riesgo;
	}

	public void setArea_influencia_riesgo(String area_influencia_riesgo) {
		this.area_influencia_riesgo = area_influencia_riesgo;
	}

	public String getCodigo_acn() {
		return codigo_acn;
	}

	public void setCodigo_acn(String codigo_acn) {
		this.codigo_acn = codigo_acn;
	}

	public int getEstado_acn_riesgo() {
		return estado_acn_riesgo;
	}

	public void setEstado_acn_riesgo(int estado_acn_riesgo) {
		this.estado_acn_riesgo = estado_acn_riesgo;
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