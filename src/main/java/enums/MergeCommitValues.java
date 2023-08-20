package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@Getter
@AllArgsConstructor
public enum MergeCommitValues {

    PR_TITLE_AND_PR_BODY("PR_TITLE", "PR_BODY"),
    PR_TITLE_AND_BLANK("PR_TITLE", "BLANK"),
    PR_TITLE_AND_MERGE_MESSAGE("MERGE_MESSAGE", "PR_TITLE");
    private final String title;
    private final String message;

    /**
     * The method returns a random enum from the MergeCommitValues enum class
     *
     * @return a random enum from the MergeCommitValues enum class
     */
    public static MergeCommitValues getRandomMergeCommitEnum() {
        final Random random = new Random();

        MergeCommitValues[] enums = MergeCommitValues.class.getEnumConstants();

        int index = random.nextInt(enums.length);

        return enums[index];
    }
}
