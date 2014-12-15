#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.samples.backend.data;

public enum Availability {
    COMING("Coming"), AVAILABLE("Available"), DISCONTINUED("Discontinued");

    private final String name;

    private Availability(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
