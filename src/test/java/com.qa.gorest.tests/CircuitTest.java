package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.pojo.User;
import com.qa.gorest.util.JsonPathValidator;
import com.qa.gorest.util.StringUtil;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.testng.annotations.Test;

import java.util.List;

public class CircuitTest extends BaseTest {

    @BeforeMethod()
    public void getUserSetup() {
        restClient = new RestClient(prop, baseURI);
    }

    @Test
    public void getAllUsersTest() {
        Response circuitResponse = restClient.get(CIRCUIT_ENDPOINT + "/2017/circuits.json", false, false);
        int statusCode = circuitResponse.statusCode();
        Assert.assertEquals(statusCode, APIHttpStatus.OK_200.getCode());
        JsonPathValidator js = new JsonPathValidator();
        List<String> countryList = js.readList(circuitResponse, "$.MRData.CircuitTable.Circuits[?(@.circuitId == 'shanghai')].Location.country");
        System.out.println(countryList);
        Assert.assertTrue(countryList.contains("China"));


//                .then().log().all()
//                .assertThat().statusCode(APIHttpStatus.OK_200.getCode());
    }
}
