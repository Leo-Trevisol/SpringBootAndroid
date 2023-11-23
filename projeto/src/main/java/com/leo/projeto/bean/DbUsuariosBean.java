package com.leo.projeto.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leo.projeto.dao.DbUsuariosDAO;
import com.leo.projeto.dao.interfaces.DbUsuariosInterface;
import com.leo.projeto.entities.DbUsuarios;
import com.leo.projeto.vo.LoginVO;

import utils.Utils;



@Component
@ApplicationScoped
public class DbUsuariosBean {
	
	DbUsuariosDAO dao = getInstance();
	
	@Autowired
	private DbUsuariosInterface dbUsuariosInterface;
	
	public String insereUsuario(DbUsuarios usuario) throws Exception {
		
		if(Utils.isEmpty(usuario.getNome()) || Utils.isEmpty(usuario.getSenha())
    			|| Utils.isEmpty(usuario.getEmail()) || usuario.getNascimento() == null ) {
    		return "Todos os campos devem ser preenchidos";
    	}
		
		Integer max = dao.max(usuario);
		if (max == null)
			max = 1;
		else
			max++;
		
		usuario.setId(max);
		
		try {
			dbUsuariosInterface.save(usuario);
			return null;
		} catch (Exception e) {
			if(e.getMessage().contains("UK_")) {
				return "Email informado já está em uso";
			}else {
				return e.getMessage();
			}
		}
		
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
		
		List<DbUsuarios> lstUsuarios = new ArrayList<DbUsuarios>();
		
		lstUsuarios = dbUsuariosInterface.findAll();
		
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
	
	public DbUsuarios loginVoToDbUsuarios(LoginVO login) {
		
		DbUsuarios usuario = new DbUsuarios();
		
		usuario.setNome(login.getNome() != null ? login.getNome() : "");
		usuario.setSenha(login.getSenha() != null ? login.getSenha() : "");
		usuario.setEmail(login.getEmail() != null ? login.getEmail() : "");
        String padrao = "dd/MM/yyyy";

        DateFormat formatoData = new SimpleDateFormat(padrao);
		try {
			usuario.setNascimento(formatoData.parse(login.getNascimento()) != null ? formatoData.parse(login.getNascimento()) : null);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return usuario;

	}

}
