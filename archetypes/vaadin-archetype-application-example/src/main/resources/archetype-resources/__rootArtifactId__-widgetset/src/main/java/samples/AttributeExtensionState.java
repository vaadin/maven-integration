#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.samples;

import com.vaadin.shared.JavaScriptExtensionState;

import java.util.HashMap;

/**
 * Shared state class for {@link AttributeExtension} communication from server
 * to client.
 */
public class AttributeExtensionState extends JavaScriptExtensionState {
    public HashMap<String, String> attributes = new HashMap<String, String>();
}
