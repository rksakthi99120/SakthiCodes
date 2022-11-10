package com.qa.api;

import org.json.JSONObject;

public class ApiRequest {
	private String endPointUrl;
	private JSONObject messageBody;
	private String authorization;
	private String contentType;
	public String getEndPointUrl() {
		return endPointUrl;
	}
	public void setEndPointUrl(String endPointUrl) {
		this.endPointUrl = endPointUrl;
	}
	public JSONObject getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(JSONObject messageBody) {
		this.messageBody = messageBody;
	}
	
	public String getAuthorization() {
		return authorization;
	}
	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	

}
