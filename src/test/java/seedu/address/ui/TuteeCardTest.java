package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTutees.BENSON;
import static seedu.address.testutil.TypicalTutees.TUTEE_WITHOUT_TAGS_AND_LESSONS;

import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import seedu.address.model.lesson.Subject;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutee.Tutee;


public class TuteeCardTest {

    private static final int INVALID_INDEX = -1;
    private static final int VALID_INDEX = 1;

    private static final Tutee VALID_TUTEE = BENSON;
    private static final Tutee VALID_TUTEE_NO_TAGS = TUTEE_WITHOUT_TAGS_AND_LESSONS;

    // Singleton pattern ensures no multiple initialisations of JavaFX toolkit
    private static boolean isJavaFxCompatible = true;

    // Solution to initialise JavaFX toolkit below adapted from https://stackoverflow.com/a/38883519
    @Test
    public void constructor_invalidTutee_throwsAssertionError() {
        if (isJavaFxCompatible && initToolkitSuccess()) {
            assertThrows(AssertionError.class, () -> new TuteeCard(null, VALID_INDEX));
        }
    }

    @Test
    public void constructor_invalidIndex_throwsAssertionError() {
        if (isJavaFxCompatible && initToolkitSuccess()) {
            assertThrows(AssertionError.class, () -> new TuteeCard(VALID_TUTEE, INVALID_INDEX));
        }
    }

    @Test
    public void constructor_validInput_success() {
        if (isJavaFxCompatible && initToolkitSuccess()) {
            TuteeCard validTuteeCard = new TuteeCard(VALID_TUTEE, VALID_INDEX);
        }
    }

    @Test
    public void addSubjectToTagTest_nullSubject_throwsNullPointerException() {
        if (isJavaFxCompatible && initToolkitSuccess()) {
            assertThrows(NullPointerException.class, () ->
                    new TuteeCard(VALID_TUTEE, VALID_INDEX).addSubjectToTag(null));
        }
    }

    @Test
    public void addSubjectToTagTest_invalidSubject_throwsIllegalArgumentException() {
        if (isJavaFxCompatible && initToolkitSuccess()) {
            assertThrows(IllegalArgumentException.class, () -> new TuteeCard(VALID_TUTEE, VALID_INDEX)
                    .addSubjectToTag(new Subject("math*")));
        }
    }

    @Test
    public void addSubjectToTagTest_validSubject_success() {
        if (isJavaFxCompatible && initToolkitSuccess()) {
            TuteeCard validTuteeCard = new TuteeCard(VALID_TUTEE_NO_TAGS, VALID_INDEX);
            Subject validSubject = new Subject("Math");
            validTuteeCard.addSubjectToTag(validSubject);

            Subject validSubject2 = new Subject("Science");
            validTuteeCard.addSubjectToTag(validSubject2);

            List<Tag> subjectsAsTags = validTuteeCard.getTags();
            assertEquals(validSubject.toString(), subjectsAsTags.get(0).tagName);
            assertEquals(validSubject2.toString(), subjectsAsTags.get(1).tagName);
        }
    }

    private boolean initToolkitSuccess() {
        try {
            Platform.startup(() -> {});
            isJavaFxCompatible = true;
            return true;
        } catch (UnsupportedOperationException | IllegalStateException e) {
            if (e instanceof UnsupportedOperationException) {
                isJavaFxCompatible = false;
                return false; // build-Ubuntu cannot run Platform.startup
            } else {
                isJavaFxCompatible = true;
                return true;
            }
        }
    }
}