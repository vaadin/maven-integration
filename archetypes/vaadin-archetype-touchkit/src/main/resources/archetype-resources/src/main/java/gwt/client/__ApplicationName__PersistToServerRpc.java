#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.gwt.client;

import com.vaadin.shared.communication.ServerRpc;

public interface ${ApplicationName}PersistToServerRpc extends ServerRpc {
    void persistToServer();
}
