package br.com.bradesco.mock.servico.externo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import br.com.bradeschat.model.Login;
import br.com.teste.servicos.Constantes;

public class ApiMock {

	private static Map<String, UsuarioApiMock> usuariosMap = new HashMap<>();

	public static String login(Login login) {
		for (UsuarioApiMock u : GeradorMock.usuarios) {
			if ((u.getAgencia().equals(login.getAgencia())) && (u.getSenha().equals(login.getSenha()))
					&& (u.getConta().equals(login.getConta()))) {

				String token = UUID.randomUUID().toString();
				u.setTokenTimestamp(new Date());
				usuariosMap.put(token, u);
				return token;
			}
		}
		return null;
	}
	
	
	public static String getSaldo(String token){
		if(usuariosMap.containsKey(token)){
			return usuariosMap.get(token).getSaldo();
		}
		return null;
	}
	
	
	public static String getUltimasTranscoes(String token){
		if(usuariosMap.containsKey(token)){
			return usuariosMap.get(token).getExtrato();
		}
		return null;
	}
	
	public static Boolean isTokenValid(String token) {
		if(usuariosMap.containsKey(token)){
			Date current=new Date();
			long diff= ((current.getTime()-usuariosMap.get(token).getTokenTimestamp().getTime()));
			diff=diff/1000;
			if(diff>Constantes.PRAZO_TOKEN_API_SALDO_EXTRATO){
				return false;
			}else{
				return true;
			}
		}
		
		return false;
		
	}
	
	
	

}
