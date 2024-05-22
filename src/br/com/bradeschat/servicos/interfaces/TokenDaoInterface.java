package br.com.bradeschat.servicos.interfaces;

import br.com.bradeschat.model.TokenAutenticacao;

public interface TokenDaoInterface {
	
	public TokenAutenticacao getTokenAutenticacao(String bradeschatAuthToken);
	public void inserir(TokenAutenticacao token);
	public void remover(String bradeschatAuthToken );

}
