package br.com.bradesco.mock.servico.externo;

import java.util.Date;

public class UsuarioApiMock {
	
	private String agencia;
	private String conta;
	private String titularidade;
	private String senha;
	private String saldo;
	private String extrato;
	private Date tokenTimestamp;
	
	
	public String getSaldo() {
		return saldo;
	}
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
	public String getExtrato() {
		return extrato;
	}
	public void setExtrato(String extrato) {
		this.extrato = extrato;
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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Date getTokenTimestamp() {
		return tokenTimestamp;
	}
	public void setTokenTimestamp(Date token_timestamp) {
		this.tokenTimestamp = token_timestamp;
	}
	

}
