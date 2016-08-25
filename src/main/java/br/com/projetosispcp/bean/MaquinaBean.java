package br.com.projetosispcp.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.projetosispcp.dao.AtividadeDAO;
import br.com.projetosispcp.dao.AtividadeMaquinaDAO;
import br.com.projetosispcp.dao.FornecedorDAO;
import br.com.projetosispcp.dao.MaquinaDAO;
import br.com.projetosispcp.dao.SetorDAO;
import br.com.projetosispcp.dao.StatusDAO;
import br.com.projetosispcp.entidade.Atividade;
import br.com.projetosispcp.entidade.AtividadeMaquina;
import br.com.projetosispcp.entidade.Fornecedor;
import br.com.projetosispcp.entidade.Maquina;
import br.com.projetosispcp.entidade.Setor;
import br.com.projetosispcp.entidade.Status;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class MaquinaBean implements Serializable{
	private Maquina maquina;
	private List<Maquina> maquinas;
	private List<Fornecedor> fornecedores;
	private List<Setor> setores;
	private List<Status> status;
	private List<AtividadeMaquina> atividadesMaquina;
	private AtividadeMaquina atividadeMaq;
	private List<Atividade> atividades;
	private Boolean trueFalse1;
	private Boolean trueFalse2;
	
	public Maquina getMaquina() {
		return maquina;
	}
	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}
	public List<Maquina> getMaquinas() {
		return maquinas;
	}
	public void setMaquinas(List<Maquina> maquinas) {
		this.maquinas = maquinas;
	}
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}
	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}
	public List<Setor> getSetores() {
		return setores;
	}
	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}
	public List<Status> getStatus() {
		return status;
	}
	public void setStatus(List<Status> status) {
		this.status = status;
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
	public List<Atividade> getAtividades() {
		return atividades;
	}
	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}
	public List<AtividadeMaquina> getAtividadesMaquina() {
		return atividadesMaquina;
	}
	public void setAtividadesMaquina(List<AtividadeMaquina> atividadesMaquina) {
		this.atividadesMaquina = atividadesMaquina;
	}
	public AtividadeMaquina getAtividadeMaq() {
		return atividadeMaq;
	}
	public void setAtividadeMaq(AtividadeMaquina atividadeMaq) {
		this.atividadeMaq = atividadeMaq;
	}
		
	public void novo(){
		maquina = new Maquina();
		trueFalse1 = false;
		trueFalse2 = true;
		
		try {
			FornecedorDAO dao = new FornecedorDAO();
			fornecedores = dao.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar Fornecedor");
			erro.printStackTrace();
		}
		try {
			SetorDAO daoS = new SetorDAO();
			setores = daoS.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar Setore");
			erro.printStackTrace();
		}
		try {
			StatusDAO daoSt = new StatusDAO();
			status = daoSt.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar Status");
			erro.printStackTrace();
		}
	}
	
	@PostConstruct
	public void listarTodos(){
		try {
			MaquinaDAO dao = new MaquinaDAO();
			maquinas = dao.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as Máquinas");
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			MaquinaDAO dao = new MaquinaDAO();
			dao.merge(maquina);
			Messages.addGlobalInfo("A Máquina: "+maquina.getDescricaoMaquina()+", foi salva com sucesso.");
			listarTodos();
			trueFalse1 = true;
			trueFalse2 = false;
			listarAtividade();
			atividadeMaq = new AtividadeMaquina();
			String pesqMaquina = maquina.getDescricaoMaquina();
			maquina = dao.buscarIdMaquina(pesqMaquina);
			AtividadeMaquinaDAO daoAM = new AtividadeMaquinaDAO();
			atividadesMaquina = daoAM.listarAtividadePorMaq(maquina.getId());
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a Máquina");
			erro.printStackTrace();
		}
	}
	
	public void listarAtividade(){
		try {
			AtividadeDAO dao = new AtividadeDAO();
			atividades = dao.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar Atividades");
			erro.printStackTrace();
		}
	}
	
	public void salvarAtividade(){
		try {
			AtividadeMaquinaDAO dao = new AtividadeMaquinaDAO();
			atividadeMaq.setIdMaquina(maquina);
			dao.merge(atividadeMaq);
			Messages.addGlobalInfo("A Atividade: "+atividadeMaq.getIdAtividade().getDescricaoAtividade()+", foi adicionaod com sucesso");
			atividadeMaq = new AtividadeMaquina();
			atividadesMaquina = dao.listarAtividadePorMaq(maquina.getId());
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar adicionar Atividade");
			erro.printStackTrace();
		}
	}
	
	public void excuirAtividade(ActionEvent evento){
		try {
			atividadeMaq = (AtividadeMaquina) evento.getComponent().getAttributes().get("atividadeExcluida");
			AtividadeMaquinaDAO dao = new AtividadeMaquinaDAO();
			dao.excluir(atividadeMaq);
			Messages.addGlobalInfo("Atividade foi removida com sucesso");
			atividadesMaquina = dao.listarAtividadePorMaq(maquina.getId());
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Não foi possível excluir a Atividade");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try {
			maquina = (Maquina) evento.getComponent().getAttributes().get("maquinaExcluida");
			MaquinaDAO dao = new MaquinaDAO();
			dao.excluir(maquina);
			Messages.addGlobalInfo("Máquina excluida com sucesso");
			maquinas = dao.listarTodos();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir a Máquina");
			erro.printStackTrace();
		}	
	}
	
	public void editar(ActionEvent evento){
		trueFalse1 = false;
		trueFalse2 = false;
				
		maquina = (Maquina) evento.getComponent().getAttributes().get("maquinaEditada");
		try {
			FornecedorDAO dao = new FornecedorDAO();
			fornecedores = dao.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar Fornecedor");
			erro.printStackTrace();
		}
		try {
			SetorDAO daoS = new SetorDAO();
			setores = daoS.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar Setore");
			erro.printStackTrace();
		}
		try {
			StatusDAO daoSt = new StatusDAO();
			status = daoSt.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar Status");
			erro.printStackTrace();
		}
		try {
			AtividadeDAO daoAt = new AtividadeDAO();
			atividades = daoAt.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar Atividades");
			erro.printStackTrace();
		}
		AtividadeMaquinaDAO daoAM = new AtividadeMaquinaDAO();
		atividadesMaquina = daoAM.listarAtividadePorMaq(maquina.getId());
	}
	
	public void limpar(){
		if (maquina == null){
			novo();
		} else {
			Long idLimpo = maquina.getId();
			novo();
			maquina.setId(idLimpo);
		}
	}
	

}
