package com.leo.projeto.resource;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.Response;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leo.projeto.entities.DbUsuarios;
import com.leo.projeto.request.LoginUsuarioRequest;
import com.leo.projeto.request.UsuarioRequest;
import com.leo.projeto.response.GenericResponse;
import com.leo.projeto.response.LoginUsuarioResponse;
import com.leo.projeto.response.LstUsuarioResponse;
import com.leo.projeto.response.ResponseHandler;
import com.leo.projeto.response.UsuarioResponse;
import com.leo.projeto.service.UsuariosService;
import com.leo.projeto.vo.PaginationVO;
import com.leo.projeto.vo.UsuarioVo;

import utils.Utils;

@RestController
@RequestMapping(value = "/api/v1/usuarios")
public class UsuariosResource implements Serializable {
	

	private static final long serialVersionUID = -6628215575302554538L;
	
	private final UsuariosService usuariosService;

	public UsuariosResource(final UsuariosService usuariosService) {
		this.usuariosService = usuariosService;
	}
	
	 @PostMapping(value = "inserirUsuario", produces = "application/json", consumes = "application/json")
		public UsuarioResponse inserirUsuario(
				@RequestBody UsuarioRequest req) throws Exception {
	    	
	    	UsuarioResponse response = new UsuarioResponse();
	    	
	    	String msgErro = usuariosService.saveUsuario(req.getUsuario());
	    	
	    	if(msgErro == null) {
	    		response.setToken("Usuário inserido com sucesso!");
	    	}else {
	    		response.setErro(msgErro);
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
	    		String msg = usuariosService.remove(usuario);
	    		return msg;
				
			} catch (Exception e) {
				return e.getMessage();
			}
	    	
	    }
	
	@PostMapping("findPaginated")
	public ResponseEntity<GenericResponse<List<UsuarioVo>>> buscarUsuarios(
			@RequestBody PaginationVO<String> paginationVO) {

		try {

			Page<UsuarioVo> geUnidadesPage = usuariosService.findAllPaginated(paginationVO.getSearch(),
					paginationVO.getPageNumber(), paginationVO.getPageSize());

			List<UsuarioVo> content = geUnidadesPage.getContent();
			long totalElements = geUnidadesPage.getTotalElements();

			return ResponseEntity.ok(new GenericResponse<>(content, totalElements));
		} catch (Exception ex) {
			return ResponseEntity.ok(new GenericResponse<>(ex.getMessage()));
		}
	}

	@GetMapping("/findAll")
	public LstUsuarioResponse findAll() throws Exception {
		
		LstUsuarioResponse response = new LstUsuarioResponse();

		List<UsuarioVo> lstUsuarios = usuariosService.findAll();

		if (lstUsuarios != null) {
			Collections.sort(lstUsuarios, (o1, o2) -> o1.getNome().compareTo(o2.getNome()));
			response.setUsuarios(lstUsuarios);
		}else {
			response.setErro("Nenhum usuário encontrado");
		}

		return response;
	}
	
	   @GetMapping("/count")
	    public Long count() {
	      return usuariosService.count();
	    }
	   
	    @PostMapping(value = "loginUsuario", produces = "application/json", consumes = "application/json")
	    public LoginUsuarioResponse loginUsuario(@RequestBody LoginUsuarioRequest req) {
	    	
	    	LoginUsuarioResponse response = new LoginUsuarioResponse();
	    	
	    	if(Utils.isEmpty(req.getNome()) || Utils.isEmpty(req.getSenha())) {
	    		response.setErro("Todos os campos devem ser informados!");
	    		return response;
	    	}
	    	
	    	DbUsuarios usuario = usuariosService.findUsuarioByNomeSenha(req.getNome(), req.getSenha());
	    	
	    	if(usuario != null) {
	    		response.setToken("Usuário logado com sucesso!");
	    	}else {
	    		response.setErro("Usuário/Senha inválido!");
	    	}
	    	return response;
	    	
	    }
	    
	    @GetMapping("/findById/{idUsuario}")
	    public ResponseEntity<GenericResponse<UsuarioVo>> findById(@PathVariable("idUsuario") Integer id) {
	    	
	    	try {

				UsuarioVo usuario = usuariosService.findById(id);
				
				if(usuario == null) {
					return ResponseEntity.ok(new GenericResponse<>("Usuário não encontrado"));
				}else {
					return ResponseEntity.ok(new GenericResponse<>(usuario, 1));
				}
			} catch (Exception ex) {
				return ResponseEntity.ok(new GenericResponse<>(ex.getMessage()));
			}
	    }
}
