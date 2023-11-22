package com.projeto.projetoandroidspring.classesfonte.response;

import java.io.Serializable;

public class UsuarioResponse implements Serializable{
	
	private String token;
	
	private String erro;

	public UsuarioResponse() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}


}
