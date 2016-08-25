package br.com.projetosispcp.bean;

import java.io.Serializable;
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
import br.com.projetosispcp.entidade.Produto;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class VolumeBean implements Serializable{
	
	private List<Produto> volumes;
	private Produto volume;
	private Boolean tf1;
	private Boolean tf2;
	
	public List<Produto> getVolumes() {
		return volumes;
	}
	public void setVolumes(List<Produto> volumes) {
		this.volumes = volumes;
	}
	public Produto getVolume() {
		return volume;
	}
	public void setVolume(Produto volume) {
		this.volume = volume;
	}
	public Boolean getTf1() {
		return tf1;
	}
	public void setTf1(Boolean tf1) {
		this.tf1 = tf1;
	}
	public Boolean getTf2() {
		return tf2;
	}
	public void setTf2(Boolean tf2) {
		this.tf2 = tf2;
	}
	
	
	public void novo(){
		volume = new Produto();
		volume.setQuantidadeEstoque(0);
		volume.setQuantidadeVolume(1);
		tf1 = false;
		tf2 = true;
	}
	
	@PostConstruct
	public void listarTodos(){
		try {
			ProdutoDAO dao = new ProdutoDAO();
			volumes = dao.buscarProduto("2%");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os Volumes");
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			ProdutoDAO dao = new ProdutoDAO();
			dao.merge(volume);
			Messages.addGlobalInfo("O Volume: "+volume.getDescricaoProduto()+", foi salvo com sucesso");
			volume = dao.buscarProdutoPorCodigoProduto(volume.getCodigoProduto());
			volumes = dao.buscarProduto("2%");
			tf1 = true;
			tf2 = false;
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Volume");
			erro.printStackTrace();
		}
	}
	
	public void liberarEditar(){
		tf1 = false;
		tf2 = true;
	}
	
	public void validarCampoCodigoVolume(FacesContext fc, UIComponent component, Object value) throws ValidatorException{
		String valor = value.toString();
		if(!valor.startsWith("2")){
			throw new ValidatorException( new FacesMessage(FacesMessage.SEVERITY_ERROR, "O Código do Produto deve começar com 2", "ERRO"));
		}
	}

	public void excluir(ActionEvent evento){
		try {
			volume = (Produto) evento.getComponent().getAttributes().get("volumeExcluido");
			ProdutoDAO produtoDao = new ProdutoDAO();
			produtoDao.excluir(volume);
			volumes = produtoDao.buscarProduto("2%");
			Messages.addGlobalInfo("Volume excluido com sucesso");
					
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir o Volume");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		tf1 = false;
		tf2= true;
		volume = (Produto) evento.getComponent().getAttributes().get("volumeEditado");
	}
	
}
