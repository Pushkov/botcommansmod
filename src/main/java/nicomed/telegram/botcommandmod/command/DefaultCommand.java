package nicomed.telegram.botcommandmod.command;

import lombok.Getter;
import lombok.Setter;
import nicomed.telegram.botcommandmod.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class DefaultCommand extends BaseBotCommand {

    @Autowired
    private CommandService commandService;

    public DefaultCommand() {
        super("default", "Return received text");
    }


    @Override
    public String getMessageText() {
        return "";
    }

    @Override
    public String getMessageText(String text) {
        return text;
    }


}
