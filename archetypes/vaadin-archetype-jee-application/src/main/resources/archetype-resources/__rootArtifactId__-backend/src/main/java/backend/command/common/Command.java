#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.backend.command.common;

import java.io.Serializable;

/**
 * Marker interface for a command. A command carries all the parameters necessary to perform the command.
 *
 * @param <RESULT> the result of the command. If the command does not return anything, {@link Void} can be used.
 */
public interface Command<RESULT> extends Serializable {
    // No methods
}
