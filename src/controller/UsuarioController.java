package controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.UsuarioDAO;
import model.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioController {

	private UsuarioDAO daoUsuario = new UsuarioDAO();

	// Página de login
	private String email;
	private String senha;

	// Filtro login
	private Usuario usuario = null;
	private boolean logado;

	// Cadastro
	private Usuario novoUsuario = new Usuario();

	public String entrar() {

		usuario = daoUsuario.validarUsuario(getEmail(), getSenha());

		if (usuario != null) {
			logado = true;
			return "clinica/index.xhtml?faces-redirect=true";
		}
		FacesMessage msg = new FacesMessage("Usuario ou senha incorretos.!", "INFO MSG");
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "";
	}

	public String sair() {
		logado = false;

		FacesMessage msg = new FacesMessage("Sessão encerrada!", "INFO MSG");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, msg);

		return "index.xhtml?faces-redirect=true";
	}

	public void salvarUsuario() {

		daoUsuario.salvarUsuario(getUsuario());

		FacesMessage msg = new FacesMessage("Dados atualizados com sucesso!", "INFO MSG");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public String cadastrarNovoUsuario() {

		if (validarUsuario()) {
			daoUsuario.cadastrarNovoUsuario(getNovoUsuario());
			setNovoUsuario(new Usuario());
			return "index.xhtml?faces-redirect=true";
		} else {
			return "";
		}
	}

	public boolean validarUsuario() {

		String validacao;
		validacao = daoUsuario.validarUsuario(getNovoUsuario());

		if (validacao != null) {
			FacesMessage msg = new FacesMessage("Usuário não disponível", "");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return false;
		}
		return true;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getNovoUsuario() {
		return novoUsuario;
	}

	public void setNovoUsuario(Usuario novoUsuario) {
		this.novoUsuario = novoUsuario;
	}
	
}