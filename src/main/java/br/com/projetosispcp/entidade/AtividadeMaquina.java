package br.com.projetosispcp.entidade;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AtividadeMaquina {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
		
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idMaquina", referencedColumnName="id", nullable=false)
	private Maquina idMaquina;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idAtividade", referencedColumnName="id", nullable=false)
	private Atividade idAtividade;
		
	@JoinColumn(name="usuarioCriacao", referencedColumnName="id")
	@ManyToOne(fetch=FetchType.EAGER)
	private Usuario usuarioCriacao;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date dataCriacao;
	
	@JoinColumn(name="usuarioAlteracao", referencedColumnName="id")
	@ManyToOne(fetch=FetchType.EAGER)
	private Usuario usuarioAlteracao;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date dataAlteracao;
	
	
	
	public Usuario getUsuarioCriacao() {
		return usuarioCriacao;
	}

	public void setUsuarioCriacao(Usuario usuarioCriacao) {
		this.usuarioCriacao = usuarioCriacao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Maquina getIdMaquina() {
		return idMaquina;
	}

	public void setIdMaquina(Maquina idMaquina) {
		this.idMaquina = idMaquina;
	}

	public Atividade getIdAtividade() {
		return idAtividade;
	}

	public void setIdAtividade(Atividade idAtividade) {
		this.idAtividade = idAtividade;
	}
}