package com.qa.gorest.client;

import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.frameworkexception.APIFrameworkException;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import javax.annotation.meta.When;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;


public class RestClient {

    //    private static final String BASE_URI = "https://gorest.co.in";
//    private static final String BEARER_TOKEN = "7b513e32051b9d502026377a37b1ff5df54f26aaa47bb0ea7c434f5a453e0649";
    private static RequestSpecBuilder specBuilder;

    private Properties prop;
    private String baseURI;
    private boolean isAuthorizationHeaderAdded = false;

//    static {
//        specBuilder = new RequestSpecBuilder();
//    }

    public RestClient(Properties prop, String baseURI) {
        specBuilder = new RequestSpecBuilder();
        this.prop = prop;
        this.baseURI = baseURI;
    }

    public void addAuthorizationHeader() {
        if (!isAuthorizationHeaderAdded) {
            specBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("tokeId"));
            isAuthorizationHeaderAdded = true;
        }
    }

    private void setRequestContentType(String contentType) {
        switch (contentType.toLowerCase()) {
            case "json":
                specBuilder.setContentType(ContentType.JSON);
                break;
            case "xml":
                specBuilder.setContentType(ContentType.XML);
                break;
            case "text":
                specBuilder.setContentType(ContentType.TEXT);
                break;
            case "multipart":
                specBuilder.setContentType(ContentType.MULTIPART);
                break;
            default:
                System.out.println("Please pass the right content type...");
                throw new APIFrameworkException("INVALIDCONTENTTYPE");
        }
    }

    private RequestSpecification createRequestSpec(boolean includeAuth) {
        specBuilder.setBaseUri(baseURI);
        if (includeAuth) {
            addAuthorizationHeader();
        }
        return specBuilder.build();
    }

    private RequestSpecification createRequestSpec(Map<String, String> headerMap, boolean includeAuth) {
        specBuilder.setBaseUri(baseURI);
        if (includeAuth) {
            addAuthorizationHeader();
        }
        if (headerMap != null) {
            specBuilder.addHeaders(headerMap);
        }
        return specBuilder.build();
    }

    private RequestSpecification createRequestSpec(Map<String, String> headerMap, Map<String, String> queryParams, boolean includeAuth) {
        specBuilder.setBaseUri(baseURI);
        if (includeAuth) {
            addAuthorizationHeader();
        }
        if (headerMap != null) {
            specBuilder.addHeaders(headerMap);
        }
        if (queryParams != null) {
            specBuilder.addQueryParams(queryParams);
        }
        return specBuilder.build();
    }

    //IN a post call we don't need query Param
    private RequestSpecification createRequestSpec(Object requestBody, String contentType, boolean includeAuth) {
        specBuilder.setBaseUri(baseURI);
        if (includeAuth) {
            addAuthorizationHeader();
        }
        setRequestContentType(contentType);
        if (requestBody != null) {
            specBuilder.setBody(requestBody);
        }
        return specBuilder.build();
    }

    private RequestSpecification createRequestSpec(Object requestBody, String contentType, Map<String, String> headerMap, boolean includeAuth) {
        specBuilder.setBaseUri(baseURI);
        if (includeAuth) {
            addAuthorizationHeader();
        }
        setRequestContentType(contentType);
        if (requestBody != null) {
            specBuilder.setBody(requestBody);
        }
        if (headerMap != null) {
            specBuilder.addHeaders(headerMap);
        }
        setRequestContentType(contentType);
        return specBuilder.build();
    }
//http Methods Utils

    public Response get(String serviceURI, boolean includeAuth, boolean log) {
        if (log) {
            return given().spec(createRequestSpec(includeAuth)).log().all().when().get(serviceURI);
        }
        return given().spec(createRequestSpec(includeAuth)).when().get(serviceURI);

    }

    public Response get(String serviceURI, Map<String, String> headersMap, boolean includeAuth, boolean log) {
        if (log) {
            return given().spec(createRequestSpec(headersMap, includeAuth)).log().all().when().get(serviceURI);
        }
        return given().spec(createRequestSpec(includeAuth)).when().get(serviceURI);
    }

    public Response get(String serviceURI, Map<String, String> headersMap, Map<String, String> queryParams, boolean includeAuth, boolean log) {
        if (log) {
            return given().spec(createRequestSpec(headersMap, queryParams, includeAuth)).log().all().when().get(serviceURI);
        }
        return given().spec(createRequestSpec(includeAuth)).when().get(serviceURI);
    }

    public Response post(String serviceURL, String contentType, Object requestBody, boolean includeAuth, boolean log) {
        if (log) {
            return given().spec(createRequestSpec(requestBody, contentType, includeAuth)).log().all()
                    .when().post(serviceURL);
        }
        return given().spec(createRequestSpec(requestBody, contentType, includeAuth))
                .when().post(serviceURL);
    }

    public Response post(String serviceURL, String contentType, Object requestBody, Map<String, String> headerMap, boolean includeAuth, boolean log) {
        if (log) {
            return given().spec(createRequestSpec(requestBody, contentType, headerMap, includeAuth)).log().all()
                    .when().post(serviceURL);
        }
        return given().spec(createRequestSpec(requestBody, contentType, headerMap, includeAuth))
                .when().post(serviceURL);
    }

    //Put Call
    public Response put(String serviceURL, String contentType, Object requestBody, boolean includeAuth, boolean log) {
        if (log) {
            return given().spec(createRequestSpec(requestBody, contentType, includeAuth)).log().all()
                    .when().put(serviceURL);
        }
        return given().spec(createRequestSpec(requestBody, contentType, includeAuth))
                .when().put(serviceURL);
    }


    public Response put(String serviceURL, String contentType, Object requestBody, Map<String, String> headerMap, boolean includeAuth, boolean log) {
        if (log) {
            return given().spec(createRequestSpec(requestBody, contentType, headerMap, includeAuth)).log().all()
                    .when().put(serviceURL);
        }
        return given().spec(createRequestSpec(requestBody, contentType, headerMap, includeAuth))
                .when().put(serviceURL);
    }

    // Patch Cal
    public Response patch(String serviceURL, String contentType, Object requestBody, boolean includeAuth, boolean log) {
        if (log) {
            return given().spec(createRequestSpec(requestBody, contentType, includeAuth)).log().all()
                    .when().patch(serviceURL);
        }
        return given().spec(createRequestSpec(requestBody, contentType, includeAuth))
                .when().patch(serviceURL);
    }


    public Response patch(String serviceURL, String contentType, Object requestBody, Map<String, String> headerMap, boolean includeAuth, boolean log) {
        if (log) {
            return given().spec(createRequestSpec(requestBody, contentType, headerMap, includeAuth)).log().all()
                    .when().patch(serviceURL);
        }
        return given().spec(createRequestSpec(requestBody, contentType, headerMap, includeAuth))
                .when().patch(serviceURL);
    }

    //Delete Cal
    public Response delete(String serviceUrl, boolean includeAuth, boolean log) {
        if (log) {
            return given().spec(createRequestSpec(includeAuth)).log().all()
                    .when().delete(serviceUrl);
        }
        return given().spec(createRequestSpec(includeAuth))
                .when().delete(serviceUrl);
    }


    public String getAccessToken(String serviceURL, String grantType, String clientId, String clientSecret) {
        //1. POST - get the access token
        RestAssured.baseURI = "https://test.api.amadeus.com";

        String accessToken = given().log().all()
                .contentType(ContentType.URLENC)
                .formParam("grant_type", grantType)
                .formParam("client_id", clientId)
                .formParam("client_secret", clientSecret)
                .when()
                .post(serviceURL)
                .then().log().all()
                .assertThat()
                .statusCode(APIHttpStatus.OK_200.getCode())
                .extract().path("access_token");

        System.out.println("access token: " + accessToken);
        return accessToken;


    }

}