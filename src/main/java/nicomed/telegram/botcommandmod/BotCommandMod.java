package nicomed.telegram.botcommandmod;

import nicomed.telegram.botcommandmod.command.BaseBotCommand;
import nicomed.telegram.botcommandmod.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Set;

@Configuration
@ComponentScan(basePackages = "nicomed.telegram.botcommandmod")
public class BotCommandMod {

    @Autowired
    private CommandService commandService;

    public SendMessage getSendMessage(Update update) {
        return commandService.getProcessedMessage(update);
    }

    private Set<BaseBotCommand> getCommands() {
        return commandService.getCommands();
    }
}
