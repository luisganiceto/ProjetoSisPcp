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
public class Unidade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 5, nullable = false, unique = true)
	private String codUnidade;

	@Column(length = 50, nullable = false)
	private String descricaoUnidade;
	
	@Column(nullable=false)
	private Boolean eFatorConversao;
	
	@Column(length = 5)
	private String medidaFatorConversao;
	
	@Column(precision=7, scale=4)
	private BigDecimal fatorConversao;

	@JoinColumn(name = "usuarioCriacao", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario usuarioCriacao;

	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataCriacao;

	@JoinColumn(name = "usuarioAlteracao", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario usuarioAlteracao;

	@Temporal(value = TemporalType.TIMESTAMP)
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

	public String getCodUnidade() {
		return codUnidade;
	}

	public void setCodUnidade(String codUnidade) {
		this.codUnidade = codUnidade;
	}

	public String getDescricaoUnidade() {
		return descricaoUnidade;
	}

	public void setDescricaoUnidade(String descricaoUnidade) {
		this.descricaoUnidade = descricaoUnidade;
	}

	public Boolean geteFatorConversao() {
		return eFatorConversao;
	}

	public void seteFatorConversao(Boolean eFatorConversao) {
		this.eFatorConversao = eFatorConversao;
	}

	public String getMedidaFatorConversao() {
		return medidaFatorConversao;
	}

	public void setMedidaFatorConversao(String medidaFatorConversao) {
		this.medidaFatorConversao = medidaFatorConversao;
	}

	public BigDecimal getFatorConversao() {
		return fatorConversao;
	}

	public void setFatorConversao(BigDecimal fatorConversao) {
		this.fatorConversao = fatorConversao;
	}
	
	@Override
	public String toString() {
		return String.format("%s [id=%d]", getClass().getSimpleName(), getId());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Unidade other = (Unidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
