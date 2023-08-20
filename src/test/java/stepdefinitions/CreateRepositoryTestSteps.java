package stepdefinitions;

import enums.CreateRepositoryParams;
import enums.ErrorMessages;
import enums.MergeCommitValues;
import enums.SquashMergeCommitValues;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import utils.Helper;

import java.util.List;

import static base.AssertionHelper.*;
import static enums.Constants.ACTIONSCRIPT;
import static enums.Constants.MOZILLA_LICENSE;
import static enums.CreateRepositoryParams.NAME;

@Slf4j
public class CreateRepositoryTestSteps extends BaseTestSteps {

    @Given("User has authentication with rights to create repository")
    public void dummyTestStepWithoutParameters() {
        log.info("Executing Dummy Test Step without parameters.");
    }

    @Given("User does NOT have authentication with rights to create repository")
    public void setUnauthorizedUser() {
        createRepositoryEndpoint.setWithToken(false);
    }

    @When("the User sends POST request to create new repository")
    public void createRepositoryWithOnlyNameSet() {
        createRepositoryEndpoint.setName(faker.lorem().characters(Helper.getRandomNumber(10, 20)));

        response = createRepositoryEndpoint.createRepository();
    }

    @When("the User uses the same repository name to create another repository")
    public void createRepositoryWithExistingName() {
        response = createRepositoryEndpoint.createRepository();
    }

    @When("the User sends POST request to create new repository by setting all valid params")
    public void createRepositoryWithAllValuesSet() {
        setAllCreateRepositoryFields(true);

        response = createRepositoryEndpoint.createRepository();
    }

    @When("the User sets the {createRepositoryParam} as {string}")
    public void createRepositoryWithInvalidNonBooleanParam(CreateRepositoryParams createRepositoryParam, String value) {
        if (createRepositoryParam != NAME) {
            createRepositoryEndpoint.setName(faker.lorem().characters(Helper.getRandomNumber(10, 20)));
        }

        setSpecificCreateRepositoryParam(createRepositoryParam, value);

        response = createRepositoryEndpoint.createRepository();
    }

    @When("the User sets the boolean {createRepositoryParam} as {string} {string}")
    public void createRepositoryWithInvalidBooleanParam(CreateRepositoryParams createRepositoryParam, String description, String value) {

        String name = faker.lorem().characters(Helper.getRandomNumber(10, 20));
        createRepositoryEndpoint.setName(name);

        JSONObject body = new JSONObject();
        body.put(NAME.getValue(), name);

        if(description.contains("String")) {
            body.put(createRepositoryParam.getValue(), value);
        } else if(description.contains("Integer")) {
            body.put(createRepositoryParam.getValue(), Integer.parseInt(value));
        } else if(description.contains("List")) {
            body.put(createRepositoryParam.getValue(), List.of(value));
        }

        createRepositoryEndpoint.setBody(body.toString());

    response =createRepositoryEndpoint.createRepository();
}

    @Then("the repository is successfully created")
    public void assertCreateRepository() {
        assertRepositoryCreatedSuccessfully(response, createRepositoryEndpoint);
    }

    @Then("the repository is successfully created with {createRepositoryParam} set to its default value {booleanValue}")
    public void assertParamSetToDefaultValue(CreateRepositoryParams createRepositoryParams, boolean defaultValue) {
        assertParamDefaultValue(response, createRepositoryParams, defaultValue, createRepositoryEndpoint);
    }

    @Then("authentication error message is returned")
    public void assertAuthenticationErrorMessageIsReturned() {
        assertAuthenticationError(response);
    }

    @Then("error message is returned {errorMessage}")
    public void assertErrorMessage(ErrorMessages errorMessage) {
        assertErrorMessageFromResponse(response, errorMessage);
    }

    private void setSpecificCreateRepositoryParam(CreateRepositoryParams createRepositoryParam, String value) {

        if (value.equals("null")) {
            value = null;
        }

        switch (createRepositoryParam) {
            case NAME -> createRepositoryEndpoint.setName(value);
            case ALLOW_SQUASH_MERGE -> createRepositoryEndpoint.setAllowSquashMerge(Boolean.parseBoolean(value));
            case SQUASH_MERGE_COMMIT_TITLE -> {
                createRepositoryEndpoint.setSquashMergeCommitTitle(value);
                createRepositoryEndpoint.setSquashMergeCommitMessage(SquashMergeCommitValues.PR_TITLE_AND_PR_BODY.getMessage());
            }
            case SQUASH_MERGE_COMMIT_MESSAGE -> {
                createRepositoryEndpoint.setSquashMergeCommitTitle(SquashMergeCommitValues.COMMIT_OR_PR_TITLE_AND_COMMIT_MESSAGES.getTitle());
                createRepositoryEndpoint.setSquashMergeCommitMessage(value);
            }
            case MERGE_COMMIT_TITLE -> {
                createRepositoryEndpoint.setMergeCommitTitle(value);
                createRepositoryEndpoint.setMergeCommitMessage(MergeCommitValues.PR_TITLE_AND_BLANK.getMessage());
            }
            case MERGE_COMMIT_MESSAGE -> {
                createRepositoryEndpoint.setMergeCommitTitle(MergeCommitValues.PR_TITLE_AND_MERGE_MESSAGE.getMessage());
                createRepositoryEndpoint.setMergeCommitMessage(value);
            }
            case GITIGNORE_TEMPLATE -> createRepositoryEndpoint.setGitIgnoreTemplate(value);
            case LICENSE_TEMPLATE -> createRepositoryEndpoint.setLicenseTemplate(value);
        }
    }

    private void setAllCreateRepositoryFields(boolean isNameSet) {
        if (isNameSet) {
            createRepositoryEndpoint.setName(faker.lorem().characters(Helper.getRandomNumber(10, 20)));
        }

        createRepositoryEndpoint.setDescription(faker.lorem().characters(Helper.getRandomNumber(20, 60)));
        createRepositoryEndpoint.setHomepage(faker.internet().url());
        createRepositoryEndpoint.setIsPrivate(Helper.getRandomBoolean());
        createRepositoryEndpoint.setHasIssues(Helper.getRandomBoolean());
        createRepositoryEndpoint.setHasProjects(Helper.getRandomBoolean());
        createRepositoryEndpoint.setHasWiki(false);
        createRepositoryEndpoint.setHasDiscussions(Helper.getRandomBoolean());
        createRepositoryEndpoint.setTeamId(faker.number().numberBetween(8, 15));
        createRepositoryEndpoint.setAutoInit(Helper.getRandomBoolean());
        createRepositoryEndpoint.setGitIgnoreTemplate(ACTIONSCRIPT.getValue());
        createRepositoryEndpoint.setLicenseTemplate(MOZILLA_LICENSE.getValue());
        createRepositoryEndpoint.setAllowSquashMerge(true);
        createRepositoryEndpoint.setAllowMergeCommit(true);
        createRepositoryEndpoint.setAllowRebaseMerge(Helper.getRandomBoolean());
        createRepositoryEndpoint.setAllowAutoMerge(false);
        createRepositoryEndpoint.setDeleteBranchOnMerge(Helper.getRandomBoolean());

        SquashMergeCommitValues squashMergeCommitValue = SquashMergeCommitValues.getRandomSquashCommitEnum();
        createRepositoryEndpoint.setSquashMergeCommitTitle(squashMergeCommitValue.getTitle());
        createRepositoryEndpoint.setSquashMergeCommitMessage(squashMergeCommitValue.getMessage());

        MergeCommitValues mergeCommitValue = MergeCommitValues.getRandomMergeCommitEnum();
        createRepositoryEndpoint.setMergeCommitTitle(mergeCommitValue.getTitle());
        createRepositoryEndpoint.setMergeCommitMessage(mergeCommitValue.getMessage());
        createRepositoryEndpoint.setHasDownloads(Helper.getRandomBoolean());
        createRepositoryEndpoint.setIsTemplate(Helper.getRandomBoolean());

    }

    @ParameterType(".*")
    public CreateRepositoryParams createRepositoryParam(String createRepositoryParam) {
        return CreateRepositoryParams.valueOf(createRepositoryParam);
    }

    @ParameterType(".*")
    public ErrorMessages errorMessage(String errorMessage) {
        return ErrorMessages.valueOf(errorMessage);
    }

    @ParameterType(value = "true|True|TRUE|false|False|FALSE")
    public Boolean booleanValue(String value) {
        return Boolean.valueOf(value);
    }
}
