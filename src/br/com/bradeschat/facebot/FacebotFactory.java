package br.com.bradeschat.facebot;

public class FacebotFactory {
	
	private static FacebookBotImpl facebookBotImpl;
	
	static{
		facebookBotImpl = new FacebookBotImpl();	
	}
	
	public static FacebookBotImpl getFacebookBot(){
		return facebookBotImpl;
	}


}
