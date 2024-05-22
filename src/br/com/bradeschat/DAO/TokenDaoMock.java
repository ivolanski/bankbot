package br.com.bradeschat.DAO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.com.bradeschat.model.TokenAutenticacao;
import br.com.bradeschat.servicos.interfaces.TokenDaoInterface;
import br.com.teste.servicos.Constantes;

public class TokenDaoMock implements TokenDaoInterface{

	private static Map<String, TokenAutenticacao> tokenMap = new HashMap<>();


	@Override
	public TokenAutenticacao getTokenAutenticacao(String bradeschatAuthToken) {
		if (tokenMap.containsKey(bradeschatAuthToken)) {
			return tokenMap.get(bradeschatAuthToken);
		}
		return null;
	}

	@Override
	public void inserir(TokenAutenticacao token) {
		tokenMap.put(token.getBradeschatAuthToken(), token);

	}
	

	@Override
	public void remover(String bradeschatAuthToken) {
		tokenMap.remove(bradeschatAuthToken);
	}

	public static Boolean isTokenValid(String bradeschatAuthToken) {
		if (tokenMap.containsKey(bradeschatAuthToken)) {
			Date current = new Date();
			long diff = ((current.getTime() - tokenMap.get(bradeschatAuthToken).getDataCriacao().getTime()));
			diff = diff / 1000;
			if (diff > Constantes.PRAZO_BRADESCHAT_AUTH_TOKEN) {
				return false;
			} else {
				return true;
			}
		}

		return false;
	}
	
	

}
