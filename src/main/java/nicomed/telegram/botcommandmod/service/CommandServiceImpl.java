package nicomed.telegram.botcommandmod.service;

import com.google.common.base.Utf8;
import lombok.extern.slf4j.Slf4j;
import nicomed.telegram.botcommandmod.command.BadCommand;
import nicomed.telegram.botcommandmod.command.BaseBotCommand;
import nicomed.telegram.botcommandmod.command.DefaultCommand;
import nicomed.telegram.botcommandmod.command.DefaultHelpCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.requireNonNullElse;
import static nicomed.telegram.botcommandmod.util.CommandUtils.getCommandText;
import static nicomed.telegram.botcommandmod.util.CommandUtils.isCommand;
import static nicomed.telegram.botcommandmod.util.Constatnts.DEFAULT_BAD_COMMAND_MESSAGE;
import static nicomed.telegram.botcommandmod.util.Constatnts.DEFAULT_HELP_COMMAND_MESSAGE;

@Slf4j
@Service
public class CommandServiceImpl implements CommandService {

    private BadCommand badCommand;
    private Set<BaseBotCommand> commandSet = new HashSet<>();
    private BaseBotCommand defaultCommand;

    @Value("${botCommandMod.bad.description:Show message if command not registered}")
    private String defaultBadDescription;
    @Value("${botCommandMod.help.description:Showing list registered commands}")
    private String defaultHelpDescription;
    @Value("${botCommandMod.bad.message:Command not registered}")
    private String defaultBadMessage;
    @Value("${botCommandMod.help.message:Commands list}")
    private String defaultHelpMessage;

    @PostConstruct
    private void init() {
        badCommand = new BadCommand("bad", defaultBadDescription);
        badCommand.setMessage( new String(defaultBadMessage.getBytes(), UTF_8));
        defaultCommand = new DefaultCommand();
        DefaultHelpCommand defaultHelpCommand = new DefaultHelpCommand("help", defaultHelpDescription, this);
        defaultHelpCommand.setMessage( new String(defaultHelpMessage.getBytes(), UTF_8));
        commandSet.add(defaultHelpCommand);
    }

    @Override
    public SendMessage getProcessedMessage(Update update) {
        Message receivedMessage = update.getMessage();
        String receivedText = receivedMessage.getText();
        String chatId = receivedMessage.getChatId().toString();
        BaseBotCommand command = getCommand(receivedText);
        String responseText = command.getMessageText(receivedText);
        return new SendMessage(chatId, responseText);
    }

    @Override
    public Set<BaseBotCommand> getCommands() {
        return commandSet;
    }

    @Override
    public Set<String> getCommandNames() {
        return commandSet.stream()
                .map(BotCommand::getCommand)
                .collect(Collectors.toSet());
    }

    @Override
    public void setDefaultCommand(BaseBotCommand command) {
        defaultCommand = Objects.requireNonNullElseGet(command, DefaultCommand::new);
    }

    @Override
    public void setBadCommandMessage(String message) {
        badCommand.setMessage(requireNonNullElse(message, DEFAULT_BAD_COMMAND_MESSAGE));
    }

    private BaseBotCommand getCommand(String commandString) {
        BaseBotCommand command = defaultCommand;
        if (isCommand(commandString)) {
            command = commandSet.stream()
                    .filter(c -> c.getCommand().equalsIgnoreCase(getCommandText(commandString))) //
                    .findFirst().orElse(badCommand);
        }
        return command;
    }
}
