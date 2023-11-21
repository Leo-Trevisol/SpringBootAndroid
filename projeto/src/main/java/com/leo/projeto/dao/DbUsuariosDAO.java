package com.leo.projeto.dao;

import java.util.ArrayList;
import java.util.List;

import com.leo.projeto.entities.DbUsuarios;

import jakarta.persistence.EntityManager;

public class DbUsuariosDAO {
	
	public DbUsuarios insereUsuario(DbUsuarios usuario) {
		
		GenericDao<DbUsuarios> genericDao = new GenericDao<DbUsuarios>(usuario);
		
		genericDao.save();
		
		return usuario;
	}
	
	public void removeUsuario(DbUsuarios usuario) {
		
		GenericDao<DbUsuarios> genericDao = new GenericDao<DbUsuarios>(usuario);
		
		genericDao.remove();

	}
	
	public void atualizaUsuario(DbUsuarios usuario) {
		
		GenericDao<DbUsuarios> genericDao = new GenericDao<DbUsuarios>(usuario);
		
		genericDao.update();

		
	}
	
	public void saveOrUpdateUsuario(DbUsuarios usuario) {
		GenericDao<DbUsuarios> genericDao = new GenericDao<DbUsuarios>(usuario);

		genericDao.saveOrUpdate();
	}
	
	public List<DbUsuarios> findAllUsuarios(){
		
		DbUsuarios usuario = new DbUsuarios();
		
		List<DbUsuarios> lstUsuarios = new ArrayList();
		
		GenericDao<DbUsuarios> genericDao = new GenericDao<DbUsuarios>(usuario);
		
		lstUsuarios = genericDao.findAll(usuario.getClass().getName());
		
		return lstUsuarios;
		
	}
	
	public Integer max(DbUsuarios usuario) throws Exception {
		
		GenericDao<DbUsuarios> genericDao = new GenericDao<DbUsuarios>(usuario);
		
		Integer max = genericDao.max(usuario.getClass());
		
		return max;

		
	}

}
