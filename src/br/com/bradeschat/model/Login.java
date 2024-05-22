package br.com.bradeschat.model;

public class Login {

	private String agencia;
	private String conta;
	private String titularidade;
	private String token;
	private String senha;
	private String bradeschatAuthToken;
	
	public Login(String agencia,String conta,String titularidade,String token,String senha,String bradeschatAuthToken){
		this.agencia=agencia;
		this.conta=conta;
		this.titularidade=titularidade;
		this.token=token;
		this.senha=senha;
		this.bradeschatAuthToken=bradeschatAuthToken;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getTitularidade() {
		return titularidade;
	}

	public void setTitularidade(String titularidade) {
		this.titularidade = titularidade;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getBradeschatAuthToken() {
		return bradeschatAuthToken;
	}

	public void setBradeschatAuthToken(String bradeschatAuthToken) {
		this.bradeschatAuthToken = bradeschatAuthToken;
	}
	
	
}
