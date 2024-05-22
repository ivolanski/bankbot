package br.com.bradeschat.model;

import java.util.Date;

import br.com.bradeschat.enums.CallbackEnum;
import br.com.bradeschat.enums.Origem;

/**
 * @author Marco A. Scheid
 * 
 * BradeschatAuthToken
 */
public class TokenAutenticacao {
	
	private String bradeschatAuthToken;
	private String id;
	private Origem origem;
	private Date dataCriacao;
	private CallbackEnum tipoRequisicao;
	
	public CallbackEnum getTipoRequisicao() {
		return tipoRequisicao;
	}
	public void setTipoRequisicao(CallbackEnum tipoRequisicao) {
		this.tipoRequisicao = tipoRequisicao;
	}
	public String getBradeschatAuthToken() {
		return bradeschatAuthToken;
	}
	public void setBradeschatAuthToken(String bradeschatAuthToken) {
		this.bradeschatAuthToken = bradeschatAuthToken;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Origem getOrigem() {
		return origem;
	}
	public void setOrigem(Origem origem) {
		this.origem = origem;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

}
