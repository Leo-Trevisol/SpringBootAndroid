package com.leo.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leo.projeto.bean.DbUsuariosBean;
import com.leo.projeto.entities.DbUsuarios;
import com.leo.projeto.request.LoginUsuarioRequest;
import com.leo.projeto.request.UsuarioRequest;
import com.leo.projeto.response.LoginUsuarioResponse;
import com.leo.projeto.response.UsuarioResponse;

@RestController
public class UsuarioController {
	
	@Autowired
	DbUsuariosBean bean;
	
    @PostMapping(value = "inserirUsuario", produces = "application/json", consumes = "application/json")
	public UsuarioResponse inserirUsuario(
			@RequestBody UsuarioRequest req) throws Exception {
    	
    	UsuarioResponse response = new UsuarioResponse();
    	
    	if(req.getUsuario().getNome() == null || req.getUsuario().getSenha() == null
    			|| req.getUsuario().getEmail() == null || req.getUsuario().getNascimento() == null ) {
    		response.setErro("Todos os campos devem ser preenchidos");
    		return response;
    	}
    	
    	DbUsuarios usuario = bean.insereUsuario(req.getUsuario());
    	
    	if(usuario != null) {
    		response.setToken("Usuario inserido com sucesso!");
    	}else {
    		response.setErro("Usuario/Senha incorreta!");
    	}
    	
    	return response;
    	
	}
    
    @PostMapping(value = "loginUsuario", produces = "application/json", consumes = "application/json")
    public LoginUsuarioResponse loginUsuario(@RequestBody LoginUsuarioRequest req) {
    	
    	LoginUsuarioResponse response = new LoginUsuarioResponse();
    	
    	if(req.getNome() == null || req.getSenha() == null) {
    		response.setErro("Todos os campos devem ser informados!");
    		return response;
    	}
    	
    	DbUsuarios usuario = bean.findUsuarioByNomeSenha(req.getNome(), req.getSenha());
    	
    	if(usuario != null) {
    		response.setToken("Usuario logado com sucesso!");
    	}else {
    		response.setErro("Usuario/Senha invalido!");
    	}
    	return response;
    	
    }

    
    @RequestMapping(value = "removeUsuario/{idUsuario}")
	public String deleteUsuario(@PathVariable("idUsuario") Integer idUsuario)
			 throws Exception {
    	
    	if(idUsuario == null) {
    		return "Usuario deve ser informado!";
    	}
    	
    	try {
    		
    		DbUsuarios usuario = new DbUsuarios();
    		usuario.setId(idUsuario);
    		bean.removeUsuario(usuario);
    		return "Usuario removido com sucesso!";
			
		} catch (Exception e) {
			return e.getMessage();
		}
    	
    }
    
    
    
    @GetMapping("/lstUsuarios")
    List<DbUsuarios> allList() {
      return bean.findAllUsuarios();
    }
    
    @GetMapping("/countUsuarios")
    Integer allCount() {
      return bean.findAllUsuarios().size();
    }
    
    
	public DbUsuariosBean getInstance() {
		if(bean == null) {
			return new DbUsuariosBean();
		}else {
			return bean;
		}
	}

}
