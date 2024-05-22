package br.com.bradeschat.basic.facebot.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.teste.servicos.Constantes;

/**
 * Servlet implementation class FacebookPoliticaPrivacidade
 */
@WebServlet(Constantes.SERVLET_FACEBOOK_PRIVACIDADE)
public class FacebookPoliticaPrivacidade extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FacebookPoliticaPrivacidade() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextJSP = Constantes.PAGINAS_LOCALIZACAO + Constantes.JSP_FACEBOOK_PRIVACIDADE;
		 
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextJSP = Constantes.PAGINAS_LOCALIZACAO + Constantes.JSP_FACEBOOK_ENTRAR;
		 
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);
	}

}
