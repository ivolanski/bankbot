package br.com.teste.servicos;

import java.util.List;

public class ResponseBotService {
	
	private List<MensagemBotService> mensagemList;
	private List<QuickReplyBotService> quickReplyList;
	private String quickReplyText;
	private String bradeschatAuthToken;
	private String tipoRequisicao;
	private boolean isTokenApiAtivo=false;

	

	public List<MensagemBotService> getMensagemList() {
		return mensagemList;
	}
	public void setMensagemList(List<MensagemBotService> mensagemList) {
		this.mensagemList = mensagemList;
	}
	public List<QuickReplyBotService> getQuickReplyList() {
		return quickReplyList;
	}
	public void setQuickReplyList(List<QuickReplyBotService> quickReplyList) {
		this.quickReplyList = quickReplyList;
	}
	public String getQuickReplyText() {
		return quickReplyText;
	}
	public void setQuickReplyText(String quickReplyText) {
		this.quickReplyText = quickReplyText;
	}

	public String getBradeschatAuthToken() {
		return bradeschatAuthToken;
	}
	public void setBradeschatAuthToken(String bradeschatAuthToken) {
		this.bradeschatAuthToken = bradeschatAuthToken;
	}

	public String getTipoRequisicao() {
		return tipoRequisicao;
	}
	public void setTipoRequisicao(String tipoRequisicao) {
		this.tipoRequisicao = tipoRequisicao;
	}
	public boolean isTokenApiAtivo() {
		return isTokenApiAtivo;
	}
	public void setTokenApiAtivo(boolean isTokenApiAtivo) {
		this.isTokenApiAtivo = isTokenApiAtivo;
	}
	@Override
	public String toString() {
		return "ResponseBotService [mensagemList=" + mensagemList + ", quickReplyList=" + quickReplyList
				+ ", quickReplyText=" + quickReplyText + ", bradeschatAuthToken=" + bradeschatAuthToken
				+ ", tipoRequisicao=" + tipoRequisicao + ", isTokenApiAtivo=" + isTokenApiAtivo + "]";
	}



}
