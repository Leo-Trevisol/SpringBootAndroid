package com.leo.projeto.bean;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leo.projeto.dao.DbUsuariosDAO;
import com.leo.projeto.dao.interfaces.DbUsuariosInterface;
import com.leo.projeto.entities.DbUsuarios;



@Component
@ApplicationScoped
public class DbUsuariosBean {
	
	DbUsuariosDAO dao = getInstance();
	
	@Autowired
	private DbUsuariosInterface dbUsuariosInterface;
	
	public DbUsuarios insereUsuario(DbUsuarios usuario) throws Exception {
		
		Integer max = dao.max(usuario);
		if (max == null)
			max = 1;
		else
			max++;
		
		usuario.setId(max);
		
		dbUsuariosInterface.save(usuario);
		
		return usuario;
	}
	
	public void removeUsuario(DbUsuarios usuario) {
		
		dbUsuariosInterface.delete(usuario);
		
	}
	
	public void atualizaUsuario(DbUsuarios usuario) {
		
		dao.atualizaUsuario(usuario);
		
	}
	
	public void saveOrUpdateUsuario(DbUsuarios usuario) {

		dao.saveOrUpdateUsuario(usuario);
	}
	
	public List<DbUsuarios> findAllUsuarios(){
		
		DbUsuarios usuario = new DbUsuarios();
		
		List<DbUsuarios> lstUsuarios = new ArrayList<DbUsuarios>();
		
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
