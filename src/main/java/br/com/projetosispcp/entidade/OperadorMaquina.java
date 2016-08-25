package br.com.projetosispcp.entidade;

import java.util.Date;

import javax.persistence.Column;
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
public class OperadorMaquina {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@JoinColumn(name="idMaquina", referencedColumnName="id", nullable=false)
	@ManyToOne(fetch=FetchType.EAGER)
	private Maquina idMaquina;
	
	@JoinColumn(name="idOperador", referencedColumnName="id", nullable=false)
	@ManyToOne(fetch=FetchType.EAGER)
	private Operador idOperador;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date dataIniciouNaMaquina;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date dataTerminouNaMaquina;
	
	@Column(length=100)
	private String observacoes;
	
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


	public Operador getIdOperador() {
		return idOperador;
	}


	public void setIdOperador(Operador idOperador) {
		this.idOperador = idOperador;
	}


	public Date getDataIniciouNaMaquina() {
		return dataIniciouNaMaquina;
	}


	public void setDataIniciouNaMaquina(Date dataIniciouNaMaquina) {
		this.dataIniciouNaMaquina = dataIniciouNaMaquina;
	}


	public Date getDataTerminouNaMaquina() {
		return dataTerminouNaMaquina;
	}


	public void setDataTerminouNaMaquina(Date dataTerminouNaMaquina) {
		this.dataTerminouNaMaquina = dataTerminouNaMaquina;
	}


	public String getObservacoes() {
		return observacoes;
	}


	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	
}
