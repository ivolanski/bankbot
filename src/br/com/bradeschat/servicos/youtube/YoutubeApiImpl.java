package br.com.bradeschat.servicos.youtube;

import com.google.api.services.samples.youtube.cmdline.bot.YoutubeBotApi;

public class YoutubeApiImpl extends YoutubeBotApi{

	@Override
	protected String getApiKey() {
		return "xxxxxxxxxxx";
	}

	@Override
	protected String getChannel() {
		return "xxxxxxxxx";
	}
	
	

}
