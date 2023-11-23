package com.leo.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leo.projeto.bean.DbUsuariosBean;
import com.leo.projeto.entities.DbUsuarios;
import com.leo.projeto.request.LoginUsuarioRequest;
import com.leo.projeto.request.UsuarioRequest;
import com.leo.projeto.response.LoginUsuarioResponse;
import com.leo.projeto.response.LstUsuarioResponse;
import com.leo.projeto.response.UsuarioResponse;

import utils.Utils;

@RestController
public class UsuarioController {
	
	@Autowired
	DbUsuariosBean bean;
	
    @PostMapping(value = "inserirUsuario", produces = "application/json", consumes = "application/json")
	public UsuarioResponse inserirUsuario(
			@RequestBody UsuarioRequest req) throws Exception {
    	
    	UsuarioResponse response = new UsuarioResponse();
    	
    	DbUsuarios usuario = bean.loginVoToDbUsuarios(req.getUsuario());
    	
    	String msgErro = bean.insereUsuario(usuario);
    	
    	if(msgErro == null) {
    		response.setToken("Usuário inserido com sucesso!");
    	}else {
    		response.setErro(msgErro);
    	}
    	
    	return response;
    	
	}
    
    @PostMapping(value = "loginUsuario", produces = "application/json", consumes = "application/json")
    public LoginUsuarioResponse loginUsuario(@RequestBody LoginUsuarioRequest req) {
    	
    	LoginUsuarioResponse response = new LoginUsuarioResponse();
    	
    	if(Utils.isEmpty(req.getNome()) || Utils.isEmpty(req.getSenha())) {
    		response.setErro("Todos os campos devem ser informados!");
    		return response;
    	}
    	
    	DbUsuarios usuario = bean.findUsuarioByNomeSenha(req.getNome(), req.getSenha());
    	
    	if(usuario != null) {
    		response.setToken("Usuário logado com sucesso!");
    	}else {
    		response.setErro("Usuário/Senha inválido!");
    	}
    	return response;
    	
    }

    
    @RequestMapping(value = "removeUsuario/{idUsuario}")
	public String deleteUsuario(@PathVariable("idUsuario") Integer idUsuario)
			 throws Exception {
    	
    	if(idUsuario == null) {
    		return "Usuário deve ser informado!";
    	}
    	
    	try {
    		
    		DbUsuarios usuario = new DbUsuarios();
    		usuario.setId(idUsuario);
    		bean.removeUsuario(usuario);
    		return "Usuário removido com sucesso!";
			
		} catch (Exception e) {
			return e.getMessage();
		}
    	
    }
    
    @GetMapping("/lstUsuarios")
    public LstUsuarioResponse allList() {
    	LstUsuarioResponse response = new LstUsuarioResponse();
    	
    	response.setUsuarios(bean.findAllUsuarios());
    	
    	if(Utils.isEmpty(response.getUsuarios())) {
    		response.setErro("Nenhum usuário encontrado");
    	}
    	
      return response;
    }
    
    @GetMapping("/countUsuarios")
    public Integer allCount() {
      return bean.findAllUsuarios().size();
    }
    
}
