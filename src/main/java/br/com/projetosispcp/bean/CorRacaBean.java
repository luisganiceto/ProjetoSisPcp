package br.com.projetosispcp.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.com.projetosispcp.dao.CorRacaDAO;
import br.com.projetosispcp.entidade.CorRaca;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class CorRacaBean implements Serializable {
	private List<CorRaca> corRacas;
	private CorRaca corRaca;
	
	public CorRaca getCorRaca() {
		return corRaca;
	}
	public void setCorRaca(CorRaca corRaca) {
		this.corRaca = corRaca;
	}
	public List<CorRaca> getCorRacas() {
		return corRacas;
	}
	public void setCorRacas(List<CorRaca> corRacas) {
		this.corRacas = corRacas;
	}
	
	public void novo(){
		corRaca = new CorRaca();
	}
	
	@PostConstruct
	public void listarTodos(){
		try {
			CorRacaDAO dao = new CorRacaDAO();
			corRacas = dao.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as Cor/Raça");
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			CorRacaDAO dao = new CorRacaDAO();
			dao.merge(corRaca);
			Messages.addGlobalInfo("A Cor/Raca: "+corRaca.getDescricaoCorRaca()+", foi salva com sucesso");
			corRacas = dao.listarTodos();
			novo();	
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a Cor/Raça");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try {
			corRaca = (CorRaca) evento.getComponent().getAttributes().get("corExcluida");
			CorRacaDAO dao = new CorRacaDAO();
			dao.excluir(corRaca);
			Messages.addGlobalInfo("Cor/Raca excluida com sucesso");
			corRacas = dao.listarTodos();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Não foi possível excluir a CorRaca");
			erro.printStackTrace();
		}
		
	}
	
	public void editar(ActionEvent evento){
		corRaca = (CorRaca) evento.getComponent().getAttributes().get("corEditada");
	}
	
	public void limpar(){
		if (corRaca == null){
			novo();
		} else {
			Long idLimpo = corRaca.getId();
			novo();
			corRaca.setId(idLimpo);
		}
	}

}
