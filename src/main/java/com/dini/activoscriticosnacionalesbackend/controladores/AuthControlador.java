package com.dini.activoscriticosnacionalesbackend.controladores;

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

import com.dini.activoscriticosnacionalesbackend.dto.JwtDTO;
import com.dini.activoscriticosnacionalesbackend.dto.LoginUsuario;
import com.dini.activoscriticosnacionalesbackend.dto.Mensaje;
import com.dini.activoscriticosnacionalesbackend.dto.NuevoUsuario;
import com.dini.activoscriticosnacionalesbackend.entidades.FileServer;
import com.dini.activoscriticosnacionalesbackend.entidades.Funcionario;
import com.dini.activoscriticosnacionalesbackend.entidades.Rol;
import com.dini.activoscriticosnacionalesbackend.entidades.Usuario;
import com.dini.activoscriticosnacionalesbackend.enumeraciones.RolNombre;
import com.dini.activoscriticosnacionalesbackend.repositorios.FuncionarioRepositorio;
import com.dini.activoscriticosnacionalesbackend.seguridad.jwt.JwtProvider;
import com.dini.activoscriticosnacionalesbackend.servicios.RolServicio;
import com.dini.activoscriticosnacionalesbackend.servicios.UsuarioServicio;

import javax.validation.Valid;

import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
    
    @Autowired
    private FileServerControlador fileServerControlador;
    
   

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        Usuario usuario =
                new Usuario(
                		nuevoUsuario.getCodigo_usuario(),
						nuevoUsuario.getCodigo_funcionario(),
						nuevoUsuario.getId_funcionario(),
						nuevoUsuario.getTipo_usuario(),
						nuevoUsuario.getEstado_usuario(),
						nuevoUsuario.getConexion_usuario(),
						nuevoUsuario.getNombre_usuario(),
						passwordEncoder.encode(nuevoUsuario.getContrasena_usuario()),
						nuevoUsuario.getId_usuario_creacion(),
						new Date(),
						nuevoUsuario.getIp_creacion()
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
                case "coordinador":
                    rolAdmin = rolService.getByRolNombre(RolNombre.ROLE_COORDINADOR).get();
                    roles.add(rolAdmin);
                    break;
                case "sector":
                    rolAdmin = rolService.getByRolNombre(RolNombre.ROLE_SECTOR).get();
                    roles.add(rolAdmin);
                    break;
                case "operador":
                    rolAdmin = rolService.getByRolNombre(RolNombre.ROLE_OPERADOR).get();
                    roles.add(rolAdmin);
                    break;
            }
        }
        usuario.setRoles(roles);
        
        FileServer f = new FileServer();
    	List<FileServer> fileServers = fileServerControlador.getAllFilesServers();
    	for(int i=0; i<fileServers.size(); i++) {
    		if(fileServers.get(i).getEstado_file_server()==1) {
    			f = fileServers.get(i);
    		}
    	}
        
        File directorio = new File(f.getDir_file_server()+"usuarios\\"+usuario.getCodigo_usuario());
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
        
        usuarioService.guardar(usuario);
        //return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
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
    
    
    @Autowired
    private FuncionarioRepositorio funcionarioRepositorio;
    
    @PostMapping("/nuevoFuncionario")
    public Funcionario createFuncionario(@Valid @RequestBody Funcionario funcionario) {
    	Date fecha = new Date();
    	funcionario.setFecha_creacion(fecha);
    	
    	FileServer f = new FileServer();
    	List<FileServer> fileServers = fileServerControlador.getAllFilesServers();
    	for(int i=0; i<fileServers.size(); i++) {
    		if(fileServers.get(i).getEstado_file_server()==1) {
    			f = fileServers.get(i);
    		}
    	}
    	
    	File directorio = new File(f.getDir_file_server()+"funcionarios\\"+funcionario.getCodigo_funcionario());
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
        return funcionarioRepositorio.save(funcionario);
    }
}