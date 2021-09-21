package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Say Hi to the user
 */
public class TrollCommand extends Command {

    public static final String COMMAND_WORD = "troll";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Does nothing except to troll.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "LMAOAOAO.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, false, false);
    }

}
