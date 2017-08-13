package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.NoResultException;

import dao.CidadeDAO;
import model.Cidade;

@FacesConverter("cidadeConverter")
public class CidadeConverter implements Converter {

	public Object getAsObject(FacesContext contet, UIComponent component, String value) {
		if (value.equals(null) || value.equals("null") || value.equals(""))
			return null;
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			Cidade resultado = cidadeDAO.buscarCidadeById(Integer.valueOf(value));
			return resultado;
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext contet, UIComponent component, Object value) {
		if (value == null || value.equals(""))
			return null;
		return String.valueOf(((Cidade) value).getId());
	}

}
