package com.projeto.projetoandroidspring.classesfonte;

import java.io.Serializable;

public class TesteResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2687124496230425252L;
	
	
	private String testeresp;


	public TesteResponse() {
	
	}


	public String getTesteresp() {
		return testeresp;
	}


	public void setTesteresp(String testeresp) {
		this.testeresp = testeresp;
	}
	
}
