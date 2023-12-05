package com.dini.activoscriticosnacionalesbackend.servicios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dini.activoscriticosnacionalesbackend.entidades.Rol;
import com.dini.activoscriticosnacionalesbackend.enumeraciones.RolNombre;
import com.dini.activoscriticosnacionalesbackend.repositorios.RolRepositorio;

import java.util.Optional;

@Service
@Transactional
public class RolServicio{

    @Autowired
    RolRepositorio rolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }
}
