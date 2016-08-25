package br.com.projetosispcp.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.projetosispcp.dao.CidadeDAO;
import br.com.projetosispcp.dao.EstadoDAO;
import br.com.projetosispcp.entidade.Cidade;
import br.com.projetosispcp.entidade.Estado;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class CidadeBean implements Serializable {
	private List<Cidade> cidades;
	private List<Estado> estados;
	private Cidade cidade;
	
	public List<Cidade> getCidades() {
		return cidades;
	}
	
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	
	public Cidade getCidade() {
		return cidade;
	}
	
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	public List<Estado> getEstados() {
		return estados;
	}
	
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	
	public void novo(){
		cidade = new Cidade();
		
		try {
			EstadoDAO estadoDao = new EstadoDAO();
			estados = estadoDao.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Falha ao tentar carregar Estados");
			erro.printStackTrace();
		}
	}
	
	@PostConstruct
	public void listar(){
		try {
			CidadeDAO cidadeDao = new CidadeDAO();
			cidades = cidadeDao.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar cidades");
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			CidadeDAO cidadeDao = new CidadeDAO();
			cidadeDao.merge(cidade);
			
			Messages.addFlashGlobalInfo("Cidade: "+cidade.getDescricaoCidade()+", foi salva com sucesso");
			novo();
			cidades = cidadeDao.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a cidade");
			erro.printStackTrace();
		}
	}
	
	public void exclui(ActionEvent evento){
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeExcluido");
			CidadeDAO cidadeDao = new CidadeDAO();
			cidadeDao.excluir(cidade);
			cidades = cidadeDao.listarTodos();
			
			Messages.addGlobalInfo("Uma Cidade foi removida com sucesso!");
			
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar excluir uma Cidade");
			e.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeEditado");
			
			EstadoDAO estadoDao = new EstadoDAO();
			estados = estadoDao.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Falha ao tentar carregar Estados");
			erro.printStackTrace();
		}
	}

}
