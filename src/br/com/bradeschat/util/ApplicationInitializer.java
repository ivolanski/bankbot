package br.com.bradeschat.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import br.com.bradeschat.TelegramBot.TelegramLongPollingBotImpl;

@WebListener	
public class ApplicationInitializer implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Servlet foi interrompido.");
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Servlet foi inicializado.");
		
		/*Inicializador do Telegram.COMENTAR ESSE TRECHO PARA DESABILITAR O TELEGRAM*/
		ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new TelegramLongPollingBotImpl());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        
        //FIM do Inicializador do Telegram
	}

}
