Feature: Create Repository Negative Tests

  @smoke @regression
  Scenario: Repository NOT created by Unauthenticated User
    Given User does NOT have authentication with rights to create repository
    When the User sends POST request to create new repository
    Then authentication error message is returned

  @smoke @regression
  Scenario: Repository NOT created when Repository name already exists
    Given User has authentication with rights to create repository
    And the User sends POST request to create new repository
    When the User uses the same repository name to create another repository
    Then error message is returned NAME_ALREADY_EXISTS_ON_THIS_ACCOUNT

  @smoke @regression
  Scenario: Repository NOT created when Repository name NOT provided
    Given User has authentication with rights to create repository
    When the User sets the NAME as 'null'
    Then error message is returned NAME_TOO_SHORT

  @regression
  Scenario Outline: Repository NOT created with invalid combination of Squash Merge Commit Title and Message <param>
    Given User has authentication with rights to create repository
    When the User sets the <param> as '<value>'
    Then error message is returned INVALID_SQUASH_MERGE_COMMIT_COMBINATION
    Examples:
      | param                       | value              |
      | SQUASH_MERGE_COMMIT_TITLE   | COMMIT_OR_PR_TITLE |
      | SQUASH_MERGE_COMMIT_MESSAGE | PR_BODY            |

  @regression
  Scenario Outline: Repository NOT created with invalid combination of Merge Commit Title and Message <param>
    Given User has authentication with rights to create repository
    When the User sets the <param> as '<value>'
    Then error message is returned INVALID_MERGE_COMMIT_COMBINATION
    Examples:
      | param                | value         |
      | MERGE_COMMIT_TITLE   | MERGE_MESSAGE |
      | MERGE_COMMIT_MESSAGE | PR_TITLE      |

  @regression
  Scenario Outline: Repository NOT created with invalid value for <param>
    Given User has authentication with rights to create repository
    When the User sets the <param> as '<value>'
    Then error message is returned <error_message>
    Examples:
      | param              | value            | error_message              |
      | GITIGNORE_TEMPLATE | invalid_template | INVALID_GITIGNORE_TEMPLATE |
      | LICENSE_TEMPLATE   | invalid template | INVALID_LICENSE_TEMPLATE   |






