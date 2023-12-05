package com.dini.activoscriticosnacionalesbackend.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dini.activoscriticosnacionalesbackend.entidades.ActivoCriticoNacionalRiesgo;

public interface ActivoCriticoNacionalRiesgoRepositorio extends JpaRepository<ActivoCriticoNacionalRiesgo, Long>{

}
