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
public class Maquina {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(length=100, nullable=false)
	private String descricaoMaquina;
	
	@Column(length=100)
	private String observacoes;
	
	@JoinColumn(name="idFornecedor", referencedColumnName="id", nullable=false)
	@ManyToOne(fetch=FetchType.EAGER)
	private Fornecedor idFornecedor;
	
	@JoinColumn(name="idSetor", referencedColumnName="id", nullable=false)
	@ManyToOne(fetch=FetchType.EAGER)
	private Setor idSetor;
	
	@Column(nullable=false)
	private Boolean ativo;
	
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

	public String getDescricaoMaquina() {
		return descricaoMaquina;
	}

	public void setDescricaoMaquina(String descricaoMaquina) {
		this.descricaoMaquina = descricaoMaquina;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Fornecedor getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Fornecedor idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	
	public Setor getIdSetor() {
		return idSetor;
	}

	public void setIdSetor(Setor idSetor) {
		this.idSetor = idSetor;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Status getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Status idStatus) {
		this.idStatus = idStatus;
	}
}
