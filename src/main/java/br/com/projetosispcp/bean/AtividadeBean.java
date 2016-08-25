package br.com.projetosispcp.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.projetosispcp.dao.AtividadeDAO;
import br.com.projetosispcp.entidade.Atividade;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class AtividadeBean implements Serializable{
	private List<Atividade> atividades;
	private Atividade atividade;
	
	public List<Atividade> getAtividades() {
		return atividades;
	}
	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}
	public Atividade getAtividade() {
		return atividade;
	}
	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
	
	public void novo(){
		atividade = new Atividade();
	}
	
	@PostConstruct
	public void listarTodos(){
		try {
			AtividadeDAO atividadeDAO = new AtividadeDAO();
			atividades = atividadeDAO.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as Atividades");
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			AtividadeDAO dao = new AtividadeDAO();
			dao.merge(atividade);
			Messages.addGlobalInfo("A Atividade: "+atividade.getDescricaoAtividade()+", foi salva com sucesso");
			atividades = dao.listarTodos();
			novo();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a Atividade");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try {
			atividade = (Atividade) evento.getComponent().getAttributes().get("atividadeExcluida");
			AtividadeDAO dao = new AtividadeDAO();
			dao.excluir(atividade);
			Messages.addGlobalInfo("Atividade excluida com sucesso");
			atividades = dao.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Não foi possível excluir a Atividade");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
			atividade = (Atividade) evento.getComponent().getAttributes().get("atividadeEditada");
	}
	
	public void limpar(){
		if (atividade == null){
			novo();
		} else {
			Long idLimpo = atividade.getId();
			novo();
			atividade.setId(idLimpo);
		}
	}

}
