package com.leo.projeto.bean;

import java.util.ArrayList;
import java.util.List;

import com.leo.projeto.dao.DbUsuariosDAO;
import com.leo.projeto.dao.GenericDao;
import com.leo.projeto.entities.DbUsuarios;

public class DbUsuariosBean {
	
	DbUsuariosDAO dao = getInstance();
	
	public DbUsuarios insereUsuario(DbUsuarios usuario) {
		
		dao.insereUsuario(usuario);
		
		return usuario;
	}
	
	public void removeUsuario(DbUsuarios usuario) {
		
		dao.removeUsuario(usuario);
		
	}
	
	public void atualizaUsuario(DbUsuarios usuario) {
		
		dao.atualizaUsuario(usuario);
		
	}
	
	public void saveOrUpdateUsuario(DbUsuarios usuario) {

		dao.saveOrUpdateUsuario(usuario);
	}
	
	public List<DbUsuarios> findAllUsuarios(){
		
		DbUsuarios usuario = new DbUsuarios();
		
		List<DbUsuarios> lstUsuarios = new ArrayList();
		
		lstUsuarios = dao.findAllUsuarios();
		
		return lstUsuarios;
		
	}
	
	public DbUsuarios findUsuarioByNomeSenha(String nome, String senha) {
		
		for(DbUsuarios usuarioValido : findAllUsuarios()) {
			if(usuarioValido.getNome().equals(nome) && usuarioValido.getSenha().equals(senha)) {
				return usuarioValido;
			}
		}
		
		return null;
	}

	public DbUsuariosDAO getInstance() {
		if(dao == null) {
			return new DbUsuariosDAO();
		}else {
			return dao;
		}
	}

}
