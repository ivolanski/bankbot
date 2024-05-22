package br.com.bradeschat.model;

import br.com.bradeschat.enums.Origem;

public class Sessao {
	
	private String id;
	private Origem origem;
	private String accessData;
	private String bradeschatAuthToken;
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
	public String getAccessData() {
		return accessData;
	}
	public void setAccessData(String accessData) {
		this.accessData = accessData;
	}
	public String getBradeschatAuthToken() {
		return bradeschatAuthToken;
	}
	public void setBradeschatAuthToken(String bradeschatAuthToken) {
		this.bradeschatAuthToken = bradeschatAuthToken;
	}
	

}
