package br.com.projetosispcp.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.management.RuntimeOperationsException;

import org.omnifaces.util.Messages;
import br.com.projetosispcp.dao.FuncaoDAO;
import br.com.projetosispcp.dao.SetorDAO;
import br.com.projetosispcp.entidade.Funcao;
import br.com.projetosispcp.entidade.Setor;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class FuncaoBean implements Serializable{
	private List<Funcao> funcoes;
	private Funcao funcao;
	private List<Setor> setores;
	
	public List<Funcao> getFuncoes() {
		return funcoes;
	}
	public void setFuncoes(List<Funcao> funcoes) {
		this.funcoes = funcoes;
	}
	public Funcao getFuncao() {
		return funcao;
	}
	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}
	public List<Setor> getSetores() {
		return setores;
	}
	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}
	
	
	public void novo(){
		funcao = new Funcao();
		
		try {
			SetorDAO dao = new SetorDAO();
			setores = dao.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os Setores");
			erro.printStackTrace();
		}
	}
	
	@PostConstruct
	public void listarTodos(){
		try {
			FuncaoDAO dao = new FuncaoDAO();
			funcoes = dao.listarTodos();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as Funções");
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			FuncaoDAO dao = new FuncaoDAO();
			dao.merge(funcao);
			Messages.addGlobalInfo("A Função: "+funcao.getDescricaoFuncao()+", foi salva com sucesso");
			funcoes = dao.listarTodos();
			novo();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a Função");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try {
			funcao = (Funcao) evento.getComponent().getAttributes().get("funcaoExcluido");
			FuncaoDAO dao = new FuncaoDAO();
			dao.excluir(funcao);
			Messages.addGlobalInfo("Função excluida com sucesso");
			funcoes = dao.listarTodos();
			
		} catch (RuntimeOperationsException erro) {
			Messages.addGlobalError("Não foi possível excluir a Função");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		try {
			funcao = (Funcao) evento.getComponent().getAttributes().get("funcaoEditado");
			SetorDAO dao = new SetorDAO();
			setores = dao.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os Setores");
			erro.printStackTrace();
		}
	}
	
	public void limpar(){
		if(funcao == null){
			novo();
		} else{
			Long idLimpo = funcao.getId();
			novo();
			funcao.setId(idLimpo);
		}
	}
	
}
