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
	ApiResponse apiResponse=new ApiResponse();
	
	@Test(groups={"TestAPI","All"})
	public void testGetAPI() {
		ApiCall apiCall=new ApiCall();
		ApiRequest apiRequest=new ApiRequest();
		apiResponse=apiCall.getApi("http://universities.hipolabs.com/search?country=India");
		
		Assertion.assertEquals(200,apiResponse.getStatusCode(),"Get University Api is not working",
				"Test to verify the Get University API");
	}
	
	@Test(groups={"TestAPI","All"},dependsOnMethods = {"testGetAPI"})
	public void testGetAPIData() {
		Assertion.assertEquals(true,apiResponse.getResponse().contains("Amrita Vishwa Vidyapeetham (Deemed University)")
				,"Get University Api is not retrieving data properly",
				"Test to verify the Get University API data validation");
	}
	
	@Test(groups={"TestAPI","All"},dependsOnMethods = {"testGetAPI"})
	public void testGetAPIDataFail() {
		Assertion.assertEquals(true,apiResponse.getResponse().contains("Test University"),
				"Get University Api is not retrieving data properly",
				"Test to verify the Get University API data validation");
	}


}
