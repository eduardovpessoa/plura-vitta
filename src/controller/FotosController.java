package controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import dao.FotosDAO;
import model.Foto;

@ManagedBean
@SessionScoped
public class FotosController {

	FotosDAO fotosDAO = new FotosDAO();

	public StreamedContent getImage() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {
			String id = context.getExternalContext().getRequestParameterMap().get("id");
			Foto foto = fotosDAO.getImage(id);
			return new DefaultStreamedContent(new ByteArrayInputStream(foto.getArquivo()));
		}
	}
}