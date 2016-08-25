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
import br.com.projetosispcp.dao.CorRacaDAO;
import br.com.projetosispcp.dao.EstadoCivilDAO;
import br.com.projetosispcp.dao.EstadoDAO;
import br.com.projetosispcp.dao.FuncaoDAO;
import br.com.projetosispcp.dao.OperadorDAO;
import br.com.projetosispcp.dao.PaisDAO;
import br.com.projetosispcp.dao.StatusDAO;
import br.com.projetosispcp.entidade.Cidade;
import br.com.projetosispcp.entidade.CorRaca;
import br.com.projetosispcp.entidade.Estado;
import br.com.projetosispcp.entidade.EstadoCivil;
import br.com.projetosispcp.entidade.Funcao;
import br.com.projetosispcp.entidade.Operador;
import br.com.projetosispcp.entidade.Pais;
import br.com.projetosispcp.entidade.Status;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped

public class OperadorBean implements Serializable{
	private List<Operador> operadores;
	private Operador operador;
	private List<Estado> estadoNatal;
	private List<Cidade> cidadeNatal;
	private Estado estadoSelecionado;
	private List<Pais> nacionalidade;
	private List<EstadoCivil> estadoCivil;
	private List<CorRaca> corRaca;
	private List<Estado> ufCartTrab;
	private List<Estado> estados;
	private Estado estadoEndSelecionado;
	private List<Cidade> cidades;
	private List<Funcao> funcao;
	private List<Status> status;
 	
	public List<Operador> getOperadores() {
		return operadores;
	}
	public void setOperadores(List<Operador> operadores) {
		this.operadores = operadores;
	}
	public Operador getOperador() {
		return operador;
	}
	public void setOperador(Operador operador) {
		this.operador = operador;
	}
	public List<Estado> getEstadoNatal() {
		return estadoNatal;
	}
	public void setEstadoNatal(List<Estado> estadoNatal) {
		this.estadoNatal = estadoNatal;
	}
	public List<Cidade> getCidadeNatal() {
		return cidadeNatal;
	}
	public void setCidadeNatal(List<Cidade> cidadeNatal) {
		this.cidadeNatal = cidadeNatal;
	}
	public Estado getEstadoSelecionado() {
		return estadoSelecionado;
	}
	public void setEstadoSelecionado(Estado estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}
	public List<Pais> getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(List<Pais> nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public List<CorRaca> getCorRaca() {
		return corRaca;
	}
	public void setCorRaca(List<CorRaca> corRaca) {
		this.corRaca = corRaca;
	}
	public List<EstadoCivil> getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(List<EstadoCivil> estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public List<Estado> getUfCartTrab() {
		return ufCartTrab;
	}
	public void setUfCartTrab(List<Estado> ufCartTrab) {
		this.ufCartTrab = ufCartTrab;
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
	public Estado getEstadoEndSelecionado() {
		return estadoEndSelecionado;
	}
	public void setEstadoEndSelecionado(Estado estadoEndSelecionado) {
		this.estadoEndSelecionado = estadoEndSelecionado;
	}
	public List<Funcao> getFuncao() {
		return funcao;
	}
	public void setFuncao(List<Funcao> funcao) {
		this.funcao = funcao;
	}
	public List<Status> getStatus() {
		return status;
	}
	public void setStatus(List<Status> status) {
		this.status = status;
	}
	
	public void novoCarregar(){
		try {
			EstadoDAO estadoDao = new EstadoDAO();
			estadoNatal = estadoDao.listarTodos();
			estadoSelecionado = null;
			ufCartTrab = estadoDao.listarTodos();
			estados = estadoDao.listarTodos();
			estadoEndSelecionado = null;
			cidadeNatal = new ArrayList<Cidade>();
			cidades = new ArrayList<Cidade>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar Estado/Cidades");
			erro.printStackTrace();
		}
		try {
			PaisDAO paisDao = new PaisDAO();
			nacionalidade = paisDao.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar Nacionalidade");
			erro.printStackTrace();
		}
		try {
			EstadoCivilDAO estadoCivilDao = new EstadoCivilDAO();
			estadoCivil = estadoCivilDao.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar Estado Civil");
			erro.printStackTrace();
		}
		try {
			CorRacaDAO corRacaDao = new CorRacaDAO();
			corRaca = corRacaDao.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar Cor/Raça");
			erro.printStackTrace();
		}
		try {
			FuncaoDAO funcaoDao = new FuncaoDAO();
			funcao = funcaoDao.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar Funções");
			erro.printStackTrace();
		}
		try {
			StatusDAO statusDao = new StatusDAO();
			status = statusDao.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar Status");
			erro.printStackTrace();
		}
	}
	
	public void novo(){
		operador = new Operador();
		novoCarregar();
	}
	
	@PostConstruct
	public void listarTodos(){
		try {
			OperadorDAO dao = new OperadorDAO();
			operadores = dao.listarTodos();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar Operadores");
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			OperadorDAO dao = new OperadorDAO();
			dao.merge(operador);
			
			Messages.addGlobalInfo("O Operador: "+operador.getNome()+", foi salvo com sucesso.");
			novo();
			listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Operador");
			erro.printStackTrace();
		}
	}
	
	public void popularCidadeNatal(){
		
		try {
			if(estadoSelecionado != null){
				CidadeDAO cidadeDao = new CidadeDAO();
				cidadeNatal = cidadeDao.buscarPorEstado(estadoSelecionado.getId());
			} else {
				cidadeNatal = new ArrayList<Cidade>();
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar Cidade");
			erro.printStackTrace();
		}
	}
		
	public void popularCidade(){
		
		try {
			if(estadoEndSelecionado != null){
				CidadeDAO cidadeDao = new CidadeDAO();
				cidades = cidadeDao.buscarPorEstado(estadoEndSelecionado.getId());
			} else {
				cidades = new ArrayList<Cidade>();
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar Cidade");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento){
		try {
			operador = (Operador) evento.getComponent().getAttributes().get("operadorExcluido");
			OperadorDAO dao = new OperadorDAO();
			dao.excluir(operador);
			
			Messages.addGlobalInfo("Operador excluido com sucesso");
			operadores = dao.listarTodos();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Não foi possível excluir o Operador");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		try {
			operador = (Operador) evento.getComponent().getAttributes().get("operadorEditado");
			

			try {
				CidadeDAO cidadeDao = new CidadeDAO();
				cidadeNatal = cidadeDao.listarTodos();
				cidades = cidadeDao.listarTodos();
			} catch (RuntimeException erro) {
				Messages.addGlobalError("Erro ao tentar listar Cidade");
				erro.printStackTrace();
			}
			try {
				EstadoDAO estadoDao = new EstadoDAO();
				ufCartTrab = estadoDao.listarTodos();
				
				estadoNatal = estadoDao.listarTodos();
				Long idEstadoNatal = operador.getNaturalidade().getIdEstado().getId();
				estadoSelecionado = estadoDao.buscar(idEstadoNatal);
				estados = estadoDao.listarTodos();
				Long idEstadoEnd = operador.getIdCidade().getId();
				estadoEndSelecionado = estadoDao.buscar(idEstadoEnd);
				
			} catch (RuntimeException erro) {
				Messages.addGlobalError("Erro ao tentar listar Cidade");
				erro.printStackTrace();
			}
			try {
				PaisDAO paisDao = new PaisDAO();
				nacionalidade = paisDao.listarTodos();
			} catch (RuntimeException erro) {
				Messages.addGlobalError("Erro ao tentar listar Nacionalidade");
				erro.printStackTrace();
			}
			try {
				EstadoCivilDAO estadoCivilDao = new EstadoCivilDAO();
				estadoCivil = estadoCivilDao.listarTodos();
			} catch (RuntimeException erro) {
				Messages.addGlobalError("Erro ao tentar listar Estado Civil");
				erro.printStackTrace();
			}
			try {
				CorRacaDAO corRacaDao = new CorRacaDAO();
				corRaca = corRacaDao.listarTodos();
			} catch (RuntimeException erro) {
				Messages.addGlobalError("Erro ao tentar listar Cor/Raça");
				erro.printStackTrace();
			}
			try {
				FuncaoDAO funcaoDao = new FuncaoDAO();
				funcao = funcaoDao.listarTodos();
			} catch (RuntimeException erro) {
				Messages.addGlobalError("Erro ao tentar listar Funções");
				erro.printStackTrace();
			}
			try {
				StatusDAO statusDao = new StatusDAO();
				status = statusDao.listarTodos();
			} catch (RuntimeException erro) {
				Messages.addGlobalError("Erro ao tentar listar Status");
				erro.printStackTrace();
			}
			
			} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar Operador");
			erro.printStackTrace();
		}
	}
	
	
	

}
