package com.dini.activoscriticosnacionalesbackend.entidades;

import java.util.Date;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

@Entity 
@Table ( name = " TA_ACN_OPEACNDESCRIPCIONES " )

public class OperadorDescripcion {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id_descripcion_operador;
	private String codigo_operador;
	private String codigo_acn;
	private long id_acn;
	private long id_operador;
	
    private String infraestructura_descripcion_operador;
    private String servicios_descripcion_operador;
    private Date fecha_descripcion_operador;
    private int valor_economico_descripcion_operador;
    
    private int estado_descripcion_operador;
    
  //Auditoria de creación
    private long id_usuario_creacion;
    private Date fecha_creacion;
    private String ip_creacion;
    
  //Auditoria de modificación
    private long id_usuario_modificacion;
    private Date fecha_modificacion;
    private String ip_modificacion;
    
    public OperadorDescripcion() {
    }
    

	public long getId_operador() {
		return id_operador;
	}


	public void setId_operador(long id_operador) {
		this.id_operador = id_operador;
	}


	public long getId_descripcion_operador() {
		return id_descripcion_operador;
	}

	public void setId_descripcion_operador(long id_descripcion_operador) {
		this.id_descripcion_operador = id_descripcion_operador;
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

	public String getInfraestructura_descripcion_operador() {
		return infraestructura_descripcion_operador;
	}

	public void setInfraestructura_descripcion_operador(String infraestructura_descripcion_operador) {
		this.infraestructura_descripcion_operador = infraestructura_descripcion_operador;
	}

	public String getServicios_descripcion_operador() {
		return servicios_descripcion_operador;
	}

	public void setServicios_descripcion_operador(String servicios_descripcion_operador) {
		this.servicios_descripcion_operador = servicios_descripcion_operador;
	}

	public Date getFecha_descripcion_operador() {
		return fecha_descripcion_operador;
	}

	public void setFecha_descripcion_operador(Date fecha_descripcion_operador) {
		this.fecha_descripcion_operador = fecha_descripcion_operador;
	}

	public int getValor_economico_descripcion_operador() {
		return valor_economico_descripcion_operador;
	}

	public void setValor_economico_descripcion_operador(int valor_economico_descripcion_operador) {
		this.valor_economico_descripcion_operador = valor_economico_descripcion_operador;
	}

	public int getEstado_descripcion_operador() {
		return estado_descripcion_operador;
	}

	public void setEstado_descripcion_operador(int estado_descripcion_operador) {
		this.estado_descripcion_operador = estado_descripcion_operador;
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

	public long getId_acn() {
		return id_acn;
	}

	public void setId_acn(long id_acn) {
		this.id_acn = id_acn;
	}
	
	

}