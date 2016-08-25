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
public class ProdutoFornecedor {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idFornecedor", referencedColumnName="id", nullable=false)
	private Fornecedor idFornecedor;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idProduto", referencedColumnName="id", nullable=false)
	private Produto idProduto;
	
	@Column(nullable=false)
	private Boolean fornecedorPadrao;
	
	@Column(nullable=false)
	private Boolean ativo;
	
	@Column
	private Integer quantidadeComprada;
	
	@Column(precision=7, scale=4)
	private BigDecimal compraMedia;
	
	@Column(precision=7, scale=4)
	private BigDecimal valorPrimeiraCompra;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date primeiraCompra;
	
	@Column(precision=7, scale=4)
	private BigDecimal valorUltimaCompra;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimaCompra;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="usuarioCriacao", referencedColumnName="id")
	private Usuario usuarioCriacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Fornecedor getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Fornecedor idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public Produto getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Produto idProduto) {
		this.idProduto = idProduto;
	}

	public Boolean getFornecedorPadrao() {
		return fornecedorPadrao;
	}

	public void setFornecedorPadrao(Boolean fornecedorPadrao) {
		this.fornecedorPadrao = fornecedorPadrao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Integer getQuantidadeComprada() {
		return quantidadeComprada;
	}

	public void setQuantidadeComprada(Integer quantidadeComprada) {
		this.quantidadeComprada = quantidadeComprada;
	}

	public BigDecimal getCompraMedia() {
		return compraMedia;
	}

	public void setCompraMedia(BigDecimal compraMedia) {
		this.compraMedia = compraMedia;
	}

	public BigDecimal getValorPrimeiraCompra() {
		return valorPrimeiraCompra;
	}

	public void setValorPrimeiraCompra(BigDecimal valorPrimeiraCompra) {
		this.valorPrimeiraCompra = valorPrimeiraCompra;
	}

	public Date getPrimeiraCompra() {
		return primeiraCompra;
	}

	public void setPrimeiraCompra(Date primeiraCompra) {
		this.primeiraCompra = primeiraCompra;
	}

	public BigDecimal getValorUltimaCompra() {
		return valorUltimaCompra;
	}

	public void setValorUltimaCompra(BigDecimal valorUltimaCompra) {
		this.valorUltimaCompra = valorUltimaCompra;
	}

	public Date getUltimaCompra() {
		return ultimaCompra;
	}

	public void setUltimaCompra(Date ultimaCompra) {
		this.ultimaCompra = ultimaCompra;
	}

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
	
	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
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
		ProdutoFornecedor other = (ProdutoFornecedor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
