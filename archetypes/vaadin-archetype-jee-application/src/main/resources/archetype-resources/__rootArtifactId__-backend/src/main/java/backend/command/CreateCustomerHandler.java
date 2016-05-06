#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.backend.command;

import ${package}.backend.command.common.SingleCommandHandler;
import ${package}.backend.command.common.ForCommand;
import ${package}.backend.entity.Customer;
import ${package}.backend.entity.repo.CustomerRepository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * This command handler is included for demonstrational purposes only. Feel free to remove or change.
 *
 * @see CreateCustomer
 */
@Transactional(Transactional.TxType.MANDATORY)
@Dependent // You can use other scopes as well
@ForCommand(CreateCustomer.class)
class CreateCustomerHandler extends SingleCommandHandler<Customer, CreateCustomer> {

    @Inject
    CustomerRepository customerRepository;

    @Override
    protected Customer doHandle(CreateCustomer command) {
        Customer entity = new Customer();
        entity.setName(command.name);
        return customerRepository.save(entity);
    }
}
