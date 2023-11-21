package com.projeto.projetoandroidspring.classesfonte;

import java.io.Serializable;

public class LoginResponse implements Serializable{

	

	
	/**
	 *
	 */
	private static final long serialVersionUID = 3067055705950941453L;
	private String token;
	private String erro;
	
	
	public LoginResponse() {
		super();
	}


	public LoginResponse(String erro, String token) {
		super();
		this.erro = erro;
		this.token= token;
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
