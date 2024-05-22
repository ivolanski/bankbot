package br.com.bradeschat.TelegramBot;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import br.com.teste.servicos.BotaoBotService;
import br.com.teste.servicos.Constantes;
import br.com.teste.servicos.MensagemBotService;
import br.com.teste.servicos.ResponseBotService;

public class TelegramBotUtil {

	public static void responseToMessage(String id, ResponseBotService response) throws TelegramApiException {
		System.out.println(Constantes.ORIGEM_TELEGRAM + ": inicio metodo gerar payload.");
		SendMessage sendMessage = new SendMessage();
		sendMessage.setChatId(id);

		/*
		 * A geração de uma URL para autenticação (utilizada em operações de
		 * Saldo/Extrato) é feita aqui, a partir de um bradeschatAuthToken
		 * recebido
		 */
		if (response.getBradeschatAuthToken() != null) {
			
			String loginLink = gerarLinkLoginSaldoExtrato(response.getBradeschatAuthToken());
			System.out.println(loginLink);

			InlineKeyboardMarkup inlineMarkup = new InlineKeyboardMarkup();
			List<List<InlineKeyboardButton>> tecladoInline = new ArrayList<>();
			List<InlineKeyboardButton> linhaTecladoInline = new ArrayList<>();
			InlineKeyboardButton teclaInline = new InlineKeyboardButton();

			// Botão Inline: Login
			teclaInline.setUrl(loginLink);
			teclaInline.setText(Constantes.CAPTION_BOTAO_ENTRAR);
			linhaTecladoInline.add(teclaInline);
			tecladoInline.add(linhaTecladoInline);
			inlineMarkup.setKeyboard(tecladoInline);

			sendMessage.setReplyMarkup(inlineMarkup);
			sendMessage.setText(Constantes.CAPTION_LOGIN_NECESSARIO);
			TelegramBotFactory.getTelegramBot().sendMessage(sendMessage);

			// sendMessage.setReplyMarkup(inlineMarkupEntrar);
			// TelegramBotFactory.getTelegramBot().sendMessage(sendMessage);

		}
		
		InlineKeyboardButton botaoInline = new InlineKeyboardButton();
		
		List<InlineKeyboardButton> linha = new ArrayList<>();
		
		List<List<InlineKeyboardButton>> linhas = new ArrayList<>();
		
		InlineKeyboardMarkup tecladoInline = new InlineKeyboardMarkup();
		
		boolean inLine = false;

		if (response.getMensagemList() != null) {
			for (MensagemBotService mensagem : response.getMensagemList()) {
				sendMessage.setReplyMarkup(TelegramTecladoUtil.tecladoMenu(response));
				if (mensagem.getTextoUnico() != null) {

					sendMessage.setText(mensagem.getTextoUnico());
					TelegramBotFactory.getTelegramBot().sendMessage(sendMessage);
					
					if (response.getQuickReplyText() != null) {
						sendMessage.setText(response.getQuickReplyText());
						TelegramBotFactory.getTelegramBot().sendMessage(sendMessage);
					}

				} else {


					for (BotaoBotService botao : mensagem.getBotoes()) {
						
						if(botao.getUrl()!=null){
						sendMessage.setText(botao.getUrl());
						TelegramBotFactory.getTelegramBot().sendMessage(sendMessage);
					}else if(botao.getCallback()!=null){
						linha = new ArrayList<>();
						botaoInline = new InlineKeyboardButton();
						botaoInline.setCallbackData(botao.getCallback());
						botaoInline.setText(botao.getTexto());
						linha.add(botaoInline);
						linhas.add(linha);
						inLine = true;
					}

				}
					
					if(inLine){
						inLine = false;
						tecladoInline.setKeyboard(linhas);	
						
						sendMessage.setText(Constantes.SUBTITULO_PADRAO_RESPOSTA);
						sendMessage.setReplyMarkup(tecladoInline);
						TelegramBotFactory.getTelegramBot().sendMessage(sendMessage);
					}
					

			}

		}
			if(sendMessage.getReplyMarkup()==null){
				sendMessage.setText(Constantes.MENSAGEM_PERGUNTA_NAO_COMPREENDIDA);
				TelegramBotFactory.getTelegramBot().sendMessage(sendMessage);
			}
			
			
	} 

	}

	private static String gerarLinkLoginSaldoExtrato(String bradeschatAuthToken) {
		return Constantes.URL_SERVIDOR + Constantes.SERVLET_TELEGRAM_ENTRAR + "?"
				+ Constantes.PARAMETRO_BRADESCHAT_AUTH_TOKEN + bradeschatAuthToken;
	}

}
