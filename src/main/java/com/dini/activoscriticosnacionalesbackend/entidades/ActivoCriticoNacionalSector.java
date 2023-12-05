package com.dini.activoscriticosnacionalesbackend.entidades;

import java.util.Date;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

@Entity 
@Table ( name = " TA_ACN_ACNSECTORES" )

public class ActivoCriticoNacionalSector {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id_acn_sector;
	
	@Column(length = 20)
	private String codigo_acn;
	
	private long id_acn;
	
	@Column(length = 20)
	private String codigo_sector;
	
	private long id_sector;
	
	private int tipo_sector;
	private int estado_acn_sector;


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
 
    public ActivoCriticoNacionalSector(){
    }
    
	public long getId_acn() {
		return id_acn;
	}

	public void setId_acn(long id_acn) {
		this.id_acn = id_acn;
	}

	public long getId_sector() {
		return id_sector;
	}

	public void setId_sector(long id_sector) {
		this.id_sector = id_sector;
	}

	public long getId_acn_sector() {
		return id_acn_sector;
	}

	public void setId_acn_sector(long id_acn_sector) {
		this.id_acn_sector = id_acn_sector;
	}

	public String getCodigo_acn() {
		return codigo_acn;
	}

	public void setCodigo_acn(String codigo_acn) {
		this.codigo_acn = codigo_acn;
	}

	public String getCodigo_sector() {
		return codigo_sector;
	}

	public void setCodigo_sector(String codigo_sector) {
		this.codigo_sector = codigo_sector;
	}

	public int getEstado_acn_sector() {
		return estado_acn_sector;
	}

	public void setEstado_acn_sector(int estado_acn_sector) {
		this.estado_acn_sector = estado_acn_sector;
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

	public int getTipo_sector() {
		return tipo_sector;
	}

	public void setTipo_sector(int tipo_sector) {
		this.tipo_sector = tipo_sector;
	}

}