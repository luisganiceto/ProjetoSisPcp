package br.com.projetosispcp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.projetosispcp.dao.CidadeDAO;
import br.com.projetosispcp.dao.EstadoDAO;
import br.com.projetosispcp.dao.FornecedorDAO;
import br.com.projetosispcp.entidade.Cidade;
import br.com.projetosispcp.entidade.Estado;
import br.com.projetosispcp.entidade.Fornecedor;


@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class FornecedorBean implements Serializable {
	private Fornecedor fornecedor;
	private List<Fornecedor> fornecedores;
	private List<Estado> estados;
	private List<Cidade> cidades;
	private Estado estadoSelecionado;
	
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}
	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public List<Cidade> getCidades() {
		return cidades;
	}
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	public List<Estado> getEstados() {
		return estados;
	}
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	public Estado getEstadoSelecionado() {
		return estadoSelecionado;
	}
	public void setEstadoSelecionado(Estado estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}
		
	public void novo(){
		fornecedor = new Fornecedor();
		try {
			EstadoDAO estadoDao = new EstadoDAO();
			estados = estadoDao.listarTodos();
			estadoSelecionado = null;
			cidades = new ArrayList<Cidade>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar Estado/Cidades");
			erro.printStackTrace();
		}
	}
	
	@PostConstruct
	public void listarTodos() {
		try {
			FornecedorDAO fornecedorDao = new FornecedorDAO();
			fornecedores = fornecedorDao.listarTodos();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar Fornecedores");
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			FornecedorDAO fornecedorDao = new FornecedorDAO();
			fornecedorDao.merge(fornecedor);
			
			Messages.addGlobalInfo("O Fornecedor: "+fornecedor.getNomeFantasia()+", foi salvo com sucesso.");
			novo();
			listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Fornecedor");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try {
			fornecedor = (Fornecedor) evento.getComponent().getAttributes().get("fornecedorExcluido");
			FornecedorDAO fornecedorDao = new FornecedorDAO();
			fornecedorDao.excluir(fornecedor);
			
			Messages.addGlobalInfo("Fornecedor excluido com sucesso");
			fornecedores = fornecedorDao.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Não foi possível excluir o fornecedor");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		try {
			fornecedor = (Fornecedor) evento.getComponent().getAttributes().get("fornecedorEditado");
			
			CidadeDAO cidadeDao = new CidadeDAO();
			cidades = cidadeDao.listarTodos();
			EstadoDAO estadoDao = new EstadoDAO();
			estados = estadoDao.listarTodos();
			Long idEstado = fornecedor.getIdCidade().getIdEstado().getId();
			estadoSelecionado = estadoDao.buscar(idEstado);
			
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar Cidades");
			erro.printStackTrace();
		}
	}
	
	public void popularCidade(){
	
		try {
			if(estadoSelecionado != null){
				CidadeDAO cidadeDao = new CidadeDAO();
				cidades = cidadeDao.buscarPorEstado(estadoSelecionado.getId());
			} else {
				cidades = new ArrayList<>();
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar Cidades");
			erro.printStackTrace();
		}
	}
	
}
