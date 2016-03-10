#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.backend.services.impl;

import ${package}.backend.services.api.CustomerService;
import ${package}.backend.entity.Customer;
import ${package}.backend.entity.repo.CustomerRepository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

/**
 * This class is included for demonstrational purposes only. Feel free to remove or change.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class CustomerServiceBean implements CustomerService {

    @Inject
    CustomerRepository customerRepository;

    @Override
    public Customer createAndReturn(String name) {
        Customer entity = new Customer();
        entity.setName(name);
        return customerRepository.save(entity);
    }
}
