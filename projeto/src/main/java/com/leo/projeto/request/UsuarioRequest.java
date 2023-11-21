package com.leo.projeto.request;

import java.io.Serializable;
import java.util.Objects;

import com.leo.projeto.entities.DbUsuarios;

public class UsuarioRequest implements Serializable{
	
	public DbUsuarios usuario;

	public UsuarioRequest(DbUsuarios usuario) {
		super();
		this.usuario = usuario;
	}

	public UsuarioRequest() {
		super();
	}

	public DbUsuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(DbUsuarios usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "UsuarioRequest [usuario=" + usuario + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioRequest other = (UsuarioRequest) obj;
		return Objects.equals(usuario, other.usuario);
	}
	
}
