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
import br.com.projetosispcp.dao.ProdutoDAO;
import br.com.projetosispcp.entidade.Estrutura;
import br.com.projetosispcp.entidade.Produto;
import br.com.projetosispcp.entidade.Tipo;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class PecaBean implements Serializable {
	private List<Produto> pecas;
	private Produto peca;
	private Boolean vf1;
	private Boolean vf2;
	private Estrutura estrutura;
	private List<Tipo> tipoProduto;
	
	public List<Produto> getPecas() {
		return pecas;
	}
	public void setPecas(List<Produto> pecas) {
		this.pecas = pecas;
	}
	public Produto getPeca() {
		return peca;
	}
	public void setPeca(Produto peca) {
		this.peca = peca;
	}
	public Boolean getVf1() {
		return vf1;
	}
	public void setVf1(Boolean vf1) {
		this.vf1 = vf1;
	}
	public Boolean getVf2() {
		return vf2;
	}
	public void setVf2(Boolean vf2) {
		this.vf2 = vf2;
	}
	public Estrutura getEstrutura() {
		return estrutura;
	}
	public void setEstrutura(Estrutura estrutura) {
		this.estrutura = estrutura;
	}
	public List<Tipo> getTipoProduto() {
		return tipoProduto;
	}
	public void setTipoProduto(List<Tipo> tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
	

	public void novo(){
		peca = new Produto();
		peca.setQuantidadeEstoque(0);
		peca.setValorCusto(new BigDecimal(0));
		vf1 = false;
		vf2= true;
	}
	
	public void liberarEdicao(){
		vf1 = false;
		vf2= true;
	}
	
	@PostConstruct
	public void listarTodos(){
		try {
			ProdutoDAO dao = new ProdutoDAO();
			pecas = dao.buscarProduto("3%");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listas as Peças");
			erro.printStackTrace();
		}
		
	}
	
	public void salvar(){
		try {
			ProdutoDAO dao = new ProdutoDAO();
			dao.merge(peca);
			Messages.addGlobalInfo("A Peça: "+peca.getDescricaoProduto()+", foi salva com sucesso");
			peca = dao.buscarProdutoPorCodigoProduto(peca.getCodigoProduto());
			pecas = dao.buscarProduto("3%");
			vf1 = true;
			vf2= false;
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar Peça");
			erro.printStackTrace();
		}
		
	}
	
	public void excluir(ActionEvent evento){
		try {
			peca = (Produto) evento.getComponent().getAttributes().get("pecaExcluido");
			ProdutoDAO produtoDao = new ProdutoDAO();
			produtoDao.excluir(peca);
			pecas = produtoDao.buscarProduto("3%");
			Messages.addGlobalInfo("Peça excluida com sucesso");
					
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir a Peça");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		liberarEdicao();
		peca = (Produto) evento.getComponent().getAttributes().get("pecaEditado");
	}
	
	public void validarCampoCodigoPeca(FacesContext fc, UIComponent component, Object value) throws ValidatorException{
		String valor = value.toString();
		if(!valor.startsWith("3")){
			new FacesMessage("Código da Peça tem que iniciar com 3");
			throw new ValidatorException( new FacesMessage(FacesMessage.SEVERITY_ERROR, "O Código do Produto deve começar com 3", "ERRO"));
					
		}
	}	

}
