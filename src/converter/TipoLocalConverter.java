package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import dao.TipoLocalDAO;
import model.TipoLocal;

@FacesConverter("tipoLocalConverter")
public class TipoLocalConverter implements Converter {

	public Object getAsObject(FacesContext contet, UIComponent component, String value) {
		if (value.equals(null) || value.equals("null") || value.equals(""))
			return null;
		try {
			TipoLocalDAO tipoLocalDAO = new TipoLocalDAO();
			TipoLocal resultado = tipoLocalDAO.buscarTipoLocalById(Integer.valueOf(value));
			return resultado;
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext contet, UIComponent component, Object value) {
		if (value == null || value.equals(""))
			return null;
		return String.valueOf(((TipoLocal) value).getIdTipoLocal());
	}

}
