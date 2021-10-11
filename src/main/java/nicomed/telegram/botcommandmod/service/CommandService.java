package nicomed.telegram.botcommandmod.service;

import nicomed.telegram.botcommandmod.command.BaseBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Set;


public interface CommandService {

    SendMessage getProcessedMessage(Update update);

    Set<BaseBotCommand> getCommands();

    Set<String> getCommandNames();

    void setDefaultCommand(BaseBotCommand command);

    void setBadCommandMessage(String message);

}
