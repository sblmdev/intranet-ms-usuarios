package com.dini.activoscriticosnacionalesbackend.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dini.activoscriticosnacionalesbackend.entidades.UsuarioFuncionario;

public interface UsuarioFuncionarioRepositorio extends JpaRepository<UsuarioFuncionario, Long>{

}
