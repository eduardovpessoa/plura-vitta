package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Cidade;
import util.Connection;

public class CidadeDAO {

	Connection connection = new Connection();

	@SuppressWarnings("unchecked")
	public List<Cidade> listAll(String paramNomeCidade) {

		EntityManager em = connection.getConexao();

		Query query = em.createQuery("SELECT c FROM Cidade c WHERE c.nome LIKE :paramNomeCidade");
		query.setParameter("paramNomeCidade", "%" + paramNomeCidade + "%");

		List<Cidade> cidadeList = query.getResultList();

		em.close();

		return cidadeList;
	}

	public Cidade findById(Integer id) {

		EntityManager em = connection.getConexao();

		Cidade cidade = em.find(Cidade.class, id);

		em.close();

		return cidade;
	}

}
