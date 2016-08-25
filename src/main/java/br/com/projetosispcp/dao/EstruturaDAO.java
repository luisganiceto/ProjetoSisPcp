package br.com.projetosispcp.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.projetosispcp.entidade.Estrutura;
import br.com.projetosispcp.util.HibernateUtil;

public class EstruturaDAO extends GenericDAO<Estrutura>{
	
	public Estrutura buscarPorProduto(Long id){
		Session sessao = HibernateUtil.getFabricaDeSessao().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Estrutura.class);
			consulta.add(Restrictions.eq("idProduto.id", id));
			Estrutura resultado = (Estrutura) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
	}

}
