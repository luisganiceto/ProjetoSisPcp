package br.com.projetosispcp.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.com.projetosispcp.dao.EstadoCivilDAO;
import br.com.projetosispcp.entidade.EstadoCivil;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class EstadoCivilBean implements Serializable {
	private List<EstadoCivil> estados;
	private EstadoCivil estado;
	
	public EstadoCivil getEstado() {
		return estado;
	}
	public void setEstado(EstadoCivil estado) {
		this.estado = estado;
	}
	public List<EstadoCivil> getEstados() {
		return estados;
	}
	public void setEstados(List<EstadoCivil> estados) {
		this.estados = estados;
	}
	
	
	public void novo(){
		estado = new EstadoCivil();
	}
	
	@PostConstruct
	public void listarTodos(){
		try {
			EstadoCivilDAO dao = new EstadoCivilDAO();
			estados = dao.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os Estados Civis");
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			EstadoCivilDAO dao = new EstadoCivilDAO();
			dao.merge(estado);
			Messages.addGlobalInfo("O Estado Civil: "+estado.getDescricaoEstadoCivil()+", foi salvo com sucesso");
			estados = dao.listarTodos();
			novo();	
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Estado Civil");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try {
			estado = (EstadoCivil) evento.getComponent().getAttributes().get("estadoExcluida");
			EstadoCivilDAO dao = new EstadoCivilDAO();
			dao.excluir(estado);
			Messages.addGlobalInfo("Estado Civil excluido com sucesso");
			estados = dao.listarTodos();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Não foi possível excluir o Estado Civil");
			erro.printStackTrace();
		}
		
	}
	
	public void editar(ActionEvent evento){
		estado = (EstadoCivil) evento.getComponent().getAttributes().get("estadoEditada");
	}
	
	public void limpar(){
		if (estado == null){
			novo();
		} else {
			Long idLimpo = estado.getId();
			novo();
			estado.setId(idLimpo);
		}
	}

}
