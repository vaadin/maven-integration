#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.backend.command;

import ${package}.backend.command.common.Command;
import ${package}.backend.entity.Customer;

/**
 * This command is included for demonstrational purposes only. Feel free to remove or change.
 *
 * @see CreateCustomerHandler
 */
public class CreateCustomer implements Command<Customer> {

    final String name;

    public CreateCustomer(String name) {
        this.name = name;
    }
}
