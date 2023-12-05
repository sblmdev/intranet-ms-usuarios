package com.sblm.intranetmsusuarios.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sblm.intranetmsusuarios.entidades.Rol;
import com.sblm.intranetmsusuarios.enumeraciones.RolNombre;

public interface RolRepositorio extends JpaRepository<Rol, Long>{
	 Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
