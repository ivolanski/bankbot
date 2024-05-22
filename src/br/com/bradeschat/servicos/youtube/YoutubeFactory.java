package br.com.bradeschat.servicos.youtube;

public class YoutubeFactory {
	
	private static YoutubeApiImpl youtubeApiImpl;
	
	static{
		youtubeApiImpl = new YoutubeApiImpl();	
	}
	
	public static YoutubeApiImpl getFacebookBot(){
		return youtubeApiImpl;
	}


}
