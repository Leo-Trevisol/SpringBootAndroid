package com.leo.projeto.request;

import java.io.Serializable;
import java.util.Objects;

import com.leo.projeto.vo.UsuarioVo;

public class UsuarioRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8024717942904931202L;
	
	public UsuarioVo usuario;

	public UsuarioRequest(UsuarioVo usuario) {
		super();
		this.usuario = usuario;
	}

	public UsuarioRequest() {
		super();
	}

	public UsuarioVo getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVo usuario) {
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
