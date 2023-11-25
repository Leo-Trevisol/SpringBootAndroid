package com.leo.projeto.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.leo.projeto.entities.DbUsuarios;

@Repository
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
		
		List<DbUsuarios> lstUsuarios = new ArrayList<DbUsuarios>();
		
		GenericDao<DbUsuarios> genericDao = new GenericDao<DbUsuarios>(usuario);
		
		lstUsuarios = genericDao.findAll(usuario.getClass().getSimpleName());
		
		return lstUsuarios;
		
	}
	
	public Integer max(DbUsuarios usuario) throws Exception {
		
		GenericDao<DbUsuarios> genericDao = new GenericDao<DbUsuarios>(usuario);
		
		Integer max = genericDao.max(usuario.getClass());
		
		return max;

		
	}

}
