package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Foto;
import model.Local;
import util.Connection;

public class LocalDAO {

	Connection conexao = new Connection();

	@SuppressWarnings("unchecked")
	public List<Local> listarLocaisByNome(String paramPesquisa) {
		List<Local> listaResultado = new ArrayList<Local>();

		EntityManager em = conexao.getConexao();

		em.getTransaction().begin();

		Query query = em.createQuery("SELECT l FROM Local l where l.nome like :paramPesquisa");
		query.setParameter("paramPesquisa", "%" + paramPesquisa + "%");
		listaResultado.addAll(query.getResultList());

		em.getTransaction().commit();

		em.close();

		return listaResultado;
	}

	public Local salvarLocal(Local local) {
		EntityManager em = conexao.getConexao();

		em.getTransaction().begin();

		em.merge(local);

		em.getTransaction().commit();

		em.close();

		return local;
	}

	public Foto inserirFoto(Foto novaFoto) {
		EntityManager em = conexao.getConexao();

		em.getTransaction().begin();

		novaFoto = em.merge(novaFoto);

		em.getTransaction().commit();

		em.close();

		return novaFoto;
	}

}
