package com.dini.activoscriticosnacionalesbackend.entidades;

import java.util.Date;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

@Entity 
@Table ( name = " TA_ACN_ACNRELACIONES " )

public class ActivoCriticoNacionalRelacion{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id_relacion_acn;
	
	private String codigo_objetivo_nacional;
	private long id_objetivo_nacional;
	private String relacion_acn_objetivo_nacional;
	private String codigo_capacidad_nacional;
	private String relacion_acn_capacidad_nacional;
	private String cuantificacion_acn_capacidad_nacional;
	private String codigo_sub_capacidad_nacional;
	private String codigo_clasificacion_acn;
	private long id_clasificacion_acn;
	
	private String codigo_acn;
	private long id_acn;
	private int estado_acn_relacion;
    
  //Auditoria de creación
    private long id_usuario_creacion;
    private Date fecha_creacion;
    private String ip_creacion;
    
  //Auditoria de modificación
    private long id_usuario_modificacion;
    private Date fecha_modificacion;
    private String ip_modificacion;
    
    public ActivoCriticoNacionalRelacion() {
    }
    
	public long getId_objetivo_nacional() {
		return id_objetivo_nacional;
	}

	public void setId_objetivo_nacional(long id_objetivo_nacional) {
		this.id_objetivo_nacional = id_objetivo_nacional;
	}

	public long getId_clasificacion_acn() {
		return id_clasificacion_acn;
	}

	public void setId_clasificacion_acn(long id_clasificacion_acn) {
		this.id_clasificacion_acn = id_clasificacion_acn;
	}

	public long getId_acn() {
		return id_acn;
	}

	public void setId_acn(long id_acn) {
		this.id_acn = id_acn;
	}

	public long getId_relacion_acn() {
		return id_relacion_acn;
	}

	public void setId_relacion_acn(long id_relacion_acn) {
		this.id_relacion_acn = id_relacion_acn;
	}

	public String getCodigo_objetivo_nacional() {
		return codigo_objetivo_nacional;
	}

	public void setCodigo_objetivo_nacional(String codigo_objetivo_nacional) {
		this.codigo_objetivo_nacional = codigo_objetivo_nacional;
	}

	public String getRelacion_acn_objetivo_nacional() {
		return relacion_acn_objetivo_nacional;
	}

	public void setRelacion_acn_objetivo_nacional(String relacion_acn_objetivo_nacional) {
		this.relacion_acn_objetivo_nacional = relacion_acn_objetivo_nacional;
	}

	public String getCodigo_capacidad_nacional() {
		return codigo_capacidad_nacional;
	}

	public void setCodigo_capacidad_nacional(String codigo_capacidad_nacional) {
		this.codigo_capacidad_nacional = codigo_capacidad_nacional;
	}

	public String getRelacion_acn_capacidad_nacional() {
		return relacion_acn_capacidad_nacional;
	}

	public void setRelacion_acn_capacidad_nacional(String relacion_acn_capacidad_nacional) {
		this.relacion_acn_capacidad_nacional = relacion_acn_capacidad_nacional;
	}

	public String getCuantificacion_acn_capacidad_nacional() {
		return cuantificacion_acn_capacidad_nacional;
	}

	public void setCuantificacion_acn_capacidad_nacional(String cuantificacion_acn_capacidad_nacional) {
		this.cuantificacion_acn_capacidad_nacional = cuantificacion_acn_capacidad_nacional;
	}

	public String getCodigo_sub_capacidad_nacional() {
		return codigo_sub_capacidad_nacional;
	}

	public void setCodigo_sub_capacidad_nacional(String codigo_sub_capacidad_nacional) {
		this.codigo_sub_capacidad_nacional = codigo_sub_capacidad_nacional;
	}

	public String getCodigo_clasificacion_acn() {
		return codigo_clasificacion_acn;
	}

	public void setCodigo_clasificacion_acn(String codigo_clasificacion_acn) {
		this.codigo_clasificacion_acn = codigo_clasificacion_acn;
	}

	public String getCodigo_acn() {
		return codigo_acn;
	}

	public void setCodigo_acn(String codigo_acn) {
		this.codigo_acn = codigo_acn;
	}

	public int getEstado_acn_relacion() {
		return estado_acn_relacion;
	}

	public void setEstado_acn_relacion(int estado_acn_relacion) {
		this.estado_acn_relacion = estado_acn_relacion;
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