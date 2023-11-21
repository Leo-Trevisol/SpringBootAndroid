package com.leo.projeto.dao.interfaces;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.springframework.stereotype.Service;


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
