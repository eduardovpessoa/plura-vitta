package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.ProfissionalDAO;
import model.Profissional;

@ManagedBean
@ViewScoped
public class ProfissionalController {

	@ManagedProperty("#{usuarioController}")
	private UsuarioController loginController;

	// DAO
	private ProfissionalDAO daoProfissional = new ProfissionalDAO();

	// Atributos
	private Profissional novoProfissional = new Profissional();
	private List<Profissional> listaProfissionais = new ArrayList<Profissional>();
	private Profissional profissionalSelecionado = new Profissional();

	@PostConstruct
	public void postConstruct() {
		listaProfissionais.addAll(daoProfissional.listarProfissionais());
	}

	/**
	 * Método responsável por adicionar um profissional.
	 * 
	 */
	public void adicionarProfissional() {
		novoProfissional.setDataCadastro(new Date());
		daoProfissional.salvarProfissional(novoProfissional);

		listaProfissionais.clear();
		listaProfissionais.addAll(daoProfissional.listarProfissionais());

		FacesMessage msg = new FacesMessage("Profissional cadastrado com sucesso!", "INFO MSG");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, msg);

		novoProfissional = new Profissional();
	}

	public void removerProfissional() {

		daoProfissional.excluirProfissional(profissionalSelecionado);

		listaProfissionais.clear();
		listaProfissionais.addAll(daoProfissional.listarProfissionais());

		FacesMessage msg = new FacesMessage("Profissional removido com sucesso!", "INFO MSG");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	/**
	 * Método reponsável por alterar um profissional.
	 */
	public void alterarProfissional() {

		daoProfissional.salvarProfissional(profissionalSelecionado);

		listaProfissionais.clear();
		listaProfissionais.addAll(daoProfissional.listarProfissionais());

		FacesMessage msg = new FacesMessage("Profissional alterado com sucesso!", "INFO MSG");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public UsuarioController getLoginController() {
		return loginController;
	}

	public void setLoginController(UsuarioController loginController) {
		this.loginController = loginController;
	}

	public ProfissionalDAO getDaoProfissional() {
		return daoProfissional;
	}

	public void setDaoProfissional(ProfissionalDAO daoProfissional) {
		this.daoProfissional = daoProfissional;
	}

	public Profissional getNovoProfissional() {
		return novoProfissional;
	}

	public void setNovoProfissional(Profissional novoProfissional) {
		this.novoProfissional = novoProfissional;
	}

	public List<Profissional> getListaProfissionais() {
		return listaProfissionais;
	}

	public void setListaProfissionais(List<Profissional> listaProfissionais) {
		this.listaProfissionais = listaProfissionais;
	}

	public Profissional getProfissionalSelecionado() {
		return profissionalSelecionado;
	}

	public void setProfissionalSelecionado(Profissional profissionalSelecionado) {
		this.profissionalSelecionado = profissionalSelecionado;
	}

}