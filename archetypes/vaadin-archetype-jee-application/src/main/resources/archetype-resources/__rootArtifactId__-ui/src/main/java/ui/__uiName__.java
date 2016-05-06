#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ui;

import ${package}.backend.command.common.CommandHandler;
import ${package}.backend.command.CreateCustomer;
import ${package}.backend.entity.Customer;
import ${package}.backend.services.api.CustomerService;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@CDIUI("")
@Push
@Theme(${uiName}Theme.THEME_NAME)
@Widgetset("${package}.${widgetsetName}")
public class ${uiName} extends UI {

    private static final Logger LOGGER = LoggerFactory.getLogger(${uiName}.class);

    @Inject
    ${uiName}Messages messages;

    @Inject
    CustomerService customerService;

    @Inject
    CommandHandler commandHandler;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(new Button(messages.uiContentButtonLabel(), this::createCustomer));
        LOGGER.info("UI {} initialized", this);
    }

    private void createCustomer(Button.ClickEvent event) {
        // This code demonstrates how to invoke a service based and a command based backend, respectively.
        // In a real world application, you probably want to pick one approach only and stick to it.
        Customer byService = customerService.createAndReturn("Created By Service");
        Customer byCommand = commandHandler.handle(new CreateCustomer("Created By Command"));
        Notification.show(String.format("%s %s", byService, byCommand));
    }
}
