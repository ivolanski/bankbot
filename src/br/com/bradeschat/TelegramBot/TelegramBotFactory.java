package br.com.bradeschat.TelegramBot;

public class TelegramBotFactory {
	
private static TelegramLongPollingBotImpl telegramBotImpl;
	
	static{
		telegramBotImpl = new TelegramLongPollingBotImpl();	
	}
	
	public static TelegramLongPollingBotImpl getTelegramBot(){
		return telegramBotImpl;
	}

}
