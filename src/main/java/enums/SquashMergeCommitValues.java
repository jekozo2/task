package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@Getter
@AllArgsConstructor
public enum SquashMergeCommitValues {

    PR_TITLE_AND_PR_BODY("PR_TITLE", "PR_BODY"),
    PR_TITLE_AND_BLANK("PR_TITLE", "BLANK"),
    PR_TITLE_AND_COMMIT_MESSAGES("PR_TITLE", "COMMIT_MESSAGES"),
    COMMIT_OR_PR_TITLE_AND_COMMIT_MESSAGES("COMMIT_OR_PR_TITLE", "COMMIT_MESSAGES");

    private final String title;
    private final String message;

    /**
     * The method returns a random enum from the SquashCommitValues enum class
     *
     * @return a random enum from the SquashCommitValues enum class
     */
    public static SquashMergeCommitValues getRandomSquashCommitEnum() {
        final Random random = new Random();

        SquashMergeCommitValues[] enums = SquashMergeCommitValues.class.getEnumConstants();

        int index = random.nextInt(enums.length);

        return enums[index];
    }
}
