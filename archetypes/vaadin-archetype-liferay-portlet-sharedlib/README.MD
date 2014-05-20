# Liferay portlet archetype, sharedlib
This archetype generates a Liferay portlet plugin project with an assumption that all Vaadin-related resources, e.g. Vaadin jars, widgetset, theme etc. are managed by portal.
## Maven profile
The resulting pom.xml uses a couple of properties that are not defined in it; this follows Liferay convention of using a maven profile to provide those properties. Please see https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/developing-plugins-using-maven-liferay-portal-6-2-dev-guide-02-en
