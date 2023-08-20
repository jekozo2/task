package enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Constants {

    APPLICATION_PROPERTIES_FILE_PATH("src/main/resources/application.properties"),
    CREATE_REPO_ENDPOINT("/user/repos"),
    ADMIN_USER("jekozo2"),
    BASE_USERS_URL("https://api.github.com/users/"),
    BASE_REPOS_URL("https://api.github.com/repos/"),
    BASE_HTML_URL("https://github.com/"),
    BASE_GIT_URL("git://github.com/"),
    BASE_SSH_URL("git@github.com:"),
    PRIVATE("private"),
    PUBLIC("public"),
    MESSAGE("message"),
    ERRORS("errors"),
    ACTIONSCRIPT("Actionscript"),
    MOZILLA_LICENSE("mpl-2.0");

    private final String value;
}
