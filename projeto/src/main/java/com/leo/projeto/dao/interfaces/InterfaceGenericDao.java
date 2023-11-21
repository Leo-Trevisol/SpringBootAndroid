package com.leo.projeto.dao.interfaces;

import java.util.List;

public interface InterfaceGenericDao {
	
	public void save();
	public void remove();
	public void update();
	public void saveOrUpdate();
	public List findAll(String classe);
	public void removeById(Integer id);


}
