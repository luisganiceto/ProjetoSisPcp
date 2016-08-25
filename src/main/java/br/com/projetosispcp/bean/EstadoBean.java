package br.com.projetosispcp.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.projetosispcp.dao.EstadoDAO;
import br.com.projetosispcp.dao.PaisDAO;
import br.com.projetosispcp.entidade.Estado;
import br.com.projetosispcp.entidade.Pais;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class EstadoBean implements Serializable {
	private List<Estado> estados;
	private List<Pais> paises;
	private Estado estado;
	
	public List<Estado> getEstados() {
		return estados;
	}
	
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	
	public List<Pais> getPaises() {
		return paises;
	}
	
	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}
		
	public Estado getEstado() {
		return estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public void novo(){
		estado = new Estado();
				
		try {
			PaisDAO paisDao = new PaisDAO();
			paises = paisDao.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar Paises");
			erro.printStackTrace();
		}
		
	}
	@PostConstruct
	public void listar(){
		try {
			EstadoDAO estadoDao = new EstadoDAO();
			estados = estadoDao.listarTodos();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar Estados.");
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			EstadoDAO estadoDao = new EstadoDAO();
			estadoDao.merge(estado);
			
			estados = estadoDao.listarTodos();
			
			Messages.addGlobalInfo("Estado: "+estado.getDescricaoEstado()+" - "+estado.getCodUF()+", salvo com sucesso!");
			novo();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Não foi possivel salvar o Estado");
			erro.printStackTrace();
		}
		
	}
	
	public void excluir(ActionEvent evento){
		try {
			estado = (Estado) evento.getComponent().getAttributes().get("estadoExcluido");
			EstadoDAO estadoDao = new EstadoDAO();
			estadoDao.excluir(estado);
			estados = estadoDao.listarTodos();
			
			Messages.addGlobalInfo("Um Estado foi removido com sucesso!");
			
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar excluir um Estado");
			e.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		try {
			estado = (Estado) evento.getComponent().getAttributes().get("estadoEditado"); 
			PaisDAO paisDao = new PaisDAO();
			paises = paisDao.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar Paises");
			erro.printStackTrace();
		}
	}
	
	
		
}
