package br.com.teste.servicos;

public class BotServicesException extends Exception {

	private static final long serialVersionUID = 1L;
	
	  public BotServicesException() { super(); }
	  public BotServicesException(String message) { super(message); }
	  public BotServicesException(String message, Throwable cause) { super(message, cause); }
	  public BotServicesException(Throwable cause) { super(cause); }

	
}
