#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.backend.entity.repo;

import ${package}.backend.entity.Customer;
import ${package}.backend.entity.common.BaseRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

/**
 * This class is included for demonstrational purposes only. Feel free to remove or change.
 */
@Transactional(Transactional.TxType.MANDATORY)
@ApplicationScoped
public class CustomerRepository extends BaseRepository<Customer> {
}
