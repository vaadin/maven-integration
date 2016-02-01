#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * CDI configuration class.
 */
public class CdiConfig {

    /**
     * Makes the {@link javax.persistence.EntityManager} available for CDI injection.
     */
    @Produces
    @Dependent
    @PersistenceContext(unitName = "${rootArtifactId}PU")
    public EntityManager entityManager;
}
