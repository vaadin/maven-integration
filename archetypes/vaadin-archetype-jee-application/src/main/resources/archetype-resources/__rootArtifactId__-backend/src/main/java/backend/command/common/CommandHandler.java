#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.backend.command.common;

/**
 * A command handler performs a {@link Command} and returns its result.
 */
public interface CommandHandler {

    /**
     * Attempts to perform the specified command and return the result. If the command cannot be performed,
     * an exception is thrown (implementation specific).
     *
     * @param command the command to perform.
     * @param <RESULT> the type of the result.
     * @param <CMD> the type of the command.
     * @return the result of the command.
     */
    <RESULT, CMD extends Command<RESULT>> RESULT handle(CMD command);
}
