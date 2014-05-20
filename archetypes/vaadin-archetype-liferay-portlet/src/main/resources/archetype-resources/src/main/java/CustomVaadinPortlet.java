package ${package};

import com.vaadin.server.DeploymentConfiguration;
import com.vaadin.server.ServiceException;
import com.vaadin.server.VaadinPortlet;
import com.vaadin.server.VaadinPortletService;
import com.vaadin.server.VaadinRequest;

/**
 * 
 * @author ${user.name}
 * This custom Vaadin portlet allows for serving Vaadin resources like theme or widgetset
 * from its web context (instead of from ROOT). Usually it doesn't need any changes.
 *
 */
public class CustomVaadinPortlet extends VaadinPortlet {

	private static final long serialVersionUID = -13615405654173335L;

	private class CustomVaadinPortletService extends VaadinPortletService {

		/**
		 *
		 */
		private static final long serialVersionUID = -6282242585931296999L;

		public CustomVaadinPortletService(final VaadinPortlet portlet,
				final DeploymentConfiguration config) throws ServiceException {
			super(portlet, config);
		}
		
		/**
		 * This method is used to determine the uri for Vaadin resources like theme
		 * or widgetset. It's overriden to point to this web application context,
		 * instead of ROOT context
		 */
		@Override
		public String getStaticFileLocation(final VaadinRequest request) {
			return request.getContextPath();
		}

	}

	@Override
	protected VaadinPortletService createPortletService(
			final DeploymentConfiguration deploymentConfiguration)
			throws ServiceException {
		final CustomVaadinPortletService customVaadinPortletService = new CustomVaadinPortletService(
				this, deploymentConfiguration);
		customVaadinPortletService.init();
		return customVaadinPortletService;
	}

}
