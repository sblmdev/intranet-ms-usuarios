package com.sblm.intranetmsusuarios.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sblm.intranetmsusuarios.entidades.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{
	 Optional<Usuario> findByNombreUsuario(String nombre_usuario);
}
