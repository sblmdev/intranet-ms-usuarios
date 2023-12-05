package com.dini.activoscriticosnacionalesbackend.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

@Entity 
@Table ( name = " TA_ACN_FILESERVERS " )

public class FileServer {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id_file_server;
	
	@Column(length = 20)
	private String codigo_file_server;
	
	@Column(length = 500)
	private String url_file_server;
	
	@Column(length = 500)
	private String dir_file_server;
	private int estado_file_server;
    
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
 
    public FileServer(){
    }

	public long getId_file_server() {
		return id_file_server;
	}

	public void setId_file_server(long id_file_server) {
		this.id_file_server = id_file_server;
	}

	public String getCodigo_file_server() {
		return codigo_file_server;
	}

	public void setCodigo_file_server(String codigo_file_server) {
		this.codigo_file_server = codigo_file_server;
	}

	public String getUrl_file_server() {
		return url_file_server;
	}

	public void setUrl_file_server(String url_file_server) {
		this.url_file_server = url_file_server;
	}

	public String getDir_file_server() {
		return dir_file_server;
	}

	public void setDir_file_server(String dir_file_server) {
		this.dir_file_server = dir_file_server;
	}

	public int getEstado_file_server() {
		return estado_file_server;
	}

	public void setEstado_file_server(int estado_file_server) {
		this.estado_file_server = estado_file_server;
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