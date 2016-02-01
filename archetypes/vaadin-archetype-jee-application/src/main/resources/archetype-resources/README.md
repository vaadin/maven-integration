#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
${rootArtifactId} Readme
===

This is a project stub for a Vaadin and Java EE 7 Web Profile application.

${symbol_pound}${symbol_pound} Compiling the widgetset

In the `${rootArtifactId}-widgetset` module, activate the `widgetset` Maven profile:

`${symbol_dollar} mvn clean package -Pwidgetset`

The compiled files will be saved in `src/main/resources/VAADIN` to avoid having to 
recompile the widgetset after every clean. When the `widgetset` profile is active,
the clean operation will remove the compiled files as well.

${symbol_pound}${symbol_pound} Running the application

In the `${rootArtifactId}-ui` module, use the Wildfly plugin:

`${symbol_dollar} mvn wildfly:run`

${symbol_pound}${symbol_pound} Packaging for production

In the `${rootArtifactId}-production` module, activate the `production` Maven profile:

`${symbol_dollar} mvn clean package -Pproduction`
