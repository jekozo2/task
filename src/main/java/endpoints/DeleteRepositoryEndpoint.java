package endpoints;

import lombok.AllArgsConstructor;
import lombok.Builder;

import static utils.RestAssuredUtils.getRequestSpecificationForDeleteRepository;

@Builder
@AllArgsConstructor
public class DeleteRepositoryEndpoint {

    private String repository;

    public void deleteRepository() {

        getRequestSpecificationForDeleteRepository()
                .pathParam("owner", "jekozo2")
                .pathParam("repo", repository)
                .when()
                .delete("repos/{owner}/{repo}");
    }
}
