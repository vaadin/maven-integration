package ${package};

import ${package}.client.${ComponentClassName}ClientRpc;
import ${package}.client.${ComponentClassName}ServerRpc;
import ${package}.client.${ComponentClassName}State;

import com.vaadin.shared.MouseEventDetails;

// This is the server-side UI component that provides public API 
// for ${ComponentClassName}
public class ${ComponentClassName} extends com.vaadin.ui.AbstractComponent {

    private int clickCount = 0;

    // To process events from the client, we implement ServerRpc
    private ${ComponentClassName}ServerRpc rpc = new ${ComponentClassName}ServerRpc() {

        // Event received from client - user clicked our widget
        public void clicked(MouseEventDetails mouseDetails) {
            
            // Send nag message every 5:th click with ClientRpc
            if (++clickCount % 5 == 0) {
                getRpcProxy(${ComponentClassName}ClientRpc.class)
                        .alert("Ok, that's enough!");
            }
            
            // Update shared state. This state update is automatically 
            // sent to the client. 
            getState().text = "You have clicked " + clickCount + " times";
        }
    };

    public ${ComponentClassName}() {

        // To receive events from the client, we register ServerRpc
        registerRpc(rpc);
    }

    // We must override getState() to cast the state to ${ComponentClassName}State
    @Override
    protected ${ComponentClassName}State getState() {
        return (${ComponentClassName}State) super.getState();
    }
}
