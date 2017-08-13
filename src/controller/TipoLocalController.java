package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.TipoLocalDAO;
import model.TipoLocal;

@ManagedBean
@ViewScoped
public class TipoLocalController {

	private TipoLocalDAO daoTipoLocal = new TipoLocalDAO();

	private List<TipoLocal> listaTipoLocal = new ArrayList<TipoLocal>();

	@PostConstruct
	public void postConstruct() {
		listaTipoLocal.addAll(daoTipoLocal.listarTodosTipoLocal());
	}

	public List<TipoLocal> getListaTipoLocal() {
		return listaTipoLocal;
	}

	public void setListaTipoLocal(List<TipoLocal> listaTipoLocal) {
		this.listaTipoLocal = listaTipoLocal;
	}

}
