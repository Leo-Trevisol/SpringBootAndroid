package com.leo.projeto.request;

import java.io.Serializable;

public class LoginUsuarioRequest implements Serializable{

	private static final long serialVersionUID = -4379040145663114114L;
	
	private String nome;
	
	private String senha;
	
	public LoginUsuarioRequest() {
		super();
	}

	public LoginUsuarioRequest(String nome, String senha) {
		super();
		this.nome = nome;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
