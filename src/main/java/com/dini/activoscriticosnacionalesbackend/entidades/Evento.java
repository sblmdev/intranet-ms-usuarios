package com.dini.activoscriticosnacionalesbackend.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table ; 
import javax.persistence.JoinColumn;

@Entity 
@Table ( name = " TA_ACN_EVENTOS" )

public class Evento {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id_evento;
	
	@Column(length = 20)
	private String codigo_evento;
	
	@Column(length = 20)
	private String codigo_acn;
	
	private Date fecha_evento;
	private int gravedad_evento;
	private int fuente_evento;
	private int tipo_documento;

	@Column(length = 10000)
	private String descripcion_evento;

	@Column(length = 10000)
	private String estructura_evento;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="id_referencia")
	private List<Archivo> archivos;
    
  //Auditoria de creación
    private long id_usuario_creacion;
    private Date fecha_creacion;
    
    @Column(length = 20)
    private String ip_creacion;
 
    public Evento(){
    }

	public long getId_evento() {
		return id_evento;
	}

	public void setId_evento(long id_evento) {
		this.id_evento = id_evento;
	}

	public String getCodigo_evento() {
		return codigo_evento;
	}

	public void setCodigo_evento(String codigo_evento) {
		this.codigo_evento = codigo_evento;
	}

	public String getCodigo_acn() {
		return codigo_acn;
	}

	public void setCodigo_acn(String codigo_acn) {
		this.codigo_acn = codigo_acn;
	}

	public Date getFecha_evento() {
		return fecha_evento;
	}

	public void setFecha_evento(Date fecha_evento) {
		this.fecha_evento = fecha_evento;
	}

	public int getGravedad_evento() {
		return gravedad_evento;
	}

	public void setGravedad_evento(int gravedad_evento) {
		this.gravedad_evento = gravedad_evento;
	}

	public int getFuente_evento() {
		return fuente_evento;
	}

	public void setFuente_evento(int fuente_evento) {
		this.fuente_evento = fuente_evento;
	}

	public int getTipo_documento() {
		return tipo_documento;
	}

	public void setTipo_documento(int tipo_documento) {
		this.tipo_documento = tipo_documento;
	}

	public String getDescripcion_evento() {
		return descripcion_evento;
	}

	public void setDescripcion_evento(String descripcion_evento) {
		this.descripcion_evento = descripcion_evento;
	}

	public String getEstructura_evento() {
		return estructura_evento;
	}

	public void setEstructura_evento(String estructura_evento) {
		this.estructura_evento = estructura_evento;
	}
	
	public List<Archivo> getArchivos() {
		return archivos;
	}

	public void setArchivos(List<Archivo> archivos) {
		this.archivos = archivos;
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

}