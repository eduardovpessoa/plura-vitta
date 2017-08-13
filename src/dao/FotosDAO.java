package dao;

import javax.persistence.EntityManager;

import model.Foto;
import util.Connection;

public class FotosDAO {

	Connection conexao = new Connection();

	public Foto getImage(String id) {
		EntityManager em = conexao.getConexao();

		em.getTransaction().begin();

		Foto foto = em.find(Foto.class, Integer.valueOf(id));

		em.getTransaction().commit();

		em.close();

		return foto;
	}
}
