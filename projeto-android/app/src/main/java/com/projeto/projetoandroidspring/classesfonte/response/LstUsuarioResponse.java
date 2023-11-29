package com.projeto.projetoandroidspring.classesfonte.response;

import java.io.Serializable;
import java.util.List;

import com.projeto.projetoandroidspring.classesfonte.vo.UsuarioVo;

public class LstUsuarioResponse implements Serializable {
	
	private List<UsuarioVo> usuarios;
	
	private String erro;

	public LstUsuarioResponse(List<UsuarioVo> usuarios) {
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

	public List<UsuarioVo> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioVo> usuarios) {
		this.usuarios = usuarios;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}
	
}
