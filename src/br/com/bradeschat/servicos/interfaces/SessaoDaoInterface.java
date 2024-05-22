package br.com.bradeschat.servicos.interfaces;

import br.com.bradeschat.enums.Origem;
import br.com.bradeschat.model.Sessao;

public interface SessaoDaoInterface {
	
	public Sessao getSessao(String id, Origem origem);
	public void criaSessao(Sessao sessao);
	public void sair(String id, Origem origem);

}
