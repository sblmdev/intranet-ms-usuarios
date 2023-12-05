package com.dini.activoscriticosnacionalesbackend.entidades;

import java.util.Date;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

@Entity 
@Table ( name = " TA_ACN_OPEACNSERVICIOS " )

public class OperadorServicio {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id_servicio_operador;
	private String codigo_operador;
	private String codigo_acn;
	private long id_acn;
	private long id_operador;
	
    private String magnitud_servicio_operador;
    private String magnitud_minima_servicio_operador;
    private String magnitud_maxima_servicio_operador;
    private int numero_trabajadores_servicio_operador;
    private int porcentaje_contribucion_servicio_operador;
    
    private int estado_servicio_operador;
    
  //Auditoria de creación
    private long id_usuario_creacion;
    private Date fecha_creacion;
    private String ip_creacion;
    
  //Auditoria de modificación
    private long id_usuario_modificacion;
    private Date fecha_modificacion;
    private String ip_modificacion;
    
    public OperadorServicio() {
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



	public long getId_servicio_operador() {
		return id_servicio_operador;
	}

	public void setId_servicio_operador(long id_servicio_operador) {
		this.id_servicio_operador = id_servicio_operador;
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

	public String getMagnitud_servicio_operador() {
		return magnitud_servicio_operador;
	}

	public void setMagnitud_servicio_operador(String magnitud_servicio_operador) {
		this.magnitud_servicio_operador = magnitud_servicio_operador;
	}

	public String getMagnitud_minima_servicio_operador() {
		return magnitud_minima_servicio_operador;
	}

	public void setMagnitud_minima_servicio_operador(String magnitud_minima_servicio_operador) {
		this.magnitud_minima_servicio_operador = magnitud_minima_servicio_operador;
	}

	public String getMagnitud_maxima_servicio_operador() {
		return magnitud_maxima_servicio_operador;
	}

	public void setMagnitud_maxima_servicio_operador(String magnitud_maxima_servicio_operador) {
		this.magnitud_maxima_servicio_operador = magnitud_maxima_servicio_operador;
	}

	public int getNumero_trabajadores_servicio_operador() {
		return numero_trabajadores_servicio_operador;
	}

	public void setNumero_trabajadores_servicio_operador(int numero_trabajadores_servicio_operador) {
		this.numero_trabajadores_servicio_operador = numero_trabajadores_servicio_operador;
	}

	public int getPorcentaje_contribucion_servicio_operador() {
		return porcentaje_contribucion_servicio_operador;
	}

	public void setPorcentaje_contribucion_servicio_operador(int porcentaje_contribucion_servicio_operador) {
		this.porcentaje_contribucion_servicio_operador = porcentaje_contribucion_servicio_operador;
	}

	public int getEstado_servicio_operador() {
		return estado_servicio_operador;
	}

	public void setEstado_servicio_operador(int estado_servicio_operador) {
		this.estado_servicio_operador = estado_servicio_operador;
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