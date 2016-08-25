package br.com.projetosispcp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.projetosispcp.entidade.Cidade;
import br.com.projetosispcp.util.HibernateUtil;

public class CidadeDAO extends GenericDAO<Cidade>{
	
	@SuppressWarnings("unchecked")
	public List<Cidade> buscarPorEstado(Long idEstado){
		Session sessao = HibernateUtil.getFabricaDeSessao().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Cidade.class);
			consulta.add(Restrictions.eq("idEstado.id", idEstado));
			consulta.addOrder(Order.asc("descricaoCidade"));
			List<Cidade> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
