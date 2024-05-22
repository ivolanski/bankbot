package br.com.bradeschat.servicos.youtube;

import com.google.api.services.samples.youtube.cmdline.bot.YoutubeBotApi;

public class YoutubeApiImpl extends YoutubeBotApi{

	@Override
	protected String getApiKey() {
		return "AIzaSyAr8exQvUWTitc8qWoZq11MLRz2Q20bMbY";
	}

	@Override
	protected String getChannel() {
		return "UCwVzFkXszeP__iWXNV2EjhA";
	}
	
	

}
