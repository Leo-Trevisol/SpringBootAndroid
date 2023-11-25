package com.leo.projeto.vo;

import java.io.Serializable;
import java.util.Date;

public class UsuarioVo implements Serializable {
	

	private static final long serialVersionUID = -2518686406370570688L;
	
	private Integer id;
	private String nome;
	private String email;
	private String senha;
    private Date nascimento;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

}
