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
public class Usuario {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(length=20, unique=true, nullable=false)
	private String codUsuario;
	
	@Column(length=32 , nullable=false)	
	private String senha;
	
	@Column(nullable = false)
	private Character tipo;
	
	@JoinColumn(name="idOperador", referencedColumnName="id")
	@ManyToOne(fetch=FetchType.EAGER)
	private Operador idOperador;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date inicioDeValidacao;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date finalDeValidacao;

	@Column(nullable = false)
	private Boolean ativo;
	
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
	
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date ultimoAcesso;
	
	
	public Date getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(Date ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(String usuario) {
		this.codUsuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Operador getIdOperador() {
		return idOperador;
	}

	public void setIdOperador(Operador idOperador) {
		this.idOperador = idOperador;
	}

	public Date getInicioDeValidacao() {
		return inicioDeValidacao;
	}

	public void setInicioDeValidacao(Date inicioDeValidacao) {
		this.inicioDeValidacao = inicioDeValidacao;
	}

	public Date getFinalDeValidacao() {
		return finalDeValidacao;
	}

	public void setFinalDeValidacao(Date finalDeValidacao) {
		this.finalDeValidacao = finalDeValidacao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Character getTipo() {
		return tipo;
	}

	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}
	
	

}
