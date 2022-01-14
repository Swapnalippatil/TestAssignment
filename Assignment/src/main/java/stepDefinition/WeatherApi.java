package stepDefinition;


import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WeatherApi {
	RequestSpecification request = RestAssured.given();
	Response response;
	
	@Given("^Weather station URL is running$")
	public void weather_station_URL_is_running() throws Throwable {
		RestAssured.baseURI ="http://api.openweathermap.org/data/2.5"; 
	}

	@When("^User makes a request without API key$")
	public void user_makes_a_request_without_API_key() throws Throwable {
	response = request.queryParam("q", "London,UK") 
				                   .get("/weather");
	}

	@Then("^Response code is (\\d+)$")
	public void response_code_is(int statusCode) throws Throwable {
		//Verify status code
		Assert.assertEquals(response.getStatusCode(),statusCode);
		
		if(statusCode==200) {
			JsonPath jsonPathEvaluator = response.jsonPath();
			String city = jsonPathEvaluator.get("name");
		
			//Verify City name in the response
			Assert.assertEquals(city, "London");
		}
	}

	@When("^User makes a request with valid API key$")
	public void user_makes_a_request_with_valid_API_key() throws Throwable {
		response = request.queryParam("q", "London,UK") 
                .queryParam("appid", "13e0f01e8de05608c4d453c3d45d149c") 
                .get("/weather");
	}

	@When("^User makes a request with invalid API key$")
	public void user_makes_a_request_with_invalid_API_key() throws Throwable {
		response = request.queryParam("q", "London,UK") 
                .queryParam("appid", "smdnse8de05608c4d453c3d4iop") 
                .get("/weather");
	}


}
