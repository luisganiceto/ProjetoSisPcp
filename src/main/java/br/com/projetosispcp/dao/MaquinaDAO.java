package br.com.projetosispcp.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import br.com.projetosispcp.entidade.Maquina;
import br.com.projetosispcp.util.HibernateUtil;

public class MaquinaDAO extends GenericDAO<Maquina>{
	
	public Maquina buscarIdMaquina(String maq){
		Session sessao = HibernateUtil.getFabricaDeSessao().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Maquina.class);
			consulta.add(Restrictions.eq("descricaoMaquina", maq));
			Maquina resultado = (Maquina) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
	}

}
