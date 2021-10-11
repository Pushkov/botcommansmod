package nicomed.telegram.botcommandmod.util;

import lombok.extern.slf4j.Slf4j;

import static nicomed.telegram.botcommandmod.util.Constatnts.COMMAND_PREFIX;

@Slf4j
public class CommandUtils {

    public static String getCommandText(String text) {
        return text.strip().split(" ")[0].substring(1);
    }

    public static boolean isCommand(String text) {
        return text.stripLeading().startsWith(COMMAND_PREFIX);
    }

}
