package br.com.projetosispcp.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.com.projetosispcp.dao.SetorDAO;
import br.com.projetosispcp.entidade.Setor;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class SetorBean implements Serializable {
	private List<Setor> setores;
	private Setor setor;
	
	public List<Setor> getSetores() {
		return setores;
	}
	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
	public void novo(){
		setor = new Setor();
	}
	
	@PostConstruct
	public void listarTodos(){
		try {
			SetorDAO dao = new SetorDAO();
			setores = dao.listarTodos();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os Setores");
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			SetorDAO dao = new SetorDAO();
			dao.merge(setor);
			Messages.addGlobalInfo("O Setor: "+setor.getDescricaoSetor()+", foi salvo com sucesso");
			setores  = dao.listarTodos();
			novo();	
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar O Setor");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try {
			setor = (Setor) evento.getComponent().getAttributes().get("setorExcluido");
			SetorDAO dao = new SetorDAO();
			dao.excluir(setor);
			Messages.addGlobalInfo("Setor excluido com sucesso");
			setores = dao.listarTodos();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Não foi possível excluir o Setor");
			erro.printStackTrace();
		}
		
	}
	
	public void editar(ActionEvent evento){
		setor = (Setor) evento.getComponent().getAttributes().get("setorEditado");
	}
	
	public void limpar(){
		if (setor == null){
			novo();
		} else {
			Long idLimpo = setor.getId();
			novo();
			setor.setId(idLimpo);
		}
	}

}
