package nicomed.telegram.botcommandmod.command;

import lombok.Getter;
import lombok.Setter;
import nicomed.telegram.botcommandmod.service.CommandService;

import java.util.stream.Collectors;

@Getter
@Setter
public class DefaultHelpCommand extends BaseBotCommand {

    private CommandService commandService;

    public DefaultHelpCommand(String command, String description, CommandService commandService) {
        super(command, description);
        this.commandService = commandService;
    }

    @Override
    public String getMessageText() {
        return "Список доступных команд\n"
                + commandService.getCommandNames().stream()
                .sorted()
                .map(s -> "/" + s)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String getMessageText(String text) {
        return getMessageText();
    }
}
