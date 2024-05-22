package br.com.bradeschat.facebot;

import java.util.ArrayList;
import java.util.List;

import br.com.bradeschat.enums.CallbackEnum;
import br.com.bradesco.facebook.bot.api.exception.FacebookApiException;
import br.com.bradesco.facebook.bot.api.outcome.request.Message;
import br.com.bradesco.facebook.bot.api.outcome.request.Payload;
import br.com.bradesco.facebook.bot.api.outcome.request.QuickReply;
import br.com.bradesco.facebook.bot.api.outcome.request.Recipient;
import br.com.bradesco.facebook.bot.api.outcome.request.attachments.button.PostbackButton;
import br.com.bradesco.facebook.bot.api.outcome.request.attachments.button.URLButton;
import br.com.bradesco.facebook.bot.api.outcome.request.attachments.generic.GenericTemplate;
import br.com.bradesco.facebook.bot.api.outcome.request.attachments.generic.GenericTemplateElement;
import br.com.bradesco.facebook.bot.api.outcome.request.attachments.generic.GenericTemplatePayload;
import br.com.bradesco.facebook.bot.api.outcome.request.enums.ButtonType;
import br.com.bradesco.facebook.bot.api.outcome.request.enums.ContentType;
import br.com.bradesco.facebook.bot.api.outcome.request.interfaces.Buttons;
import br.com.bradesco.facebook.bot.api.outcome.response.actionPojo.SendText;
import br.com.teste.servicos.BotaoBotService;
import br.com.teste.servicos.Constantes;
import br.com.teste.servicos.MensagemBotService;
import br.com.teste.servicos.ResponseBotService;

public class FacebookBotUtil {

	public static void responseToMessage(String id, ResponseBotService response) throws FacebookApiException {
		System.out.println("inicio metodo gerar payload");

		GenericTemplateElement template = new GenericTemplateElement();
		List<QuickReply> quickReplies = new ArrayList<>();
		QuickReply quickReply = new QuickReply();
		int contador = 0;
		boolean hasTemplate = false;

		Recipient r = new Recipient();
		r.setId(id);

		/*
		 * A geração de uma URL para autenticação (utilizada em operações de
		 * Saldo/Extrato) é feita aqui, a partir de um bradeschatAuthToken
		 * recebido
		 */

		if (response.getBradeschatAuthToken() != null) {
			String loginLink = gerarLinkLoginSaldoExtrato(response.getBradeschatAuthToken());

			hasTemplate = true;

			BotaoBotService botao = new BotaoBotService();
			botao.setTexto(Constantes.CAPTION_BOTAO_ENTRAR);
			botao.setUrl(loginLink);
			List<BotaoBotService> botoes = new ArrayList<>();
			botoes.add(botao);

			List<MensagemBotService> mensagens = new ArrayList<>();
			MensagemBotService mandarLogin = new MensagemBotService();
			mandarLogin.setTitulo(Constantes.CAPTION_LOGIN_NECESSARIO);
			mandarLogin.setImagemUrl(Constantes.URL_IMAGEM_LOGIN);
			mandarLogin.setSubtitulo(Constantes.SUBTITULO_LOGIN_NECESSARIO);
			mandarLogin.setBotoes(botoes);

			mensagens.add(mandarLogin);
			response.setMensagemList(mensagens);

		}

		GenericTemplateElement[] elementos = new GenericTemplateElement[response.getMensagemList().size()];

		for (MensagemBotService mensagem : response.getMensagemList()) {
			if (mensagem.getTextoUnico() != null) {

				FacebotFactory.getFacebookBot().sendText(new SendText(id, mensagem.getTextoUnico()));
			} else {
				hasTemplate = true;

				// Primeiro, cria os botões, caso os tenha recebido
				URLButton urlButton = null;
				Buttons[] botoes = new Buttons[mensagem.getBotoes().size()];
				int posBotao = 0;
				for (BotaoBotService botao : mensagem.getBotoes()) {
					System.out.println("Botao a processar: " + botao);

					if (botao.getUrl() != null) {
						urlButton = new URLButton();
						urlButton.setTitle(botao.getTexto());
						urlButton.setType(ButtonType.web_url);
						urlButton.setUrl(botao.getUrl());
						botoes[posBotao] = urlButton;
						posBotao++;
					} else if (botao.getCallback() != null) {
						PostbackButton pbButton = new PostbackButton();
						pbButton.setPayload(botao.getCallback());
						pbButton.setTitle(botao.getTexto());
						pbButton.setType(ButtonType.postback);
						botoes[posBotao] = pbButton;
						posBotao++;
					}
				}

				// criei um elemento de template generico
				template = new GenericTemplateElement();
				template.setButtons(botoes);
				template.setTitle(mensagem.getTitulo());

				if (mensagem.getSubtitulo() != null) {
					template.setSubtitle(mensagem.getSubtitulo());
				}

				if (mensagem.getImagemUrl() != null) {
					template.setImageUrl(mensagem.getImagemUrl());
				}

				if (mensagem.getTemplateItemUrl() != null) {
					template.setItemUrl(mensagem.getTemplateItemUrl());
				}
				System.out.println("Titulo do Template: " + template.getTitle());

				elementos[contador++] = template;

			}

		}

		if (hasTemplate) {
			GenericTemplatePayload gtPayload = new GenericTemplatePayload();
			gtPayload.setElements(elementos);

			GenericTemplate gt = new GenericTemplate();
			gt.setGenericTemplatePayload(gtPayload);
			Message message = new Message();

			message.setAttachmentContent(gt);

			Payload payload = new Payload();
			payload.setRecipient(r);
			payload.setMessage(message);

			FacebotFactory.getFacebookBot().sendPayload(payload);
		}

		// CONTRUINDO MENU DE QUICK REPLIES
		if (response.getTipoRequisicao().equals(CallbackEnum.BUSCAR_REDE_CREDENCIADA.toString())) {

			quickReply = new QuickReply();
			quickReply.setContentType(ContentType.location);
			quickReplies.add(quickReply);
		} else {

			for (CallbackEnum requisicoes : CallbackEnum.values()) {
				if (requisicoes.getTexto().equals(Constantes.COMANDO_BUSCAR_YOUTUBE)) {
					continue;
				}
				if (requisicoes.getTexto().equals(Constantes.COMANDO_SAIR) && !response.isTokenApiAtivo()) {
					continue;
				}
				quickReply = new QuickReply();
				quickReply.setContentType(ContentType.text);
				quickReply.setTitle(requisicoes.getTexto());
				quickReply.setPayload(requisicoes.getTexto());
				quickReplies.add(quickReply);
			}
		}

		if (quickReplies.size() > 0) {
			Message message = new Message();
			if (response.getTipoRequisicao().equals(CallbackEnum.BUSCAR_YOUTUBE.toString()) && !hasTemplate) {
				message.setText(Constantes.MENSAGEM_PERGUNTA_NAO_COMPREENDIDA);
			} else {
				message.setText(Constantes.MENSAGEM_PERGUNTA_AJUDAR_EM_ALGO_MAIS);
			}

			if (response.getQuickReplyText() != null) {
				message.setText(response.getQuickReplyText());
			}
			message.setQuickReply(quickReplies);
			Payload payload = new Payload();
			payload.setRecipient(r);
			payload.setMessage(message);
			FacebotFactory.getFacebookBot().sendPayload(payload);
		}

	}

	private static String gerarLinkLoginSaldoExtrato(String bradeschatAuthToken) {
		return Constantes.URL_SERVIDOR + Constantes.SERVLET_FACEBOOK_ENTRAR + "?"
				+ Constantes.PARAMETRO_BRADESCHAT_AUTH_TOKEN + bradeschatAuthToken;
	}

}
