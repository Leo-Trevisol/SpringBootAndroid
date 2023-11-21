package com.leo.projeto.entities.pk;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class DbUsuariosPK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3527251472039893791L;
	
	@Id
	@Column(name ="id")
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DbUsuariosPK other = (DbUsuariosPK) obj;
		return Objects.equals(id, other.id);
	}
	
}
