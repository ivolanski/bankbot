package br.com.bradeschat.facebot;

import br.com.bradeschat.enums.Origem;
import br.com.bradesco.facebook.bot.api.exception.FacebookApiException;
import br.com.bradesco.facebook.bot.api.income.setup.echo.message.received.attachments.WebhookMessageAttachment;
import br.com.bradesco.facebook.bot.api.income.webhook.WebhookMessaging;
import br.com.bradesco.facebook.bot.api.outcome.response.actionPojo.SendText;
import br.com.bradesco.facebook.bot.api.principal.FacebookBot;
import br.com.teste.servicos.BotService;
import br.com.teste.servicos.Constantes;
import br.com.teste.servicos.ResponseBotService;

public class FacebookBotImpl extends FacebookBot {

	@Override
	public void attachmentReceived(WebhookMessaging webhookMessaging) {
		boolean isCoordinate=false;
		try {
			for (WebhookMessageAttachment attachment : webhookMessaging.getWebhookMessage().getAttachment()) {
				
				if (attachment.getPayload().getCoordinates() != null) {
					isCoordinate=true;
					ResponseBotService resposta = BotService.processarLocalizacao(attachment.getPayload().getCoordinates().getLat(), attachment.getPayload().getCoordinates().getCoordinateLong(), webhookMessaging.getWebhookMessagingSender().getId(), Origem.FACEBOOK);
					FacebookBotUtil.responseToMessage(webhookMessaging.getWebhookMessagingSender().getId(), resposta);
					break;
				}
			}
			if(!isCoordinate){
			sendText(new SendText(webhookMessaging.getWebhookMessagingSender().getId(),
					Constantes.MENSAGEM_ANEXO_RECEBIDO));
			}
		} catch (FacebookApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void messagePostback(WebhookMessaging webhookMessaging) {
		System.out.println("message postback recebido: "+webhookMessaging.getWebhookPostback().getPayload());
		try {
			ResponseBotService resposta = BotService.processarPergunta(webhookMessaging.getWebhookPostback().getPayload(), webhookMessaging.getWebhookMessagingSender().getId(), Origem.FACEBOOK);
			FacebookBotUtil.responseToMessage(webhookMessaging.getWebhookMessagingSender().getId(), resposta);
			
		} catch (FacebookApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void echoMessageReceived(WebhookMessaging webhookMessaging) {
		System.out.println("Echo recebido");
		
	}

	@Override
	protected String getBotToken() {
		return "";

	
	}

	@Override
	public void messageAccountLinking(WebhookMessaging webhookMessaging) {
		System.out.println("Account linking recebido");
		
	}

	@Override
	public void messageCheckoutUpdate(WebhookMessaging webhookMessaging) {
		System.out.println("Checkout update recebido");
		
	}

	@Override
	public void messageDelivered(WebhookMessaging webhookMessaging) {
		System.out.println("Message delivered recebido");
		
	}

	@Override
	public void messageOptin(WebhookMessaging webhookMessaging) {
		System.out.println("Message optin recebido");
		
	}

	@Override
	public void messagePayment(WebhookMessaging webhookMessaging) {
		System.out.println("Message Payment received");
		
	}



	@Override
	public void messageRead(WebhookMessaging webhookMessaging) {
		System.out.println("A mensagem foi lida");
		
	}

	@Override
	public void messageRefferal(WebhookMessaging webhookMessaging) {
		System.out.println("refferal aconteceu: ");	
	}

	@Override
	public void quickReplyCallbackReceived(WebhookMessaging webhookMessaging) {
		System.out.println("Quick reply callback recebido: "+webhookMessaging.getWebhookMessage().getQuickReply().getPayload());
		
		try {
			ResponseBotService resposta = BotService.processarPergunta(webhookMessaging.getWebhookMessage().getQuickReply().getPayload(), webhookMessaging.getWebhookMessagingSender().getId(), Origem.FACEBOOK);
			FacebookBotUtil.responseToMessage(webhookMessaging.getWebhookMessagingSender().getId(), resposta);
			
		} catch (FacebookApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void textReceived(WebhookMessaging webhookMessaging) {
		System.out.println("Mensagem recebida: "+webhookMessaging.getWebhookMessage().getText());
		try {
			
			
			ResponseBotService resposta = BotService.processarPergunta(webhookMessaging.getWebhookMessage().getText(), webhookMessaging.getWebhookMessagingSender().getId(), Origem.FACEBOOK);
			FacebookBotUtil.responseToMessage(webhookMessaging.getWebhookMessagingSender().getId(), resposta);
			
			
			
		} catch (FacebookApiException e) {
			System.out.println(e.getMessage());
		}
	}
	
	

}
