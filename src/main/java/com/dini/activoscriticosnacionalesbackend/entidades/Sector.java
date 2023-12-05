package com.dini.activoscriticosnacionalesbackend.entidades;

import java.util.Date;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

@Entity 
@Table ( name = " TA_ACN_SECTORES " )
public class Sector {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id_sector;
	
	@Column(length = 20)
	private String codigo_sector;
	
	@Column(length = 200)
    private String nombre_sector;
	
	@Column(length = 20)
    private String sigla_sector;
    private int tipo_sector;
	
    @Column(length = 5000)
    private String descripcion_sector;
    private int estado_sector;
    
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
    
    public Sector() {	
    }

	public Sector(long id_sector, String codigo_sector, String nombre_sector, String descripcion_sector,
			int estado_sector, long id_usuario_creacion, Date fecha_creacion, String ip_creacion,
			long id_usuario_modificacion, Date fecha_modificacion, String ip_modificacion) {
		super();
		this.id_sector = id_sector;
		this.codigo_sector = codigo_sector;
		this.nombre_sector = nombre_sector;
		this.descripcion_sector = descripcion_sector;
		this.estado_sector = estado_sector;
		this.id_usuario_creacion = id_usuario_creacion;
		this.fecha_creacion = fecha_creacion;
		this.ip_creacion = ip_creacion;
		this.id_usuario_modificacion = id_usuario_modificacion;
		this.fecha_modificacion = fecha_modificacion;
		this.ip_modificacion = ip_modificacion;
	}

	public long getId_sector() {
		return id_sector;
	}

	public void setId_sector(long id_sector) {
		this.id_sector = id_sector;
	}

	public String getCodigo_sector() {
		return codigo_sector;
	}

	public void setCodigo_sector(String codigo_sector) {
		this.codigo_sector = codigo_sector;
	}

	public String getNombre_sector() {
		return nombre_sector;
	}

	public void setNombre_sector(String nombre_sector) {
		this.nombre_sector = nombre_sector;
	}

	public String getDescripcion_sector() {
		return descripcion_sector;
	}

	public void setDescripcion_sector(String descripcion_sector) {
		this.descripcion_sector = descripcion_sector;
	}

	public int getEstado_sector() {
		return estado_sector;
	}

	public void setEstado_sector(int estado_sector) {
		this.estado_sector = estado_sector;
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

	public String getSigla_sector() {
		return sigla_sector;
	}

	public void setSigla_sector(String sigla_sector) {
		this.sigla_sector = sigla_sector;
	}

	public int getTipo_sector() {
		return tipo_sector;
	}

	public void setTipo_sector(int tipo_sector) {
		this.tipo_sector = tipo_sector;
	}
	
	

}