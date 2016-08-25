package br.com.projetosispcp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import br.com.projetosispcp.entidade.AtividadeMaquina;
import br.com.projetosispcp.util.HibernateUtil;

public class AtividadeMaquinaDAO extends GenericDAO<AtividadeMaquina>{
	
	@SuppressWarnings("unchecked")
	public List<AtividadeMaquina> listarAtividadePorMaq(Long idMaquina) {
		Session sessao = HibernateUtil.getFabricaDeSessao().openSession();
		try {
			Criteria consulta = sessao.createCriteria(AtividadeMaquina.class);
			consulta.add(Restrictions.eq("idMaquina.id", idMaquina));
			List<AtividadeMaquina> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		
	}

}
