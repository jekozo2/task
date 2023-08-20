Feature: Create Repository Positive Tests


  @smoke @regression
  Scenario: Repository successfully created by Authenticated User with only 'name' set
    Given User has authentication with rights to create repository
    When the User sends POST request to create new repository
    Then the repository is successfully created

  @smoke @regression
  Scenario: Repository successfully created by Authenticated User with all params set
    Given User has authentication with rights to create repository
    When the User sends POST request to create new repository by setting all valid params
    Then the repository is successfully created

  @regression
  Scenario Outline: Create repository uses default value of '<default_value>'when the request is set with invalid value for <param> param as <description>
    Given User has authentication with rights to create repository
    When the User sets the boolean <param> as '<description>' '<value>'
    Then the repository is successfully created with <param> set to its default value <default_value>
    Examples:
      | param                  | description   | value | default_value |
#      | PRIVATE                | String value  | text | true          |
#      | PRIVATE                | Integer value | 3     | true          |
#      | PRIVATE                | List value    | false | true          |
#      | HAS_ISSUES             | String value  | text | true          |
#      | HAS_ISSUES             | Integer value | -5    | true          |
#      | HAS_ISSUES             | List value    | false | true          |
#      | HAS_PROJECTS           | String value  | false | true          |
#      | HAS_PROJECTS           | Integer value | 100   | true          |
#      | HAS_PROJECTS           | List value    | false | true          |
#      | HAS_WIKI               | String value  | text | true          |
#      | HAS_WIKI               | Integer value | 20     | true          |
#      | HAS_WIKI               | List value    | false | true          |
#      | HAS_DISCUSSIONS        | String value  | text  | true         |
#      | HAS_DISCUSSIONS        | Integer value | 10    | true         |
#      | HAS_DISCUSSIONS        | List value    | false  | true         |
      | ALLOW_SQUASH_MERGE     | String value  | text  | true          |
      | ALLOW_SQUASH_MERGE     | Integer value | 4     | true          |
      | ALLOW_SQUASH_MERGE     | List value    | false | true          |
      | ALLOW_MERGE_COMMIT     | String value  | test  | true          |
      | ALLOW_MERGE_COMMIT     | Integer value | 12    | true          |
      | ALLOW_MERGE_COMMIT     | List value    | false | true          |
      | ALLOW_REBASE_MERGE     | String value  | text  | true          |
      | ALLOW_REBASE_MERGE     | Integer value | 33    | true          |
      | ALLOW_REBASE_MERGE     | List value    | false | true          |
      | ALLOW_AUTO_MERGE       | String value  | text  | true          |
      | ALLOW_AUTO_MERGE       | Integer value | 11    | true          |
      | ALLOW_AUTO_MERGE       | List value    | true  | true          |
      | DELETE_BRANCH_ON_MERGE | String value  | text  | true          |
      | DELETE_BRANCH_ON_MERGE | Integer value | -1    | true          |
      | DELETE_BRANCH_ON_MERGE | List value    | true  | true          |
      | HAS_DOWNLOADS          | String value  | text  | false         |
      | HAS_DOWNLOADS          | Integer value | 03    | false         |
      | HAS_DOWNLOADS          | List value    | false | false         |
      | IS_TEMPLATE            | String value  | text  | true          |
      | IS_TEMPLATE            | Integer value | 25    | true          |
      | IS_TEMPLATE            | List value    | false | true          |


