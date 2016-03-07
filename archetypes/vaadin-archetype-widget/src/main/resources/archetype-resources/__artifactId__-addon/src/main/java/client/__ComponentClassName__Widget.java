package ${package}.client;

import com.google.gwt.user.client.ui.Label;

// Extend any GWT Widget
public class ${ComponentClassName}Widget extends Label {

	public ${ComponentClassName}Widget() {

		// CSS class-name should not be v- prefixed
		setStyleName("${artifactId}");

		// State is set to widget in ${ComponentClassName}Connector		
	}

}