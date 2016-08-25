package br.com.projetosispcp.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.projetosispcp.dao.TipoDAO;
import br.com.projetosispcp.entidade.Tipo;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class TipoProdutoBean implements Serializable{
	private List<Tipo> tiposProduto;
	private Tipo tipoProduto;
	
	public List<Tipo> getTiposProduto() {
		return tiposProduto;
	}
	public void setTiposProduto(List<Tipo> tiposProduto) {
		this.tiposProduto = tiposProduto;
	}
	public Tipo getTipoProduto() {
		return tipoProduto;
	}
	public void setTipoProduto(Tipo tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
	
	public void novo(){
		tipoProduto = new Tipo();
	}
	
	@PostConstruct
	public void listarItens(){
		try {
			TipoDAO tipoDAO = new TipoDAO();
			tiposProduto = tipoDAO.listarTodos();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar Tipos de Produto");
			erro.printStackTrace();
		}
	}
	
	public void salvarTipoProduto(){
		try {
			TipoDAO tipoDAO = new TipoDAO();
			tipoDAO.merge(tipoProduto);
			tiposProduto = tipoDAO.listarTodos();
			Messages.addGlobalInfo("Tipo de Produto: "+tipoProduto.getDescricaoTipo()+ ", salvo com sucesso");
			novo();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Tipo do Produto");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		tipoProduto = (Tipo) evento.getComponent().getAttributes().get("tipoEditado");
	}
	
	public void excluir(ActionEvent evento){
		try {
			tipoProduto = (Tipo) evento.getComponent().getAttributes().get("tipoExcluido");
			TipoDAO tipoDAO = new TipoDAO();
			tipoDAO.excluir(tipoProduto);
			Messages.addGlobalInfo("Tipo de Produto excluido com sucesso");
			tiposProduto = tipoDAO.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Não é possível excluir Tipo de Produto");
			erro.printStackTrace();
		}
	}
	
	public void limpar(){
		if (tipoProduto == null){
			novo();
		} else {
			Long idLimpo = tipoProduto.getId();
			novo();
			tipoProduto.setId(idLimpo);
		}
	}
	
}
