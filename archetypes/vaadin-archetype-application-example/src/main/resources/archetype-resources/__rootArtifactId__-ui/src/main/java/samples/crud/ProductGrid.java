#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.samples.crud;

import java.util.Collection;
import java.util.Locale;

import ${package}.samples.backend.data.Availability;
import ${package}.samples.backend.data.Product;

import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.MethodProperty;
import com.vaadin.v7.data.util.converter.Converter;
import com.vaadin.v7.data.util.converter.StringToEnumConverter;
import com.vaadin.v7.data.util.converter.StringToIntegerConverter;
import com.vaadin.v7.data.util.filter.Or;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.server.FontAwesome;
import com.vaadin.v7.ui.Grid;
import com.vaadin.v7.ui.renderers.HtmlRenderer;

/**
 * Grid of products, handling the visual presentation and filtering of a set of
 * items. This version uses an in-memory data source that is suitable for small
 * data sets.
 */
public class ProductGrid extends Grid {

    private StringToEnumConverter availabilityConverter = new StringToEnumConverter() {
        @Override
        public String convertToPresentation(Enum availability,
                java.lang.Class<? extends String> targetType, Locale locale)
                throws Converter.ConversionException {
            String text = super.convertToPresentation(availability, targetType,
                    locale);

            String color = "";
            if (availability == Availability.AVAILABLE) {
                color = "${symbol_pound}2dd085";
            } else if (availability == Availability.COMING) {
                color = "${symbol_pound}ffc66e";
            } else if (availability == Availability.DISCONTINUED) {
                color = "${symbol_pound}f54993";
            }

            String iconCode = "<span class=${symbol_escape}"v-icon${symbol_escape}" style=${symbol_escape}"font-family: "
                    + FontAwesome.CIRCLE.getFontFamily() + ";color:" + color
                    + "${symbol_escape}">&${symbol_pound}x"
                    + Integer.toHexString(FontAwesome.CIRCLE.getCodepoint())
                    + ";</span>";

            return iconCode + " " + text;
        };
    };

    public ProductGrid() {
        setSizeFull();

        setSelectionMode(SelectionMode.SINGLE);

        BeanItemContainer<Product> container = new BeanItemContainer<Product>(
                Product.class);
        setContainerDataSource(container);
        setColumnOrder("id", "productName", "price", "availability",
                "stockCount", "category");

        // Show empty stock as "-"
        getColumn("stockCount").setConverter(new StringToIntegerConverter() {
            @Override
            public String convertToPresentation(Integer value,
                    java.lang.Class<? extends String> targetType, Locale locale)
                    throws Converter.ConversionException {
                if (value == 0) {
                    return "-";
                }

                return super.convertToPresentation(value, targetType, locale);
            };
        });

        // Add an traffic light icon in front of availability
        getColumn("availability").setConverter(availabilityConverter)
                .setRenderer(new HtmlRenderer());

        // Add " €" automatically after price
        getColumn("price").setConverter(new EuroConverter());

        // Show categories as a comma separated list
        getColumn("category").setConverter(new CollectionToStringConverter());

        // Align columns using a style generator and theme rule until ${symbol_pound}15438
        setCellStyleGenerator(new CellStyleGenerator() {

            @Override
            public String getStyle(CellReference cellReference) {
                if (cellReference.getPropertyId().equals("price")
                        || cellReference.getPropertyId().equals("stockCount")) {
                    return "align-right";
                }
                return null;
            }
        });
    }

    /**
     * Filter the grid based on a search string that is searched for in the
     * product name, availability and category columns.
     *
     * @param filterString
     *            string to look for
     */
    public void setFilter(String filterString) {
        getContainer().removeAllContainerFilters();
        if (filterString.length() > 0) {
            SimpleStringFilter nameFilter = new SimpleStringFilter(
                    "productName", filterString, true, false);
            SimpleStringFilter availabilityFilter = new SimpleStringFilter(
                    "availability", filterString, true, false);
            SimpleStringFilter categoryFilter = new SimpleStringFilter(
                    "category", filterString, true, false);
            getContainer().addContainerFilter(
                    new Or(nameFilter, availabilityFilter, categoryFilter));
        }

    }

    private BeanItemContainer<Product> getContainer() {
        return (BeanItemContainer<Product>) super.getContainerDataSource();
    }

    @Override
    public Product getSelectedRow() throws IllegalStateException {
        return (Product) super.getSelectedRow();
    }

    public void setProducts(Collection<Product> products) {
        getContainer().removeAllItems();
        getContainer().addAll(products);
    }

    public void refresh(Product product) {
        // We avoid updating the whole table through the backend here so we can
        // get a partial update for the grid
        BeanItem<Product> item = getContainer().getItem(product);
        if (item != null) {
            // Updated product
            MethodProperty p = (MethodProperty) item.getItemProperty("id");
            p.fireValueChange();
        } else {
            // New product
            getContainer().addBean(product);
        }
    }

    public void remove(Product product) {
        getContainer().removeItem(product);
    }
}
