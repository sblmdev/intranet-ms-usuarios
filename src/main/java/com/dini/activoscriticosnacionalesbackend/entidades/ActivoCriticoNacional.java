package com.dini.activoscriticosnacionalesbackend.entidades;

import java.util.Date;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

@Entity 
@Table ( name = " TA_ACN_ACN " )

public class ActivoCriticoNacional{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id_acn;
	
	@Column(length = 20)
	private String codigo_acn;
	
	@Column(length = 100)
	private String acn_denominacion;
	
	private int estado_acn;
	
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
    
    public ActivoCriticoNacional() {
    }

	public String getAcn_denominacion() {
		return acn_denominacion;
	}

	public void setAcn_denominacion(String acn_denominacion) {
		this.acn_denominacion = acn_denominacion;
	}



	public long getId_acn() {
		return id_acn;
	}

	public void setId_acn(long id_acn) {
		this.id_acn = id_acn;
	}

	public String getCodigo_acn() {
		return codigo_acn;
	}

	public void setCodigo_acn(String codigo_acn) {
		this.codigo_acn = codigo_acn;
	}

	
	public int getEstado_acn() {
		return estado_acn;
	}

	public void setEstado_acn(int estado_acn) {
		this.estado_acn = estado_acn;
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