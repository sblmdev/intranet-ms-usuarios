package com.dini.activoscriticosnacionalesbackend.entidades;

import java.util.Date;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

@Entity 
@Table ( name = " TA_ACN_OPEACNUBICACIONES " )

public class OperadorUbicacion {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id_ubicacion_operador;
	private String codigo_operador;
	private String codigo_acn;
	private long id_acn;
	private long id_operador;
	
    private String coordenadas_ubicacion_operador;
    private String vias_acceso_ubicacion_operador;
    private String croquis_ubicacion_operador;
    private String direccion_ubicacion_operador;
    private String domicilio_legal_ubicacion_operador;
    private String postal_direccion_ubicacion_operador;
    private String postal_domicilio_ubicacion_operador;
    private String foto_satelital_ubicacion_operador;
    private String extension_ubicacion_operador;
    private String ficha_registral_ubicacion_operador;
    private String carreteras_ubicacion_operador;
    private String oceanos_ubicacion_operador;
    private String dependencia_policial_ubicacion_operador;
    private String compañia_bombero_ubicacion_operador;
    
    private int estado_ubicacion_operador;
        
  //Auditoria de creación
    private long id_usuario_creacion;
    private Date fecha_creacion;
    private String ip_creacion;
    
  //Auditoria de modificación
    private long id_usuario_modificacion;
    private Date fecha_modificacion;
    private String ip_modificacion;
    
    public OperadorUbicacion() {
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



	public long getId_ubicacion_operador() {
		return id_ubicacion_operador;
	}

	public void setId_ubicacion_operador(long id_ubicacion_operador) {
		this.id_ubicacion_operador = id_ubicacion_operador;
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

	public String getCoordenadas_ubicacion_operador() {
		return coordenadas_ubicacion_operador;
	}

	public void setCoordenadas_ubicacion_operador(String coordenadas_ubicacion_operador) {
		this.coordenadas_ubicacion_operador = coordenadas_ubicacion_operador;
	}

	public String getVias_acceso_ubicacion_operador() {
		return vias_acceso_ubicacion_operador;
	}

	public void setVias_acceso_ubicacion_operador(String vias_acceso_ubicacion_operador) {
		this.vias_acceso_ubicacion_operador = vias_acceso_ubicacion_operador;
	}

	public String getCroquis_ubicacion_operador() {
		return croquis_ubicacion_operador;
	}

	public void setCroquis_ubicacion_operador(String croquis_ubicacion_operador) {
		this.croquis_ubicacion_operador = croquis_ubicacion_operador;
	}

	public String getDireccion_ubicacion_operador() {
		return direccion_ubicacion_operador;
	}

	public void setDireccion_ubicacion_operador(String direccion_ubicacion_operador) {
		this.direccion_ubicacion_operador = direccion_ubicacion_operador;
	}

	public String getDomicilio_legal_ubicacion_operador() {
		return domicilio_legal_ubicacion_operador;
	}

	public void setDomicilio_legal_ubicacion_operador(String domicilio_legal_ubicacion_operador) {
		this.domicilio_legal_ubicacion_operador = domicilio_legal_ubicacion_operador;
	}

	public String getPostal_direccion_ubicacion_operador() {
		return postal_direccion_ubicacion_operador;
	}

	public void setPostal_direccion_ubicacion_operador(String postal_direccion_ubicacion_operador) {
		this.postal_direccion_ubicacion_operador = postal_direccion_ubicacion_operador;
	}

	public String getPostal_domicilio_ubicacion_operador() {
		return postal_domicilio_ubicacion_operador;
	}

	public void setPostal_domicilio_ubicacion_operador(String postal_domicilio_ubicacion_operador) {
		this.postal_domicilio_ubicacion_operador = postal_domicilio_ubicacion_operador;
	}

	public String getFoto_satelital_ubicacion_operador() {
		return foto_satelital_ubicacion_operador;
	}

	public void setFoto_satelital_ubicacion_operador(String foto_satelital_ubicacion_operador) {
		this.foto_satelital_ubicacion_operador = foto_satelital_ubicacion_operador;
	}

	public String getExtension_ubicacion_operador() {
		return extension_ubicacion_operador;
	}

	public void setExtension_ubicacion_operador(String extension_ubicacion_operador) {
		this.extension_ubicacion_operador = extension_ubicacion_operador;
	}

	public String getFicha_registral_ubicacion_operador() {
		return ficha_registral_ubicacion_operador;
	}

	public void setFicha_registral_ubicacion_operador(String ficha_registral_ubicacion_operador) {
		this.ficha_registral_ubicacion_operador = ficha_registral_ubicacion_operador;
	}

	public String getCarreteras_ubicacion_operador() {
		return carreteras_ubicacion_operador;
	}

	public void setCarreteras_ubicacion_operador(String carreteras_ubicacion_operador) {
		this.carreteras_ubicacion_operador = carreteras_ubicacion_operador;
	}

	public String getOceanos_ubicacion_operador() {
		return oceanos_ubicacion_operador;
	}

	public void setOceanos_ubicacion_operador(String oceanos_ubicacion_operador) {
		this.oceanos_ubicacion_operador = oceanos_ubicacion_operador;
	}

	public String getDependencia_policial_ubicacion_operador() {
		return dependencia_policial_ubicacion_operador;
	}

	public void setDependencia_policial_ubicacion_operador(String dependencia_policial_ubicacion_operador) {
		this.dependencia_policial_ubicacion_operador = dependencia_policial_ubicacion_operador;
	}

	public String getCompañia_bombero_ubicacion_operador() {
		return compañia_bombero_ubicacion_operador;
	}

	public void setCompañia_bombero_ubicacion_operador(String compañia_bombero_ubicacion_operador) {
		this.compañia_bombero_ubicacion_operador = compañia_bombero_ubicacion_operador;
	}

	public int getEstado_ubicacion_operador() {
		return estado_ubicacion_operador;
	}

	public void setEstado_ubicacion_operador(int estado_ubicacion_operador) {
		this.estado_ubicacion_operador = estado_ubicacion_operador;
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