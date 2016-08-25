package br.com.projetosispcp.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.projetosispcp.dao.TipoFerramentaDAO;
import br.com.projetosispcp.entidade.TipoFerramenta;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class TipoFerramentaBean implements Serializable {
	private List<TipoFerramenta> tiposDeFerramenta;
	private TipoFerramenta tipoFerramenta;
	
	public List<TipoFerramenta> getTiposDeFerramenta() {
		return tiposDeFerramenta;
	}
	public void setTiposDeFerramenta(List<TipoFerramenta> tiposDeFerramenta) {
		this.tiposDeFerramenta = tiposDeFerramenta;
	}
	public TipoFerramenta getTipoFerramenta() {
		return tipoFerramenta;
	}
	public void setTipoFerramenta(TipoFerramenta tipoFerramenta) {
		this.tipoFerramenta = tipoFerramenta;
	}
	
	public void novo(){
		tipoFerramenta = new TipoFerramenta();
	}
	
	@PostConstruct
	public void listarTodos(){
		try {
			TipoFerramentaDAO dao = new TipoFerramentaDAO();
			tiposDeFerramenta = dao.listarTodos();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os Tipos de Ferramentas");
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			TipoFerramentaDAO dao = new TipoFerramentaDAO();
			dao.merge(tipoFerramenta);
			Messages.addGlobalInfo("O Tipo de Ferramenta: "+tipoFerramenta.getDescricao()+", foi salvo com sucesso");
			tiposDeFerramenta = dao.listarTodos();
			novo();
					
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Tipo de Ferramenta");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try {
			tipoFerramenta = (TipoFerramenta) evento.getComponent().getAttributes().get("tipoExcluido");
			TipoFerramentaDAO dao = new TipoFerramentaDAO();
			dao.excluir(tipoFerramenta);
			Messages.addGlobalInfo("Tipo de Ferramenta excluido com sucesso");
			tiposDeFerramenta = dao.listarTodos();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Não foi possível excluir o Tipo de Ferramenta");
			erro.printStackTrace();
		}
		
	}
	
	public void editar(ActionEvent evento){
		tipoFerramenta = (TipoFerramenta) evento.getComponent().getAttributes().get("tipoEditado");
	}
	
	public void limpar(){
		if (tipoFerramenta == null){
			novo();
		} else {
			Long idLimpo = tipoFerramenta.getId();
			novo();
			tipoFerramenta.setId(idLimpo);
		}
	}

}
