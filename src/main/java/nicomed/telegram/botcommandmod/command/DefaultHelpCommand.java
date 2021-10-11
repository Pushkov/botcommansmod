package nicomed.telegram.botcommandmod.command;

import lombok.Getter;
import lombok.Setter;
import nicomed.telegram.botcommandmod.service.CommandService;

import java.util.stream.Collectors;

import static nicomed.telegram.botcommandmod.util.Constatnts.DEFAULT_HELP_COMMAND_MESSAGE;

@Getter
@Setter
public class DefaultHelpCommand extends BaseBotCommand {

    private String message = DEFAULT_HELP_COMMAND_MESSAGE;

    private CommandService commandService;

    public DefaultHelpCommand(String command, String description, CommandService commandService) {
        super(command, description);
        this.commandService = commandService;
    }

    @Override
    public String getMessageText() {
        return  message + ":\n"
                + commandService.getCommandNames().stream()
                .sorted()
                .map(s -> "/" + s)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String getMessageText(String text) {
        return getMessageText();
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
