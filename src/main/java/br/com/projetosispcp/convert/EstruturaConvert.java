package br.com.projetosispcp.convert;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import br.com.projetosispcp.bean.PecaBean;
import br.com.projetosispcp.entidade.Produto;

@FacesConverter("estruturaConverter")
public class EstruturaConvert implements Converter {

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if(value != null && value.trim().length() > 0){
			try {
				PecaBean pecaBean = (PecaBean) fc.getExternalContext().getApplicationMap().get("pecaBean");
				return pecaBean.getPecas().get(Integer.parseInt(value));
			} catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversão erro", "inválido"));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if(object != null){
			return String.valueOf(((Produto) object).getId());
		} else{
			return null;
		}
	}

}

