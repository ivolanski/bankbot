package br.com.teste.servicos;

public class BotaoBotService {
	
	private String texto;
	private String callback;
	private String url;
	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "BotaoBotService [texto=" + texto + ", callback=" + callback + ", url=" + url + "]";
	}

}
