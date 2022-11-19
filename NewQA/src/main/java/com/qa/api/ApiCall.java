package com.qa.api;


import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiCall {
	
	public ApiResponse getApi(ApiRequest request) {
		RestAssured.baseURI=request.getEndPointUrl();
		RequestSpecification reqSpec=RestAssured.given().header("Authorization", request.getAuthorization(), null);
		Response response=reqSpec.request(Method.GET);
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setStatusCode(response.getStatusCode());
		apiResponse.setResponse(response.asPrettyString());
		return apiResponse;
	}
	
	public ApiResponse getApi(String endPointUrl) {
		RequestSpecification reqSpec=RestAssured.given();
		Response response=reqSpec.when().get(endPointUrl);
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setStatusCode(response.getStatusCode());
		apiResponse.setResponse(response.asPrettyString());
		return apiResponse;
	}
	
	public ApiResponse postApiCall(ApiRequest request) {
		ApiResponse apiResponse=new ApiResponse();
		return apiResponse;
	}
	
	public ApiResponse putApiCall(ApiRequest request) {
		return null;
	}
	
	public ApiResponse deleteApiCall(ApiRequest request) {
		return null;
	}

}
