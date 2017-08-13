package dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import model.Usuario;
import util.Connection;

public class UsuarioDAO {
	
	Connection conexao = new Connection();
	
	public Usuario validarUsuario(String email , String senha){
		
		Usuario usuario= null;
		
		EntityManager em = conexao.getConexao();
		
		em.getTransaction().begin();    
	    
		Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email and u.senha=:senha");
		query.setParameter("email", email); 
		query.setParameter("senha", senha); 
		
		try{
		  usuario = (Usuario) query.getSingleResult();
		}catch (NoResultException e) {
		  usuario = null;
		}
				
		em.getTransaction().commit();
		
		return usuario;
	}
	
	public Usuario cadastrarNovoUsuario(Usuario novoUsuario){
		
		EntityManager em = conexao.getConexao();

		em.getTransaction().begin();    
	    
			em.persist(novoUsuario);
				
		em.getTransaction().commit();
		
		return novoUsuario;
	}

	public String validarUsuario(Usuario novoUsuario) {
		EntityManager em = conexao.getConexao();
		String usuario= null;
		
		em.getTransaction().begin();    
	    
		Query query = em.createQuery("SELECT u.email FROM Usuario u WHERE u.email = :email");
		query.setParameter("email", novoUsuario.getEmail()); 
		
		try{
		  usuario = (String) query.getSingleResult();
		}catch (NoResultException e) {
		  usuario = null;
		}
				
		em.getTransaction().commit();
		
		return usuario;
		
	}
	
	public Usuario salvarUsuario(Usuario usuario){
		
		EntityManager em = conexao.getConexao();

		em.getTransaction().begin();    
	    
			em.merge(usuario);
				
		em.getTransaction().commit();
		
		return usuario;
	}
}
