package com.sblm.intranetmsusuarios.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sblm.intranetmsusuarios.dto.JwtDTO;
import com.sblm.intranetmsusuarios.dto.LoginUsuario;
import com.sblm.intranetmsusuarios.dto.Mensaje;
import com.sblm.intranetmsusuarios.dto.NuevoUsuario;
import com.sblm.intranetmsusuarios.entidades.Rol;
import com.sblm.intranetmsusuarios.entidades.Usuario;
import com.sblm.intranetmsusuarios.enumeraciones.RolNombre;
import com.sblm.intranetmsusuarios.seguridad.jwt.JwtProvider;
import com.sblm.intranetmsusuarios.servicios.RolServicio;
import com.sblm.intranetmsusuarios.servicios.UsuarioServicio;

import javax.validation.Valid;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
public class AuthControlador {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioServicio usuarioService;

    @Autowired
    RolServicio rolService;

    @Autowired
    JwtProvider jwtProvider;
   

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        Usuario usuario =
                new Usuario(
                		nuevoUsuario.getDni(),
						nuevoUsuario.getTipo(),
						nuevoUsuario.getEstado(),
						nuevoUsuario.getUsuario(),
						passwordEncoder.encode(nuevoUsuario.getContrasena()),
						new Date()
						);
        
        Set<String> rolesStr = nuevoUsuario.getRoles();
        Set<Rol> roles = new HashSet<>();
        for (String rol : rolesStr) {
        	Rol rolAdmin =  null;
            switch (rol) {
                case "administrador":
                    rolAdmin = rolService.getByRolNombre(RolNombre.ROLE_ADMINISTRADOR).get();
                    roles.add(rolAdmin);
                    break;
                case "publicador":
                    rolAdmin = rolService.getByRolNombre(RolNombre.ROLE_PUBLICADOR).get();
                    roles.add(rolAdmin);
                    break;
                case "cementerio":
                    rolAdmin = rolService.getByRolNombre(RolNombre.ROLE_CEMENTERIO).get();
                    roles.add(rolAdmin);
                    break;
            }
        }
        usuario.setRoles(roles);
        
        usuarioService.guardar(usuario);
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Usuario o contraseña incorrecta"), HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<JwtDTO>(jwtDTO, HttpStatus.OK);
    }
    
}