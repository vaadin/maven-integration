#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import javax.servlet.annotation.WebServlet;

import ${package}.backend.CrudService;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 */
@Theme("${themeName}")
@Widgetset("com.vaadin.v7.Vaadin7WidgetSet")
public class ${uiName} extends UI {

    private CrudService<Person> service = new CrudService<>();
    private BeanItemContainer<Person> dataSource = new BeanItemContainer<Person>(Person.class);

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener(e -> {
            service.save(new Person(name.getValue()));
            dataSource.removeAllItems();
            dataSource.addAll(service.findAll());
        });

        Grid grid = new Grid(dataSource);
        grid.setSizeFull();

        // This is a component from the ${rootArtifactId}-addon module
        //layout.addComponent(new MyComponent());
        layout.addComponents(name, button, grid);
        layout.setSizeFull();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.setExpandRatio(grid, 1.0f);

        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "${uiName}Servlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = ${uiName}.class, productionMode = false)
    public static class ${uiName}Servlet extends VaadinServlet {
    }
}
