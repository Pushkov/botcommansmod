package nicomed.telegram.botcommandmod.command;

import static nicomed.telegram.botcommandmod.util.Constatnts.DEFAULT_BAD_COMMAND_MESSAGE;

public class BadCommand extends BaseBotCommand {

    private String message = DEFAULT_BAD_COMMAND_MESSAGE;

    public BadCommand(String command, String description) {
        super(command, description);
    }

    @Override
    public String getMessageText() {
        return message;
    }

    @Override
    public String getMessageText(String text) {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
