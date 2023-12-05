package com.dini.activoscriticosnacionalesbackend.entidades;


import java.util.Date;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

@Entity 
@Table ( name = " TA_ACN_OPEREPLEGALES " )

public class OperadorFuncionario {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id_operador_funcionario;
	
	@Column(length = 20)
	private String codigo_operador;
	
	private long id_operador;
	
	@Column(length = 20)
	private String codigo_funcionario;
	
	private long id_funcionario;

	@Column(length = 100)
	private String cargo_operador_funcionario; 
	
	@Column(length = 9)
	private String fijo_operador_funcionario;
	
	@Column(length = 4)
	private String anexo_operador_funcionario;
	
	@Column(length = 9)
    private String movil_operador_funcionario;
	
	@Column(length = 9)
    private String movil2_operador_funcionario;
	
	@Column(length = 50)
    private String email_operador_funcionario;
	
	@Column(length = 50)
    private String email2_operador_funcionario;
	
    private int estado_operador_funcionario;
    private Date inicio_operador_funcionario;
    private Date fin_operador_funcionario;
    
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
    
    public OperadorFuncionario() {
    	
    }
    
	public long getId_operador() {
		return id_operador;
	}

	public void setId_operador(long id_operador) {
		this.id_operador = id_operador;
	}

	public long getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(long id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	public long getId_operador_funcionario() {
		return id_operador_funcionario;
	}

	public void setId_operador_funcionario(long id_operador_funcionario) {
		this.id_operador_funcionario = id_operador_funcionario;
	}

	public String getCodigo_operador() {
		return codigo_operador;
	}

	public void setCodigo_operador(String codigo_operador) {
		this.codigo_operador = codigo_operador;
	}

	public String getCodigo_funcionario() {
		return codigo_funcionario;
	}

	public void setCodigo_funcionario(String codigo_funcionario) {
		this.codigo_funcionario = codigo_funcionario;
	}

	public String getCargo_operador_funcionario() {
		return cargo_operador_funcionario;
	}

	public void setCargo_operador_funcionario(String cargo_operador_funcionario) {
		this.cargo_operador_funcionario = cargo_operador_funcionario;
	}

	public String getFijo_operador_funcionario() {
		return fijo_operador_funcionario;
	}

	public void setFijo_operador_funcionario(String fijo_operador_funcionario) {
		this.fijo_operador_funcionario = fijo_operador_funcionario;
	}

	public String getAnexo_operador_funcionario() {
		return anexo_operador_funcionario;
	}

	public void setAnexo_operador_funcionario(String anexo_operador_funcionario) {
		this.anexo_operador_funcionario = anexo_operador_funcionario;
	}

	public String getMovil_operador_funcionario() {
		return movil_operador_funcionario;
	}

	public void setMovil_operador_funcionario(String movil_operador_funcionario) {
		this.movil_operador_funcionario = movil_operador_funcionario;
	}

	public String getMovil2_operador_funcionario() {
		return movil2_operador_funcionario;
	}

	public void setMovil2_operador_funcionario(String movil2_operador_funcionario) {
		this.movil2_operador_funcionario = movil2_operador_funcionario;
	}

	public String getEmail_operador_funcionario() {
		return email_operador_funcionario;
	}

	public void setEmail_operador_funcionario(String email_operador_funcionario) {
		this.email_operador_funcionario = email_operador_funcionario;
	}

	public String getEmail2_operador_funcionario() {
		return email2_operador_funcionario;
	}

	public void setEmail2_operador_funcionario(String email2_operador_funcionario) {
		this.email2_operador_funcionario = email2_operador_funcionario;
	}

	public int getEstado_operador_funcionario() {
		return estado_operador_funcionario;
	}

	public void setEstado_operador_funcionario(int estado_operador_funcionario) {
		this.estado_operador_funcionario = estado_operador_funcionario;
	}

	public Date getInicio_operador_funcionario() {
		return inicio_operador_funcionario;
	}

	public void setInicio_operador_funcionario(Date inicio_operador_funcionario) {
		this.inicio_operador_funcionario = inicio_operador_funcionario;
	}

	public Date getFin_operador_funcionario() {
		return fin_operador_funcionario;
	}

	public void setFin_operador_funcionario(Date fin_operador_funcionario) {
		this.fin_operador_funcionario = fin_operador_funcionario;
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