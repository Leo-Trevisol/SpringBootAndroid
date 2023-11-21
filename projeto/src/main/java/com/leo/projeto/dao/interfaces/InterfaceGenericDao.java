package com.leo.projeto.dao.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import jakarta.enterprise.context.ApplicationScoped;

@Service
@ApplicationScoped
public interface InterfaceGenericDao {
	
	public void save();
	public void remove();
	public void update();
	public void saveOrUpdate();
	public List findAll(String classe);
	public Integer max(Class clazz);


}
