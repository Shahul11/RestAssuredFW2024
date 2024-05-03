package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import java.util.HashMap;
import java.util.Map;


import static org.hamcrest.Matchers.equalTo;

public class GetUserTest extends BaseTest {

    //public RestClient resClient;

    @BeforeMethod()
    public void getUserSetup(){
        restClient = new RestClient(prop, baseURI);
    }

    @Test(priority = 3)
    public void getAllUsersTest() {
        restClient.get(GOREST_ENDPOINT,true, true)
                .then().log().all()
                .assertThat().statusCode(APIHttpStatus.OK_200.getCode());
    }

    @Test(priority = 2)
    public void getUserTest() {

        restClient.get(GOREST_ENDPOINT +"/6878219", true,true)
                .then().log().all()
                .assertThat().statusCode(APIHttpStatus.OK_200.getCode())
                .and().body("id", equalTo(6878219));
    }

    @Test(priority =1)
    public void getUserWithQueryParamTest() {

        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("name", "sha");
        queryParams.put("status", "active");

        restClient.get(GOREST_ENDPOINT, null, queryParams, true, true)
                .then().log().all()
                .assertThat().statusCode(APIHttpStatus.OK_200.getCode());

    }
}
