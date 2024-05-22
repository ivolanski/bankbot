package br.com.bradeschat.DAO;

import br.com.bradeschat.model.Login;
import br.com.bradeschat.servicos.interfaces.ConsumoSaldoExtratoApiImplnterface;
import br.com.bradesco.mock.servico.externo.ApiMock;



public class ConsumoSaldoExtratoApiMock implements ConsumoSaldoExtratoApiImplnterface {

	@Override
	public String login(Login login) {
		
		return ApiMock.login(login);
	}

	@Override
	public String getSaldo(String apiToken) {
		return ApiMock.getSaldo(apiToken);
	}

	@Override
	public String getUltimasTransacoes(String apiToken) {
		return ApiMock.getUltimasTranscoes(apiToken);
	}

	@Override
	public Boolean isTokenValid(String apiToken) {

		return ApiMock.isTokenValid(apiToken);
	}


}
