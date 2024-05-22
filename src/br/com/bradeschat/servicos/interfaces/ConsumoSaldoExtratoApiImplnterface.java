package br.com.bradeschat.servicos.interfaces;

import br.com.bradeschat.model.Login;

public interface ConsumoSaldoExtratoApiImplnterface {
	
	public String login(Login login);
	public String getSaldo(String apiToken);
	public String getUltimasTransacoes(String apiToken);
	public Boolean isTokenValid(String apiToken);

}
