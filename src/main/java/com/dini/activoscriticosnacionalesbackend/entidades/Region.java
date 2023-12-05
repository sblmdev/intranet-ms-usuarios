package com.dini.activoscriticosnacionalesbackend.entidades;

import java.util.Date;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

@Entity 
@Table ( name = " TA_ACN_REGIONES " )

public class Region {
	@Id
	@Column(length = 6)
	private String id_region;
	
	@Column(length = 30)
	private String region;
 
    public Region(){
    }

	public String getId_region() {
		return id_region;
	}

	public void setId_region(String id_region) {
		this.id_region = id_region;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

}