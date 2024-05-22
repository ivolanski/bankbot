package br.com.teste.servicos;

import java.util.List;

public class MensagemBotService {
	
	private String imagemUrl;
	private String titulo;
	private String subtitulo;
	private List<BotaoBotService> botoes;
	private String textoUnico;
	private String templateItemUrl;

	
	
	public String getImagemUrl() {
		return imagemUrl;
	}
	public void setImagemUrl(String imagemUrl) {
		this.imagemUrl = imagemUrl;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getSubtitulo() {
		return subtitulo;
	}
	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}
	public List<BotaoBotService> getBotoes() {
		return botoes;
	}
	public void setBotoes(List<BotaoBotService> botoes) {
		this.botoes = botoes;
	}
	public String getTextoUnico() {
		return textoUnico;
	}
	public void setTextoUnico(String textoUnico) {
		this.textoUnico = textoUnico;
	}

	public String getTemplateItemUrl() {
		return templateItemUrl;
	}
	public void setTemplateItemUrl(String templateItemUrl) {
		this.templateItemUrl = templateItemUrl;
	}
	@Override
	public String toString() {
		return "MensagemBotService [imagemUrl=" + imagemUrl + ", titulo=" + titulo + ", subtitulo=" + subtitulo
				+ ", botoes=" + botoes + ", textoUnico=" + textoUnico + ", templateItemUrl=" + templateItemUrl + "]";
	}

}
