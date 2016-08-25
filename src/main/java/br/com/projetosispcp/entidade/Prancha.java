package br.com.projetosispcp.entidade;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Prancha {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(length=50, nullable=false)
	private String descricao;
	
	@Column(length=20, nullable=false)
	private String codigoPrancha;
	
	@Column(precision=7, scale=4, nullable=false)
	private BigDecimal largura;
	
	@Column(precision=7, scale=4, nullable=false)
	private BigDecimal comprimento;
	
	@Column(precision=7, scale=4, nullable=false)
	private BigDecimal espessura;
	
	@Column(length=100)
	private String observacoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigoPrancha() {
		return codigoPrancha;
	}

	public void setCodigoPrancha(String codigoPrancha) {
		this.codigoPrancha = codigoPrancha;
	}

	public BigDecimal getLargura() {
		return largura;
	}

	public void setLargura(BigDecimal largura) {
		this.largura = largura;
	}

	public BigDecimal getComprimento() {
		return comprimento;
	}

	public void setComprimento(BigDecimal comprimento) {
		this.comprimento = comprimento;
	}

	public BigDecimal getEspessura() {
		return espessura;
	}

	public void setEspessura(BigDecimal espessura) {
		this.espessura = espessura;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	
}
