#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import ${package}.ui.*;
import ${package}.gwt.client.*;

import com.vaadin.addon.touchkit.extensions.OfflineMode;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.TabBarView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.UI;

/**
 * The UI's "main" class
 */
@SuppressWarnings("serial")
@Widgetset("${package}.gwt.${ApplicationName}WidgetSet")
@Theme("touchkit")
public class ${ApplicationName}TouchKitUI extends UI {

    private final ${ApplicationName}PersistToServerRpc serverRpc = new ${ApplicationName}PersistToServerRpc() {
        @Override
        public void persistToServer() {
            // TODO this method is called from client side to store offline data
        }
    };

    @Override
    protected void init(VaadinRequest request) {
        final TabBarView tabBarView = new TabBarView();
        final NavigationManager navigationManager = new NavigationManager();
        navigationManager.setCaption("Tab 1");
        navigationManager.setCurrentComponent(new MenuView());
        Tab tab;
        tab = tabBarView.addTab(navigationManager);
        tab.setIcon(FontAwesome.BOOK);
        tab = tabBarView.addTab(new Label("Tab 2"), "Tab 2");
        tab.setIcon(FontAwesome.AMBULANCE);
        tab = tabBarView.addTab(new Label("Tab 3"), "Tab 3");
        tab.setIcon(FontAwesome.DOWNLOAD);
        setContent(tabBarView);

        // to use the OfflineMode connector is optional.
        OfflineMode offlineMode = new OfflineMode();
        offlineMode.extend(this);
        offlineMode.setPersistentSessionCookie(true);
        offlineMode.setOfflineModeEnabled(true);
        offlineMode.setOfflineModeTimeout(15);
    }
}

