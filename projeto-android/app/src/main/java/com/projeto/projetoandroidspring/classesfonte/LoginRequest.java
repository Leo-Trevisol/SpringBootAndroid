package com.projeto.projetoandroidspring.classesfonte;

import java.io.Serializable;

public class LoginRequest  implements Serializable {
	
	private String usuario;
	private String senha;
	
	
	public LoginRequest(){
		super();
	}


	public LoginRequest(String usuario, String senha) {
		super();
		this.usuario = usuario;
		this.senha = senha;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}

	

}
