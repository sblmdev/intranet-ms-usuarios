package com.dini.activoscriticosnacionalesbackend.entidades;

import java.util.Date;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

@Entity 
@Table ( name = " TA_ACN_ACNIMPACTOS " )

public class ActivoCriticoNacionalImpacto{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id_impacto_acn;
	
	private String economico_1_impacto;
	private String economico_2_impacto;
	private String economico_3_impacto;
	private String economico_4_impacto;
	private String economico_5_impacto;
	private String poblacion_1_impacto;
	private String poblacion_2_impacto;
	private String poblacion_3_impacto;
	private String poblacion_4_impacto;
	private String ambiental_1_impacto;
	
	private String codigo_acn;
	private long id_acn;
	private int estado_acn_impacto;
    
  //Auditoria de creación
    private long id_usuario_creacion;
    private Date fecha_creacion;
    private String ip_creacion;
    
  //Auditoria de modificación
    private long id_usuario_modificacion;
    private Date fecha_modificacion;
    private String ip_modificacion;
    
    public ActivoCriticoNacionalImpacto() {
    }
    
	public long getId_acn() {
		return id_acn;
	}

	public void setId_acn(long id_acn) {
		this.id_acn = id_acn;
	}

	public long getId_impacto_acn() {
		return id_impacto_acn;
	}

	public void setId_impacto_acn(long id_impacto_acn) {
		this.id_impacto_acn = id_impacto_acn;
	}

	public String getEconomico_1_impacto() {
		return economico_1_impacto;
	}

	public void setEconomico_1_impacto(String economico_1_impacto) {
		this.economico_1_impacto = economico_1_impacto;
	}

	public String getEconomico_2_impacto() {
		return economico_2_impacto;
	}

	public void setEconomico_2_impacto(String economico_2_impacto) {
		this.economico_2_impacto = economico_2_impacto;
	}

	public String getEconomico_3_impacto() {
		return economico_3_impacto;
	}

	public void setEconomico_3_impacto(String economico_3_impacto) {
		this.economico_3_impacto = economico_3_impacto;
	}

	public String getEconomico_4_impacto() {
		return economico_4_impacto;
	}

	public void setEconomico_4_impacto(String economico_4_impacto) {
		this.economico_4_impacto = economico_4_impacto;
	}

	public String getEconomico_5_impacto() {
		return economico_5_impacto;
	}

	public void setEconomico_5_impacto(String economico_5_impacto) {
		this.economico_5_impacto = economico_5_impacto;
	}

	public String getPoblacion_1_impacto() {
		return poblacion_1_impacto;
	}

	public void setPoblacion_1_impacto(String poblacion_1_impacto) {
		this.poblacion_1_impacto = poblacion_1_impacto;
	}

	public String getPoblacion_2_impacto() {
		return poblacion_2_impacto;
	}

	public void setPoblacion_2_impacto(String poblacion_2_impacto) {
		this.poblacion_2_impacto = poblacion_2_impacto;
	}

	public String getPoblacion_3_impacto() {
		return poblacion_3_impacto;
	}

	public void setPoblacion_3_impacto(String poblacion_3_impacto) {
		this.poblacion_3_impacto = poblacion_3_impacto;
	}

	public String getPoblacion_4_impacto() {
		return poblacion_4_impacto;
	}

	public void setPoblacion_4_impacto(String poblacion_4_impacto) {
		this.poblacion_4_impacto = poblacion_4_impacto;
	}

	public String getAmbiental_1_impacto() {
		return ambiental_1_impacto;
	}

	public void setAmbiental_1_impacto(String ambiental_1_impacto) {
		this.ambiental_1_impacto = ambiental_1_impacto;
	}

	public String getCodigo_acn() {
		return codigo_acn;
	}

	public void setCodigo_acn(String codigo_acn) {
		this.codigo_acn = codigo_acn;
	}

	public int getEstado_acn_impacto() {
		return estado_acn_impacto;
	}

	public void setEstado_acn_impacto(int estado_acn_impacto) {
		this.estado_acn_impacto = estado_acn_impacto;
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