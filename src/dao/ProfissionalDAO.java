package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Foto;
import model.Profissional;
import util.Connection;

public class ProfissionalDAO {
	Connection conexao = new Connection();

	public Profissional salvarProfissional(Profissional profissional) {
		EntityManager em = conexao.getConexao();

		em.getTransaction().begin();

		em.merge(profissional);

		em.getTransaction().commit();

		em.close();

		return profissional;
	}

	public List<Profissional> listarProfissionais() {
		List<Profissional> listaProfissional = new ArrayList<Profissional>();

		EntityManager em = conexao.getConexao();

		em.getTransaction().begin();

		Query query = em.createQuery("SELECT p FROM Profissional p");

		listaProfissional.addAll(query.getResultList());

		em.getTransaction().commit();

		em.close();

		return listaProfissional;
	}

	public void excluirProfissional(Profissional profissional) {
		EntityManager em = conexao.getConexao();
		em.getTransaction().begin();
		em.remove(profissional);
		em.getTransaction().commit();
		em.close();
	}

}
