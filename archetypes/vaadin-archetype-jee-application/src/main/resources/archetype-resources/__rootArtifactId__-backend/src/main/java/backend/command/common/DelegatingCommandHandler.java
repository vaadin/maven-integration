#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.backend.command.common;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * An implementation of {@link CommandHandler} that takes any command and tries to look up a handler based on
 * the handler's {@link ForCommand} qualifier using CDI. When clients inject a {@link CommandHandler} without
 * any qualifiers, this is the instance that should be injected.
 */
@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
class DelegatingCommandHandler implements CommandHandler {

    @Inject @Any Instance<CommandHandler> handler;

    @Override
    public <RESULT, CMD extends Command<RESULT>> RESULT handle(CMD command) {
        return handler.select(new ForCommandQualifier(command.getClass())).get().handle(command);
    }
}
