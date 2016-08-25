package br.com.projetosispcp.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.com.projetosispcp.dao.PausaDAO;
import br.com.projetosispcp.entidade.Pausa;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class PausaBean implements Serializable {
	private List<Pausa> pausas;
	private Pausa pausa;
	
	public Pausa getPausa() {
		return pausa;
	}
	public void setPausa(Pausa pausa) {
		this.pausa = pausa;
	}
	public List<Pausa> getPausas() {
		return pausas;
	}
	public void setPausas(List<Pausa> pausas) {
		this.pausas = pausas;
	}
	
	
	public void novo(){
		pausa = new Pausa();
	}
	
	@PostConstruct
	public void listarTodos(){
		try {
			PausaDAO dao = new PausaDAO();
			pausas = dao.listarTodos();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as Pausas");
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			PausaDAO dao = new PausaDAO();
			dao.merge(pausa);
			Messages.addGlobalInfo("A Pausa: "+pausa.getDescricaoPausa()+", foi salva com sucesso");
			pausas = dao.listarTodos();
			novo();	
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a Pausa");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try {
			pausa = (Pausa) evento.getComponent().getAttributes().get("pausaExcluida");
			PausaDAO dao = new PausaDAO();
			dao.excluir(pausa);
			Messages.addGlobalInfo("Pausa excluida com sucesso");
			pausas = dao.listarTodos();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Não foi possível excluir a Pausa");
			erro.printStackTrace();
		}
		
	}
	
	public void editar(ActionEvent evento){
		pausa = (Pausa) evento.getComponent().getAttributes().get("pausaEditada");
	}
	
	public void limpar(){
		if (pausa == null){
			novo();
		} else {
			Long idLimpo = pausa.getId();
			novo();
			pausa.setId(idLimpo);
		}
	}

}
