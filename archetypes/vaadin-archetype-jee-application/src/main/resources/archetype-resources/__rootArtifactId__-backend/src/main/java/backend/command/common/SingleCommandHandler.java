#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.backend.command.common;

/**
 * Base class for a {@link CommandHandler} that supports a single {@link Command}. The concrete implementation
 * must remember to also use the {@link ForCommand} annotation, otherwise it will not work.
 *
 * @param <RESULT> the result type of the command.
 * @param <CMD> the command type.
 */
public abstract class SingleCommandHandler<RESULT, CMD extends Command<RESULT>> implements CommandHandler {

    /**
     * Performs the command.
     *
     * @param command the command.
     * @return the result of the command.
     */
    protected abstract RESULT doHandle(CMD command);

    @Override
    @SuppressWarnings("unchecked")
    public <R, C extends Command<R>> R handle(C command) {
        return (R) doHandle((CMD) command);
    }
}
