#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.entity.common;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;

/**
 * Base class for entities.
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;

    /**
     * Gets the ID of this entity.
     *
     * @return the ID or {@code null} if the entity has not been persisted yet.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of this entity. You normally want to let JPA handle this and should not need to set
     * it explicitly.
     *
     * @param id the ID to use.
     */
    protected void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the optimistic locking version of this entity.
     *
     * @return the version or {@code null} if the entity has not been persisted yet.
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the version of this entity. You normally want to let JPA handle this and should not need to set
     * it explicitly.
     *
     * @param version the version to use.
     */
    protected void setVersion(Long version) {
        this.version = version;
    }

    /**
     * Checks if this entity is new (transient) or persistent.
     *
     * @return true if this is a new entity, false if it is persistent.
     */
    public boolean isNew() {
        return id == null;
    }

    /**
     * Two entities are considered equal if they are of the same class and have the same ID.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return id != null && id.equals(((BaseEntity) o).id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return String.format("%s@%x(id: %d, version: %d)", getClass().getName(), System.identityHashCode(this),
                id, version);
    }
}
