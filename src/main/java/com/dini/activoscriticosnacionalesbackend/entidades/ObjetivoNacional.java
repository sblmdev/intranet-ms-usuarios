package com.dini.activoscriticosnacionalesbackend.entidades;

import java.util.Date;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

@Entity 
@Table ( name = " TA_ACN_OBJNACIONALES " )

public class ObjetivoNacional {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id_objetivo_nacional;
    
    @Column(length = 20)
    private String codigo_objetivo_nacional;
    
    @Column(length = 500)
    private String nombre_objetivo_nacional;
    
    @Column(length = 5000)
    private String descripcion_objetivo_nacional;
    
    private int estado_objetivo_nacional;
    
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
 
    public ObjetivoNacional(){
    }

	public ObjetivoNacional(long id_objetivo_nacional, String codigo_objetivo_nacional, String nombre_objetivo_nacional,
			String descripcion_objetivo_nacional, int estado_objetivo_nacional, long id_usuario_creacion,
			Date fecha_creacion, String ip_creacion, long id_usuario_modificacion, Date fecha_modificacion,
			String ip_modificacion) {
		super();
		this.id_objetivo_nacional = id_objetivo_nacional;
		this.codigo_objetivo_nacional = codigo_objetivo_nacional;
		this.nombre_objetivo_nacional = nombre_objetivo_nacional;
		this.descripcion_objetivo_nacional = descripcion_objetivo_nacional;
		this.estado_objetivo_nacional = estado_objetivo_nacional;
		this.id_usuario_creacion = id_usuario_creacion;
		this.fecha_creacion = fecha_creacion;
		this.ip_creacion = ip_creacion;
		this.id_usuario_modificacion = id_usuario_modificacion;
		this.fecha_modificacion = fecha_modificacion;
		this.ip_modificacion = ip_modificacion;
	}

	public long getId_objetivo_nacional() {
		return id_objetivo_nacional;
	}

	public void setId_objetivo_nacional(long id_objetivo_nacional) {
		this.id_objetivo_nacional = id_objetivo_nacional;
	}

	public String getCodigo_objetivo_nacional() {
		return codigo_objetivo_nacional;
	}

	public void setCodigo_objetivo_nacional(String codigo_objetivo_nacional) {
		this.codigo_objetivo_nacional = codigo_objetivo_nacional;
	}

	public String getNombre_objetivo_nacional() {
		return nombre_objetivo_nacional;
	}

	public void setNombre_objetivo_nacional(String nombre_objetivo_nacional) {
		this.nombre_objetivo_nacional = nombre_objetivo_nacional;
	}

	public String getDescripcion_objetivo_nacional() {
		return descripcion_objetivo_nacional;
	}

	public void setDescripcion_objetivo_nacional(String descripcion_objetivo_nacional) {
		this.descripcion_objetivo_nacional = descripcion_objetivo_nacional;
	}

	public int getEstado_objetivo_nacional() {
		return estado_objetivo_nacional;
	}

	public void setEstado_objetivo_nacional(int estado_objetivo_nacional) {
		this.estado_objetivo_nacional = estado_objetivo_nacional;
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
