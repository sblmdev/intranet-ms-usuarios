package com.dini.activoscriticosnacionalesbackend.entidades;

import java.util.Date;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

@Entity 
@Table ( name = " TA_ACN_ARCHIVOS" )

public class Archivo {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id_archivo;
	
	@Column(length = 20)
	private String codigo_archivo;
	
	@Column(length = 500)
	private String nombre_archivo;
	
	@Column(length = 500)
	private String url_archivo;
	
	private int estado_archivo;
	private int tipo_archivo;
    
  //Auditoria de creación
    private long id_usuario_creacion;
    private Date fecha_creacion;
    
    @Column(length = 20)
    private String ip_creacion;
    
    public Archivo(){
    }

	public long getId_archivo() {
		return id_archivo;
	}

	public void setId_archivo(long id_archivo) {
		this.id_archivo = id_archivo;
	}

	public String getNombre_archivo() {
		return nombre_archivo;
	}

	public void setNombre_archivo(String nombre_archivo) {
		this.nombre_archivo = nombre_archivo;
	}

	public String getUrl_archivo() {
		return url_archivo;
	}

	public void setUrl_archivo(String url_archivo) {
		this.url_archivo = url_archivo;
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

	public String getCodigo_archivo() {
		return codigo_archivo;
	}

	public void setCodigo_archivo(String codigo_archivo) {
		this.codigo_archivo = codigo_archivo;
	}

	public int getTipo_archivo() {
		return tipo_archivo;
	}

	public void setTipo_archivo(int tipo_archivo) {
		this.tipo_archivo = tipo_archivo;
	}

	public int getEstado_archivo() {
		return estado_archivo;
	}

	public void setEstado_archivo(int estado_archivo) {
		this.estado_archivo = estado_archivo;
	}

}