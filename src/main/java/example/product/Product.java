package example.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;

record Product(@Id Long id, String name) implements Persistable<Long> {
    /**
     * Returns the id of the entity.
     *
     * @return the id. Can be {@literal null}.
     */
    @Override
    public Long getId() {
        return this.id;
    }

    /**
     * Returns if the {@code Persistable} is new or was persisted already.
     *
     * @return if {@literal true} the object is new.
     */
    @Override
    public boolean isNew() {
        return true;
    }
}
