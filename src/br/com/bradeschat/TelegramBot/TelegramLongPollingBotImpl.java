package br.com.bradeschat.TelegramBot;

import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import br.com.bradeschat.enums.Origem;
import br.com.teste.servicos.BotService;
import br.com.teste.servicos.ResponseBotService;

public class TelegramLongPollingBotImpl extends TelegramLongPollingBot 	 {

	@Override
	public String getBotUsername() {
		return "IgorPocBot";
	}

	@Override
	public void onUpdateReceived(Update update) {
		
		if(update.getMessage()!=null && update.getMessage().hasLocation()){
			try {
				processarLocalizacao(update);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
			
		} else if(update.getMessage()!=null && update.getMessage().getText()!=null){
			try {
				processarMensagem(update);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		} 
		if(update.getCallbackQuery()!=null && update.getCallbackQuery().getData()!=null){
			try {
				processarCallback(update);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
			
		}
		
	
	}

	private void processarMensagem(Update update) throws TelegramApiException {
		System.out.println("Mensagem recebida: "+update.getMessage().getText());
		ResponseBotService resposta = BotService.processarPergunta(update.getMessage().getText(), update.getMessage().getFrom().getId().toString(), Origem.TELEGRAM);
		TelegramBotUtil.responseToMessage(update.getMessage().getFrom().getId().toString(),resposta);
		System.out.println(resposta);
		
	}
	
	private void processarCallback(Update update) throws TelegramApiException{
		System.out.println("Callback recebido: "+update.getCallbackQuery().getData());
		ResponseBotService resposta=BotService.processarPergunta(update.getCallbackQuery().getData(), update.getCallbackQuery().getFrom().getId().toString(), Origem.TELEGRAM);
		TelegramBotUtil.responseToMessage(update.getCallbackQuery().getFrom().getId().toString(), resposta);
	}
	
	private void processarLocalizacao(Update update) throws TelegramApiException {
		System.out.println("Localizacao recebido: "+update.getMessage().getText());
		ResponseBotService resposta=BotService.processarLocalizacao(update.getMessage().getLocation().getLatitude().toString(), update.getMessage().getLocation().getLongitude().toString(), update.getMessage().getFrom().getId().toString(), Origem.TELEGRAM);
		TelegramBotUtil.responseToMessage(update.getMessage().getFrom().getId().toString(), resposta);
	}

	@Override
	public String getBotToken() {
		return "313696487:AAG1DU5-kEZSB_2_V4cahcwoZ1pMpLoWTxA";
	}
	
	
	public void processarComandosService(String comando, String id) throws TelegramApiException{
		System.out.println("Comando service recebido: "+comando);
		ResponseBotService resposta = BotService.processarPergunta(comando,id, Origem.TELEGRAM);
		TelegramBotUtil.responseToMessage(id,resposta);
		System.out.println(resposta);
	}

}
