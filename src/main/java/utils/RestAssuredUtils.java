package utils;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import static enums.Constants.APPLICATION_PROPERTIES_FILE_PATH;
import static utils.Helper.loadProperties;

@Slf4j
public class RestAssuredUtils {

    private final static String token = loadProperties(APPLICATION_PROPERTIES_FILE_PATH.getValue()).getProperty("oauth.token");

    public static RequestSpecification getRequestSpecification(Boolean withToken) {

        String baseURI = loadProperties(APPLICATION_PROPERTIES_FILE_PATH.getValue()).getProperty("baseUri");

        log.info("Request URL: {}", baseURI);
        log.info("User has authentication {}", String.valueOf(withToken).toUpperCase());

        if (withToken == null) {
            withToken = true;
        }
        if (withToken) {
            return RestAssured
                    .given()
                    .baseUri(baseURI)
                    .header("Accept", "application/vnd.github+json")
                    .auth()
                    .oauth2(token);
        } else {

            return RestAssured
                    .given()
                    .baseUri(baseURI)
                    .header("Accept", "application/vnd.github+json");
        }
    }

    public static RequestSpecification getRequestSpecificationForDeleteRepository() {

        String baseURI = loadProperties(APPLICATION_PROPERTIES_FILE_PATH.getValue()).getProperty("baseUri");

            return RestAssured
                    .given()
                    .baseUri(baseURI)
                    .header("Accept", "application/vnd.github+json")
                    .auth()
                    .oauth2(loadProperties(APPLICATION_PROPERTIES_FILE_PATH.getValue()).getProperty("oauth.delete.token"));
    }
}
