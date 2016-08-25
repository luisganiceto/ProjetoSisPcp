package br.com.projetosispcp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.projetosispcp.entidade.ProdutoFornecedor;
import br.com.projetosispcp.util.HibernateUtil;

public class ProdutoFornecedorDAO extends GenericDAO<ProdutoFornecedor>{

	@SuppressWarnings("unchecked")
	public List<ProdutoFornecedor> listarFornecedorPorMP(Long idMateriaPrima) {
		Session sessao = HibernateUtil.getFabricaDeSessao().openSession();
		try {
			Criteria consulta = sessao.createCriteria(ProdutoFornecedor.class);
			consulta.add(Restrictions.eq("idProduto.id", idMateriaPrima));
			List<ProdutoFornecedor> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		
	}
}
