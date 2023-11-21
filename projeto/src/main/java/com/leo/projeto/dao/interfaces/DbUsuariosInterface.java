package com.leo.projeto.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.leo.projeto.entities.DbUsuarios;
import com.leo.projeto.entities.pk.DbUsuariosPK;

@Service
public interface DbUsuariosInterface extends JpaRepository<DbUsuarios, DbUsuariosPK>{
	
}
