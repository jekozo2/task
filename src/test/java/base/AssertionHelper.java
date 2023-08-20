package base;

import endpoints.CreateRepositoryEndpoint;
import enums.*;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import models.ErrorMessageModel;
import models.ErrorsModel;
import org.testng.Assert;

import java.time.LocalDate;
import java.util.List;

import static enums.Constants.*;
import static enums.CreateRepositoryResponseFields.*;
import static enums.ErrorMessages.USER_NOT_AUTHORIZED;
import static org.hamcrest.Matchers.*;
import static stepdefinitions.BaseTestSteps.deleteRepository;

public class AssertionHelper {

    public static void assertRepositoryCreatedSuccessfully(Response response, CreateRepositoryEndpoint createRepositoryEndpoint) {

        try {
            response
                    .then().log().all()
                    .assertThat()
                    .statusCode(HttpStatuses.STATUS_CREATED.getStatusCode())
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JsonSchemaFilePaths.CREATE_REPOSITORY_SCHEMA_FILE_PATH.getSchemaPath()))
                    .body(ID.getValue(), is(notNullValue()))
                    .body(NAME.getValue(), equalTo(createRepositoryEndpoint.getName()))
                    .body(FULL_NAME.getValue(), equalTo(ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName()))
                    .body(CreateRepositoryResponseFields.PRIVATE.getValue(), equalTo(createRepositoryEndpoint.getIsPrivate()))
                    .body(OWNER.getValue() + "." + LOGIN.getValue(), equalTo(ADMIN_USER.getValue()))
                    .body(OWNER.getValue() + "." + NODE_ID.getValue(), is(notNullValue()))
                    .body(OWNER.getValue() + "." + AVATART_URL.getValue(), is(notNullValue()))
                    .body(OWNER.getValue() + "." + GRAVATAR_ID.getValue(), is(notNullValue()))
                    .body(OWNER.getValue() + "." + URL.getValue(), equalTo(BASE_USERS_URL.getValue() + ADMIN_USER.getValue()))
                    .body(OWNER.getValue() + "." + HTML_URL.getValue(), equalTo(BASE_HTML_URL.getValue() + ADMIN_USER.getValue()))
                    .body(OWNER.getValue() + "." + FOLLOWERS_URL.getValue(), equalTo(BASE_USERS_URL.getValue() + ADMIN_USER.getValue() + "/followers"))
                    .body(OWNER.getValue() + "." + FOLLOWING.getValue(), equalTo(BASE_USERS_URL.getValue() + ADMIN_USER.getValue() + "/following{/other_user}"))
                    .body(OWNER.getValue() + "." + GISTS_URL.getValue(), equalTo(BASE_USERS_URL.getValue() + ADMIN_USER.getValue() + "/gists{/gist_id}"))
                    .body(OWNER.getValue() + "." + STARRED_URL.getValue(), equalTo(BASE_USERS_URL.getValue() + ADMIN_USER.getValue() + "/starred{/owner}{/repo}"))
                    .body(OWNER.getValue() + "." + SUBSCRIPTIONS_URL.getValue(), equalTo(BASE_USERS_URL.getValue() + ADMIN_USER.getValue() + "/subscriptions"))
                    .body(OWNER.getValue() + "." + ORGANIZATIONS_URL.getValue(), equalTo(BASE_USERS_URL.getValue() + ADMIN_USER.getValue() + "/orgs"))
                    .body(OWNER.getValue() + "." + REPOS_URL.getValue(), equalTo(BASE_USERS_URL.getValue() + ADMIN_USER.getValue() + "/repos"))
                    .body(OWNER.getValue() + "." + EVENTS_URL.getValue(), equalTo(BASE_USERS_URL.getValue() + ADMIN_USER.getValue() + "/events{/privacy}"))
                    .body(OWNER.getValue() + "." + RECEIVED_EVENTS_URL.getValue(), equalTo(BASE_USERS_URL.getValue() + ADMIN_USER.getValue() + "/received_events"))
                    .body(OWNER.getValue() + "." + TYPE.getValue(), equalTo("User"))
                    .body(OWNER.getValue() + "." + SITE_ADMIN.getValue(), equalTo(false))
                    .body(HTML_URL.getValue(), equalTo(BASE_HTML_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName()))
                    .body(DESCRIPTION.getValue(), equalTo(createRepositoryEndpoint.getDescription()))
                    .body(FORK.getValue(), equalTo(false))
                    .body(URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName()))
                    .body(FORKS_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/forks"))
                    .body(KEYS_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/keys{/key_id}"))
                    .body(COLLABORATORS_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/collaborators{/collaborator}"))
                    .body(TEAMS_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/teams"))
                    .body(HOOKS_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/hooks"))
                    .body(ISSUE_EVENTS_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/issues/events{/number}"))
                    .body(EVENTS_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/events"))
                    .body(ASSIGNEES_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/assignees{/user}"))
                    .body(BRANCHES_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/branches{/branch}"))
                    .body(TAGS_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/tags"))
                    .body(BLOBS_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/git/blobs{/sha}"))
                    .body(GIT_TAGS_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/git/tags{/sha}"))
                    .body(GIT_REFS_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/git/refs{/sha}"))
                    .body(TREES_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/git/trees{/sha}"))
                    .body(STATUSES_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/statuses/{sha}"))
                    .body(LANGUAGES_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/languages"))
                    .body(STARGAZERS_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/stargazers"))
                    .body(CONTRIBUTORS_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/contributors"))
                    .body(SUBSCRIBERS_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/subscribers"))
                    .body(SUBSCRIPTION_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/subscription"))
                    .body(COMMITS_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/commits{/sha}"))
                    .body(GIT_COMMITS_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/git/commits{/sha}"))
                    .body(COMMENTS_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/comments{/number}"))
                    .body(ISSUE_COMMENT_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/issues/comments{/number}"))
                    .body(CONTENTS_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/contents/{+path}"))
                    .body(COMPARE_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/compare/{base}...{head}"))
                    .body(MERGES_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/merges"))
                    .body(ARCHIVE_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/{archive_format}{/ref}"))
                    .body(DOWNLOADS_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/downloads"))
                    .body(ISSUES_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/issues{/number}"))
                    .body(PULLS_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/pulls{/number}"))
                    .body(MILESTONES_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/milestones{/number}"))
                    .body(NOTIFICATIONS_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/notifications{?since,all,participating}"))
                    .body(LABELS_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/labels{/name}"))
                    .body(RELEASES_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/releases{/id}"))
                    .body(DEPLOYMENTS_URL.getValue(), equalTo(BASE_REPOS_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + "/deployments"))
                    .body(CREATED_AT.getValue(), containsString(LocalDate.now().toString()))
                    .body(UPDATED_AT.getValue(), containsString(LocalDate.now().toString()))
                    .body(PUSHED_AT.getValue(), containsString(LocalDate.now().toString()))
                    .body(GIT_URL.getValue(), equalTo(BASE_GIT_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + ".git"))
                    .body(SSH_URL.getValue(), equalTo(BASE_SSH_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + ".git"))
                    .body(CLONE_URL.getValue(), equalTo(BASE_HTML_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName() + ".git"))
                    .body(SVN_URL.getValue(), equalTo(BASE_HTML_URL.getValue() + ADMIN_USER.getValue() + "/" + createRepositoryEndpoint.getName()))
                    .body(HOMEPAGE.getValue(), equalTo(createRepositoryEndpoint.getHomepage()))
                    .body(SIZE.getValue(), equalTo(0))
                    .body(STARGAZERS_COUNT.getValue(), equalTo(0))
                    .body(WATCHERS_COUNT.getValue(), equalTo(0))
                    .body(LANGUAGE.getValue(), is(nullValue()))
                    .body(HAS_ISSUES.getValue(), equalTo(createRepositoryEndpoint.getHasIssues()))
                    .body(HAS_PROJECTS.getValue(), equalTo(createRepositoryEndpoint.getHasProjects()))
                    .body(HAS_DOWNLOADS.getValue(), equalTo(createRepositoryEndpoint.getHasDownloads()))
                    .body(HAS_WIKI.getValue(), equalTo(createRepositoryEndpoint.getHasWiki()))
                    .body(HAS_PAGES.getValue(), equalTo(createRepositoryEndpoint.getHasPages()))
                    .body(HAS_DISCUSSIONS.getValue(), equalTo(createRepositoryEndpoint.getHasDiscussions()))
                    .body(FORKS_COUNT.getValue(), equalTo(0))
                    .body(MIRROR_URL.getValue(), is(nullValue()))
                    .body(ARCHIVED.getValue(), equalTo(false))
                    .body(DISABLED.getValue(), equalTo(false))
                    .body(OPEN_ISSUES_COUNT.getValue(), equalTo(0))
                    .body(LICENSE.getValue(), is(nullValue()))
                    .body(ALLOW_FORKING.getValue(), equalTo(true))
                    .body(IS_TEMPLATE.getValue(), equalTo(createRepositoryEndpoint.getIsTemplate()))
                    .body(WEB_COMMIT_SIGNOFF_REQUIRED.getValue(), equalTo(false))
                    .body(TOPICS.getValue(), hasSize(0))
                    .body(VISIBILITY.getValue(), equalTo(createRepositoryEndpoint.getVisibility()))
                    .body(FORKS.getValue(), equalTo(0))
                    .body(OPEN_ISSUES.getValue(), equalTo(0))
                    .body(WATCHERS.getValue(), equalTo(0))
                    .body(DEFAULT_BRANCH.getValue(), equalTo("main"))
                    .body(PERMISSIONS.getValue() + "." + ADMIN.getValue(), equalTo(true))
                    .body(PERMISSIONS.getValue() + "." + MAINTAIN.getValue(), equalTo(true))
                    .body(PERMISSIONS.getValue() + "." + PUSH.getValue(), equalTo(true))
                    .body(PERMISSIONS.getValue() + "." + TRIAGE.getValue(), equalTo(true))
                    .body(PERMISSIONS.getValue() + "." + PULL.getValue(), equalTo(true))
                    .body(ALLOW_SQUASH_MERGE.getValue(), equalTo(createRepositoryEndpoint.getAllowSquashMerge()))
                    .body(ALLOW_MERGE_COMMIT.getValue(), equalTo(createRepositoryEndpoint.getAllowMergeCommit()))
                    .body(ALLOW_REBASE_MERGE.getValue(), equalTo(createRepositoryEndpoint.getAllowRebaseMerge()))
                    .body(ALLOW_AUTO_MERGE.getValue(), equalTo(createRepositoryEndpoint.getAllowAutoMerge()))
                    .body(DELETE_BRANCH_ON_MERGE.getValue(), equalTo(createRepositoryEndpoint.getDeleteBranchOnMerge()))
                    .body(ALLOW_UPDATE_BRANCH.getValue(), equalTo(false))
                    .body(USE_SQUASH_PR_TITLE_AS_DEFAULT.getValue(), is(notNullValue()))
                    .body(SQUASH_MERGE_COMMIT_MESSAGE.getValue(), equalTo(createRepositoryEndpoint.getSquashMergeCommitMessage()))
                    .body(SQUASH_MERGE_COMMIT_TITLE.getValue(), equalTo(createRepositoryEndpoint.getSquashMergeCommitTitle()))
                    .body(MERGE_COMMIT_MESSAGE.getValue(), equalTo(createRepositoryEndpoint.getMergeCommitMessage()))
                    .body(MERGE_COMMIT_TITLE.getValue(), equalTo(createRepositoryEndpoint.getMergeCommitTitle()))
                    .body(NETWORK_COUNT.getValue(), equalTo(0))
                    .body(SUBSCRIBERS_COUNT.getValue(), is(notNullValue()));
        } finally {
            deleteRepository(createRepositoryEndpoint);
        }
    }

    public static void assertAuthenticationError(Response response) {
        response
                .then()
                .assertThat()
                .statusCode(HttpStatuses.STATUS_UNAUTHORIZED.getStatusCode())
                .body(MESSAGE.getValue(), equalTo(USER_NOT_AUTHORIZED.getErrorMessage()));
    }

    public static void assertErrorMessageFromResponse(Response response, ErrorMessages errorMessage) {

        response
                .then()
                .assertThat()
                .statusCode(HttpStatuses.STATUS_UNPROCESSABLE_ENTITY.getStatusCode())
                .body(MESSAGE.getValue(), equalTo(errorMessage.getMainError()));

        List<String> errorMessages = response.as(ErrorMessageModel.class).getErrors()
                .stream()
                .map(ErrorsModel::getMessage)
                .toList();

        errorMessages.forEach(em -> {
            if (em != null) {
                Assert.assertEquals(em, errorMessage.getErrorMessage());
            }
        });
    }

    public static void assertParamDefaultValue(Response response, CreateRepositoryParams createRepositoryParams, boolean defaultValue, CreateRepositoryEndpoint createRepositoryEndpoint) {
        try{
            response
                    .then()
                    .assertThat()
                     .body(createRepositoryParams.getValue(), equalTo(defaultValue));
        } finally {
            deleteRepository(createRepositoryEndpoint);
        }
    }
}
