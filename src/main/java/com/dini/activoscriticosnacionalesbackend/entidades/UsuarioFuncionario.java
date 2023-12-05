package com.dini.activoscriticosnacionalesbackend.entidades;

import java.util.Date;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity 
@Table ( name = " TA_ACN_USUFUNCIONARIOS" )

public class UsuarioFuncionario {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id_usuario_funcionario;
	private long id_usuario;
	
	@Column(length = 20)
	private String codigo_usuario;
	private int estado_usuario_funcionario;

	@Column(length = 9)
    private String fijo_usuario_funcionario;
	
	@Column(length = 4)
    private String anexo_usuario_funcionario;
	
	@Column(length = 9)
    private String movil_usuario_funcionario;
	
	@Column(length = 9)
    private String movil2_usuario_funcionario;
	
	@Column(length = 50)
    private String correo_usuario_funcionario;
	
    private Date fecha_alta_usuario_funcionario;
    private Date fecha_baja_usuario_funcionario;
    
    @Column(length = 200)
    private String procedencia_usuario_funcionario;
    
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
 
    public UsuarioFuncionario(){
    }
    
	public int getEstado_usuario_funcionario() {
		return estado_usuario_funcionario;
	}

	public void setEstado_usuario_funcionario(int estado_usuario_funcionario) {
		this.estado_usuario_funcionario = estado_usuario_funcionario;
	}

	public long getId_usuario_funcionario() {
		return id_usuario_funcionario;
	}

	public void setId_usuario_funcionario(long id_usuario_funcionario) {
		this.id_usuario_funcionario = id_usuario_funcionario;
	}

	public long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getCodigo_usuario() {
		return codigo_usuario;
	}

	public void setCodigo_usuario(String codigo_usuario) {
		this.codigo_usuario = codigo_usuario;
	}

	public String getFijo_usuario_funcionario() {
		return fijo_usuario_funcionario;
	}

	public void setFijo_usuario_funcionario(String fijo_usuario_funcionario) {
		this.fijo_usuario_funcionario = fijo_usuario_funcionario;
	}

	public String getAnexo_usuario_funcionario() {
		return anexo_usuario_funcionario;
	}

	public void setAnexo_usuario_funcionario(String anexo_usuario_funcionario) {
		this.anexo_usuario_funcionario = anexo_usuario_funcionario;
	}

	public String getMovil_usuario_funcionario() {
		return movil_usuario_funcionario;
	}

	public void setMovil_usuario_funcionario(String movil_usuario_funcionario) {
		this.movil_usuario_funcionario = movil_usuario_funcionario;
	}

	public String getMovil2_usuario_funcionario() {
		return movil2_usuario_funcionario;
	}

	public void setMovil2_usuario_funcionario(String movil2_usuario_funcionario) {
		this.movil2_usuario_funcionario = movil2_usuario_funcionario;
	}

	public String getCorreo_usuario_funcionario() {
		return correo_usuario_funcionario;
	}

	public void setCorreo_usuario_funcionario(String correo_usuario_funcionario) {
		this.correo_usuario_funcionario = correo_usuario_funcionario;
	}

	public Date getFecha_alta_usuario_funcionario() {
		return fecha_alta_usuario_funcionario;
	}

	public void setFecha_alta_usuario_funcionario(Date fecha_alta_usuario_funcionario) {
		this.fecha_alta_usuario_funcionario = fecha_alta_usuario_funcionario;
	}

	public Date getFecha_baja_usuario_funcionario() {
		return fecha_baja_usuario_funcionario;
	}

	public void setFecha_baja_usuario_funcionario(Date fecha_baja_usuario_funcionario) {
		this.fecha_baja_usuario_funcionario = fecha_baja_usuario_funcionario;
	}

	public String getProcedencia_usuario_funcionario() {
		return procedencia_usuario_funcionario;
	}

	public void setProcedencia_usuario_funcionario(String procedencia_usuario_funcionario) {
		this.procedencia_usuario_funcionario = procedencia_usuario_funcionario;
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