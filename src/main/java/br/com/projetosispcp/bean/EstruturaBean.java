package br.com.projetosispcp.bean;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.omnifaces.util.Messages;
import br.com.projetosispcp.dao.EstruturaDAO;
import br.com.projetosispcp.dao.ProdutoDAO;
import br.com.projetosispcp.dao.TipoDAO;
import br.com.projetosispcp.entidade.Estrutura;
import br.com.projetosispcp.entidade.Produto;
import br.com.projetosispcp.entidade.Tipo;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class EstruturaBean implements Serializable {
	private List<Estrutura> estruturas;
	private Estrutura estrutura;
	private Character tipoEstrutura;
	private List<Produto> produtos;
	private List<Tipo> tipos;
	
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public Character getTipoEstrutura() {
		return tipoEstrutura;
	}
	public void setTipoEstrutura(Character tipoEstrutura) {
		this.tipoEstrutura = tipoEstrutura;
	}
	public List<Estrutura> getEstruturas() {
		return estruturas;
	}
	public void setEstruturas(List<Estrutura> estruturas) {
		this.estruturas = estruturas;
	}
	public Estrutura getEstrutura() {
		return estrutura;
	}
	public void setEstrutura(Estrutura estrutura) {
		this.estrutura = estrutura;
	}
	public List<Tipo> getTipos() {
		return tipos;
	}
	public void setTipos(List<Tipo> tipos) {
		this.tipos = tipos;
	}
	

	public void novo(){
		estrutura = new Estrutura();
		produtos = new ArrayList<Produto>();
		tipoEstrutura = null;
		
		try {
			TipoDAO dao = new TipoDAO();
			tipos = dao.listarTodos();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar Tipos de Produtos");
			erro.printStackTrace();
		}
	}
	
	@PostConstruct
	public void listarTodos(){
		try {
			EstruturaDAO dao = new EstruturaDAO();
			estruturas = dao.listarTodos();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar Estruturas");
			erro.printStackTrace();
		}
	}
	
	public List<Produto> listarProdutos(String nome){
		String inicial = null;
		if (tipoEstrutura == 'C'){
			inicial = "1%";
		} else if(tipoEstrutura == 'V') {
			inicial = "2%";
		} else if (tipoEstrutura == 'P') {
			inicial = "3%";
		} else {
			Messages.addGlobalError("Selecione o Tipo da Estrutura");
		}
		ProdutoDAO dao = new ProdutoDAO();
		produtos = dao.buscarProduto(inicial);
		
		return produtos;
	}
	
	public void sugerirCodReduzido(){
	 	estrutura.setCodigoReduzido(estrutura.getIdProduto().getCodigoProduto());
	}
 	
	public void salvar(){
		System.out.println(estrutura.getIdProduto());
		System.out.println(estrutura.getIdTipo());
	}
		
	public void excluir(ActionEvent evento){
		
	}
	
	public void editar(ActionEvent evento){
		
	}
	
}
