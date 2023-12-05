package com.dini.activoscriticosnacionalesbackend.entidades;

import java.util.Date;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

@Entity 
@Table ( name = " TA_ACN_PROVINCIAS " )

public class Provincia {
	@Id
	@Column(length = 6)
	private String id_provincia;
	
	@Column(length = 30)
	private String provincia;
	
	@Column(length = 6)
	private String id_region;
 
    public Provincia(){
    }

	public String getId_provincia() {
		return id_provincia;
	}

	public void setId_provincia(String id_provincia) {
		this.id_provincia = id_provincia;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getId_region() {
		return id_region;
	}

	public void setId_region(String id_region) {
		this.id_region = id_region;
	}

	
	
}