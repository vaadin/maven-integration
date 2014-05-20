# Liferay portlet archetype, bare
This archetype generates a Liferay portlet plugin project that is self-contained. This means that all Vaadin-related resources, like jars, widgetsets etc. are managed by the project itself and not by portal server.
## Maven profile
The resulting pom.xml uses a couple of properties that are not defined in it; this follows Liferay convention of using a maven profile to provide those properties. Please see https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/developing-plugins-using-maven-liferay-portal-6-2-dev-guide-02-en
