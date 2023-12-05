package com.dini.activoscriticosnacionalesbackend.seguridad;

import com.dini.activoscriticosnacionalesbackend.entidades.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;

public class UsuarioPrincipal implements UserDetails {

	private long id_usuario;	
	private String codigo_usuario;
	private String codigo_funcionario;
	private long id_funcionario;

    private int tipo_usuario;
    private int estado_usuario;
    private int conexion_usuario;
    private String nombre_usuario;
    private String contrasena_usuario;

  //Auditoria de creación
    private long id_usuario_creacion;
    private Date fecha_creacion;
    private String ip_creacion;
    
    private Collection<? extends GrantedAuthority> authorities;
    

	public UsuarioPrincipal(long id_usuario, String codigo_usuario, String codigo_funcionario, long id_funcionario,
			int tipo_usuario, int estado_usuario, int conexion_usuario, String nombre_usuario,
			String contrasena_usuario, long id_usuario_creacion, Date fecha_creacion, String ip_creacion,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id_usuario = id_usuario;
		this.codigo_usuario = codigo_usuario;
		this.codigo_funcionario = codigo_funcionario;
		this.id_funcionario = id_funcionario;
		this.tipo_usuario = tipo_usuario;
		this.estado_usuario = estado_usuario;
		this.conexion_usuario = conexion_usuario;
		this.nombre_usuario = nombre_usuario;
		this.contrasena_usuario = contrasena_usuario;
		this.id_usuario_creacion = id_usuario_creacion;
		this.fecha_creacion = fecha_creacion;
		this.ip_creacion = ip_creacion;
		this.authorities = authorities;
	}

	public static UsuarioPrincipal build(Usuario usuario){
        List<GrantedAuthority> authorities =
                usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name())).collect(Collectors.toList());
        return new UsuarioPrincipal(usuario.getId_usuario(), 
        							usuario.getCodigo_usuario(),
        							usuario.getCodigo_funcionario(),
        							usuario.getId_funcionario(),
        							usuario.getTipo_usuario(),
        							usuario.getEstado_usuario(),
        							usuario.getConexion_usuario(),
        							usuario.getNombreUsuario(),
        							usuario.getContrasena_usuario(),
        							usuario.getId_usuario_creacion(),
        							usuario.getFecha_creacion(),
        							usuario.getIp_creacion(),
        							authorities);
    }

	public long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getCodigo_usuario() {
		return codigo_usuario;
	}

	public void setCodigo_usuario(String codigo_usuario) {
		this.codigo_usuario = codigo_usuario;
	}

	public String getCodigo_funcionario() {
		return codigo_funcionario;
	}

	public void setCodigo_funcionario(String codigo_funcionario) {
		this.codigo_funcionario = codigo_funcionario;
	}

	public long getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(long id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	public int getTipo_usuario() {
		return tipo_usuario;
	}

	public void setTipo_usuario(int tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}

	public int getEstado_usuario() {
		return estado_usuario;
	}

	public void setEstado_usuario(int estado_usuario) {
		this.estado_usuario = estado_usuario;
	}

	public int getConexion_usuario() {
		return conexion_usuario;
	}

	public void setConexion_usuario(int conexion_usuario) {
		this.conexion_usuario = conexion_usuario;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getContrasena_usuario() {
		return contrasena_usuario;
	}

	public void setContrasena_usuario(String contrasena_usuario) {
		this.contrasena_usuario = contrasena_usuario;
	}

	public long getId_usuario_creacion() {
		return id_usuario_creacion;
	}

	public void setId_usuario_creacion(long id_usuario_creacion) {
		this.id_usuario_creacion = id_usuario_creacion;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public String getIp_creacion() {
		return ip_creacion;
	}

	public void setIp_creacion(String ip_creacion) {
		this.ip_creacion = ip_creacion;
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
		return this.contrasena_usuario;
	}

	@Override
	public String getUsername() {
	return this.nombre_usuario;
	}
}