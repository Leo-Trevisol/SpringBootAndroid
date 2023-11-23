package com.leo.projeto.response;

import java.io.Serializable;
import java.util.List;

import com.leo.projeto.entities.DbUsuarios;

public class LstUsuarioResponse implements Serializable {
	
	private List<DbUsuarios> usuarios;
	
	private String erro;

	public LstUsuarioResponse(List<DbUsuarios> usuarios) {
		super();
		this.usuarios = usuarios;
	}

	public LstUsuarioResponse(String erro) {
		super();
		this.erro = erro;
	}

	public LstUsuarioResponse() {
		super();
	}

	public List<DbUsuarios> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<DbUsuarios> usuarios) {
		this.usuarios = usuarios;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}
	
}
