package com.leo.projeto.vo;

import java.io.Serializable;
import java.util.Date;

public class LoginVO implements Serializable {

	private static final long serialVersionUID = -5073749983363212928L;
	
	private String nome;
	
	private String senha;
	
	private String email;
	
	private String nascimento;

	public LoginVO(String nome, String senha, String email, String nascimento) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.nascimento = nascimento;
	}

	public LoginVO() {
		super();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}
	

}
