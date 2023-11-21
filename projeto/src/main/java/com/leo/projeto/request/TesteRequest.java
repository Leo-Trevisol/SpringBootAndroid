package com.leo.projeto.request;

import java.io.Serializable;

public class TesteRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5063668698079127805L;
	
	private String teste;

	public TesteRequest(String teste) {
		super();
		this.teste = teste;
	}

	public TesteRequest() {
		super();
	}

	public String getTeste() {
		return teste;
	}

	public void setTeste(String teste) {
		this.teste = teste;
	}
	

}
