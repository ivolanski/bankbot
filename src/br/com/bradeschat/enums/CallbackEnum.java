package br.com.bradeschat.enums;

import br.com.teste.servicos.Constantes;

/**
 * @author Marco A. Scheid
 * 
 * Classe para listar as funcionalidades disponibilizadas pelo Bradeschat.
 *
 */
public enum CallbackEnum {
	
	MENU(Constantes.COMANDO_MENU),
	SALDO(Constantes.COMANDO_SALDO), 
	ULTIMAS_TRANSACOES(Constantes.COMANDO_ULTIMAS_TRANSACOES),
	BUSCAR_REDE_CREDENCIADA(Constantes.MENSAGEM_BRADESCO_MAIS_PROXIMO),
	AJUDA(Constantes.COMANDO_AJUDA),
	SAIR(Constantes.COMANDO_SAIR),
	BUSCAR_YOUTUBE(Constantes.COMANDO_BUSCAR_YOUTUBE);
	
	private String texto;
	
	CallbackEnum(String texto){
		this.texto = texto;
	}
	
	public String getTexto(){
		return texto;
	}

	
	
	
}
