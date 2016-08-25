package br.com.projetosispcp.entidade;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Trilho {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(length=10, nullable=false)
	private String posicaoTrilho;
	
	@Column(precision=7, scale=4, nullable=false)
	private BigDecimal largura;
	
	@Column(precision=7, scale=4, nullable=false)
	private BigDecimal comprimento;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPosicaoTrilho() {
		return posicaoTrilho;
	}

	public void setPosicaoTrilho(String posicaoTrilho) {
		this.posicaoTrilho = posicaoTrilho;
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

	
	
}
