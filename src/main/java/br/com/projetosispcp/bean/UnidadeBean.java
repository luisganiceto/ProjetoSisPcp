package br.com.projetosispcp.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.com.projetosispcp.dao.UnidadeDAO;
import br.com.projetosispcp.entidade.Unidade;


@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class UnidadeBean implements Serializable{
	private List<Unidade> unidades;
	private List<Unidade> fator;
	private Unidade unidade;
	private Boolean trueFalse1;
	private Boolean trueFalse2;
	public List<Unidade> getUnidades() {
		return unidades;
	}
	public void setUnidades(List<Unidade> unidades) {
		this.unidades = unidades;
	}
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	public Boolean getTrueFalse1() {
		return trueFalse1;
	}
	public void setTrueFalse1(Boolean trueFalse1) {
		this.trueFalse1 = trueFalse1;
	}
	public Boolean getTrueFalse2() {
		return trueFalse2;
	}
	public void setTrueFalse2(Boolean trueFalse2) {
		this.trueFalse2 = trueFalse2;
	}
	public List<Unidade> getFator() {
		return fator;
	}
	public void setFator(List<Unidade> fator) {
		this.fator = fator;
	}
	
	public void novo(){
		unidade = new Unidade();
		trueFalse1 = false;
		trueFalse2 = true;
	}
	
	@PostConstruct
	public void listarTodos(){
		try {
			UnidadeDAO dao = new UnidadeDAO();
			unidades = dao.listarTodos();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as Unidades");
			erro.printStackTrace();
		}
	}
	
	public void salvarUnidade(){
		try {
			UnidadeDAO dao = new UnidadeDAO();
			dao.merge(unidade);
			trueFalse1 = true;
			trueFalse2 = false;
			listarFatorConversão();
			String buscarPorUnidade = unidade.getCodUnidade();
			unidade = dao.buscarUnidade(buscarPorUnidade);
			System.out.println("ID "+unidade.getId());
			
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a Unidade");
			erro.printStackTrace();
		}
	}
	
	public void listarFatorConversão(){
		try {
			UnidadeDAO dao = new UnidadeDAO();
			fator = dao.listarFator(); 
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar Fator de Conversão");
			erro.printStackTrace();
		}
	}
	
	public void salvarFator(){
		try {
			UnidadeDAO dao = new UnidadeDAO();
			dao.merge(unidade);
			Messages.addGlobalInfo("A Unidade foi salva com sucesso");
			novo();
			trueFalse1 = false;
			trueFalse2 = true;
			unidades = dao.listarTodos();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a Unidade");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try {
			unidade = (Unidade) evento.getComponent().getAttributes().get("unidadeExcluido");
			UnidadeDAO unidadeDao = new UnidadeDAO();
			unidadeDao.excluir(unidade);
			
			Messages.addGlobalInfo("Unidade excluida com sucesso");
			unidades = unidadeDao.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Não foi possível excluir o fornecedor");
			erro.printStackTrace();
		}
		
	}
	
	public void editar(ActionEvent evento){
		trueFalse1 = false;
		trueFalse2 = false;
		try {
			unidade = (Unidade) evento.getComponent().getAttributes().get("unidadeEditado");
			
			UnidadeDAO dao = new UnidadeDAO();
			fator = dao.listarFator();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar Cidades");
			erro.printStackTrace();
		}
		
	}

}
