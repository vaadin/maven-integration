#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
${rootArtifactId} Readme
===

This is a project stub for a Vaadin and Java EE 7 Web Profile application.

${symbol_pound}${symbol_pound} Performing the priming build

In the root of your newly generated project, run:

`${symbol_dollar} mvn clean install -Pwidgetset`

This will download all the dependencies and build the entire application, including the widgetset.

${symbol_pound}${symbol_pound} Compiling the widgetset

You already built the widgetset during the priming build. If you need to rebuild it for some reason you
can do it by activating the `widgetset` Maven profile in the `${rootArtifactId}-widgetset` module:

`${symbol_dollar} mvn clean package -Pwidgetset`

The compiled files will be saved in `src/main/resources/VAADIN` to avoid having to 
recompile the widgetset after every clean (so you probably want to keep them out of your source code repository).
When the `widgetset` profile is active, the clean operation will remove the compiled files as well.

${symbol_pound}${symbol_pound} Running the application in development mode

#if( $targetAppServer == 'wildfly' )

In the `${rootArtifactId}-ui` module, use the Wildfly plugin:

`${symbol_dollar} mvn wildfly:run`

The application will use Wildfly's example data source `java:jboss/datasources/ExampleDS` and it will set up
the schema itself.

#elseif( $targetAppServer == 'glassfish' )

In the ´${rootArtifactId}-ui` module, use the GlassFish plugin:

`${symbol_dollar} mvn embedded-glassfish:run`

The application will use GlassFish's default data source `jdbc/__default` and it will set up the schema itself.

#else

Start up your application server and make sure it has a data source with the JNDI name 
`java:app/${rootArtifactId}/${rootArtifactId}DS`. Then deploy the WAR found in the `${rootArtifactId}-ui/target`
directory. The application will set up the schema itself.

#end

${symbol_pound}${symbol_pound} Packaging for production

The backend module `${rootArtifactId}-backend` has an extra resources directory called `resources-production` that
contains files that should only be included in the production build of the application. It will overwrite any files in
`resources` that have the same name (like `persistence.xml` for example).

A similar setup is used for the `webapp` directory in `${rootArtifactId}-ui` where there is a `webapp-production`
directory.

To package the project for production, clean and build the entire project using the `production` Maven profile:

`${symbol_dollar} mvn clean package -Pproduction`

This will not only copy the production files into the program archives, but also recompile the SCSS theme and the 
widgetset with a production optimized configuration.

Finally, deploy the WAR-file found in the `${rootArtifactId}-ui/target` directory to your application server.
