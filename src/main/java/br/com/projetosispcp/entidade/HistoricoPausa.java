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
public class HistoricoPausa {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long idHistoricoPausa;
	
	@JoinColumn(name="idPausa", referencedColumnName="id", nullable=false)
	@ManyToOne(fetch=FetchType.EAGER)
	private Pausa idPausa;
	
	@JoinColumn(name="idAtividadeOrdem", referencedColumnName="id", nullable=false)
	@ManyToOne(fetch=FetchType.EAGER)
	private AtividadeOrdemProducao idAtividadeOrdem;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date inicioPausa;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date fimPausa;
	
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


	public Long getIdHistoricoPausa() {
		return idHistoricoPausa;
	}

	public void setIdHistoricoPausa(Long idHistoricoPausa) {
		this.idHistoricoPausa = idHistoricoPausa;
	}

	public Pausa getIdPausa() {
		return idPausa;
	}

	public void setIdPausa(Pausa idPausa) {
		this.idPausa = idPausa;
	}

	public AtividadeOrdemProducao getIdAtividadeOrdem() {
		return idAtividadeOrdem;
	}

	public void setIdAtividadeOrdem(AtividadeOrdemProducao idAtividadeOrdem) {
		this.idAtividadeOrdem = idAtividadeOrdem;
	}

	public Date getInicioPausa() {
		return inicioPausa;
	}

	public void setInicioPausa(Date inicioPausa) {
		this.inicioPausa = inicioPausa;
	}

	public Date getFimPausa() {
		return fimPausa;
	}

	public void setFimPausa(Date fimPausa) {
		this.fimPausa = fimPausa;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	
}
