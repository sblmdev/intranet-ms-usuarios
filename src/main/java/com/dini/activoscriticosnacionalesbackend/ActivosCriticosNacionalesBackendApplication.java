package com.dini.activoscriticosnacionalesbackend;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dini.activoscriticosnacionalesbackend.entidades.Archivo;
import com.dini.activoscriticosnacionalesbackend.entidades.Evento;
import com.dini.activoscriticosnacionalesbackend.repositorios.EventoRepositorio;

@SpringBootApplication
public class ActivosCriticosNacionalesBackendApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ActivosCriticosNacionalesBackendApplication.class, args);
	}

}
