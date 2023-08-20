package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CreateRepositoryParams {

    NAME("name"),
    DESCRIPTION("description"),
    HOMEPAGE("homepage"),
    PRIVATE("private"),
    HAS_ISSUES("has_issues"),
    HAS_PROJECTS("has_projects"),
    HAS_WIKI("has_wiki"),
    HAS_PAGES("has_Pages"),
    HAS_DISCUSSIONS("has_discussions"),
    TEAM_ID("team_id"),
    AUTO_INIT("auto_init"),
    GITIGNORE_TEMPLATE("gitignore_template"),
    LICENSE_TEMPLATE("license_template"),
    ALLOW_SQUASH_MERGE("allow_squash_merge"),
    ALLOW_MERGE_COMMIT("allow_merge_commit"),
    ALLOW_REBASE_MERGE("allow_rebase_merge"),
    ALLOW_AUTO_MERGE("allow_auto_merge"),
    DELETE_BRANCH_ON_MERGE("delete_branch_on_merge"),
    SQUASH_MERGE_COMMIT_TITLE("squash_merge_commit_title"),
    SQUASH_MERGE_COMMIT_MESSAGE("squash_merge_commit_message"),
    MERGE_COMMIT_TITLE("merge_commit_title"),
    MERGE_COMMIT_MESSAGE("merge_commit_message"),
    HAS_DOWNLOADS("has_downloads"),
    IS_TEMPLATE("is_template");


    private final String value;
}
