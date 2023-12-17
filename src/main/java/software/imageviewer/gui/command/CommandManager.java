package software.imageviewer.gui.command;

public interface CommandManager {
    CommandManager add(Class<? extends Command> commandClass, Command command);
    void execute(Class<? extends Command> commandClass);
}
