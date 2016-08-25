package br.com.projetosispcp.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * @author luisg
 *
 */
public class HibernateUtil {
	
	private static SessionFactory fabricaDeSessao = criarFabricaDeSessao();
	
	public static SessionFactory getFabricaDeSessao() {
		return fabricaDeSessao;
	}
	
	private static SessionFactory criarFabricaDeSessao(){
		try {
			Configuration configuracao = new Configuration().configure();
			
			ServiceRegistry registro = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties()).build();
			
			SessionFactory fabrica = configuracao.buildSessionFactory(registro);
			
			return fabrica;
		} catch (Throwable ex) {
            System.err.println("A Fabrica de Sessão não pode ser criada. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
	}

}
