package br.com.projetosispcp.entidade;

import java.math.BigDecimal;
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
public class FerramentaMaquina {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@JoinColumn(name="idFerramenta", referencedColumnName="id", nullable=false)
	@ManyToOne(fetch=FetchType.EAGER)
	private Ferramenta idFerramenta;
	
	@JoinColumn(name="idMaquina", referencedColumnName="id",nullable=false)
	@ManyToOne(fetch=FetchType.EAGER)
	private Maquina idMaquina;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date dataColocou;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataRetirou;
	
	@Column(precision=7, scale=4)
	private BigDecimal tempoDeUso;
	
	@JoinColumn(name="idUnidade", referencedColumnName="id", nullable=false)
	@ManyToOne(fetch=FetchType.EAGER)
	private Unidade idUnidade;
	
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

	public Ferramenta getIdFerramenta() {
		return idFerramenta;
	}

	public void setIdFerramenta(Ferramenta idFerramenta) {
		this.idFerramenta = idFerramenta;
	}

	public Maquina getIdMaquina() {
		return idMaquina;
	}

	public void setIdMaquina(Maquina idMaquina) {
		this.idMaquina = idMaquina;
	}

	public Date getDataColocou() {
		return dataColocou;
	}

	public void setDataColocou(Date dataColocou) {
		this.dataColocou = dataColocou;
	}

	public Date getDataRetirou() {
		return dataRetirou;
	}

	public void setDataRetirou(Date dataRetirou) {
		this.dataRetirou = dataRetirou;
	}

	public BigDecimal getTempoDeUso() {
		return tempoDeUso;
	}

	public void setTempoDeUso(BigDecimal tempoDeUso) {
		this.tempoDeUso = tempoDeUso;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Unidade getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(Unidade idUnidade) {
		this.idUnidade = idUnidade;
	}
	
	
	
}
