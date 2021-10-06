package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's education level in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidLevel(String)}
 */
public class Level {

    public static final String MESSAGE_CONSTRAINTS = "Education level should only contain the prefix [p, s, j], "
            + "followed by the year of study eg. p2, s4, j1";
    /*
     * The first character has to be either p, s, j.
     * For p valid years are 1-6
     * For s, valid years are 1-5
     * For j, valid years are 1-2
     */
    public static final String VALIDATION_REGEX_PRIMARY = "[p][1-6]";
    public static final String VALIDATION_REGEX_SECONDARY = "[s][1-5]";
    public static final String VALIDATION_REGEX_JC = "[j][1-2]";

    public final String value;

    public final String index;

    /**
     * Constructs an {@code Level}.
     *
     * @param level A valid education level.
     */
    public Level(String level) {
        requireNonNull(level);
        checkArgument(isValidLevel(level), MESSAGE_CONSTRAINTS);
        index = Level.parse(level);
        value = level;
    }

    /**
     * Returns the full string representation of an indexed education level.
     * The String will be used for UI display.
     *
     * @param index The input education level.
     * @return The education level spelt out in full.
     */
    public static String parse(String index) {
        char[] splitByCharacter = index.toCharArray();
        char firstChar = splitByCharacter[0];
        char secondChar = splitByCharacter[1];

        switch (firstChar) {
        case 'p':
            return "Primary " + secondChar;
        case 's':
            return "Secondary " + secondChar;
        case 'j':
            return "JC " + secondChar;
        default:
            return "Should not happen due to regex validation";
        }
    }

    /**
     * Returns if a given string is a valid education level.
     */
    public static boolean isValidLevel(String test) {
        return test.matches(VALIDATION_REGEX_PRIMARY)
                || test.matches(VALIDATION_REGEX_SECONDARY)
                || test.matches(VALIDATION_REGEX_JC);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Level // instanceof handles nulls
                && value.equals(((Level) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
