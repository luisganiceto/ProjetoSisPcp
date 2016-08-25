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
public class OrdemProducao {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@JoinColumn(name="idItemLote", referencedColumnName="id", nullable=false)
	@ManyToOne(fetch=FetchType.EAGER)
	private ItemLote idItemLote;
	
	@Column(nullable=false)
	private Integer quandtidade;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date dataInicio;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date dataFinalizou;

	@JoinColumn(name="idStatus", referencedColumnName="id", nullable=false)
	@ManyToOne(fetch=FetchType.EAGER)
	private Status idStatus;
	
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

	public ItemLote getIdItemLote() {
		return idItemLote;
	}

	public void setIdItemLote(ItemLote idItemLote) {
		this.idItemLote = idItemLote;
	}

	public Integer getQuandtidade() {
		return quandtidade;
	}

	public void setQuandtidade(Integer quandtidade) {
		this.quandtidade = quandtidade;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFinalizou() {
		return dataFinalizou;
	}

	public void setDataFinalizou(Date dataFinalizou) {
		this.dataFinalizou = dataFinalizou;
	}

	public Status getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Status idStatus) {
		this.idStatus = idStatus;
	}
}
