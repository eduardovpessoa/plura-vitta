package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class Connection {
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("PluraVitta");

	public EntityManager getConexao(){ 		
	 return factory.createEntityManager();
	}
}