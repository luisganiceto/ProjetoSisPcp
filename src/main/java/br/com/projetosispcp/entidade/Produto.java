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
public class Produto {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(length=20, nullable=false)
	private String codigoProduto;
	
	@Column(length=20)
	private String codigoEan;
	
	@Column(length=100, nullable=false)
	private String descricaoProduto;
	
	@Column(name="quantidadeVolume")
	private Integer quantidadeVolume;
	
	@Column(nullable=false)
	private Boolean ativo;
	
	@Column(precision=7, scale=4)
	private BigDecimal pesoBruto;
	
	@Column(precision=7, scale=4)
	private BigDecimal pesoLiquido;
	
	@Column(precision=7, scale=4)
	private BigDecimal comprimento;
	
	@Column(precision=7, scale=4)
	private BigDecimal altura;
	
	@Column(precision=7, scale=4)
	private BigDecimal largura;
	
	@Column(precision=7, scale=4)
	private BigDecimal espessura;
	
	@Column(precision=7, scale=4)
	private BigDecimal valorBruto;
	
	@Column(precision=7, scale=4)
	private BigDecimal valorLiquido;
	
	@Column(precision=7, scale=4)
	private BigDecimal valorCusto;
	
	@Column(nullable=false)
	private Integer quantidadeEstoque;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="unidadeCompra", referencedColumnName="id")
	private Unidade unidadeCompra;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="unidadeControle", referencedColumnName="id")
	private Unidade unidadeControle;
	
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

	public String getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public BigDecimal getPesoBruto() {
		return pesoBruto;
	}

	public void setPesoBruto(BigDecimal pesoBruto) {
		this.pesoBruto = pesoBruto;
	}

	public BigDecimal getPesoLiquido() {
		return pesoLiquido;
	}

	public void setPesoLiquido(BigDecimal pesoLiquido) {
		this.pesoLiquido = pesoLiquido;
	}

	public BigDecimal getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(BigDecimal valorBruto) {
		this.valorBruto = valorBruto;
	}

	public BigDecimal getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(BigDecimal valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public String getCodigoEan() {
		return codigoEan;
	}

	public void setCodigoEan(String codigoEan) {
		this.codigoEan = codigoEan;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public BigDecimal getComprimento() {
		return comprimento;
	}

	public void setComprimento(BigDecimal comprimento) {
		this.comprimento = comprimento;
	}

	public BigDecimal getAltura() {
		return altura;
	}

	public void setAltura(BigDecimal altura) {
		this.altura = altura;
	}

	public BigDecimal getLargura() {
		return largura;
	}

	public void setLargura(BigDecimal largura) {
		this.largura = largura;
	}

	public BigDecimal getEspessura() {
		return espessura;
	}

	public void setEspessura(BigDecimal espessura) {
		this.espessura = espessura;
	}

	public BigDecimal getValorCusto() {
		return valorCusto;
	}

	public void setValorCusto(BigDecimal valorCusto) {
		this.valorCusto = valorCusto;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	public Integer getQuantidadeVolume() {
		return quantidadeVolume;
	}

	public void setQuantidadeVolume(Integer quantidadeVolume) {
		this.quantidadeVolume = quantidadeVolume;
	}

	public Unidade getUnidadeCompra() {
		return unidadeCompra;
	}

	public void setUnidadeCompra(Unidade unidadeCompra) {
		this.unidadeCompra = unidadeCompra;
	}

	public Unidade getUnidadeControle() {
		return unidadeControle;
	}

	public void setUnidadeControle(Unidade unidadeControle) {
		this.unidadeControle = unidadeControle;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

	
	
}
