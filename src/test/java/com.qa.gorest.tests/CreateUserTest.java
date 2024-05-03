package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.pojo.User;
import com.qa.gorest.util.StringUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class CreateUserTest extends BaseTest {


    @BeforeMethod()
    public void getUserSetup() {
        restClient = new RestClient(prop, baseURI);
    }


    @DataProvider
    public Object[][] getUserTestSheetData() {
        return new Object[][] {
                {"Subodh", "male", "active"},
                {"Seema", "female", "inactive"},
                {"Madhuri", "female", "active"}
        };
    }


    @Test(dataProvider = "getUserTestSheetData")
    public void createUserTest(String name, String gender, String status) {

        //1. Post Call
        User newUser = new User(name, gender, StringUtil.getRandomEmailId(), status);
        //  RestClient resClient = new RestClient();
        Integer userId = restClient.post(GOREST_ENDPOINT, "json", newUser, true, true)
                .then().log().all()
                .assertThat().statusCode(APIHttpStatus.CREATED_201.getCode()).extract().body().path("id");

        System.out.println("User Id is --->" + userId);
        //2. GET:

        RestClient clientGet = new RestClient(prop, baseURI);
        clientGet.get(GOREST_ENDPOINT + "/" + userId, true, true).then().log().all()
                .assertThat().statusCode(APIHttpStatus.OK_200.getCode())
                .assertThat().body("id", equalTo(userId));

    }


}
