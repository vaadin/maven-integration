#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ui.i18n;

import com.vaadin.ui.UI;
import org.apache.deltaspike.core.api.common.DeltaSpike;
import org.apache.deltaspike.core.api.message.LocaleResolver;

import java.util.Locale;

/**
 * Custom {@link org.apache.deltaspike.core.api.message.LocaleResolver} that returns the locale
 * of the current {@link com.vaadin.ui.UI}, or the default locale if no UI is available.
 */
@DeltaSpike
public class VaadinUILocaleResolver implements LocaleResolver {

    @Override
    public Locale getLocale() {
        final UI ui = UI.getCurrent();
        if (ui == null) {
            return Locale.getDefault();
        } else {
            return ui.getLocale();
        }
    }
}
