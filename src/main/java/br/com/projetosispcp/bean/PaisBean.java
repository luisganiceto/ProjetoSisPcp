package br.com.projetosispcp.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;


import br.com.projetosispcp.dao.PaisDAO;
import br.com.projetosispcp.entidade.Pais;


@SuppressWarnings("serial")
@SessionScoped
@ManagedBean
public class PaisBean implements Serializable{
	private Pais pais;
	private List<Pais> paises;
	
	public Pais getPais() {
		if(pais == null){
			novo();
		}
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
	public List<Pais> getPaises() {
		return paises;
	}
	
	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}

	@PostConstruct
	public void listar(){
		try {
			PaisDAO paisDAO = new PaisDAO();
			paises = paisDAO.listarTodos();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar os Países.");
			erro.printStackTrace();
		}
	}
	
	public void novo(){
		pais = new Pais();
	}
	
	public void salvar(){
		try {
			PaisDAO paisDao = new PaisDAO();
			paisDao.merge(pais);
			paises = paisDao.listarTodos();
			
			Messages.addGlobalInfo("O Pais: "+pais.getDescricaoPais()+", foi salvo com sucesso!");
			novo();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao salvar o País");
			erro.printStackTrace();
		}
		
	}
	
	public void excluir(ActionEvent evento){
		try {
			pais = (Pais) evento.getComponent().getAttributes().get("paisExcluido");
			PaisDAO paisDao = new PaisDAO();
			paisDao.excluir(pais);
			paises = paisDao.listarTodos();
			
			Messages.addGlobalInfo("Um País foi removido com sucesso!");
			
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar excluir um País");
			e.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		pais = (Pais) evento.getComponent().getAttributes().get("paisEditado");
	}
}
