package com.sblm.intranetmsusuarios.servicios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sblm.intranetmsusuarios.entidades.Rol;
import com.sblm.intranetmsusuarios.enumeraciones.RolNombre;
import com.sblm.intranetmsusuarios.repositorios.RolRepositorio;

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
