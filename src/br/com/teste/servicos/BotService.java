package br.com.teste.servicos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.google.api.services.youtube.model.SearchResult;

import br.com.bradeschat.DAO.ConsumoSaldoExtratoApiMock;
import br.com.bradeschat.DAO.SessaoDaoMock;
import br.com.bradeschat.DAO.TokenDaoMock;
import br.com.bradeschat.enums.CallbackEnum;
import br.com.bradeschat.enums.Origem;
import br.com.bradeschat.model.Login;
import br.com.bradeschat.model.Sessao;
import br.com.bradeschat.model.TokenAutenticacao;
import br.com.bradeschat.servicos.interfaces.ConsumoSaldoExtratoApiImplnterface;
import br.com.bradeschat.servicos.interfaces.SessaoDaoInterface;
import br.com.bradeschat.servicos.interfaces.TokenDaoInterface;
import br.com.bradeschat.servicos.youtube.YoutubeApiImpl;

public class BotService {
	
	
	public static ResponseBotService processarPergunta(String pergunta, String id, Origem origem) {

		if (pergunta.toLowerCase().contains(Constantes.COMANDO_SALDO.toLowerCase())) {
			System.out.println("Processamento de saldo");
			try {
				return motorSaldoExtrato(id, origem, CallbackEnum.SALDO);
			} catch (BotServicesException e) {
				System.out.println(e.getMessage());
			}
		} else if (pergunta.toLowerCase().contains(Constantes.COMANDO_ULTIMAS_TRANSACOES.toLowerCase())
				|| pergunta.toLowerCase().contains(Constantes.EXTRATO.toLowerCase())) {
			try {
				System.out.println("Processamento de extrato");
				return motorSaldoExtrato(id, origem, CallbackEnum.ULTIMAS_TRANSACOES);
			} catch (BotServicesException e) {
				System.out.println(e.getMessage());
			}
		} else if (pergunta.toLowerCase().equals(Constantes.MENSAGEM_BRADESCO_MAIS_PROXIMO.toLowerCase())) {
			System.out.println(" processando Bradesco mais proximo");
			return buscarBradescoMaisProximo(id, origem,CallbackEnum.BUSCAR_REDE_CREDENCIADA);
		} else if (pergunta.toLowerCase().contains(Constantes.COMANDO_SAIR.toLowerCase())) {
			return sair(id, origem,CallbackEnum.SAIR);
		} else if (pergunta.toLowerCase().contains(Constantes.COMANDO_MENU.toLowerCase())
				|| pergunta.toLowerCase().contains("ola") || pergunta.toLowerCase().contains("oi")
				|| pergunta.toLowerCase().contains("hello")
				|| pergunta.toLowerCase().contains("ok")) {
			return mostrarMenu(id,origem,CallbackEnum.MENU);
		} else if(pergunta.toLowerCase().contains(Constantes.COMANDO_AJUDA.toLowerCase())
				|| pergunta.toLowerCase().contains(Constantes.COMANDO_START.toLowerCase())
				|| pergunta.toLowerCase().contains(Constantes.COMANDO_COMECAR.toLowerCase())){
			return mostrarAjuda(id, origem, CallbackEnum.AJUDA);
		} else{
			return buscarPerguntas(id, origem,pergunta,CallbackEnum.BUSCAR_YOUTUBE);
		}
		return null;
	}

	public static ResponseBotService processarLocalizacao(String latitude, String longitude,String id, Origem origem){
		SessaoDaoInterface sessaoDao = new SessaoDaoMock();
		ConsumoSaldoExtratoApiImplnterface seApi = new ConsumoSaldoExtratoApiMock();
		Sessao sessao = sessaoDao.getSessao(id, origem);
		
		List<MensagemBotService> mensagens = new ArrayList<>();
		MensagemBotService resp = new MensagemBotService();
		String linkMaps=Constantes.URL_MAPS + latitude + "," +longitude;
		System.out.println(linkMaps);
		resp.setTextoUnico(linkMaps);
		
		mensagens.add(resp);
		ResponseBotService response = new ResponseBotService();
		response.setTipoRequisicao(CallbackEnum.MENU.toString());
		response.setMensagemList(mensagens);
		if(sessao != null && seApi.isTokenValid(sessao.getAccessData())){
			response.setTokenApiAtivo(true);
		}
		return response;
	}
	
	private static ResponseBotService mostrarAjuda(String id, Origem origem, CallbackEnum callbackEnum){
		ResponseBotService response = new ResponseBotService();
		MensagemBotService mensagem = new MensagemBotService();
		List<MensagemBotService> mensagens = new ArrayList<>();
		mensagem.setTextoUnico(Constantes.MENSAGEM_OLA);		
		mensagens.add(mensagem);
		response.setMensagemList(mensagens);
		response.setQuickReplyText(Constantes.AJUDA_SOBRE_BRADESCHAT);
		response.setTipoRequisicao(callbackEnum.toString());
		return response;

	}
	
	private static ResponseBotService mostrarMenu(String id, Origem origem,CallbackEnum callbackEnum) {
		SessaoDaoInterface sessaoDao = new SessaoDaoMock();
		ConsumoSaldoExtratoApiImplnterface seApi = new ConsumoSaldoExtratoApiMock();
		Sessao sessao = sessaoDao.getSessao(id, origem);
				
		ResponseBotService response = new ResponseBotService();
		MensagemBotService mensagem = new MensagemBotService();
		List<MensagemBotService> mensagens = new ArrayList<>();
		mensagem.setTextoUnico(Constantes.MENSAGEM_OLA);		
		mensagens.add(mensagem);
		response.setMensagemList(mensagens);
		response.setQuickReplyText(Constantes.MENSAGEM_GREETINGS_MENU);
		response.setTipoRequisicao(callbackEnum.toString());
		if(sessao != null && seApi.isTokenValid(sessao.getAccessData())){
			response.setTokenApiAtivo(true);
		}
		return response;
	}

	private static ResponseBotService sair(String id, Origem origem,CallbackEnum callbackEnum) {
		SessaoDaoInterface sessaoDao = new SessaoDaoMock();
		sessaoDao.sair(id, origem);
		List<MensagemBotService> mensagens = new ArrayList<>();
		MensagemBotService resp = new MensagemBotService();
		resp.setTextoUnico(Constantes.MENSAGEM_SAIR);
		mensagens.add(resp);
		ResponseBotService response = new ResponseBotService();
		response.setTipoRequisicao(callbackEnum.toString());
		response.setMensagemList(mensagens);
		response.setTokenApiAtivo(false);
		return response;
	}

	private static ResponseBotService buscarPerguntas(String id, Origem origem,String pergunta, CallbackEnum callbackEnum) {

		SessaoDaoInterface sessaoDao = new SessaoDaoMock();
		ConsumoSaldoExtratoApiImplnterface seApi = new ConsumoSaldoExtratoApiMock();
		Sessao sessao = sessaoDao.getSessao(id, origem);
		
		YoutubeApiImpl youtube = null;

		try {
			youtube = new YoutubeApiImpl();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			List<SearchResult> youtubeResponse = youtube.searchVideos(pergunta, 5);
			List<MensagemBotService> mensagens = new ArrayList<>();

			for (SearchResult searchResult : youtubeResponse) {
				MensagemBotService resp = new MensagemBotService();
				resp.setImagemUrl(searchResult.getSnippet().getThumbnails().getDefault().getUrl());
				resp.setTitulo(searchResult.getSnippet().getTitle());
				resp.setSubtitulo(searchResult.getSnippet().getDescription());
				String videoUrl = "https://www.youtube.com/watch?v=" + searchResult.getId().getVideoId();
				resp.setBotoes(GeradorBotoesBotService.gerarBotaoUrlList("Ver video", videoUrl));
				resp.setTemplateItemUrl(videoUrl);
				mensagens.add(resp);

			}
			

			ResponseBotService response = new ResponseBotService();
			response.setMensagemList(mensagens);
			response.setTipoRequisicao(callbackEnum.toString());
			if(sessao != null && seApi.isTokenValid(sessao.getAccessData())){
				response.setTokenApiAtivo(true);
			}
			return response;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}
	
	private static ResponseBotService buscarBradescoMaisProximo(String id, Origem origem, CallbackEnum callbackEnum) {
		SessaoDaoInterface sessaoDao = new SessaoDaoMock();
		ConsumoSaldoExtratoApiImplnterface seApi = new ConsumoSaldoExtratoApiMock();
		Sessao sessao = sessaoDao.getSessao(id, origem);
		
		List<MensagemBotService> mensagens = new ArrayList<>();
		MensagemBotService resp = new MensagemBotService();
		resp.setTextoUnico(Constantes.MENSAGEM_COMPARTILHE_LOCALIZACAO_VERBOSO);
		mensagens.add(resp);
		ResponseBotService response = new ResponseBotService();
		response.setQuickReplyText(Constantes.MENSAGEM_UTILIZE_SEND_LOCATION);
		response.setTipoRequisicao(callbackEnum.toString());
		response.setMensagemList(mensagens);
		if(sessao != null && seApi.isTokenValid(sessao.getAccessData())){
			response.setTokenApiAtivo(true);
		}
		return response;
	}

	private static ResponseBotService motorSaldoExtrato(String id, Origem origem, CallbackEnum callbackEnum)
			throws BotServicesException {

		SessaoDaoInterface sessaoDao = new SessaoDaoMock();
		ConsumoSaldoExtratoApiImplnterface seApi = new ConsumoSaldoExtratoApiMock();

		Sessao sessao = sessaoDao.getSessao(id, origem);
		
		if (sessao != null && seApi.isTokenValid(sessao.getAccessData())) {
			// temos uma sessao podemos fazer busca por saldo extrato
			String saldoExtratoResposta = null;
			List<MensagemBotService> mensagens = new ArrayList<>();
			MensagemBotService resp = new MensagemBotService();

			switch (callbackEnum) {
			case SALDO:
				saldoExtratoResposta = seApi.getSaldo(sessao.getAccessData());
				

				if (saldoExtratoResposta != null && seApi.isTokenValid(sessao.getAccessData())) {

					resp.setTextoUnico("Seu saldo é: \n" + saldoExtratoResposta);
					mensagens.add(resp);
					ResponseBotService response = new ResponseBotService();
					response.setTipoRequisicao(callbackEnum.toString());
					response.setMensagemList(mensagens);
					response.setTokenApiAtivo(true);
					return response;
				} else {
					// api com chave expirada
					// manda o user pra login
					return solicitarLogin(id, origem, callbackEnum);
				}

			case ULTIMAS_TRANSACOES:
				saldoExtratoResposta = seApi.getUltimasTransacoes(sessao.getAccessData());

				if (saldoExtratoResposta != null && seApi.isTokenValid(sessao.getAccessData())) {
					resp.setTextoUnico(Constantes.MENSAGEM_ULTIMAS_TRANSACOES_TITULO_RESPOSTA);
					mensagens.add(resp);
					resp = new MensagemBotService();
					resp.setTextoUnico(saldoExtratoResposta);
					mensagens.add(resp);
					ResponseBotService response = new ResponseBotService();
					response.setTipoRequisicao(callbackEnum.toString());
					response.setMensagemList(mensagens);
					response.setTokenApiAtivo(true);
					return response;
				} else {
					// api com chave expirada
					// manda o user pra login
					return solicitarLogin(id, origem, callbackEnum);
				}

			default:
				throw new BotServicesException(
						"tipo de transação " + callbackEnum + " não suportado pelo motor de saldo extrado.");
			}

		} else {
			// popula o objeto com um link de autenticacao
			return solicitarLogin(id, origem, callbackEnum);
		}

	}

	private static ResponseBotService solicitarLogin(String id, Origem origem, CallbackEnum callbackEnum) {
		TokenAutenticacao tokenAutenticacao = new TokenAutenticacao();
		tokenAutenticacao.setBradeschatAuthToken(UUID.randomUUID().toString());
		tokenAutenticacao.setDataCriacao(new Date());
		System.out.println(tokenAutenticacao.getDataCriacao());
		tokenAutenticacao.setId(id);
		tokenAutenticacao.setOrigem(origem);
		tokenAutenticacao.setTipoRequisicao(callbackEnum);

		TokenDaoMock bradeschatAuthTokenDAO = new TokenDaoMock();
		bradeschatAuthTokenDAO.inserir(tokenAutenticacao);
		ResponseBotService response = new ResponseBotService();
		response.setBradeschatAuthToken(tokenAutenticacao.getBradeschatAuthToken());
		response.setTipoRequisicao(callbackEnum.toString());
		System.out.println(tokenAutenticacao.getBradeschatAuthToken());
		return response;
	}

	public static boolean validarBradeschatAuthToken(String bradeschatAuthToken){
		if(TokenDaoMock.isTokenValid(bradeschatAuthToken)){
			return true;
		}
		return false;
	}
	
	public static TokenAutenticacao obterDadosRequisicao(String bradeschatAuthToken){
		TokenDaoInterface tokenDao = new TokenDaoMock();
		TokenAutenticacao tokenAutenticacao = new TokenAutenticacao();
		tokenAutenticacao=tokenDao.getTokenAutenticacao(bradeschatAuthToken);
		return tokenAutenticacao;
	}
	
	public static TokenAutenticacao autenticar(Login login) {
		// descobrir se existe sessao
		TokenDaoInterface tokenDao = new TokenDaoMock();
		TokenAutenticacao tokenAutenticacao = tokenDao.getTokenAutenticacao(login.getBradeschatAuthToken());
		SessaoDaoInterface sessaoDao = new SessaoDaoMock();
		ConsumoSaldoExtratoApiImplnterface seApi = new ConsumoSaldoExtratoApiMock();

		if (tokenAutenticacao != null) {
			/*
			 * autenticacao esta vindo como esperada, existe um token de
			 * relacionamento vamos fazer login do usuario
			 */
			String apiToken = seApi.login(login);

			if (apiToken != null && seApi.isTokenValid(apiToken)) {
				// login foi efetuado com sucesso
				// hora de criar sessao e destruir o token utilizado
				tokenDao.remover(login.getBradeschatAuthToken());
				Sessao sessao = new Sessao();
				sessao.setBradeschatAuthToken(login.getBradeschatAuthToken());
				sessao.setId(tokenAutenticacao.getId());
				sessao.setOrigem(tokenAutenticacao.getOrigem());
				sessao.setAccessData(apiToken);
				sessaoDao.criaSessao(sessao);

				return tokenAutenticacao;

			} else {
				// falha nos dados de login
				return null;
			}

		} else {
			// estou autenticando com um token nao existente
			return null;
		}

	}
	


}
