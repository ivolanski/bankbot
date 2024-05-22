package br.com.bradeschat.basic.facebot.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import br.com.bradeschat.facebot.FacebookBotImpl;
import br.com.bradeschat.facebot.FacebotFactory;
import br.com.bradesco.facebook.bot.api.exception.FacebookApiException;
import br.com.teste.servicos.Constantes;

/**
 * Servlet implementation class Callback
 */
@WebServlet(urlPatterns = { "", "/callback" })
public class Callback extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SEPARATOR = "====================================";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Callback() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		handleRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void handleRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {

		try {
			System.out.println("================Headers ================");
			System.out.println("=======================================");
		} catch (Exception e2) {
			e2.printStackTrace();
			System.out.println("Exception hub challenge: "+e2.getMessage());
		}
		try {
			String responseToClient = req.getParameter("hub.challenge");
			if (responseToClient != null) {
				res.setStatus(HttpServletResponse.SC_OK);
				res.getWriter().write(responseToClient);
				res.getWriter().flush();
				res.getWriter().close();
				res.getWriter().append("Fetch-Mode").append(req.getParameter("hub.mode"));
				res.getWriter().append("App Verify Token:").append(req.getParameter("hub.verify_token"));
				res.getWriter().append("App Challenge No").append(req.getParameter("hub.challenge"));
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("Exception hub challenge: " + e1.getMessage());
		}
		try {
			System.out.println(SEPARATOR);
			System.out.println("Json recebido");
			BufferedReader br = req.getReader();
			String jsonString = "";
			String str;
			while ((str = br.readLine()) != null) {
				System.out.println(str);
				jsonString += str;
			}
			System.out.println(jsonString);
			boolean isFacebook =verificarHubSignature(req.getHeader(Constantes.SIGNATURE_HEADER).substring(5),jsonString);
			if(isFacebook){
				FacebookBotImpl bot = FacebotFactory.getFacebookBot();
				try {
					bot.processarJsonRecebido(jsonString);
				} catch (FacebookApiException e) {
					e.printStackTrace();
					System.out.println("Facebook exception: " + e.getMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception generica: " + e.getMessage());
		}

		res.setStatus(HttpServletResponse.SC_OK);

	}

	public boolean verificarHubSignature(String receivedSignature, String body) throws NoSuchAlgorithmException, InvalidKeyException{
		System.out.println("executando verificarHubSignature...");
		Mac hmac = Mac.getInstance("HmacSHA1");
		hmac.init(new SecretKeySpec("39c79fc95fc9ac36a127cce144520b2b".getBytes(Charset.forName("UTF-8")), "HmacSHA1"));
		String calculatedSignature = DatatypeConverter.printHexBinary(hmac.doFinal(body.getBytes(Charset.forName("UTF-8"))));
		System.out.println("Signature Recebida: " + receivedSignature + " Signature calculada: " + calculatedSignature);
		if(receivedSignature.equalsIgnoreCase(calculatedSignature)){
			return true;
		} 
		return false;
		
	}
	

	
}
