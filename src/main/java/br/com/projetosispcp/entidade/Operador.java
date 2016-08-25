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
public class Operador {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(length = 50, nullable = false)
	private String nome;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date dataNascimento;
	
	@JoinColumn(name="naturalidade", referencedColumnName="id")
	@ManyToOne(fetch=FetchType.EAGER)
	private Cidade naturalidade;
	
	@JoinColumn(name="nacionalidade", referencedColumnName="id")
	@ManyToOne(fetch=FetchType.EAGER)
	private Pais nacionalidade;
	
	@JoinColumn(name="idEstadoCivil", referencedColumnName="id")
	@ManyToOne(fetch=FetchType.EAGER)
	private EstadoCivil idEstadoCivil;
	
	@JoinColumn(name="idCorRaca", referencedColumnName="id")
	@ManyToOne(fetch=FetchType.EAGER)
	private CorRaca idCorRaca;
	
	@Column(length=50)
	private String nomePai;
	
	@Column(length=50)
	private String nomeMae;
	
	@Column(length=20)
	private String numeroRg;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEmissaoRg;
	
	@Column(length=20)
	private String orgaoEmissorRG;
	
	@Column(length=14, nullable=false)
	private String cpf;
	
	@Column(length=20)
	private String numeroCartTrabalho;
	
	@Column(length=10)
	private String serieCartTrabalho;
	
	@JoinColumn(name="ufCartTrabalho")
	@ManyToOne(fetch=FetchType.EAGER)
	private Estado ufCartTrabalho;
		
	@Column(length=10, nullable=false)
	private String cep;
	
	@Column(length=50, nullable=false)
	private String rua;
	
	@Column(length=10)
	private String numero;
	
	@Column(length=50)
	private String complemento;
	
	@Column(length=50)
	private String bairro;
	
	@JoinColumn(name="idCidade", referencedColumnName="id")
	@ManyToOne(fetch=FetchType.EAGER)
	private Cidade idCidade;
	
	@Column(length=5, nullable=false)
	private String chapa;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAdmissao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDemissao;
	
	@JoinColumn(name="idFuncao", referencedColumnName="id")
	@ManyToOne(fetch=FetchType.EAGER)
	private Funcao idFuncao;
	
	@Column(precision=7, scale=4)
	private Double salario;
	
	@JoinColumn(name="idStatus", referencedColumnName="id")
	@ManyToOne(fetch=FetchType.EAGER)
	private Status idStatus;
	
	@Column(length=100)
	private String observacao;	
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Cidade getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(Cidade naturalidade) {
		this.naturalidade = naturalidade;
	}

	public Pais getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(Pais nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public EstadoCivil getIdEstadoCivil() {
		return idEstadoCivil;
	}

	public void setIdEstadoCivil(EstadoCivil idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
	}

	public CorRaca getIdCorRaca() {
		return idCorRaca;
	}

	public void setIdCorRaca(CorRaca idCorRaca) {
		this.idCorRaca = idCorRaca;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getNumeroRg() {
		return numeroRg;
	}

	public void setNumeroRg(String numeroRg) {
		this.numeroRg = numeroRg;
	}

	public Date getDataEmissaoRg() {
		return dataEmissaoRg;
	}

	public void setDataEmissaoRg(Date dataEmissaoRg) {
		this.dataEmissaoRg = dataEmissaoRg;
	}

	public String getOrgaoEmissorRG() {
		return orgaoEmissorRG;
	}

	public void setOrgaoEmissorRG(String orgaoEmissorRG) {
		this.orgaoEmissorRG = orgaoEmissorRG;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNumeroCartTrabalho() {
		return numeroCartTrabalho;
	}

	public void setNumeroCartTrabalho(String numeroCartTrabalho) {
		this.numeroCartTrabalho = numeroCartTrabalho;
	}

	public String getSerieCartTrabalho() {
		return serieCartTrabalho;
	}

	public void setSerieCartTrabalho(String serieCartTrabalho) {
		this.serieCartTrabalho = serieCartTrabalho;
	}

	public Estado getUfCartTrabalho() {
		return ufCartTrabalho;
	}

	public void setUfCartTrabalho(Estado ufCartTrabalho) {
		this.ufCartTrabalho = ufCartTrabalho;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Cidade getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Cidade idCidade) {
		this.idCidade = idCidade;
	}

	public String getChapa() {
		return chapa;
	}

	public void setChapa(String chapa) {
		this.chapa = chapa;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public Date getDataDemissao() {
		return dataDemissao;
	}

	public void setDataDemissao(Date dataDemissao) {
		this.dataDemissao = dataDemissao;
	}

	public Funcao getIdFuncao() {
		return idFuncao;
	}

	public void setIdFuncao(Funcao idFuncao) {
		this.idFuncao = idFuncao;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Status getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Status idStatus) {
		this.idStatus = idStatus;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
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
		Operador other = (Operador) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
