#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import com.vaadin.Application;
import com.vaadin.ui.Button;
import com.vaadin.ui.Window;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class WidgetTestApplication extends Application
{
    private Window window;

    @Override
    public void init()
    {
        window = new Window("Widget Test");
        setMainWindow(window);
        window.addComponent(new MyComponent());
    }
    
}
