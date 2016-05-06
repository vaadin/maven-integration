#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.backend.command.common;

import javax.enterprise.util.AnnotationLiteral;

/**
 * Annotation literal for {@link ForCommand}, used by {@link DelegatingCommandHandler}.
 */
class ForCommandQualifier extends AnnotationLiteral<ForCommand> implements ForCommand {

    final Class<? extends Command> value;

    ForCommandQualifier(Class<? extends Command> value) {
        this.value = value;
    }

    @Override
    public Class<? extends Command> value() {
        return value;
    }
}
