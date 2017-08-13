package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Foto;
import model.TipoLocal;
import util.Connection;

public class TipoLocalDAO {

	Connection conexao = new Connection();

	public List<TipoLocal> listarTodosTipoLocal() {

		List<TipoLocal> listaResultado = new ArrayList<TipoLocal>();

		EntityManager em = conexao.getConexao();

		em.getTransaction().begin();

		Query query = em.createQuery("SELECT t FROM TipoLocal t");

		listaResultado.addAll(query.getResultList());

		em.getTransaction().commit();

		em.close();

		return listaResultado;
	}

	public TipoLocal buscarTipoLocalById(Integer idTipoLocal) {

		TipoLocal tipoLocal;

		EntityManager em = conexao.getConexao();

		em.getTransaction().begin();

		try {
			tipoLocal = em.find(TipoLocal.class, idTipoLocal);
		} catch (Exception e) {
			tipoLocal = null;
		}

		em.getTransaction().commit();

		em.close();

		return tipoLocal;
	}

	public List<Foto> buscarTodasFotos() {
		List<Foto> listaResultado = new ArrayList<Foto>();

		EntityManager em = conexao.getConexao();

		em.getTransaction().begin();

		Query query = em.createQuery("SELECT f FROM Fotos f");

		listaResultado.addAll(query.getResultList());

		em.getTransaction().commit();

		em.close();

		return listaResultado;
	}
}
