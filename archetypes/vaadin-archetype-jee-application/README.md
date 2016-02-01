JEE Vaadin Project Skeleton
===========================

This repository contains a project skeleton and a corresponding Maven archetype for setting
up a multi-module Maven project for a web application with the following features:

- Uses Java 8 and Java EE 7 Web Profile
- UI built with Vaadin 7.5 and Vaadin CDI 1.0
- Backend built with CDI and EJB
- Database access using JPA
- DeltaSpike repositories and I18N
- Includes a custom (but empty) theme based on Valo and the necessary Maven configuration to compile it
- Includes a custom (but empty) widgetset and the necessary Maven configuration to compile it
- Includes an overlay for building a WAR file optimized for production

## Try it out

1. Clone this repository

    `$ git clone https://github.com/peholmst/jee-vaadin-project-skeleton.git`

2. CD into the `archetype` subdirectory and build and install the archetype:

    `$ mvn clean install`

3. Create a new project using the archetype (feel free to modify the project parameters):

    ```
    $ mvn archetype:generate -DarchetypeGroupId=com.vaadin \
                             -DarchetypeArtifactId=vaadin-jee-archetype \
                             -DarchetypeVersion=1.0-SNAPSHOT \
                             -DgroupId=my.company \
                             -DartifactId=myproject \
                             -Dversion=0.1-SNAPSHOT \
                             -DthemeName=mytheme \
                             -DuiName=MyUI \
                             -DwidgetsetName=MyWidgetset
    ```

4. CD into your newly created project and perform the priming build: 

    `$ mvn clean install -Pwidgetset`

5. CD into the `[yourproject]-ui` module and start the application:

    `$ mvn wildfly:run`

## Please note

This is a prototype for an official Vaadin archetype. Package names etc. will very likely change.
