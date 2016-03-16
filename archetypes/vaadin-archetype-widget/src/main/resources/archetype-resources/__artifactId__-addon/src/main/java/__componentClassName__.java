package ${package};

import ${package}.client.${componentClassName}ClientRpc;
import ${package}.client.${componentClassName}ServerRpc;
import ${package}.client.${componentClassName}State;

import com.vaadin.shared.MouseEventDetails;

// This is the server-side UI component that provides public API 
// for ${componentClassName}
public class ${componentClassName} extends com.vaadin.ui.AbstractComponent {

    private int clickCount = 0;

    // To process events from the client, we implement ServerRpc
    private ${componentClassName}ServerRpc rpc = new ${componentClassName}ServerRpc() {

        // Event received from client - user clicked our widget
        public void clicked(MouseEventDetails mouseDetails) {
            
            // Send nag message every 5:th click with ClientRpc
            if (++clickCount % 5 == 0) {
                getRpcProxy(${componentClassName}ClientRpc.class)
                        .alert("Ok, that's enough!");
            }
            
            // Update shared state. This state update is automatically 
            // sent to the client. 
            getState().text = "You have clicked " + clickCount + " times";
        }
    };

    public ${componentClassName}() {

        // To receive events from the client, we register ServerRpc
        registerRpc(rpc);
    }

    // We must override getState() to cast the state to ${componentClassName}State
    @Override
    protected ${componentClassName}State getState() {
        return (${componentClassName}State) super.getState();
    }
}
