package stepdefinitions;

import com.github.javafaker.Faker;
import endpoints.CreateRepositoryEndpoint;
import endpoints.DeleteRepositoryEndpoint;
import io.restassured.response.Response;

public class BaseTestSteps {

    protected CreateRepositoryEndpoint createRepositoryEndpoint = CreateRepositoryEndpoint.builder().build();

    protected Faker faker = new Faker();
    protected Response response;

    public static void deleteRepository(CreateRepositoryEndpoint createRepositoryEndpoint) {
        DeleteRepositoryEndpoint deleteRepositoryEndpoint = DeleteRepositoryEndpoint.builder().repository(createRepositoryEndpoint.getName()).build();
        deleteRepositoryEndpoint.deleteRepository();
    }

}
