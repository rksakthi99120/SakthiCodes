package com.test.api;

import org.testng.annotations.Test;

import com.qa.api.ApiCall;
import com.qa.api.ApiRequest;
import com.qa.api.ApiResponse;
import com.qa.report.Assertion;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestApiModule {
	
	@Test(groups={"TestAPI","Module1"})
	public void testGetAPI() {
		ApiCall apiCall=new ApiCall();
		ApiRequest apiRequest=new ApiRequest();
		ApiResponse apiResponse=new ApiResponse();
		apiResponse=apiCall.getApi("");
		
		Assertion.assertEquals(200,apiResponse.getStatusCode());
	}

}
