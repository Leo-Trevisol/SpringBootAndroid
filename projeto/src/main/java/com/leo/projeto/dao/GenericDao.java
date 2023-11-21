package com.leo.projeto.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.leo.projeto.connection.HibernateUtil;
import com.leo.projeto.dao.interfaces.InterfaceGenericDao;


public class GenericDao<Object> implements InterfaceGenericDao {
	
	private Session session;
	private Transaction transaction= null;
	private Object obj;
	
	public GenericDao(Object obj) {
		this.session = HibernateUtil.getSession();
		this.obj = obj;
	}

	@Override
	public void save() {
		try {
		transaction = session.beginTransaction();
		session.save(obj);
		transaction.commit();
	}catch(Exception e) {
		System.out.println("Deu erro ao inserir:"  + obj);
		transaction.rollback();
	}
	}

	@Override
	public void remove() {
		try {
		transaction = session.beginTransaction();
		session.delete(obj);
		transaction.commit();
	}catch(Exception e) {
		System.out.println("Deu erro ao excluir:"  + e.getMessage() + "");
		transaction.rollback();
	}
		
	}

	@Override
	public void update() {
		try {
			transaction = session.beginTransaction();
			session.update(obj);
			transaction.commit();
		}catch(Exception e) {
			System.out.println("Deu erro ao excluir:"  + e.getMessage() + "");
			transaction.rollback();
		}
		
	}
	
	@Override
	public void saveOrUpdate() {
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(obj);
			transaction.commit();
		}catch(Exception e) {
			System.out.println("Deu erro ao excluir:"  + e.getMessage() + "");
			transaction.rollback();
		}
		
	}

	@Override
	public List findAll(String classe) {
		List lista = session.createQuery("from " + classe).list();
		return lista;
	}


	@Override
	public Integer max(Class clazz) {
		Integer max = (Integer) session.createQuery("select max(id) from " + clazz.getName()).uniqueResult();
		return max;
	}

	
	
	

}
