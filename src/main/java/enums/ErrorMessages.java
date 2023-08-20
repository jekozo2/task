package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages {

    REPOSITORY_CREATION_FAIL(null, "Repository creation failed."),
    USER_NOT_AUTHORIZED(null, "Requires authentication"),
    NAME_ALREADY_EXISTS_ON_THIS_ACCOUNT(REPOSITORY_CREATION_FAIL.errorMessage, "name already exists on this account"),
    NAME_TOO_SHORT(REPOSITORY_CREATION_FAIL.errorMessage, "name is too short (minimum is 1 character)"),
    INVALID_SQUASH_MERGE_COMMIT_COMBINATION(REPOSITORY_CREATION_FAIL.errorMessage, "Sorry, invalid setting combination. The following are valid combinations for the squash commit title and message: PR_TITLE and PR_BODY, PR_TITLE and BLANK, PR_TITLE and COMMIT_MESSAGES, COMMIT_OR_PR_TITLE and COMMIT_MESSAGES. (invalid_squash_commit_setting_combo)"),
    INVALID_MERGE_COMMIT_COMBINATION(REPOSITORY_CREATION_FAIL.errorMessage, "Sorry, invalid setting combination. The following are valid combinations for the merge commit title and message: PR_TITLE and PR_BODY, PR_TITLE and BLANK, MERGE_MESAGE and PR_TITLE. (invalid_merge_commit_setting_combo)"),
    INVALID_GITIGNORE_TEMPLATE(REPOSITORY_CREATION_FAIL.errorMessage, "gitignore_template is an unknown gitignore template."),
    INVALID_LICENSE_TEMPLATE(REPOSITORY_CREATION_FAIL.errorMessage, "license_template is an unknown license template.");

    private final String mainError;
    private final String errorMessage;
}
