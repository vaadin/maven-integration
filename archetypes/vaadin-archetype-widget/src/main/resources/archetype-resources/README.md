#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
${symbol_pound} ${componentClassName} Add-on for Vaadin 7

${componentClassName} is an UI component add-on for Vaadin 7.

${symbol_pound}${symbol_pound} Online demo

Try the add-on demo at <url of the online demo>

${symbol_pound}${symbol_pound} Download release

Official releases of this add-on are available at Vaadin Directory. For Maven instructions, download and reviews, go to http://vaadin.com/addon/${artifactId}

${symbol_pound}${symbol_pound} Building and running demo

git clone <url of the ${componentClassName} repository>
mvn clean install
cd demo
mvn jetty:run

To see the demo, navigate to http://localhost:8080/

${symbol_pound}${symbol_pound} Development with Eclipse IDE

For further development of this add-on, the following tool-chain is recommended:
- Eclipse IDE
- m2e wtp plug-in (install it from Eclipse Marketplace)
- Vaadin Eclipse plug-in (install it from Eclipse Marketplace)
- JRebel Eclipse plug-in (install it from Eclipse Marketplace)
- Chrome browser

${symbol_pound}${symbol_pound}${symbol_pound} Importing project

Choose File > Import... > Existing Maven Projects

Note that Eclipse may give "Plugin execution not covered by lifecycle configuration" errors for pom.xml. Use "Permanently mark goal resources in pom.xml as ignored in Eclipse build" quick-fix to mark these errors as permanently ignored in your project. Do not worry, the project still works fine. 

${symbol_pound}${symbol_pound}${symbol_pound} Debugging server-side

If you have not already compiled the widgetset, do it now by running vaadin:install Maven target for ${artifactId}-root project.

If you have a JRebel license, it makes on the fly code changes faster. Just add JRebel nature to your ${artifactId}-demo project by clicking project with right mouse button and choosing JRebel > Add JRebel Nature

To debug project and make code modifications on the fly in the server-side, right-click the ${artifactId}-demo project and choose Debug As > Debug on Server. Navigate to http://localhost:8080/${artifactId}-demo/ to see the application.

${symbol_pound}${symbol_pound}${symbol_pound} Debugging client-side

Debugging client side code in the ${artifactId}-demo project:
  - run "mvn vaadin:run-codeserver" on a separate console while the application is running
  - activate Super Dev Mode in the debug window of the application or by adding ?superdevmode to the URL
  - You can access Java-sources and set breakpoints inside Chrome if you enable source maps from inspector settings.
 
${symbol_pound}${symbol_pound} Release notes

${symbol_pound}${symbol_pound}${symbol_pound} Version ${version}
- ...
- ...

${symbol_pound}${symbol_pound} Roadmap

This component is developed as a hobby with no public roadmap or any guarantees of upcoming releases. That said, the following features are planned for upcoming releases:
- ...
- ...

${symbol_pound}${symbol_pound} Issue tracking

The issues for this add-on are tracked on its github.com page. All bug reports and feature requests are appreciated. 

${symbol_pound}${symbol_pound} Contributions

Contributions are welcome, but there are no guarantees that they are accepted as such. Process for contributing is the following:
- Fork this project
- Create an issue to this project about the contribution (bug or feature) if there is no such issue about it already. Try to keep the scope minimal.
- Develop and test the fix or functionality carefully. Only include minimum amount of code needed to fix the issue.
- Refer to the fixed issue in commit
- Send a pull request for the original project
- Comment on the original issue that you have implemented a fix for it

${symbol_pound}${symbol_pound} License & Author

Add-on is distributed under Apache License 2.0. For license terms, see LICENSE.txt.

${componentClassName} is written by <...>

${symbol_pound} Developer Guide

${symbol_pound}${symbol_pound} Getting started

Here is a simple example on how to try out the add-on component:

<...>

For a more comprehensive example, see src/test/java/org/vaadin/template/demo/DemoUI.java

${symbol_pound}${symbol_pound} Features

${symbol_pound}${symbol_pound}${symbol_pound} Feature A

<...>

${symbol_pound}${symbol_pound}${symbol_pound} Feature B

<...>

${symbol_pound}${symbol_pound}${symbol_pound} Feature C

<...>

${symbol_pound}${symbol_pound} API

${componentClassName} JavaDoc is available online at <...>