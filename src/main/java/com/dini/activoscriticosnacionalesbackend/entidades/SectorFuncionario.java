package com.dini.activoscriticosnacionalesbackend.entidades;


import java.util.Date;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

@Entity 
@Table ( name = " TA_ACN_SECFUNCIONARIOS " )

public class SectorFuncionario {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id_sector_funcionario;
	
	@Column(length = 20)
	private String codigo_sector;
	private long id_sector;
	
	@Column(length = 20)
	private String codigo_funcionario;
	private long id_funcionario;

	@Column(length = 100)
	private String cargo_sector_funcionario;
	
	@Column(length = 9)
	private String fijo_sector_funcionario;
	
	@Column(length = 4)
	private String anexo_sector_funcionario;
	
	@Column(length = 9)
    private String movil_sector_funcionario;
	
	@Column(length = 9)
    private String movil2_sector_funcionario;
	
	@Column(length = 50)
    private String email_sector_funcionario;
	
	@Column(length = 50)
    private String email2_sector_funcionario;
	
    private int estado_sector_funcionario;
    private Date inicio_sector_funcionario;
    private Date fin_sector_funcionario;
   
    
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
    
    public SectorFuncionario() {
    	
    }
   
	public long getId_sector() {
		return id_sector;
	}

	public void setId_sector(long id_sector) {
		this.id_sector = id_sector;
	}

	public long getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(long id_funcionario) {
		this.id_funcionario = id_funcionario;
	}


	public long getId_sector_funcionario() {
		return id_sector_funcionario;
	}
	public void setId_sector_funcionario(long id_sector_funcionario) {
		this.id_sector_funcionario = id_sector_funcionario;
	}
	public String getCodigo_sector() {
		return codigo_sector;
	}
	public void setCodigo_sector(String codigo_sector) {
		this.codigo_sector = codigo_sector;
	}
	public String getCodigo_funcionario() {
		return codigo_funcionario;
	}
	public void setCodigo_funcionario(String codigo_funcionario) {
		this.codigo_funcionario = codigo_funcionario;
	}
	public String getCargo_sector_funcionario() {
		return cargo_sector_funcionario;
	}
	public void setCargo_sector_funcionario(String cargo_sector_funcionario) {
		this.cargo_sector_funcionario = cargo_sector_funcionario;
	}
	public String getFijo_sector_funcionario() {
		return fijo_sector_funcionario;
	}
	public void setFijo_sector_funcionario(String fijo_sector_funcionario) {
		this.fijo_sector_funcionario = fijo_sector_funcionario;
	}
	public String getMovil_sector_funcionario() {
		return movil_sector_funcionario;
	}
	public void setMovil_sector_funcionario(String movil_sector_funcionario) {
		this.movil_sector_funcionario = movil_sector_funcionario;
	}
	public String getEmail_sector_funcionario() {
		return email_sector_funcionario;
	}
	public void setEmail_sector_funcionario(String email_sector_funcionario) {
		this.email_sector_funcionario = email_sector_funcionario;
	}
	public int getEstado_sector_funcionario() {
		return estado_sector_funcionario;
	}
	public void setEstado_sector_funcionario(int estado_sector_funcionario) {
		this.estado_sector_funcionario = estado_sector_funcionario;
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

	public String getAnexo_sector_funcionario() {
		return anexo_sector_funcionario;
	}

	public void setAnexo_sector_funcionario(String anexo_sector_funcionario) {
		this.anexo_sector_funcionario = anexo_sector_funcionario;
	}

	public String getMovil2_sector_funcionario() {
		return movil2_sector_funcionario;
	}

	public void setMovil2_sector_funcionario(String movil2_sector_funcionario) {
		this.movil2_sector_funcionario = movil2_sector_funcionario;
	}

	public Date getInicio_sector_funcionario() {
		return inicio_sector_funcionario;
	}

	public void setInicio_sector_funcionario(Date inicio_sector_funcionario) {
		this.inicio_sector_funcionario = inicio_sector_funcionario;
	}

	public Date getFin_sector_funcionario() {
		return fin_sector_funcionario;
	}

	public void setFin_sector_funcionario(Date fin_sector_funcionario) {
		this.fin_sector_funcionario = fin_sector_funcionario;
	}

	public String getEmail2_sector_funcionario() {
		return email2_sector_funcionario;
	}

	public void setEmail2_sector_funcionario(String email2_sector_funcionario) {
		this.email2_sector_funcionario = email2_sector_funcionario;
	}
	
}