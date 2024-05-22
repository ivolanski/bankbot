package br.com.teste.servicos;

import java.util.ArrayList;
import java.util.List;

public class GeradorBotoesBotService {
	
	public static BotaoBotService gerarBotaoCallback(String texto){
		BotaoBotService botao = new BotaoBotService();
		botao.setCallback(texto);
		botao.setTexto(texto);
		return botao;
	}
	
	public static List<BotaoBotService> gerarBotaoCallbackList(String texto){
		BotaoBotService botao = new BotaoBotService();
		botao.setCallback(texto);
		botao.setTexto(texto);
		List<BotaoBotService> botaoList = new ArrayList<>();
		botaoList.add(botao);
		return botaoList;
	}
	
	public static List<BotaoBotService> gerarBotaoUrlList(String texto, String url){
		BotaoBotService botao = new BotaoBotService();
		botao.setUrl(url);
		botao.setTexto(texto);
		List<BotaoBotService> botaoList = new ArrayList<>();
		botaoList.add(botao);
		return botaoList;
	}

}
