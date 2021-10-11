### BotCommandMod 
Библиотека для создания и использования команд в приложении созданном с помощью Spring-framework. 


#### Создание класса команды
Класс команды наследуется от абстрактного класса <b><i>BaseBotCommand</i></b> и помечается аннотацией <b><i>@BotModCommand</i></b>.
######
Аннотация имеет параметр <b><i>isDefault</i></b> - значение по умолчанию - <b><i>false</i></b>
######
При значении <b><i>isDefault = true</i></b> команда регистрируется, как команда по умолчанию, и, исполняется, когда боту передается сообщение не содержащее никакой команды. ( т.е. не начинается с символа / ).
######
Если при регистрации ни одна из команд не была зарегистрирована как по умолчанию, то бот просто возвращает пользователю его сообщение. 
#### Создание класса команды HELP
Если, при создании бота, не будет зарегистрирована команда <b><i>help</i></b>, то бот использует команду <b><i>help</i></b> по умолчанию, которая возвращает пользователю список зарегистрированных команд. 


в файл <b><i>application.properties</i></b>

#### Добавить в соответсвующие секции POM.XML:

        <repository>
            <id>packagecloubd-AndreyPushkov</id>
            <url>https://packagecloud.io/AndreyPushkov/snapshot/maven2</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>
######

        <dependency>
            <groupId>nicomed.telegram</groupId>
            <artifactId>botcommandmod</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>        
##### Класс Вашего Spring приложения или бота пометить аннотацией:
        @Import(BotCommandMod.class)   

