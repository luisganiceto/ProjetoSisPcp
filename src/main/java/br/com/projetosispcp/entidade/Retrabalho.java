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
public class Retrabalho {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(length=100, nullable=false)
	private String motivo;
	
	@Column(nullable=false)
	private Integer quantidade;
	
	@JoinColumn(name="idAtividadeOrdemProducao", referencedColumnName="id", nullable=false)
	@ManyToOne(fetch=FetchType.EAGER)
	private AtividadeOrdemProducao idAtividadeOrdemProducao;
	
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

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public AtividadeOrdemProducao getIdAtividadeOrdemProducao() {
		return idAtividadeOrdemProducao;
	}

	public void setIdAtividadeOrdemProducao(AtividadeOrdemProducao idAtividadeOrdemProducao) {
		this.idAtividadeOrdemProducao = idAtividadeOrdemProducao;
	}
	
	
}
