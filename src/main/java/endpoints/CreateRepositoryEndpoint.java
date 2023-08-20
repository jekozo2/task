package endpoints;

import enums.MergeCommitValues;
import enums.SquashMergeCommitValues;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import static enums.Constants.CREATE_REPO_ENDPOINT;
import static enums.Constants.PUBLIC;
import static enums.CreateRepositoryParams.*;
import static utils.RestAssuredUtils.getRequestSpecification;

@Data
@Slf4j
@Builder
@AllArgsConstructor
public class CreateRepositoryEndpoint {

    private String name;
    private String description;
    private String homepage;
    private Boolean isPrivate;
    private Boolean hasIssues;
    private Boolean hasProjects;
    private Boolean hasWiki;
    private Boolean hasPages;
    private Boolean hasDiscussions;
    private Integer teamId;
    private Boolean autoInit;
    private String gitIgnoreTemplate;
    private String licenseTemplate;
    private Boolean allowSquashMerge;
    private Boolean allowMergeCommit;
    private Boolean allowRebaseMerge;
    private Boolean allowAutoMerge;
    private Boolean deleteBranchOnMerge;
    private String squashMergeCommitTitle;
    private String squashMergeCommitMessage;
    private String mergeCommitTitle;
    private String mergeCommitMessage;
    private Boolean hasDownloads;
    private Boolean isTemplate;
    private String body;
    private String visibility;
    private Boolean withToken;


    public Response createRepository() {

        RequestSpecification requestSpecification = getRequestSpecification(withToken);

        if(body == null) {
            setBodyParams();
        }

        return requestSpecification
                .log().all()
                .body(body)
                .when()
                .post(CREATE_REPO_ENDPOINT.getValue());
    }

    private void setBodyParams() {

        JSONObject body = new JSONObject();

        if (name != null) {
            body.put(NAME.getValue(), name);
        }

        if (description != null) {
            body.put(DESCRIPTION.getValue(), description);
        }

        if (homepage != null) {
            body.put(HOMEPAGE.getValue(), homepage);
        }

        if (isPrivate != null) {
            body.put(PRIVATE.getValue(), isPrivate);

            if (isPrivate) {
                visibility = PRIVATE.getValue();
            } else {
                visibility = PUBLIC.getValue();
            }
        } else {
            isPrivate = false;

            visibility = PUBLIC.getValue();
        }


        if (hasIssues != null) {
            body.put(HAS_ISSUES.getValue(), hasIssues);
        } else {
            hasIssues = true;
        }

        if (hasProjects != null) {
            body.put(HAS_PROJECTS.getValue(), hasProjects);
        } else {
            hasProjects = true;
        }

        if (hasWiki != null) {
            body.put(HAS_WIKI.getValue(), hasWiki);
        } else {
            hasWiki = true;
        }

        if (hasPages != null) {
            body.put(HAS_PAGES.getValue(), hasPages);
        } else {
            hasPages = false;
        }

        if (hasDiscussions != null) {
            body.put(HAS_DISCUSSIONS.getValue(), hasDiscussions);
        } else {
            hasDiscussions = false;
        }

        if (teamId != null) {
            body.put(TEAM_ID.getValue(), teamId);
        }

        if (autoInit != null) {
            body.put(AUTO_INIT.getValue(), autoInit);
        } else {
            autoInit = false;
        }

        if (gitIgnoreTemplate != null) {
            body.put(GITIGNORE_TEMPLATE.getValue(), gitIgnoreTemplate);
        }

        if (licenseTemplate != null) {
            body.put(LICENSE_TEMPLATE.getValue(), licenseTemplate);
        }

        if (allowSquashMerge != null) {
            body.put(ALLOW_SQUASH_MERGE.getValue(), allowSquashMerge);
        } else {
            allowSquashMerge = true;
        }

        if (allowMergeCommit != null) {
            body.put(ALLOW_MERGE_COMMIT.getValue(), allowMergeCommit);
        } else {
            allowMergeCommit = true;
        }

        if (allowRebaseMerge != null) {
            body.put(ALLOW_REBASE_MERGE.getValue(), allowRebaseMerge);
        } else {
            allowRebaseMerge = true;
        }

        if (allowAutoMerge != null) {
            body.put(ALLOW_AUTO_MERGE.getValue(), allowAutoMerge);
        } else {
            allowAutoMerge = false;
        }

        if (deleteBranchOnMerge != null) {
            body.put(DELETE_BRANCH_ON_MERGE.getValue(), deleteBranchOnMerge);
        } else {
            deleteBranchOnMerge = false;
        }

        if (squashMergeCommitTitle != null) {
            body.put(SQUASH_MERGE_COMMIT_TITLE.getValue(), squashMergeCommitTitle);
        } else {
            squashMergeCommitTitle = SquashMergeCommitValues.COMMIT_OR_PR_TITLE_AND_COMMIT_MESSAGES.getTitle();
        }

        if (squashMergeCommitMessage != null) {
            body.put(SQUASH_MERGE_COMMIT_MESSAGE.getValue(), squashMergeCommitMessage);
        } else {
            squashMergeCommitMessage = SquashMergeCommitValues.COMMIT_OR_PR_TITLE_AND_COMMIT_MESSAGES.getMessage();
        }

        if (mergeCommitTitle != null) {
            body.put(MERGE_COMMIT_TITLE.getValue(), mergeCommitTitle);
        } else {
            mergeCommitTitle = MergeCommitValues.PR_TITLE_AND_MERGE_MESSAGE.getTitle();
        }

        if (mergeCommitMessage != null) {
            body.put(MERGE_COMMIT_MESSAGE.getValue(), mergeCommitMessage);
        } else {
            mergeCommitMessage = MergeCommitValues.PR_TITLE_AND_MERGE_MESSAGE.getMessage();
        }

        if (hasDownloads != null) {
            body.put(HAS_DOWNLOADS.getValue(), hasDownloads);
        } else {
            hasDownloads = true;
        }

        if (isTemplate != null) {
            body.put(IS_TEMPLATE.getValue(), isTemplate);
        } else {
            isTemplate = false;
        }

        this.body = body.toString();
    }
}
