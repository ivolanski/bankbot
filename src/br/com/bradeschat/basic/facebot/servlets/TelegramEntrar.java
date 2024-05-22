package br.com.bradeschat.basic.facebot.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.telegram.telegrambots.exceptions.TelegramApiException;

import br.com.bradeschat.TelegramBot.TelegramBotFactory;
import br.com.bradeschat.model.Login;
import br.com.bradeschat.model.TokenAutenticacao;
import br.com.teste.servicos.BotService;
import br.com.teste.servicos.Constantes;

/**
 * Servlet implementation class TelegramEntrar
 */
@WebServlet(Constantes.SERVLET_TELEGRAM_ENTRAR)
public class TelegramEntrar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TelegramEntrar() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bradeschatAuthToken = request.getParameter("bradeschat_auth_token");
		request.setAttribute("bradeschat_auth_token", bradeschatAuthToken);
		String nextJSP = Constantes.PAGINAS_LOCALIZACAO + Constantes.JSP_TELEGRAM_ENTRAR;
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bradeschatAuthToken = request.getParameter("bradeschat_auth_token");
		Login login = new Login(request.getParameter("agencia"), request.getParameter("conta"),
				request.getParameter("titular"), request.getParameter("token"), request.getParameter("senha"),
				bradeschatAuthToken);

		TokenAutenticacao token=null;
		if(BotService.validarBradeschatAuthToken(bradeschatAuthToken)){
			token = BotService.autenticar(login);
		}
		if (token != null) {
			request.setAttribute(Constantes.MENSAGEM, Constantes.MENSAGEM_LOGIN_SUCESSO_TELEGRAM);
			try {
				TelegramBotFactory.getTelegramBot().processarComandosService(token.getTipoRequisicao().getTexto(), token.getId());
			} catch (TelegramApiException e) {
				e.printStackTrace();
				request.setAttribute("bradeschat_auth_token", bradeschatAuthToken);
				request.setAttribute(Constantes.MENSAGEM, Constantes.MENSAGEM_LOGIN_ERRO);
			}
		} else {

			request.setAttribute("bradeschat_auth_token", bradeschatAuthToken);
			
			if(!BotService.validarBradeschatAuthToken(bradeschatAuthToken)){
				request.setAttribute(Constantes.MENSAGEM, Constantes.MENSAGEM_LOGIN_ERRO_BRADESCHAT_AUTH_TOKEN_EXPIRADO);
			} else{
				request.setAttribute(Constantes.MENSAGEM, Constantes.MENSAGEM_LOGIN_ERRO);
			}
		}
		String nextJSP = Constantes.PAGINAS_LOCALIZACAO + Constantes.JSP_TELEGRAM_RESULTADO_LOGIN;
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request, response);
	}

}
