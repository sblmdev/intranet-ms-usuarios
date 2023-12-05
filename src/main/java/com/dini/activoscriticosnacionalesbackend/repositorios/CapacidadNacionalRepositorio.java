package com.dini.activoscriticosnacionalesbackend.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dini.activoscriticosnacionalesbackend.entidades.CapacidadNacional;

public interface CapacidadNacionalRepositorio extends JpaRepository<CapacidadNacional, Long>{


}
