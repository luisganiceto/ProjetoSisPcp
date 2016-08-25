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
public class Lote {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(length=50, nullable=false)
	private String descricaoLote;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date dataInicioPrevisto;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date dataFinalPrevisto;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date dataIniciou;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date dataFinalizou;
	
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

	public String getDescricaoLote() {
		return descricaoLote;
	}

	public void setDescricaoLote(String descricaoLote) {
		this.descricaoLote = descricaoLote;
	}

	public Date getDataInicioPrevisto() {
		return dataInicioPrevisto;
	}

	public void setDataInicioPrevisto(Date dataInicioPrevisto) {
		this.dataInicioPrevisto = dataInicioPrevisto;
	}

	public Date getDataFinalPrevisto() {
		return dataFinalPrevisto;
	}

	public void setDataFinalPrevisto(Date dataFinalPrevisto) {
		this.dataFinalPrevisto = dataFinalPrevisto;
	}

	public Date getDataIniciou() {
		return dataIniciou;
	}

	public void setDataIniciou(Date dataIniciou) {
		this.dataIniciou = dataIniciou;
	}

	public Date getDataFinalizou() {
		return dataFinalizou;
	}

	public void setDataFinalizou(Date dataFinalizou) {
		this.dataFinalizou = dataFinalizou;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	
}
