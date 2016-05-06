#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.backend.entity;

import ${package}.backend.entity.common.BaseEntity;

import java.util.Objects;

import javax.persistence.Entity;

/**
 * This class is included for demonstrational purposes only. Feel free to remove or change.
 */
@Entity
public class Customer extends BaseEntity {

    private String name = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name);
    }
}
