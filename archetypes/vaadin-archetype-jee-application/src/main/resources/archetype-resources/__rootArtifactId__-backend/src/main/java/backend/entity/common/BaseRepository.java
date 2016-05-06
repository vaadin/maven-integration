package ${package}.backend.entity.common;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.Optional;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Base class for JPA repositories.
 */
public abstract class BaseRepository<E extends BaseEntity> {

    @Inject
    protected EntityManager entityManager;

    private Class<E> entityClass;

    /**
     * Default constructor that tries to determine the entity class by introspection. If this is not possible,
     * subclasses must override {@link #getEntityClass()}.
     */
    @SuppressWarnings("unchecked")
    protected BaseRepository() {
        final Type genericSuperclass = getClass().getGenericSuperclass();
        try {
            entityClass = (Class<E>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        } catch (Exception ex) {
            entityClass = null;
        }
    }

    /**
     * Gets the entity class that is passed to the JPA entity manager when finding entities.
     *
     * @return the entity class (never {@code null}).
     */
    protected Class<E> getEntityClass() {
        if (entityClass == null) {
            throw new UnsupportedOperationException(
                "Unable to determine entity class by introspection, please override getEntityClass()");
        }
        return entityClass;
    };

    /**
     * Saves the specified entity. If the entity is {@link BaseEntity#isNew() new}, it is persisted. Otherwise, it is
     * merged.
     *
     * @see EntityManager#persist(Object)
     * @see EntityManager#merge(Object)
     * @param entity the entity to save (never {@code null}).
     * @return the saved entity which may or may not be the same object instance as the passed in entity (never
     *         {@code null}).
     */
    public E save(E entity) {
        Objects.requireNonNull(entity);
        if (entity.isNew()) {
            entityManager.persist(entity);
        } else {
            entity = entityManager.merge(entity);
        }
        return entity;
    }

    /**
     * Saves the specified entity and flushes the entity manager.
     *
     * @see #save(BaseEntity)
     * @see #flush()
     * @param entity the entity to save (never {@code null}).
     * @return the saved entity which may or may not be the same object instance as the passed in entity (never
     *         {@code null}).
     */
    public E saveAndFlush(E entity) {
        E saved = save(entity);
        flush();
        return saved;
    }

    /**
     * Flushes the entity manager.
     *
     * @see EntityManager#flush()
     */
    public void flush() {
        entityManager.flush();
    }

    /**
     * Finds the entity with the specified ID.
     *
     * @param id the ID of the entity to find (never {@code null}).
     * @return an {@code Optional} containing the entity if it was found or an empty {@code Optional} if it was not.
     */
    public Optional<E> findById(Long id) {
        return Optional.ofNullable(entityManager.find(getEntityClass(), Objects.requireNonNull(id)));
    }

    /**
     * Deletes the specified entity.
     *
     * @param entity the entity to delete (never {@code null}).
     */
    public void delete(E entity) {
        entityManager.remove(Objects.requireNonNull(entity));
    }

    /**
     * Deletes the specified entity and flushes the entity manager.
     *
     * @see #delete(BaseEntity)
     * @see #flush()
     * @param entity the entity to delete (never {@code null}).
     */
    public void deleteAndFlush(E entity) {
        delete(entity);
        flush();
    }
}
