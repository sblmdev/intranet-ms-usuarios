package com.dini.activoscriticosnacionalesbackend.entidades;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

@Entity 
@Table ( name = " TA_ACN_CODIGOS " )

public class Codigo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    
    private long capacidades_nacionales;
    private long sub_capacidades_nacionales;
    private long clasificaciones_acn;
    private long objetivos_nacionales;
    private long activos_criticos_nacionales;
    private long operadores;
    private long usuarios;
    private long sectores;
    private long contactos;
    private long funcionarios;
    private long documentos;
    private long eventos;
    private long archivos;
    private long files_servers;
    
    public Codigo(){
    }

	public long getContactos() {
		return contactos;
	}

	public void setContactos(long contactos) {
		this.contactos = contactos;
	}

	public long getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(long funcionarios) {
		this.funcionarios = funcionarios;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCapacidades_nacionales() {
		return capacidades_nacionales;
	}

	public void setCapacidades_nacionales(long capacidades_nacionales) {
		this.capacidades_nacionales = capacidades_nacionales;
	}

	public long getSub_capacidades_nacionales() {
		return sub_capacidades_nacionales;
	}

	public void setSub_capacidades_nacionales(long sub_capacidades_nacionales) {
		this.sub_capacidades_nacionales = sub_capacidades_nacionales;
	}

	public long getClasificaciones_acn() {
		return clasificaciones_acn;
	}

	public void setClasificaciones_acn(long clasificaciones_acn) {
		this.clasificaciones_acn = clasificaciones_acn;
	}

	public long getObjetivos_nacionales() {
		return objetivos_nacionales;
	}

	public void setObjetivos_nacionales(long objetivos_nacionales) {
		this.objetivos_nacionales = objetivos_nacionales;
	}

	public long getActivos_criticos_nacionales() {
		return activos_criticos_nacionales;
	}

	public void setActivos_criticos_nacionales(long activos_criticos_nacionales) {
		this.activos_criticos_nacionales = activos_criticos_nacionales;
	}

	public long getOperadores() {
		return operadores;
	}

	public void setOperadores(long operadores) {
		this.operadores = operadores;
	}

	public long getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(long usuarios) {
		this.usuarios = usuarios;
	}

	public long getSectores() {
		return sectores;
	}

	public void setSectores(long sectores) {
		this.sectores = sectores;
	}

	public long getDocumentos() {
		return documentos;
	}

	public void setDocumentos(long documentos) {
		this.documentos = documentos;
	}

	public long getEventos() {
		return eventos;
	}

	public void setEventos(long eventos) {
		this.eventos = eventos;
	}

	public long getArchivos() {
		return archivos;
	}

	public void setArchivos(long archivos) {
		this.archivos = archivos;
	}

	public long getFiles_servers() {
		return files_servers;
	}

	public void setFiles_servers(long files_server) {
		this.files_servers = files_server;
	}
	
	
}