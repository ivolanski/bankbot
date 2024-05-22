package br.com.bradeschat.TelegramBot;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import br.com.bradeschat.enums.CallbackEnum;
import br.com.teste.servicos.Constantes;
import br.com.teste.servicos.ResponseBotService;

public class TelegramTecladoUtil {
	
	public static ReplyKeyboardMarkup tecladoMenu(ResponseBotService response){
		
		ReplyKeyboardMarkup teclado = new ReplyKeyboardMarkup();
		teclado.setResizeKeyboard(true);
		
		List<KeyboardRow> linhasTeclado = new ArrayList<>();
		
		
		KeyboardRow linhaTeclado = new KeyboardRow();	
		KeyboardButton tecla= new KeyboardButton();
		int contadorTeclasPorLinha = 0;
		
		/*if(response.getTipoRequisicao().equals(CallbackEnum.BUSCAR_REDE_CREDENCIADA.toString())){
			tecla.setRequestLocation(true);
			tecla.setText(Constantes.MENSAGEM_COMPARTILHE_LOCALIZACAO);
			++contadorTeclasPorLinha;
			linhaTeclado.add(tecla);
		}*/
		for(CallbackEnum requisicoes : CallbackEnum.values()){

			if(requisicoes.getTexto().equals(Constantes.COMANDO_BUSCAR_YOUTUBE)
					|| requisicoes.getTexto().equals(Constantes.COMANDO_MENU)){
				continue;
			}
			
			if(requisicoes.getTexto().equals(Constantes.COMANDO_SAIR)
					&& !response.isTokenApiAtivo()){
				continue;
			}
			tecla = new KeyboardButton();
			tecla.setText(requisicoes.getTexto());
			if(requisicoes.getTexto().equals(Constantes.MENSAGEM_BRADESCO_MAIS_PROXIMO)){
				tecla.setRequestLocation(true);
			}
			
			++contadorTeclasPorLinha;
			
			if(tecla.getText().length()>14){
				linhasTeclado.add(linhaTeclado);
				linhaTeclado=new KeyboardRow();
				contadorTeclasPorLinha=2;
			} else if(contadorTeclasPorLinha>2){
			linhasTeclado.add(linhaTeclado);
				linhaTeclado=new KeyboardRow();
					contadorTeclasPorLinha=1;			
			}
			linhaTeclado.add(tecla);

		}
		
		linhasTeclado.add(linhaTeclado);
		
		return teclado.setKeyboard(linhasTeclado);
	}

}
