package br.com.projetosispcp.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.management.RuntimeOperationsException;

import org.omnifaces.util.Messages;

import br.com.projetosispcp.dao.StatusDAO;
import br.com.projetosispcp.dao.TipoStatusDAO;
import br.com.projetosispcp.entidade.Status;
import br.com.projetosispcp.entidade.TipoStatus;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class StatusBean implements Serializable{
	private List<Status> listaStatus;
	private Status status;
	private List<TipoStatus> tipoStatus;
 	
	public List<Status> getListaStatus() {
		return listaStatus;
	}
	public void setListaStatus(List<Status> listaStatus) {
		this.listaStatus = listaStatus;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public void novo(){
		status = new Status();
		try {
			TipoStatusDAO dao = new TipoStatusDAO();
			tipoStatus = dao.listarTodos();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar Tipo de Status");
			erro.printStackTrace();
		}
	}
	
	public List<TipoStatus> getTipoStatus() {
		return tipoStatus;
	}
	public void setTipoStatus(List<TipoStatus> tipoStatus) {
		this.tipoStatus = tipoStatus;
	}
	
	@PostConstruct
	public void listarTodos(){
		try {
			StatusDAO dao = new StatusDAO();
			listaStatus = dao.listarTodos();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar Status");
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			StatusDAO dao = new StatusDAO();
			dao.merge(status);
			Messages.addGlobalInfo("O Status: "+status.getDescricaoStatus()+", foi salvo com sucesso");
			listaStatus = dao.listarTodos();
			novo();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Status");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try {
			status = (Status) evento.getComponent().getAttributes().get("statusExcluido");
			StatusDAO dao = new StatusDAO();
			dao.excluir(status);
			Messages.addGlobalInfo("Status excluido com sucesso");
			listaStatus = dao.listarTodos();
			
		} catch (RuntimeOperationsException erro) {
			Messages.addGlobalError("Não foi possível excluir o Status");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
			
			try {
				status = (Status) evento.getComponent().getAttributes().get("statusEditado");
				TipoStatusDAO dao = new TipoStatusDAO();
				tipoStatus = dao.listarTodos();
			} catch (RuntimeException erro) {
				Messages.addGlobalError("Ocorreu um erro ao tentar listar Tipo de Status");
				erro.printStackTrace();
			}
	}
	
	public void limpar(){
		if(status == null){
			novo();
		} else{
			Long idLimpo = status.getId();
			novo();
			status.setId(idLimpo);
		}
	}

}
