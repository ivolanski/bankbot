package br.com.teste.servicos;

public class Constantes {
	
	//STRINGS
	
	//Comandos
	/*Adicionar aqui os comandos a serem usados no CallbackEnum. 
	 * Basicamente, eles identificam cada uma das funcionalidades do Bradeschat.
	 * Destinam-se, também, aos paylodas de botões e similares.*/
	public static final String COMANDO_SALDO = "Saldo";
	public static final String COMANDO_ULTIMAS_TRANSACOES = "Últimas transações";
	public static final String COMANDO_SAIR = "Sair";
	public static final String COMANDO_MENU="Menu";
	public static final String COMANDO_BUSCAR_YOUTUBE="Buscar Pergunta";	
	public static final String COMANDO_AJUDA="Ajuda";
	public static final String COMANDO_START="start";
	public static final String COMANDO_COMECAR="começar";
	
	//Mensagens para recebimento
	public static final String EXTRATO = "extrato";
	public static final String TRANSACOES="transações";
	
	//Mensagens para envio
	public static final String MENSAGEM_SAIR = "Até Logo!";
	public static final String MENSAGEM_LOGIN_SUCESSO_FACEBOOK = "Login efetuado com sucesso. Clique em fechar esta janela para retornar ao Messenger.";
	public static final String MENSAGEM_LOGIN_SUCESSO_TELEGRAM = "Login efetuado com sucesso. Clique em fechar esta janela para retornar ao Telegram.";
	public static final String MENSAGEM_LOGIN_ERRO="Falha ao entrar";
	public static final String MENSAGEM_LOGIN_ERRO_BRADESCHAT_AUTH_TOKEN_EXPIRADO="Seu pedido expirou. Por favor, feche esta janela e faça seu pedido novamente.";
	public static final String MENSAGEM="mensagem";
	public static final String MENSAGEM_GREETINGS_MENU="No Bradeschat você pode consultar:";
	public static final String MENSAGEM_FAZER_SEGUIR="O que deseja fazer a seguir?";
	public static final String MENSAGEM_OLA="Olá!";
	public static final String MENSAGEM_ULTIMAS_TRANSACOES_TITULO_RESPOSTA = "Suas últimas transações: ";
	public static final String MENSAGEM_BRADESCO_MAIS_PROXIMO = "Bradesco mais próximo";
	public static final String MENSAGEM_BRADESCO_MAIS_PROXIMO_COMO_USAR = "Compartilhe sua localização para encontrar o Bradesco mais próximo";
	public static final String MENSAGEM_PERGUNTA_AJUDAR_EM_ALGO_MAIS = "Posso ajudar em algo mais ?";
	public static final String MENSAGEM_PERGUNTA_NAO_COMPREENDIDA = "Não localizamos nenhuma informação sobre a mensagem recebida, tente novamente com palavras diferentes.";
	public static final String MENSAGEM_RESPOSTA_ANEXO_RECEBIDO_INDEFINIDO = "Desculpe, ainda não consigo entender esse tipo de mensagem";
	public static final String MENSAGEM_COMPARTILHE_LOCALIZACAO_VERBOSO ="Por favor, compartilhe sua localização.";
	public static final String MENSAGEM_ANEXO_RECEBIDO="Obrigado por me enviar esse anexo !";
	public static final String MENSAGEM_UTILIZE_SEND_LOCATION="Utilize o botão abaixo:";
	public static final String MENSAGEM_COMPARTILHE_LOCALIZACAO ="Compartilhe Sua Localização";
	public static final String MENSAGEM_LOCALIZACAO_HARD_CODED="Encontrei um caixa eletrônico bem próximo de você!\nAv. Comendador Macedo, 135";
	
	//Ajuda	
	public static final String AJUDA_SOBRE_BRADESCHAT="O que é o Bradeschat?\n\nO Bradeschat é um robô para chats desenvolvido pelo Banco Bradesco.\n"
													+ "Aqui você pode:\n\n1) Consultar Saldo e Últimas Transações\n"
													+ "2) Buscar nossa rede credenciada mais próxima\n"
													+ "3) Pesquisar os vídeos de ajuda do canal do Bradesco no Youtube.\n\n"
													+ "Não se preocupe com sua segurança: antes de ter acesso às suas informações pessoais "
													+ "você precisará autenticar sua conta. Nós vamos te dar uma tela de login e "
													+ "te devolver rapidinho pra cá! Depois disso você consulta o que quiser, sem precisar logar de novo por até 20 minutos.\n\n"
													+ "Viu como é fácil?! Pra começar é só conversar comigo, ou escolher um botão abaixo.";

	//Origem
	public static final String ORIGEM_FACEBOOK="Facebook";
	public static final String ORIGEM_TELEGRAM="Telegram";
	
	//Parâmetros
	public static final String PARAMETRO_BRADESCHAT_AUTH_TOKEN = "bradeschat_auth_token=";
	
	//Títulos e Captions
	public static final String TITULO_PADRAO_RESPOSTA = "Opções relacionadas";
	public static final String SUBTITULO_PADRAO_RESPOSTA = "Veja o que encontramos:";
	public static final String CAPTION_LOGIN_NECESSARIO = "Opção protegida";
	public static final String SUBTITULO_LOGIN_NECESSARIO = "Clique em entrar";
	public static final String CAPTION_BOTAO_ENTRAR = "Efetuar Login";
	
	//Servlets
	public static final String SERVLET_SUCESSO= "/sucesso";
	public static final String SERVLET_FACEBOOK_ENTRAR = "/facebook/entrar";
	public static final String SERVLET_TELEGRAM_ENTRAR = "/telegram/entrar";
	public static final String SERVLET_FACEBOOK_RESULTADO_LOGIN = "/facebook/resultado_login";
	public static final String SERVLET_TELEGRAM_RESULTADO_LOGIN = "/telegram/resultado_login";
	public static final String SERVLET_FACEBOOK_PRIVACIDADE = "/facebook/privacidade";
	public static final String SERVLET_TELEGRAM_PRIVACIDADE = "/telegram/privacidade";
	public static final String JSP_FACEBOOK_ENTRAR = "facebook_entrar.jsp";
	public static final String JSP_TELEGRAM_ENTRAR = "telegram_entrar.jsp";
	public static final String JSP_FACEBOOK_PRIVACIDADE="facebook_politica_privacidade.jsp";
	public static final String JSP_FACEBOOK_RESULTADO_LOGIN="facebook_resultado_login.jsp";
	public static final String JSP_TELEGRAM_PRIVACIDADE="telegram_politica_privacidade.jsp";
	public static final String JSP_TELEGRAM_RESULTADO_LOGIN="telegram_resultado_login.jsp";
	
	//URL
	public static final String URL_IMAGEM_LOGIN = "https://cryptoid.com.br/wp-content/uploads/2015/09/SSL-22-1440x564_c.jpg";
	public static final String URL_SERVIDOR = "https://www.qbot.com.br/qbot";
	public static final String PAGINAS_LOCALIZACAO = "/WEB-INF/pages/";
	public static final String URL_MAPS = "https://www.google.com.br/maps/search/bradesco/@";
	
	//Miscelaneous
	public static final String PULAR_LINHA="\n";
	public static final String VID="@vid ";
	public static final String SIGNATURE_HEADER="x-hub-signature";
	public static final String FACEBOOK_SECRET="";

	//INTEIROS
	public static final int PRAZO_TOKEN_API_SALDO_EXTRATO=1200;
	public static final int PRAZO_BRADESCHAT_AUTH_TOKEN=45;
	
}
