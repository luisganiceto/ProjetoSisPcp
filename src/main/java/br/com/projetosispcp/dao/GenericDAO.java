package br.com.projetosispcp.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import br.com.projetosispcp.util.HibernateUtil;

public class GenericDAO<Entidade> {
	
	private Class<Entidade> classe;
	
	
	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public void salvar(Entidade entidade){
		Session sessao = HibernateUtil.getFabricaDeSessao().openSession();
		
		Transaction tx = null;
		
		try {
			tx = sessao.beginTransaction();
			sessao.save(entidade);
			tx.commit();
		} catch (RuntimeException e) {
			
			if (tx != null){
				tx.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Entidade> listarTodos(){
		Session sessao = HibernateUtil.getFabricaDeSessao().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(classe);
			List<Entidade> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Entidade buscar(Long id){
		Session sessao = HibernateUtil.getFabricaDeSessao().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(classe);
			consulta.add(Restrictions.idEq(id));
			Entidade resultado = (Entidade) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
	}
	
	public void excluir(Entidade entidade){
		Session sessao = HibernateUtil.getFabricaDeSessao().openSession();
		
		Transaction tx = null;
		
		try {
			tx = sessao.beginTransaction();
			sessao.delete(entidade);
			tx.commit();
		} catch (RuntimeException e) { 
			
			if (tx != null){
				tx.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}
	}
	
	public void editar(Entidade entidade){
		Session sessao = HibernateUtil.getFabricaDeSessao().openSession();
		Transaction tx = null;
		try {
			tx = sessao.beginTransaction();
			sessao.update(entidade);
			tx.commit();
		} catch (RuntimeException e) {
				if (tx != null){
				tx.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}
	}
	
	public void merge(Entidade entidade){
		Session sessao = HibernateUtil.getFabricaDeSessao().openSession();
		Transaction tx = null;
		try {
			tx = sessao.beginTransaction();
			sessao.merge(entidade);
			tx.commit();
		} catch (RuntimeException e) {
				if (tx != null){
				tx.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}
	}
	

}
