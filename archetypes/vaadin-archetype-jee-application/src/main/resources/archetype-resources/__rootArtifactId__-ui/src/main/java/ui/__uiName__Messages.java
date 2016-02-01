#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ui;

import ${package}.ui.i18n.VaadinUILocaleResolver;
import org.apache.deltaspike.core.api.message.MessageBundle;
import org.apache.deltaspike.core.api.message.MessageContextConfig;
import org.apache.deltaspike.core.api.message.MessageTemplate;

@MessageBundle
@MessageContextConfig(localeResolver = VaadinUILocaleResolver.class)
public interface ${uiName}Messages {

    @MessageTemplate("{ui_content_button_label}")
    String uiContentButtonLabel();
}
