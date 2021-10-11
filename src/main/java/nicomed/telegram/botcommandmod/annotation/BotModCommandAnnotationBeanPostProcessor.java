package nicomed.telegram.botcommandmod.annotation;

import nicomed.telegram.botcommandmod.command.BaseBotCommand;
import nicomed.telegram.botcommandmod.service.CommandService;
import nicomed.telegram.botcommandmod.service.CommandServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BotModCommandAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private ApplicationContext context;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        BotModCommand annotation = bean.getClass().getAnnotation(BotModCommand.class);
        if (annotation != null) {
            CommandService commandService = context.getBean(CommandServiceImpl.class);
            if (commandService != null) {
                if (annotation.isDefault()) {
                    commandService.setDefaultCommand((BaseBotCommand) bean);
                } else {
                    commandService.getCommands().add((BaseBotCommand) bean);
                }
            }
        }
        return bean;
    }
}
