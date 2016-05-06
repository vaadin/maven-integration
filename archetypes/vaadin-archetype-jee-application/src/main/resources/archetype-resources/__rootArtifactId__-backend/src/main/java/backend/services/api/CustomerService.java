#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.backend.services.api;

import ${package}.backend.entity.Customer;

import javax.ejb.Local;

/**
 * This interface is included for demonstrational purposes only. Feel free to remove or change.
 */
@Local
public interface CustomerService {

    Customer createAndReturn(String name);
}
