package com.dini.activoscriticosnacionalesbackend.entidades;


import java.util.Date;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

@Entity 
@Table ( name = " TA_ACN_OPEACNCONTACTOS " )

public class OperadorContacto {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id_operador_contacto;
	private String codigo_operador;
	private String codigo_funcionario;
	private long id_funcionario;
	private String codigo_acn;
	private long id_acn;
	private long id_operador;

	private String cargo_operador_contacto; 
	private String fijo_operador_contacto;
	private String anexo_operador_contacto;
    private String movil_operador_contacto;
    private String movil2_operador_contacto;
    private String email_operador_contacto;
    private String email2_operador_contacto;
    private int estado_operador_contacto;
    private Date inicio_operador_contacto;
    private Date fin_operador_contacto;
    private int tipo_operador_contacto;
    
  //Auditoria de creación
    private long id_usuario_creacion;
    private Date fecha_creacion;
    private String ip_creacion;
    
  //Auditoria de modificación
    private long id_usuario_modificacion;
    private Date fecha_modificacion;
    private String ip_modificacion;
    
    public OperadorContacto() {
    	
    }
    
	public long getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(long id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	public long getId_operador() {
		return id_operador;
	}

	public void setId_operador(long id_operador) {
		this.id_operador = id_operador;
	}

	public long getId_operador_contacto() {
		return id_operador_contacto;
	}

	public void setId_operador_contacto(long id_operador_contacto) {
		this.id_operador_contacto = id_operador_contacto;
	}

	public String getCodigo_operador() {
		return codigo_operador;
	}

	public void setCodigo_operador(String codigo_operador) {
		this.codigo_operador = codigo_operador;
	}

	public String getCargo_operador_contacto() {
		return cargo_operador_contacto;
	}

	public void setCargo_operador_contacto(String cargo_operador_contacto) {
		this.cargo_operador_contacto = cargo_operador_contacto;
	}

	public String getFijo_operador_contacto() {
		return fijo_operador_contacto;
	}

	public void setFijo_operador_contacto(String fijo_operador_contacto) {
		this.fijo_operador_contacto = fijo_operador_contacto;
	}

	public String getAnexo_operador_contacto() {
		return anexo_operador_contacto;
	}

	public void setAnexo_operador_contacto(String anexo_operador_contacto) {
		this.anexo_operador_contacto = anexo_operador_contacto;
	}

	public String getMovil_operador_contacto() {
		return movil_operador_contacto;
	}

	public void setMovil_operador_contacto(String movil_operador_contacto) {
		this.movil_operador_contacto = movil_operador_contacto;
	}

	public String getMovil2_operador_contacto() {
		return movil2_operador_contacto;
	}

	public void setMovil2_operador_contacto(String movil2_operador_contacto) {
		this.movil2_operador_contacto = movil2_operador_contacto;
	}

	public String getEmail_operador_contacto() {
		return email_operador_contacto;
	}

	public void setEmail_operador_contacto(String email_operador_contacto) {
		this.email_operador_contacto = email_operador_contacto;
	}

	public String getEmail2_operador_contacto() {
		return email2_operador_contacto;
	}

	public void setEmail2_operador_contacto(String email2_operador_contacto) {
		this.email2_operador_contacto = email2_operador_contacto;
	}

	public int getEstado_operador_contacto() {
		return estado_operador_contacto;
	}

	public void setEstado_operador_contacto(int estado_operador_contacto) {
		this.estado_operador_contacto = estado_operador_contacto;
	}

	public Date getInicio_operador_contacto() {
		return inicio_operador_contacto;
	}

	public void setInicio_operador_contacto(Date inicio_operador_contacto) {
		this.inicio_operador_contacto = inicio_operador_contacto;
	}

	public Date getFin_operador_contacto() {
		return fin_operador_contacto;
	}

	public void setFin_operador_contacto(Date fin_operador_contacto) {
		this.fin_operador_contacto = fin_operador_contacto;
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

	public String getCodigo_acn() {
		return codigo_acn;
	}

	public void setCodigo_acn(String codigo_acn) {
		this.codigo_acn = codigo_acn;
	}

	public int getTipo_operador_contacto() {
		return tipo_operador_contacto;
	}

	public void setTipo_operador_contacto(int tipo_operador_contacto) {
		this.tipo_operador_contacto = tipo_operador_contacto;
	}

	public String getCodigo_funcionario() {
		return codigo_funcionario;
	}

	public void setCodigo_funcionario(String codigo_funcionario) {
		this.codigo_funcionario = codigo_funcionario;
	}

	public long getId_acn() {
		return id_acn;
	}

	public void setId_acn(long id_acn) {
		this.id_acn = id_acn;
	}
	
}