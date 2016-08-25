package br.com.projetosispcp.dao;

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
import br.com.projetosispcp.entidade.Produto;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class ConjuntoBean implements Serializable{
	
	private List<Produto> conjuntos;
	private Produto conjunto;
	private Boolean vf1;
	private Boolean vf2;
	
	public List<Produto> getConjuntos() {
		return conjuntos;
	}
	public void setConjuntos(List<Produto> conjuntos) {
		this.conjuntos = conjuntos;
	}
	public Produto getConjunto() {
		return conjunto;
	}
	public void setConjunto(Produto conjunto) {
		this.conjunto = conjunto;
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
	
	public void novo(){
		conjunto = new Produto();
		conjunto.setQuantidadeEstoque(0);
		conjunto.setQuantidadeVolume(1);
		conjunto.setValorCusto(new BigDecimal(0));
		vf1 = false;
		vf2 = true;
	}
	
	@PostConstruct
	public void listarTodos(){
		try {
			ProdutoDAO dao = new ProdutoDAO();
			conjuntos = dao.buscarProduto("1%");
			
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			ProdutoDAO dao = new ProdutoDAO();
			dao.merge(conjunto);
			conjuntos = dao.buscarProduto("1%");
			Messages.addGlobalInfo("O Conjunto: "+conjunto.getDescricaoProduto()+", foi salvo com sucesso", "a");
			conjunto = dao.buscarProdutoPorCodigoProduto(conjunto.getCodigoProduto());
			vf1 = true;
			vf2 = false;
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Conjunto");
			erro.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public void editar(ActionEvent evento){
		conjunto = (Produto) evento.getComponent().getAttributes().get("conjuntoEditado");
		vf1 = false;
		vf2 = true;
		
	}
	
	public void excluir(ActionEvent evento){
		try {
			conjunto = (Produto) evento.getComponent().getAttributes().get("conjuntoExcluido");
			ProdutoDAO dao = new ProdutoDAO();
			dao.excluir(conjunto);
			conjuntos = dao.buscarProduto("1%");
			Messages.addGlobalInfo("Conjunto Excluido com sucesso");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir Conjunto");
			e.printStackTrace();
		}
	}

	public void validarCampoCodigoConjunto(FacesContext fc, UIComponent component, Object value) throws ValidatorException{
		String valor = value.toString();
		if(!valor.startsWith("1")){
			throw new ValidatorException( new FacesMessage(FacesMessage.SEVERITY_ERROR, "O Código do Produto deve começar com 1", "ERRO"));
		}
	}
	
	public void liberandoEdicao(){
		vf1 = false;
		vf2 = true;
	}
}
