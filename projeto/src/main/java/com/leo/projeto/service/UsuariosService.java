package com.leo.projeto.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.projeto.dao.DbUsuariosDAO;
import com.leo.projeto.entities.DbUsuarios;
import com.leo.projeto.repository.UsuariosRepository;
import com.leo.projeto.response.ResponseHandler;
import com.leo.projeto.vo.UsuarioVo;

@Service
public class UsuariosService implements Serializable {


	private static final long serialVersionUID = 6100935394540880645L;
	
	private final UsuariosRepository usuariosRepository;
	
	@Autowired
	private DbUsuariosDAO usuariosDAO;

	public UsuariosService(final UsuariosRepository usuariosRepository) {
		this.usuariosRepository = usuariosRepository;
	}

	public List<UsuarioVo> findAll() {
		return this.usuariosRepository.findAll().stream().map(this::entityToVO).collect(Collectors.toList());
	}

	private UsuarioVo entityToVO(DbUsuarios entity) {
		UsuarioVo vo = new UsuarioVo();
		vo.setId(entity.getId());
		vo.setNome(entity.getNome());
		vo.setSenha(entity.getSenha());
		vo.setEmail(entity.getEmail());
		vo.setNascimento(entity.getNascimento());
		return vo;
	}
	
	public DbUsuarios voToEntity(UsuarioVo vo) {
		DbUsuarios dbUsuarios = new DbUsuarios();
		dbUsuarios.setId(vo.getId());
		dbUsuarios.setNome(vo.getNome());
		dbUsuarios.setSenha(vo.getSenha());
		dbUsuarios.setEmail(vo.getEmail());
		dbUsuarios.setNascimento(vo.getNascimento());
		return dbUsuarios;
	}
	
	public String saveUsuario(UsuarioVo usuario) throws Exception {
		
		DbUsuarios usu = voToEntity(usuario);
		
		Integer max = usuariosDAO.max(usu);
		if (max == null)
			max = 1;
		else
			max++;
		
		usu.setId(max);
		
		try {
			usuariosRepository.save(usu);
			return "Usuario inserido com sucesso!";
		} catch (Exception e) {
			if(e.getMessage().contains("UK_")) {
				return "Email informado já está em uso";
			}else {
				return e.getMessage();
			}
		}
		
		
	}

	@Transactional(readOnly = true)
	public Page<UsuarioVo> findAllPaginated(final String search, final int pageNumber, final int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "id"));
		return usuariosRepository.findByNomeContainingIgnoreCase(search, pageable).map(this::entityToVO);
	}
	
	public long count() {
		return usuariosRepository.count();
	}
	
	public String remove(DbUsuarios entity) {
		
		try {
		
		UsuarioVo usuario = findById(entity.getId());
		
		if(usuario == null) {
			return "Usuário não encontrado";
		}else {
			usuariosRepository.delete(entity);
			return "Usuário deletado com sucesso";
		}
		
		}catch(Exception e) {
			return "Erro ao deletar usuário " + e.getMessage();
		}
		
	}
	
	public DbUsuarios findUsuarioByNomeSenha(String nome, String senha) {
			
			for(UsuarioVo usuarioValido : findAll()) {
				if(usuarioValido.getNome().equals(nome) && usuarioValido.getSenha().equals(senha)) {
					return voToEntity(usuarioValido);
				}
			}
			
			return null;
		
	}
	
	public UsuarioVo findById(Integer id) {
		
		Optional<DbUsuarios> usuario = usuariosRepository.findById(id);
		
		if(usuario.isPresent()) {
			UsuarioVo usu = entityToVO(usuario.get());
			return usu;
		}
		
		 return null;
		
	}
}
