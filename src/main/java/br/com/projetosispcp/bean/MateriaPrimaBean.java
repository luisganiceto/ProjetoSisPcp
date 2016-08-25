package br.com.projetosispcp.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import org.omnifaces.util.Messages;
import br.com.projetosispcp.dao.FornecedorDAO;
import br.com.projetosispcp.dao.ProdutoDAO;
import br.com.projetosispcp.dao.ProdutoFornecedorDAO;
import br.com.projetosispcp.dao.UnidadeDAO;
import br.com.projetosispcp.entidade.Fornecedor;
import br.com.projetosispcp.entidade.Produto;
import br.com.projetosispcp.entidade.ProdutoFornecedor;
import br.com.projetosispcp.entidade.Unidade;

@SuppressWarnings("serial")
@SessionScoped
@ManagedBean
public class MateriaPrimaBean implements Serializable {
	private Produto materiaPrima;
	private List<Produto> materiasPrimas;
	private List<ProdutoFornecedor> fornecedoresMP;
	private ProdutoFornecedor fornecedorMP;
	private Boolean trueFalse;
	private List<Fornecedor> fornecedores;
	private List<Unidade> unidadeCompra;
	private List<Unidade> unidadeControle;
	
	
	public List<Produto> getMateriasPrimas() {
		return materiasPrimas;
	}
	public void setMateriasPrimas(List<Produto> materiasPrimas) {
		this.materiasPrimas = materiasPrimas;
	}
	public Produto getMateriaPrima() {
		return materiaPrima;
	}
	public void setMateriaPrima(Produto materiaPrima) {
		this.materiaPrima = materiaPrima;
	}
	public List<ProdutoFornecedor> getFornecedoresMP() {
		return fornecedoresMP;
	}
	public void setFornecedoresMP(List<ProdutoFornecedor> fornecedoresMP) {
		this.fornecedoresMP = fornecedoresMP;
	}
	public Boolean getTrueFalse() {
		return trueFalse;
	}
	public void setTrueFalse(Boolean trueFalse) {
		this.trueFalse = trueFalse;
	}
	public ProdutoFornecedor getFornecedorMP() {
		return fornecedorMP;
	}
	public void setFornecedorMP(ProdutoFornecedor fornecedorMP) {
		this.fornecedorMP = fornecedorMP;
	}
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}
	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}
	public List<Unidade> getUnidadeCompra() {
		return unidadeCompra;
	}
	public void setUnidadeCompra(List<Unidade> unidadeCompra) {
		this.unidadeCompra = unidadeCompra;
	}
	public List<Unidade> getUnidadeControle() {
		return unidadeControle;
	}
	public void setUnidadeControle(List<Unidade> unidadeControle) {
		this.unidadeControle = unidadeControle;
	}

	// CRUD MATÉRIA PRIMA
	@PostConstruct
	public void listar(){
		try {
			ProdutoDAO produtoDao = new ProdutoDAO();
			materiasPrimas = produtoDao.buscarProduto("5%");
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar as Matérias Primas");
			erro.printStackTrace();
		}
	}
	
	public void novo(){
		materiaPrima = new Produto();
		trueFalse = false;
		materiaPrima.setQuantidadeEstoque(0);
		materiaPrima.setValorCusto(new BigDecimal(0));
		listarFornecedores();
		
		try {
			UnidadeDAO dao = new UnidadeDAO();
			unidadeCompra = dao.listarTodos();
			unidadeControle = dao.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar Unidades");
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			ProdutoDAO produtoDao = new ProdutoDAO();
			produtoDao.merge(materiaPrima);
			Messages.addGlobalInfo("Matéria Prima salva com sucesso");
			materiasPrimas = produtoDao.buscarProduto("5%");
			String codigoParaPesquisa = materiaPrima.getCodigoProduto();
			materiaPrima = produtoDao.buscarProdutoPorCodigoProduto(codigoParaPesquisa);
			System.out.println(materiaPrima.getId());
			trueFalse = true;
			} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar Matéria Prima");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try {
			materiaPrima = (Produto) evento.getComponent().getAttributes().get("materiaPrimaExcluido");
			ProdutoDAO produtoDao = new ProdutoDAO();
			produtoDao.excluir(materiaPrima);
			materiasPrimas = produtoDao.buscarProduto("5%");
			Messages.addGlobalInfo("Matéria Prima excluida com sucesso");
					
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir a Matéria Prima");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		materiaPrima = (Produto) evento.getComponent().getAttributes().get("materiaPrimaExcluido");
		trueFalse = false;
		listarFornecedores();
	}
	
	
	public void validarCampoCodigoMP(FacesContext fc, UIComponent component, Object value) throws ValidatorException{
		String valor = value.toString();
		if(!valor.startsWith("5")){
			new FacesMessage("Código da Matéria Prima tem que iniciar com 5");
			throw new ValidatorException( new FacesMessage(FacesMessage.SEVERITY_ERROR, "Deve começar com 5", "ERRO"));
					
		}
	}
	
	// CRUD FORNECEDOR DA MATÉRIA PRIMA
	
	public void novoFornecedor(){
		fornecedorMP = new ProdutoFornecedor();
		try {
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedores = fornecedorDAO.listarTodos();
			fornecedorMP.setIdProduto(materiaPrima);
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar Fornecedores");
			erro.printStackTrace();
		}
	}
	public void listarFornecedores(){
		try {
			ProdutoFornecedorDAO produtoFornecedorDao = new ProdutoFornecedorDAO();
			fornecedoresMP = produtoFornecedorDao.listarFornecedorPorMP(materiaPrima.getId());
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar Fornecedores");
			erro.printStackTrace();
		}
	}
	
	public void salvarFornecedor(){
		try {
			ProdutoFornecedorDAO produtoFornecedorDAO = new ProdutoFornecedorDAO();
			produtoFornecedorDAO.merge(fornecedorMP);
			fornecedoresMP = produtoFornecedorDAO.listarFornecedorPorMP(materiaPrima.getId());
			Messages.addGlobalInfo("Fornecedor: "+fornecedorMP.getIdFornecedor().getNomeFantasia()+", adicionado com sucesso!");
			novoFornecedor();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar adicionar o Fornecedor");
			erro.printStackTrace();
		}
	}
	
	public void excluirFornecedor(ActionEvent evento){
		try {
			fornecedorMP = (ProdutoFornecedor) evento.getComponent().getAttributes().get("forMateriaPrimaExcluido");
			ProdutoFornecedorDAO produtoFornecedorDao = new ProdutoFornecedorDAO();
			produtoFornecedorDao.excluir(fornecedorMP);
			fornecedoresMP = produtoFornecedorDao.listarFornecedorPorMP(materiaPrima.getId());
			Messages.addGlobalInfo("Fornecedor excluida com sucesso");
					
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir o Fornecedor");
			erro.printStackTrace();
		}
	}
	
	public void editarFornecedor(ActionEvent evento){
		try {
			fornecedorMP = (ProdutoFornecedor) evento.getComponent().getAttributes().get("forMateriaPrimaEditado");
			
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedores = fornecedorDAO.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar Fornecedores");
			erro.printStackTrace();
		}
		
	}
}
