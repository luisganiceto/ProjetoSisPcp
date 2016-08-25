package br.com.projetosispcp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.projetosispcp.entidade.Unidade;
import br.com.projetosispcp.util.HibernateUtil;

public class UnidadeDAO extends GenericDAO<Unidade>{
	
	
	@SuppressWarnings("unchecked")
	public List<Unidade> listarFator(){
		Session sessao = HibernateUtil.getFabricaDeSessao().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Unidade.class);
			consulta.add(Restrictions.eq("eFatorConversao",true));
			List<Unidade> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
	}
	
	public Unidade buscarUnidade(String UN){
		Session sessao = HibernateUtil.getFabricaDeSessao().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Unidade.class);
			consulta.add(Restrictions.eq("codUnidade", UN));
			Unidade resultado = (Unidade) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		
	}

}
