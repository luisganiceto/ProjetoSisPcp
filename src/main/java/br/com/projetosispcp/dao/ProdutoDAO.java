package br.com.projetosispcp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


import br.com.projetosispcp.entidade.Produto;
import br.com.projetosispcp.util.HibernateUtil;

public class ProdutoDAO extends GenericDAO<Produto>{
	
	@SuppressWarnings("unchecked")
	public List<Produto> buscarProduto(String prd){
		Session sessao = HibernateUtil.getFabricaDeSessao().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Produto.class);
			consulta.add(Restrictions.like("codigoProduto", prd));
			List<Produto> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
	}
	
	public Produto buscarProdutoPorCodigoProduto(String prd){
		Session sessao = HibernateUtil.getFabricaDeSessao().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Produto.class);
			consulta.add(Restrictions.eq("codigoProduto", prd));
			Produto resultado = (Produto) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
	}
	
	
}
