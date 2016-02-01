#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ui;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.cdi.CDIUI;
import ${package}.boundary.SampleBoundary;
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
    SampleBoundary boundary;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(new Button(messages.uiContentButtonLabel(), this::invokeBoundary));
        LOGGER.info("UI {} initialized", this);
    }

    private void invokeBoundary(Button.ClickEvent event) {
        Notification.show(boundary.createAndReturn("Foobar!").toString());
    }
}
