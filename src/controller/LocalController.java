package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import dao.CidadeDAO;
import dao.LocalDAO;
import dao.TipoLocalDAO;
import model.Cidade;
import model.Foto;
import model.Local;
import model.TipoLocal;

@ManagedBean
@ViewScoped
public class LocalController {

	LocalDAO daoLocal = new LocalDAO();
	CidadeDAO daoCidade = new CidadeDAO();

	TipoLocal tipoLocalOneMenu = new TipoLocal();

	Local localSelecionado = new Local();
	String paramPesquisa;
	List<Local> listaLocais = new ArrayList<Local>();
	List<TipoLocal> listaTipoLocais = new ArrayList<TipoLocal>();

	public void pesquisarLocais() {
		listaLocais.clear();
		listaLocais.addAll(daoLocal.listarLocaisByNome(getParamPesquisa()));
	}

	public void salvarLocal() {

		daoLocal.salvarLocal(localSelecionado);

		RequestContext context = RequestContext.getCurrentInstance();

		context.execute("PF('dlgDetalhesLocal').hide();");

		FacesMessage msg = new FacesMessage("Local salvo com sucesso!", "INFO MSG");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, msg);

		context.update("formPesquisarLocais");

	}

	public List<Cidade> listaCidadesComplete(String query) {
		return daoCidade.listarCidadeByNome(query);
	}

	public void novoLocal() {
		this.localSelecionado = new Local();
	}

	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		try {
			byte[] foto = IOUtils.toByteArray(event.getFile().getInputstream());

			Foto novaFoto = new Foto();
			novaFoto.setArquivo(foto);
			novaFoto.setLocal(getLocalSelecionado());

			novaFoto = daoLocal.inserirFoto(novaFoto);

			localSelecionado.getFotos().add(novaFoto);

		} catch (Exception e) {
			System.out.println("erro");
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	@PostConstruct
	public void postConstruct() {
		TipoLocalDAO dao = new TipoLocalDAO();
		listaTipoLocais = dao.listarTodosTipoLocal();
	}

	// Getters and Setters

	public TipoLocal getTipoLocalOneMenu() {
		return tipoLocalOneMenu;
	}

	public void setTipoLocalOneMenu(TipoLocal tipoLocalOneMenu) {
		this.tipoLocalOneMenu = tipoLocalOneMenu;
	}

	public String getParamPesquisa() {
		return paramPesquisa;
	}

	public void setParamPesquisa(String paramPesquisa) {
		this.paramPesquisa = paramPesquisa;
	}

	public List<Local> getListaLocais() {
		return listaLocais;
	}

	public void setListaLocais(List<Local> listaLocais) {
		this.listaLocais = listaLocais;
	}

	public Local getLocalSelecionado() {
		return localSelecionado;
	}

	public void setLocalSelecionado(Local localSelecionado) {
		this.localSelecionado = localSelecionado;
	}

	public List<TipoLocal> getListaTipoLocais() {
		return listaTipoLocais;
	}

	public void setListaTipoLocais(List<TipoLocal> listaTipoLocais) {
		this.listaTipoLocais = listaTipoLocais;
	}
}
