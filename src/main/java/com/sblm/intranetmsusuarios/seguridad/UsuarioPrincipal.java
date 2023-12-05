package com.sblm.intranetmsusuarios.seguridad;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sblm.intranetmsusuarios.entidades.Usuario;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioPrincipal implements UserDetails {

	private long id;	
	private String dni;

    private int tipo;
    private int estado;
    private String usuario;
    private String contrasena;

    private Date fechaCreacion;
    
    private Collection<? extends GrantedAuthority> authorities;
    
	public UsuarioPrincipal(long id, String dni, int tipo, int estado, String usuario,
			String contrasena, Date fechaCreacion, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.dni = dni;
		this.tipo = tipo;
		this.estado = estado;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.fechaCreacion = fechaCreacion;
		this.authorities = authorities;
	}

	public static UsuarioPrincipal build(Usuario usuario){
        List<GrantedAuthority> authorities =
                usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name())).collect(Collectors.toList());
        return new UsuarioPrincipal(usuario.getId(), 
        							usuario.getDni(),
        							usuario.getTipo(),
        							usuario.getEstado(),
        							usuario.getUsuario(),
        							usuario.getContrasena(),
        							usuario.getFechaCreacion(),
        							authorities);
    }
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

	@Override
	public String getPassword() {
		return this.contrasena;
	}

	@Override
	public String getUsername() {
		return this.usuario;
	}
}