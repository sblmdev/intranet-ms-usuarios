package com.dini.activoscriticosnacionalesbackend.entidades;

import java.util.Date;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

@Entity 
@Table ( name = " TA_ACN_OPERADORES " )

public class Operador {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id_operador;
	
	@Column(length = 20)
	private String codigo_operador;
	
	@Column(length = 100)
    private String nombre_operador;
	
	@Column(length = 20)
	private String criptonomo_operador;
	
    private int consorcio_operador;
    private Date fecha_inicio_operador;
    private int tipo_operador;
    private int tipo_nacionalidad_operador;
    private int nacionalidad_operador;
    private int estado_operador;
    
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
    
    public Operador() {
    }


	public String getCriptonomo_operador() {
		return criptonomo_operador;
	}

	public void setCriptonomo_operador(String criptonomo_operador) {
		this.criptonomo_operador = criptonomo_operador;
	}

	public long getId_operador() {
		return id_operador;
	}

	public void setId_operador(long id_operador) {
		this.id_operador = id_operador;
	}

	public String getCodigo_operador() {
		return codigo_operador;
	}

	public void setCodigo_operador(String codigo_operador) {
		this.codigo_operador = codigo_operador;
	}

	public String getNombre_operador() {
		return nombre_operador;
	}

	public void setNombre_operador(String nombre_operador) {
		this.nombre_operador = nombre_operador;
	}

	public int getConsorcio_operador() {
		return consorcio_operador;
	}

	public void setConsorcio_operador(int consorcio_operador) {
		this.consorcio_operador = consorcio_operador;
	}

	public Date getFecha_inicio_operador() {
		return fecha_inicio_operador;
	}

	public void setFecha_inicio_operador(Date fecha_inicio_operador) {
		this.fecha_inicio_operador = fecha_inicio_operador;
	}

	public int getTipo_operador() {
		return tipo_operador;
	}

	public void setTipo_operador(int tipo_operador) {
		this.tipo_operador = tipo_operador;
	}

	public int getTipo_nacionalidad_operador() {
		return tipo_nacionalidad_operador;
	}

	public void setTipo_nacionalidad_operador(int tipo_nacionalidad_operador) {
		this.tipo_nacionalidad_operador = tipo_nacionalidad_operador;
	}

	public int getNacionalidad_operador() {
		return nacionalidad_operador;
	}

	public void setNacionalidad_operador(int nacionalidad_operador) {
		this.nacionalidad_operador = nacionalidad_operador;
	}

	public int getEstado_operador() {
		return estado_operador;
	}

	public void setEstado_operador(int estado_operador) {
		this.estado_operador = estado_operador;
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