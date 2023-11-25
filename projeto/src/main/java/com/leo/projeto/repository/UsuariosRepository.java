package com.leo.projeto.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leo.projeto.entities.DbUsuarios;
import com.leo.projeto.entities.pk.DbUsuariosPK;

@Repository
public interface UsuariosRepository extends JpaRepository<DbUsuarios, DbUsuariosPK> {

	Page<DbUsuarios> findByNomeContainingIgnoreCase(final String search, Pageable pageable);

}
