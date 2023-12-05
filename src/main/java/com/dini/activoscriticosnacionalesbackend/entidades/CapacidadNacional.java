package com.dini.activoscriticosnacionalesbackend.entidades;

import java.util.Date;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

@Entity 
@Table ( name = " TA_ACN_CAPNACIONALES " )

public class CapacidadNacional {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id_capacidad_nacional;
	
	@Column(length = 20)
	private String codigo_capacidad_nacional;
	
	private long id_sector;
	
	@Column(length = 20)
	private String codigo_sector;
	
	 @Column(length = 100)
    private String nombre_capacidad_nacional;
	 
    @Column(length = 5000)
    private String descripcion_capacidad_nacional;

    private int estado_capacidad_nacional;
    
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
    
 
    public CapacidadNacional(){
    }


	public long getId_sector() {
		return id_sector;
	}


	public void setId_sector(long id_sector) {
		this.id_sector = id_sector;
	}


	public long getId_capacidad_nacional() {
		return id_capacidad_nacional;
	}


	public void setId_capacidad_nacional(long id_capacidad_nacional) {
		this.id_capacidad_nacional = id_capacidad_nacional;
	}


	public String getCodigo_capacidad_nacional() {
		return codigo_capacidad_nacional;
	}


	public void setCodigo_capacidad_nacional(String codigo_capacidad_nacional) {
		this.codigo_capacidad_nacional = codigo_capacidad_nacional;
	}


	public String getNombre_capacidad_nacional() {
		return nombre_capacidad_nacional;
	}


	public void setNombre_capacidad_nacional(String nombre_capacidad_nacional) {
		this.nombre_capacidad_nacional = nombre_capacidad_nacional;
	}


	public String getDescripcion_capacidad_nacional() {
		return descripcion_capacidad_nacional;
	}


	public void setDescripcion_capacidad_nacional(String descripcion_capacidad_nacional) {
		this.descripcion_capacidad_nacional = descripcion_capacidad_nacional;
	}


	public int getEstado_capacidad_nacional() {
		return estado_capacidad_nacional;
	}


	public void setEstado_capacidad_nacional(int estado_capacidad_nacional) {
		this.estado_capacidad_nacional = estado_capacidad_nacional;
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


	public String getCodigo_sector() {
		return codigo_sector;
	}


	public void setCodigo_sector(String codigo_sector) {
		this.codigo_sector = codigo_sector;
	}
	
}
