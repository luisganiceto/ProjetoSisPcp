package br.com.projetosispcp.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.com.projetosispcp.dao.FerramentaDAO;
import br.com.projetosispcp.dao.FornecedorDAO;
import br.com.projetosispcp.dao.TipoFerramentaDAO;
import br.com.projetosispcp.entidade.Ferramenta;
import br.com.projetosispcp.entidade.Fornecedor;
import br.com.projetosispcp.entidade.TipoFerramenta;
@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class FerramentaBean implements Serializable {
	private List<Ferramenta> ferramentas;
	private Ferramenta ferramenta;
	private List<TipoFerramenta> tipos;
	private List<Fornecedor> fornecedores;
	
	public List<Ferramenta> getFerramentas() {
		return ferramentas;
	}
	public void setFerramentas(List<Ferramenta> ferramentas) {
		this.ferramentas = ferramentas;
	}
	public Ferramenta getFerramenta() {
		return ferramenta;
	}
	public void setFerramenta(Ferramenta ferramenta) {
		this.ferramenta = ferramenta;
	}
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}
	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}
	public List<TipoFerramenta> getTipos() {
		return tipos;
	}
	public void setTipos(List<TipoFerramenta> tipos) {
		this.tipos = tipos;
	}
	
	public void novo(){
		ferramenta = new Ferramenta();
		try {
			TipoFerramentaDAO daoF = new TipoFerramentaDAO();
			tipos = daoF.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar Tipos de Ferramentas");
			erro.printStackTrace();
		}
		
		try {
			FornecedorDAO dao = new FornecedorDAO();
			fornecedores = dao.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar Fornecedores");
			erro.printStackTrace();
		}
	}
	
	@PostConstruct
	public void listarTodos(){
		try {
			FerramentaDAO dao = new FerramentaDAO();
			ferramentas = dao.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar Ferramentas");
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			FerramentaDAO dao = new FerramentaDAO();
			dao.merge(ferramenta);
			
			Messages.addGlobalInfo("A Ferramenta: "+ferramenta.getDescricaoFerramenta()+", foi salva com sucesso.");
			novo();
			listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a Ferramenta");
			erro.printStackTrace();
		}
	}
	
	public void limpar(){
		if (ferramenta == null){
			novo();
		} else {
			Long idLimpo = ferramenta.getId();
			novo();
			ferramenta.setId(idLimpo);
		}
	}
	
	public void excluir(ActionEvent evento){
		try {
			ferramenta = (Ferramenta) evento.getComponent().getAttributes().get("ferExcluida");
			FerramentaDAO dao = new FerramentaDAO();
			dao.excluir(ferramenta);
			ferramentas= dao.listarTodos();
			
			Messages.addGlobalInfo("Uma Ferramenta foi removida com sucesso!");
			
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar excluir uma Ferramenta");
			e.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		try {
			ferramenta = (Ferramenta) evento.getComponent().getAttributes().get("ferEditado");
			TipoFerramentaDAO daoF = new TipoFerramentaDAO();
			tipos = daoF.listarTodos();
			FornecedorDAO dao = new FornecedorDAO();
			fornecedores = dao.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar Tipos de Ferramentas/Fornecedores");
			erro.printStackTrace();
		}
	}
	
}
