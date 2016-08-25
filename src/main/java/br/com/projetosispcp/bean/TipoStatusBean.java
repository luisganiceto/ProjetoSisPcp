package br.com.projetosispcp.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.com.projetosispcp.dao.TipoStatusDAO;
import br.com.projetosispcp.entidade.TipoStatus;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class TipoStatusBean implements Serializable {
	private List<TipoStatus> tiposDeStatus;
	private TipoStatus tipoStatus;
	
	public List<TipoStatus> getTiposDeStatus() {
		return tiposDeStatus;
	}
	public void setTiposDeStatus(List<TipoStatus> tiposDeStatus) {
		this.tiposDeStatus = tiposDeStatus;
	}
	public TipoStatus getTipoStatus() {
		return tipoStatus;
	}
	public void setTipoStatus(TipoStatus tipoStatus) {
		this.tipoStatus = tipoStatus;
	}
	
	
	public void novo(){
		tipoStatus = new TipoStatus();
	}
	
	@PostConstruct
	public void listarTodos(){
		try {
			TipoStatusDAO dao = new TipoStatusDAO();
			tiposDeStatus = dao.listarTodos();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os Tipos de Status");
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			TipoStatusDAO dao = new TipoStatusDAO();
			dao.merge(tipoStatus);
			Messages.addGlobalInfo("O Tipo de Status: "+tipoStatus.getDescricao()+", foi salvo com sucesso");
			tiposDeStatus = dao.listarTodos();
			novo();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Tipo de Ferramenta");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try {
			tipoStatus = (TipoStatus) evento.getComponent().getAttributes().get("tipoExcluido");
			TipoStatusDAO dao = new TipoStatusDAO();
			dao.excluir(tipoStatus);
			Messages.addGlobalInfo("Tipo de Status excluido com sucesso");
			tiposDeStatus = dao.listarTodos();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Não foi possível excluir o Tipo de Status");
			erro.printStackTrace();
		}
		
	}
	
	public void editar(ActionEvent evento){
		tipoStatus = (TipoStatus) evento.getComponent().getAttributes().get("tipoEditado");
	}
	
	public void limpar(){
		if (tipoStatus == null){
			novo();
		} else {
			Long idLimpo = tipoStatus.getId();
			novo();
			tipoStatus.setId(idLimpo);
		}
	}

}
