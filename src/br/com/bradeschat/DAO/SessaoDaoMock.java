package br.com.bradeschat.DAO;

import java.util.HashMap;
import java.util.Map;

import br.com.bradeschat.enums.Origem;
import br.com.bradeschat.model.Sessao;
import br.com.bradeschat.servicos.interfaces.SessaoDaoInterface;

public class SessaoDaoMock implements SessaoDaoInterface {
	
	private static Map<String, Sessao> sessaoMap = new HashMap<>();


	@Override
	public Sessao getSessao(String id, Origem origem) {
		if(sessaoMap.containsKey(id+origem)){
			return sessaoMap.get(id+origem);
		}
		return null;
	}


	@Override
	public void criaSessao(Sessao sessao) {
		sessaoMap.put(sessao.getId()+sessao.getOrigem(), sessao);
		
	}


	@Override
	public void sair(String id, Origem origem) {
		sessaoMap.remove(id+origem);
		
	}

}
