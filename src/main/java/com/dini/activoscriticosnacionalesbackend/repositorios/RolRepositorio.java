package com.dini.activoscriticosnacionalesbackend.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dini.activoscriticosnacionalesbackend.entidades.Rol;
import com.dini.activoscriticosnacionalesbackend.enumeraciones.RolNombre;

public interface RolRepositorio extends JpaRepository<Rol, Long>{
	 Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
