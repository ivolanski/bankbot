package br.com.bradeschat.basic.facebot.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.bradeschat.DAO.TokenDaoMock;
import br.com.bradeschat.facebot.FacebotFactory;
import br.com.bradeschat.model.Login;
import br.com.bradeschat.model.TokenAutenticacao;
import br.com.bradesco.facebook.bot.api.income.setup.echo.message.received.WebhookMessage;
import br.com.bradesco.facebook.bot.api.income.webhook.WebhookMessaging;
import br.com.bradesco.facebook.bot.api.income.webhook.WebhookMessagingSender;
import br.com.teste.servicos.BotService;
import br.com.teste.servicos.Constantes;

/**
 * Servlet implementation class Entrar
 */
@WebServlet(Constantes.SERVLET_FACEBOOK_ENTRAR)
public class FacebookEntrar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FacebookEntrar() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bradeschatAuthToken = request.getParameter("bradeschat_auth_token");
		request.setAttribute("bradeschat_auth_token", bradeschatAuthToken);
		String nextJSP = Constantes.PAGINAS_LOCALIZACAO +  Constantes.JSP_FACEBOOK_ENTRAR;
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bradeschatAuthToken = request.getParameter("bradeschat_auth_token");
		Login login = new Login(request.getParameter("agencia"),
								request.getParameter("conta"),
								request.getParameter("titular"),
								request.getParameter("token"),
								request.getParameter("senha"),
								bradeschatAuthToken);	

		TokenAutenticacao token=null;
		if(BotService.validarBradeschatAuthToken(bradeschatAuthToken)){
			token = BotService.autenticar(login);
		}		
		if(token!=null){
			request.setAttribute(Constantes.MENSAGEM, Constantes.MENSAGEM_LOGIN_SUCESSO_FACEBOOK);
			WebhookMessaging webhookMessaging = new WebhookMessaging();
			WebhookMessagingSender webhookMessagingSender = new WebhookMessagingSender();
			webhookMessagingSender.setId(token.getId());
			webhookMessaging.setWebhookMessagingSender(webhookMessagingSender);
			WebhookMessage webhookMessage = new WebhookMessage();
			webhookMessage.setText(token.getTipoRequisicao().getTexto());
			webhookMessaging.setWebhookMessagingSender(webhookMessagingSender);
			webhookMessaging.setWebhookMessage(webhookMessage);
			FacebotFactory.getFacebookBot().textReceived(webhookMessaging);
			String nextJSP = Constantes.PAGINAS_LOCALIZACAO + Constantes.JSP_FACEBOOK_RESULTADO_LOGIN;
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);
		}else{
			
			System.out.println("BradeschatAuthToken "+ bradeschatAuthToken);
			request.setAttribute("bradeschat_auth_token", bradeschatAuthToken);
			if(!BotService.validarBradeschatAuthToken(bradeschatAuthToken)){
				request.setAttribute(Constantes.MENSAGEM, Constantes.MENSAGEM_LOGIN_ERRO_BRADESCHAT_AUTH_TOKEN_EXPIRADO);
				WebhookMessaging webhookMessaging = new WebhookMessaging();
				WebhookMessagingSender webhookMessagingSender = new WebhookMessagingSender();
				webhookMessagingSender.setId(BotService.obterDadosRequisicao(bradeschatAuthToken).getId());
				webhookMessaging.setWebhookMessagingSender(webhookMessagingSender);
				WebhookMessage webhookMessage = new WebhookMessage();
				webhookMessage.setText(BotService.obterDadosRequisicao(bradeschatAuthToken).getTipoRequisicao().getTexto());
				TokenDaoMock tokendao = new TokenDaoMock();
				tokendao.remover(bradeschatAuthToken);
				webhookMessaging.setWebhookMessagingSender(webhookMessagingSender);
				webhookMessaging.setWebhookMessage(webhookMessage);
				FacebotFactory.getFacebookBot().textReceived(webhookMessaging);
				
				//ainda não
				
			}else{
				request.setAttribute(Constantes.MENSAGEM, Constantes.MENSAGEM_LOGIN_ERRO);
			}
			String nextJSP = Constantes.PAGINAS_LOCALIZACAO + Constantes.JSP_FACEBOOK_RESULTADO_LOGIN;
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);
			
		}
		
	}

}
